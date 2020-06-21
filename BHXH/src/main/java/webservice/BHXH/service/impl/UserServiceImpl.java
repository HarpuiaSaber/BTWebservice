package webservice.BHXH.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import webservice.BHXH.dao.InsuranceDao;
import webservice.BHXH.dao.PaymentHistoryDao;
import webservice.BHXH.dao.UserDao;
import webservice.BHXH.entity.*;
import webservice.BHXH.enums.Gender;
import webservice.BHXH.enums.Role;
import webservice.BHXH.exception.InternalServerException;
import webservice.BHXH.model.UserPrincipal;
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
    @Autowired
    private PaymentHistoryDao paymentHistoryDao;

    @Override
    public void add(UserDto t) {
        User user = toEntity(t);
        userDao.add(user);
        t.setId(user.getId());

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
        user.setName(dto.getName());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setGender(Gender.valueOf(dto.getGender()));
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
        return dto;
    }

    @Override
    public UserPaymentMoneyDto getPaymentMoney(Long userId) {
        User user = userDao.getById(userId);
        if (user != null) {
            Insurance insurance = insuranceDao.getByUser(userId);
            int month = insurance.getMethod().getMonth();
            UserPaymentMoneyDto userPaymentMoney = new UserPaymentMoneyDto();
            userPaymentMoney.setUserId(userId);

            long baseSalary = user.getBaseSalary();
            SupportType supportType = user.getSupportType();
            double income = supportType.getIncome();
            int percent = supportType.getPercent();

            userPaymentMoney.setMonth(month);
            userPaymentMoney.setPaymentMoney((income + baseSalary) * month * 22 / 100);
            userPaymentMoney.setSupportMoney(income * percent * month * 22 / 100);
            userPaymentMoney.setTotalMoney(userPaymentMoney.getPaymentMoney() - userPaymentMoney.getSupportMoney());
            return userPaymentMoney;
        }
        return null;
    }

    @Override
    public void setBaseSalary(Long userId, Long baseSalary) throws InternalServerException {
        User user = userDao.getById(userId);
        if (user != null) {
            //check payment history have record before now
            //if true: paid set baseSalary
            //PaymentHistory paymentHistory = paymentHistoryDao.getLatestOfUser(userId);
//            Insurance insurance = insuranceDao.getByUser(userId);
//            int month = insurance.getMethod().getMonth();
//            Date now = new Date();
//            Date old = new Date(now.getYear(), now.getMonth() - month, now.getDate());
//            Date latest = paymentHistory.getTime();
//            if (!latest.before(old)) {
                user.setBaseSalary(baseSalary);
//            } else {
//                throw new InternalServerException("Chưa đóng bảo hiểm trước đó");
//            }
        } else {
            throw new InternalServerException("Không tìm thấy user");
        }
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
