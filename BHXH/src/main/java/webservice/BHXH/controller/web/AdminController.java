package webservice.BHXH.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import webservice.BHXH.model.UserPrincipal;
import webservice.BHXH.model.dto.UserDto;
import webservice.BHXH.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public String profile(Model model) {
        UserPrincipal currentUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        UserDto userDto = userService.getById(currentUser.getId());
        model.addAttribute("userAccountDTO", userDto);
        return "admin/user/profile.html";
    }

    @GetMapping("/insurance")
    public String insurance() {
        return "admin/user/list-insurance.html";
    }

    @GetMapping("/paymentHistory")
    public String paymentHistory() {
        return "admin/user/list-payment-history.html";
    }
}
