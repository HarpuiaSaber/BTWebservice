package webservice.BHXH.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import webservice.BHXH.dao.InsuranceDao;
import webservice.BHXH.dao.UserDao;
import webservice.BHXH.entity.*;
import webservice.BHXH.enums.Gender;
import webservice.BHXH.exception.InternalServerException;
import webservice.BHXH.model.UserPrincipal;
import webservice.BHXH.model.dto.ConfigDto;
import webservice.BHXH.model.dto.UserDto;
import webservice.BHXH.model.dto.UserPaymentMoneyDto;
import webservice.BHXH.service.UserService;
import webservice.BHXH.utils.DateTimeUtils;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private InsuranceDao insuranceDao;

    @Override
    public void add(UserDto t) throws InternalServerException {
        User old = userDao.getByUsername(t.getUsername());
        if (old == null) {
            User user = toEntity(t);
            SupportType supportType = new SupportType();
            supportType.setId(3L);
            user.setSupportType(supportType);
            userDao.add(user);
            t.setId(user.getId());
        } else {
            throw new InternalServerException("Username đã tồn tại");
        }
    }

    @Override
    public void update(UserDto t) {
        if (userDao.exists(t.getId())) {
            userDao.update(toEntity(t));
        }
    }

    @Override
    public void delete(Long k) {
        User user = userDao.getById(k);
        if (user != null) {
            userDao.update(user);
        }

    }

    @Override
    public UserDto getById(Long k) {
        User user = userDao.getById(k);
        if (user != null) {
            return toDto(user);
        }
        return null;
    }

    @Override
    public List<UserDto> getAll() {
        List<UserDto> dtos = new ArrayList<UserDto>();
        List<User> users = userDao.getAll();
        for (User user : users) {
            dtos.add(toDto(user));
        }
        return dtos;
    }

    User toEntity(UserDto dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setGender(Gender.valueOf(dto.getGender()));
        user.setRole(dto.getRole());
        user.setDob(DateTimeUtils.parseDate(dto.getDob(), DateTimeUtils.DD_MM_YYYY));
        user.setIdentity(dto.getIdentity());
        user.setEnabled(dto.getEnabled());
        user.setPhone(dto.getPhone());
        Village village = new Village();
        village.setId(dto.getVillageId());
        user.setVillage(village);
        return user;
    }

    UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setGender(user.getGender().getValue());
        dto.setRole(user.getRole());
        dto.setDob(DateTimeUtils.formatDate(user.getDob(), DateTimeUtils.DD_MM_YYYY));
        dto.setIdentity(user.getIdentity());
        dto.setEnabled(user.getEnabled());
        dto.setPhone(user.getPhone());
        dto.setVillageId(user.getVillage().getId());
        dto.setLocation(user.getVillage().getName() + ", " + user.getVillage().getDistrict().getName()
                + ", " + user.getVillage().getDistrict().getProvince().getName());
        dto.setBaseSalary(user.getBaseSalary());
        return dto;
    }

    @Override
    public UserPaymentMoneyDto getPaymentMoney(Long userId) throws InternalServerException {
        User user = userDao.getById(userId);
        if (user != null) {
            if (user.getBaseSalary() == null) {
                throw new InternalServerException("Phải cấu hình lương cơ bản trước");
            } else {
                Insurance insurance = insuranceDao.getByUser(userId);
                int month = insurance.getMethod().getMonth();
                UserPaymentMoneyDto userPaymentMoney = new UserPaymentMoneyDto();
                userPaymentMoney.setUserId(userId);

                long baseSalary = user.getBaseSalary();
                SupportType supportType = user.getSupportType();
                double income = supportType.getIncome();
                int percent = supportType.getPercent();

                userPaymentMoney.setMonth(month);
                userPaymentMoney.setPaymentMoney((double) baseSalary * month * 22 / 100);
                userPaymentMoney.setSupportMoney(income * percent * month * 22 / 10000);
                userPaymentMoney.setTotalMoney(userPaymentMoney.getPaymentMoney() - userPaymentMoney.getSupportMoney());
                userPaymentMoney.setPaid(false);
                return userPaymentMoney;
            }
        }
        return null;
    }

    @Override
    public ConfigDto getMyConfig(Long userId) {
        ConfigDto dto = new ConfigDto();
        Insurance insurance = insuranceDao.getByUser(userId);
        dto.setBaseSalary(insurance.getUser().getBaseSalary());
        dto.setMethodId(insurance.getMethod().getId());
        return dto;
    }

    @Override
    public void setConfig(Long userId, ConfigDto dto) {
        User user = userDao.getById(userId);
        user.setBaseSalary(dto.getBaseSalary());
        userDao.update(user);
        Insurance insurance = insuranceDao.getByUser(userId);
        Method method = new Method();
        method.setId(dto.getMethodId());
        insurance.setMethod(method);
        insuranceDao.update(insurance);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.getByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("not found");
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().name()));
        System.out.println(user.getRole());

        UserPrincipal principal = new UserPrincipal(user.getUsername(), user.getPassword(), user.getEnabled(), true,
                true, true, authorities);
        principal.setId(user.getId());
        principal.setName(user.getName());
        principal.setRole(user.getRole());

        return principal;
    }
}
