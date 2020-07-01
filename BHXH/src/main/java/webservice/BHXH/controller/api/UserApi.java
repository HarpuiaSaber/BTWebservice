package webservice.BHXH.controller.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import webservice.BHXH.exception.InternalServerException;
import webservice.BHXH.model.UserPrincipal;
import webservice.BHXH.model.dto.ConfigDto;
import webservice.BHXH.model.dto.ResponseDto;
import webservice.BHXH.model.dto.UserDto;
import webservice.BHXH.model.dto.UserPaymentMoneyDto;
import webservice.BHXH.service.InsuranceService;
import webservice.BHXH.service.PaymentHistoryService;
import webservice.BHXH.service.UserService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*", maxAge = -1)
public class UserApi {
    @Autowired
    private UserService userService;

    @Autowired
    private PaymentHistoryService paymentHistoryService;

    @PutMapping("/update")
    public @ResponseBody
    UserDto updateUser(@RequestBody UserDto dto) {
        userService.update(dto);
        return dto;
    }

    @GetMapping("/getById")
    public @ResponseBody
    UserDto getById(@RequestParam Long id) {
        return userService.getById(id);
    }

    @GetMapping("/getMyConfig")
    public @ResponseBody
    ConfigDto getMyConfig() {
        UserPrincipal currentUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return userService.getMyConfig(currentUser.getId());
    }

    @PostMapping("/setMyConfig")
    public @ResponseBody
    ConfigDto getMyConfig(@RequestBody ConfigDto dto) {
        UserPrincipal currentUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        userService.setConfig(currentUser.getId(), dto);
        return dto;
    }

    @GetMapping("/getPaymentMoney")
    public @ResponseBody
    ResponseDto<UserPaymentMoneyDto> getPaymentMoney() throws InternalServerException {
        UserPrincipal currentUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        List<UserPaymentMoneyDto> dtos = new ArrayList<UserPaymentMoneyDto>();
        UserPaymentMoneyDto dto = null;
        dto = paymentHistoryService.getPaidMoney(currentUser.getId());
        if (!dto.isPaid()) {
            dto = userService.getPaymentMoney(currentUser.getId());
        }
        dtos.add(dto);
        return new ResponseDto<UserPaymentMoneyDto>(1, dtos);
    }

}
