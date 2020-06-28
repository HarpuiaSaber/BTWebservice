package webservice.BHXH.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webservice.BHXH.dao.InsuranceDao;
import webservice.BHXH.entity.Insurance;
import webservice.BHXH.entity.Method;
import webservice.BHXH.entity.User;
import webservice.BHXH.model.dto.InsuranceDto;
import webservice.BHXH.model.dto.MethodDto;
import webservice.BHXH.model.dto.UserDto;
import webservice.BHXH.model.search.InsuranceSearch;
import webservice.BHXH.service.InsuranceService;
import webservice.BHXH.utils.DateTimeUtils;

@Service
@Transactional
public class InsuranceServiceImpl implements InsuranceService {
    @Autowired
    private InsuranceDao insuranceDao;

    @Override
    public void add(InsuranceDto t) {
        insuranceDao.add(toEntity(t));

    }

    @Override
    public void update(InsuranceDto t) {
        if (insuranceDao.exists(t.getId())) {
            insuranceDao.update(toEntity(t));
        }

    }

    @Override
    public void delete(Long k) {
        // TODO Auto-generated method stub

    }

    @Override
    public InsuranceDto getById(Long k) {
        return toDto(insuranceDao.getById(k));
    }

    @Override
    public List<InsuranceDto> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<InsuranceDto> search(InsuranceSearch search) {
        List<InsuranceDto> dtos = new ArrayList<InsuranceDto>();
        List<Insurance> insurances = insuranceDao.search(search);
        for (Insurance insurance : insurances) {
            dtos.add(toDto(insurance));
        }
        return dtos;
    }

    @Override
    public List<InsuranceDto> searchWithPaging(InsuranceSearch search) {
        List<InsuranceDto> dtos = new ArrayList<InsuranceDto>();
        List<Insurance> insurances = insuranceDao.searchWithPaging(search);
        for (Insurance insurance : insurances) {
            dtos.add(toDto(insurance));
        }
        return dtos;
    }

    @Override
    public long countTotal(InsuranceSearch search) {
        return insuranceDao.countTotal(search);
    }

    @Override
    public InsuranceDto getByUser(Long userId) {
        return toDto(insuranceDao.getByUser(userId));
    }

    InsuranceDto toDto(Insurance insurance) {
        InsuranceDto dto = new InsuranceDto();
        dto.setId(insurance.getId());
        dto.setCode(insurance.getCode());
        dto.setRegDate(DateTimeUtils.formatDate(insurance.getRegDate(), DateTimeUtils.DD_MM_YYYY));
        UserDto userDto = new UserDto();
        User user = insurance.getUser();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setGender(user.getGender().getValue());
        userDto.setRole(user.getRole());
        userDto.setDob(DateTimeUtils.formatDate(user.getDob(), DateTimeUtils.DD_MM_YYYY));
        userDto.setIdentity(user.getIdentity());
        userDto.setEnabled(user.getEnabled());
        userDto.setPhone(user.getPhone());
        userDto.setVillageId(user.getVillage().getId());
        userDto.setLocation(user.getVillage().getName() + ", " + user.getVillage().getDistrict().getName()
                + ", " + user.getVillage().getDistrict().getProvince().getName());
        userDto.setBaseSalary(user.getBaseSalary());
        dto.setUser(userDto);
        Method method = insurance.getMethod();
        MethodDto methodDto = new MethodDto();
        methodDto.setId(method.getId());
        methodDto.setMonth(method.getMonth());
        methodDto.setName(method.getName());
        dto.setMethod(methodDto);
        return dto;
    }

    Insurance toEntity(InsuranceDto dto) {
        Insurance insurance = new Insurance();
        insurance.setId(dto.getId());
        insurance.setRegDate(new Date());
        UserDto userDto = dto.getUser();
        User user = new User();
        user.setId(userDto.getId());
        insurance.setUser(user);
        MethodDto methodDto = dto.getMethod();
        Method method = new Method();
        method.setId(methodDto.getId());
        insurance.setMethod(method);
        return insurance;
    }
}
