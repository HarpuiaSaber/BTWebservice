package webservice.BHXH.dao;

import webservice.BHXH.entity.PaymentHistory;
import webservice.BHXH.model.search.PaymentHistorySearch;

import java.util.List;

public interface PaymentHistoryDao extends BaseDao<PaymentHistory, Long> {

    List<PaymentHistory> search(PaymentHistorySearch search);

    List<PaymentHistory> searchWithPaging(PaymentHistorySearch search);

    long countTotal(PaymentHistorySearch search);

    PaymentHistory getLatest(Long userId);
}
