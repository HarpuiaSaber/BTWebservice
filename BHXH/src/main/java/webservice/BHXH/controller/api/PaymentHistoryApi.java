package webservice.BHXH.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import webservice.BHXH.model.UserPrincipal;
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
    ResponseDto<PaymentHistoryDto> getMyPaymentHistories(@RequestBody PaymentHistorySearch paymentHistorySearch) {
        UserPrincipal currentUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        paymentHistorySearch.setUserId(currentUser.getId());
        List<PaymentHistoryDto> dtos = paymentHistoryService.searchWithPaging(paymentHistorySearch);
        return new ResponseDto<PaymentHistoryDto>(paymentHistoryService.countTotal(paymentHistorySearch), dtos.size() + paymentHistorySearch.getStart(), dtos);
    }

}
