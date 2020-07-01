package webservice.BHXH.controller.web;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import webservice.BHXH.enums.PaypalPaymentIntent;
import webservice.BHXH.enums.PaypalPaymentMethod;
import webservice.BHXH.exception.InternalServerException;
import webservice.BHXH.model.UserPrincipal;
import webservice.BHXH.model.dto.InsuranceDto;
import webservice.BHXH.model.dto.PaymentHistoryDto;
import webservice.BHXH.model.dto.UserPaymentMoneyDto;
import webservice.BHXH.service.InsuranceService;
import webservice.BHXH.service.PaymentHistoryService;
import webservice.BHXH.service.PaypalService;
import webservice.BHXH.service.UserService;
import webservice.BHXH.utils.Constants;


@Controller
@RequestMapping("/user")
public class PaymentController {

    @Autowired
    private PaypalService paypalService;

    @Autowired
    private UserService userService;

    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private PaymentHistoryService paymentHistoryService;

    @PostMapping(value = "/payViaPalPal")
    public String pay(@RequestParam float price) throws InternalServerException {
        UserPrincipal currentUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        UserPaymentMoneyDto userPaymentMoneyDto = userService.getPaymentMoney(currentUser.getId());
        if (price != userPaymentMoneyDto.getTotalMoney()) {
            throw new InternalServerException("Tiền không khớp");
        }
        price = (float) (price * 0.000043);
        try {
            Payment payment = paypalService.createPayment(
                    price,
                    "USD",
                    PaypalPaymentMethod.paypal,
                    PaypalPaymentIntent.sale,
                    "payment description",
                    Constants.baseUrl + "/user/cancelPayPal",
                    Constants.baseUrl + "/user/successPayPal");
            for (Links links : payment.getLinks()) {
                if (links.getRel().equals("approval_url")) {
                    return "redirect:" + links.getHref();
                }
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return "general/error/error";
    }

    @GetMapping("/cancelPayPal")
    public String cancelPay() {
        return "cancel";
    }

    @GetMapping("/successPayPal")
    public String createPyamentHistory(@RequestParam(name = "paymentId") String paymentId,
                                       @RequestParam(name = "token") String token,
                                       @RequestParam(name = "PayerID") String payerId) throws InternalServerException {

        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if(payment.getState().equals("approved")){
                UserPrincipal currentUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
                        .getPrincipal();
                UserPaymentMoneyDto userPaymentMoneyDto = userService.getPaymentMoney(currentUser.getId());
                PaymentHistoryDto dto = new PaymentHistoryDto();
                dto.setTransactionId(paymentId);
                InsuranceDto insuranceDto = insuranceService.getByUser(currentUser.getId());
                dto.setInsurance(insuranceDto);
                dto.setMethod(insuranceDto.getMethod());
                dto.setBaseSalary(insuranceDto.getUser().getBaseSalary());
                dto.setCost(userPaymentMoneyDto.getTotalMoney());
                dto.setSupportMoney(userPaymentMoneyDto.getSupportMoney());
                dto.setPaymentMoney(userPaymentMoneyDto.getPaymentMoney());
                paymentHistoryService.add(dto);
                return "redirect:/user/myInsurance";
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }

        return "redirect:/general/error/error";

    }

}
