package webservice.BHXH.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import webservice.BHXH.model.UserPrincipal;
import webservice.BHXH.model.search.DateSearch;
import webservice.BHXH.model.dto.PaymentHistoryDto;
import webservice.BHXH.model.dto.ResponseDto;
import webservice.BHXH.model.search.PaymentHistorySearch;
import webservice.BHXH.service.PaymentHistoryService;

import java.util.List;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = -1)
public class PaymentHistoryApi {
    @Autowired
    private PaymentHistoryService paymentHistoryService;

    @PostMapping("/user/myPaymentHistories")
    public @ResponseBody
    ResponseDto<PaymentHistoryDto> getMyPaymentHistories(@RequestBody DateSearch dto) {
        UserPrincipal currentUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        PaymentHistorySearch paymentHistorySearch = new PaymentHistorySearch();
        paymentHistorySearch.setUserId(currentUser.getId());
        paymentHistorySearch.setToDate(dto.getToDate());
        paymentHistorySearch.setFromDate(dto.getFromDate());
        paymentHistorySearch.setStart(dto.getStart());
        paymentHistorySearch.setLength(dto.getLength());
        List<PaymentHistoryDto> dtos = paymentHistoryService.searchWithPaging(paymentHistorySearch);
        return new ResponseDto<PaymentHistoryDto>(paymentHistoryService.countTotal(paymentHistorySearch), dtos.size() + paymentHistorySearch.getStart(), dtos);
    }

    @PostMapping("/admin/paymentHistories/search")
    public @ResponseBody
    ResponseDto<PaymentHistoryDto> search(@RequestBody PaymentHistorySearch paymentHistorySearch) {
        List<PaymentHistoryDto> dtos = paymentHistoryService.searchWithPaging(paymentHistorySearch);
        return new ResponseDto<PaymentHistoryDto>(paymentHistoryService.countTotal(paymentHistorySearch), dtos.size() + paymentHistorySearch.getStart(), dtos);
    }

}
