package webservice.BHXH.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webservice.BHXH.dao.PaymentHistoryDao;
import webservice.BHXH.entity.Insurance;
import webservice.BHXH.entity.PaymentHistory;
import webservice.BHXH.entity.Method;
import webservice.BHXH.entity.User;
import webservice.BHXH.model.dto.InsuranceDto;
import webservice.BHXH.model.dto.PaymentHistoryDto;
import webservice.BHXH.model.dto.MethodDto;
import webservice.BHXH.model.dto.UserDto;
import webservice.BHXH.model.search.PaymentHistorySearch;
import webservice.BHXH.service.PaymentHistoryService;
import webservice.BHXH.utils.DateTimeUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentHistoryService {
    @Autowired
    private PaymentHistoryDao PaymentHistoryDao;

    @Override
    public void add(PaymentHistoryDto t) {
        // TODO Auto-generated method stub

    }

    @Override
    public void update(PaymentHistoryDto t) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(Long k) {
        // TODO Auto-generated method stub

    }

    @Override
    public PaymentHistoryDto getById(Long k) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<PaymentHistoryDto> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<PaymentHistoryDto> search(PaymentHistorySearch search) {
        List<PaymentHistoryDto> dtos = new ArrayList<PaymentHistoryDto>();
        List<PaymentHistory> paymentHistorys = PaymentHistoryDao.search(search);
        for(PaymentHistory PaymentHistory : paymentHistorys){
            dtos.add(toDto(PaymentHistory));
        }
        return dtos;
    }

    @Override
    public List<PaymentHistoryDto> searchWithPaging(PaymentHistorySearch search) {
        List<PaymentHistoryDto> dtos = new ArrayList<PaymentHistoryDto>();
        List<PaymentHistory> paymentHistorys = PaymentHistoryDao.searchWithPaging(search);
        for(PaymentHistory PaymentHistory : paymentHistorys){
            dtos.add(toDto(PaymentHistory));
        }
        return dtos;
    }

    @Override
    public long countTotal(PaymentHistorySearch search) {
        return PaymentHistoryDao.countTotal(search);
    }

    @Override
    public List<PaymentHistoryDto> getByUser(Long userId) {
        List<PaymentHistoryDto> dtos = new ArrayList<PaymentHistoryDto>();
        List<PaymentHistory> paymentHistorys = PaymentHistoryDao.getByUser(userId);
        for(PaymentHistory PaymentHistory : paymentHistorys){
            dtos.add(toDto(PaymentHistory));
        }
        return dtos;
    }

    PaymentHistoryDto toDto(PaymentHistory paymentHistory) {
        PaymentHistoryDto dto = new PaymentHistoryDto();
        dto.setId(paymentHistory.getId());
        dto.setTime(DateTimeUtils.formatDate(paymentHistory.getTime(), DateTimeUtils.DD_MM_YYYY));
        Insurance insurance = paymentHistory.getInsurance();
        InsuranceDto insuranceDto = new InsuranceDto();
        insuranceDto.setId(insurance.getId());
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
        insuranceDto.setUser(userDto);
        dto.setInsurance(insuranceDto);
        MethodDto methodDto = new MethodDto();
        Method method = paymentHistory.getMethod();
        methodDto.setId(method.getId());
        methodDto.setName(method.getName());
        methodDto.setMonth(method.getMonth());
        dto.setMethod(methodDto);
        return dto;
    }
}
