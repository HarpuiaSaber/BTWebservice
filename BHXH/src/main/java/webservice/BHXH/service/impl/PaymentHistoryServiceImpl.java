package webservice.BHXH.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webservice.BHXH.dao.InsuranceDao;
import webservice.BHXH.dao.PaymentHistoryDao;
import webservice.BHXH.entity.Insurance;
import webservice.BHXH.entity.PaymentHistory;
import webservice.BHXH.entity.Method;
import webservice.BHXH.entity.User;
import webservice.BHXH.model.dto.*;
import webservice.BHXH.model.search.PaymentHistorySearch;
import webservice.BHXH.service.PaymentHistoryService;
import webservice.BHXH.utils.DateTimeUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PaymentHistoryServiceImpl implements PaymentHistoryService {
    @Autowired
    private PaymentHistoryDao paymentHistoryDao;

    @Autowired
    private InsuranceDao insuranceDao;

    @Override
    public void add(PaymentHistoryDto t) {
        PaymentHistory paymentHistory = toEntity(t);
        paymentHistory.setTime(new Date());
        PaymentHistory latest = paymentHistoryDao.getLatest(t.getInsurance().getUser().getId());
        Insurance insurance = insuranceDao.getByUser(t.getInsurance().getUser().getId());
        if (latest == null) {
            paymentHistory.setStartDate(insurance.getRegDate());
        } else {
            Date oldStartDate = latest.getStartDate();
            int oldMonth = latest.getMethod().getMonth() + oldStartDate.getMonth();
            Date newDate = null;
            if (oldMonth > 12) {
                newDate = new Date(oldStartDate.getYear() + 1, oldMonth - 12, oldStartDate.getDate());
            } else {
                newDate = new Date(oldStartDate.getYear(), oldMonth, oldStartDate.getDate());
            }
            paymentHistory.setStartDate(newDate);
        }
        paymentHistoryDao.add(paymentHistory);

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
        List<PaymentHistory> paymentHistorys = paymentHistoryDao.search(search);
        for (PaymentHistory PaymentHistory : paymentHistorys) {
            dtos.add(toDto(PaymentHistory));
        }
        return dtos;
    }

    @Override
    public List<PaymentHistoryDto> searchWithPaging(PaymentHistorySearch search) {
        List<PaymentHistoryDto> dtos = new ArrayList<PaymentHistoryDto>();
        List<PaymentHistory> paymentHistorys = paymentHistoryDao.searchWithPaging(search);
        for (PaymentHistory PaymentHistory : paymentHistorys) {
            dtos.add(toDto(PaymentHistory));
        }
        return dtos;
    }

    @Override
    public long countTotal(PaymentHistorySearch search) {
        return paymentHistoryDao.countTotal(search);
    }

    @Override
    public UserPaymentMoneyDto getPaidMoney(Long userId) {
        PaymentHistory latest = paymentHistoryDao.getLatest(userId);
        UserPaymentMoneyDto dto = new UserPaymentMoneyDto();
        dto.setUserId(userId);
        if (latest != null) {
            Date oldStartDate = latest.getStartDate();
            int oldMonth = latest.getMethod().getMonth() + oldStartDate.getMonth();
            Date oldEndDate = null;
            if (oldMonth > 12) {
                oldEndDate = new Date(oldStartDate.getYear() + 1, oldMonth - 12, oldStartDate.getDate());
            } else {
                oldEndDate = new Date(oldStartDate.getYear(), oldMonth, oldStartDate.getDate());
            }
            Date now = new Date();
            if (now.after(oldStartDate) && now.before(oldEndDate)) {
                dto.setPaid(true);
                dto.setMonth(latest.getMethod().getMonth());
                dto.setPaymentMoney(latest.getPaymentMoney());
                dto.setSupportMoney(latest.getSupportMoney());
                dto.setTotalMoney(latest.getCost());
            } else {
                dto.setPaid(false);
            }
        }
        return dto;
    }

    PaymentHistoryDto toDto(PaymentHistory paymentHistory) {
        PaymentHistoryDto dto = new PaymentHistoryDto();
        dto.setId(paymentHistory.getId());
        dto.setTime(DateTimeUtils.formatDate(paymentHistory.getTime(), DateTimeUtils.DD_MM_YYYY));
        dto.setStartDate(DateTimeUtils.formatDate(paymentHistory.getStartDate(), DateTimeUtils.DD_MM_YYYY));
        dto.setBaseSalary(paymentHistory.getBaseSalary());
        dto.setCost(paymentHistory.getCost());
        dto.setTransactionId(paymentHistory.getTransactionId());
        dto.setSupportMoney(paymentHistory.getSupportMoney());
        dto.setPaymentMoney(paymentHistory.getPaymentMoney());
        Insurance insurance = paymentHistory.getInsurance();
        InsuranceDto insuranceDto = new InsuranceDto();
        insuranceDto.setId(insurance.getId());
        insuranceDto.setCode(insurance.getCode());
        UserDto userDto = new UserDto();
        User user = insurance.getUser();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
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

    PaymentHistory toEntity(PaymentHistoryDto dto) {
        PaymentHistory paymentHistory = new PaymentHistory();
        paymentHistory.setId(dto.getId());
        paymentHistory.setTransactionId(dto.getTransactionId());
        Method method = new Method();
        method.setId(dto.getMethod().getId());
        paymentHistory.setMethod(method);
        Insurance insurance = new Insurance();
        insurance.setId(dto.getInsurance().getId());
        paymentHistory.setInsurance(insurance);
        paymentHistory.setBaseSalary(dto.getBaseSalary());
        paymentHistory.setPaymentMoney(dto.getPaymentMoney());
        paymentHistory.setSupportMoney(dto.getSupportMoney());
        paymentHistory.setCost(dto.getCost());
        return paymentHistory;
    }
}
