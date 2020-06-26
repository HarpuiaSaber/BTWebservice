package webservice.BHXH.service;

import webservice.BHXH.model.dto.PaymentHistoryDto;
import webservice.BHXH.model.dto.UserPaymentMoneyDto;
import webservice.BHXH.model.search.PaymentHistorySearch;

import java.util.List;

public interface PaymentHistoryService extends BaseService<PaymentHistoryDto, Long> {

    List<PaymentHistoryDto> search(PaymentHistorySearch search);

    List<PaymentHistoryDto> searchWithPaging(PaymentHistorySearch search);

    long countTotal(PaymentHistorySearch search);

    UserPaymentMoneyDto getPaidMoney(Long userId);

}
