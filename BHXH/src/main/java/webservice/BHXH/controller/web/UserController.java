package webservice.BHXH.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import webservice.BHXH.enums.Role;
import webservice.BHXH.model.UserPrincipal;
import webservice.BHXH.model.dto.UserDto;
import webservice.BHXH.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public String profile(Model model) {
        UserPrincipal currentUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        UserDto userDto = userService.getById(currentUser.getId());
        model.addAttribute("user", userDto);
        return "client/user/profile.html";
    }

    @GetMapping("/myInsurance")
    public String myInsurance(Model model) {
        return "client/user/my-insurance.html";
    }

    @GetMapping("/myPaymentHistories")
    public String myPaymentHistories(Model model) {
        return "client/user/payment-histories.html";
    }
}
