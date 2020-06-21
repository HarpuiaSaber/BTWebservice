package webservice.BHXH.service.impl;

import java.util.ArrayList;
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
        // TODO Auto-generated method stub

    }

    @Override
    public void update(InsuranceDto t) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(Long k) {
        // TODO Auto-generated method stub

    }

    @Override
    public InsuranceDto getById(Long k) {
        // TODO Auto-generated method stub
        return null;
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
        for(Insurance insurance : insurances){
            dtos.add(toDto(insurance));
        }
        return dtos;
    }

    @Override
    public List<InsuranceDto> searchWithPaging(InsuranceSearch search) {
        List<InsuranceDto> dtos = new ArrayList<InsuranceDto>();
        List<Insurance> insurances = insuranceDao.searchWithPaging(search);
        for(Insurance insurance : insurances){
            dtos.add(toDto(insurance));
        }
        return dtos;
    }

    @Override
    public long countTotal(InsuranceSearch search) {
        return insuranceDao.countTotal(search);
    }

    InsuranceDto toDto(Insurance insurance) {
        InsuranceDto dto = new InsuranceDto();
        dto.setId(insurance.getId());
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
        dto.setUser(userDto);
        MethodDto methodDto = new MethodDto();
        Method method = insurance.getMethod();
        methodDto.setId(method.getId());
        methodDto.setName(method.getName());
        methodDto.setMonth(method.getMonth());
        dto.setMethod(methodDto);
        return dto;
    }
}
