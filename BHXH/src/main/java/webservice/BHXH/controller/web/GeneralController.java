package webservice.BHXH.controller.web;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import webservice.BHXH.enums.Role;
import webservice.BHXH.model.UserPrincipal;

@Controller
public class GeneralController {

    @GetMapping("/")
    public String root() {
        return "redirect:/swagger-ui.html";
    }

    @GetMapping("/home")
    public String home() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
            if (principal.getRole() == Role.ADMIN) {
                return "/admin/user/home.html";
            } else {
                return "/client/user/home.html";
            }
        }
        return "/general/user/home.html";
    }

    @GetMapping("/login")
    public String loginPage() {
        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
                !(SecurityContextHolder.getContext().getAuthentication()
                        instanceof AnonymousAuthenticationToken)) {
            return "redirect:/profile";
        }
        return "client/user/login.html";
    }

    @GetMapping("/profile")
    public String profile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
            if (principal.getRole() == Role.ADMIN) {
                return "redirect:/admin/profile";
            } else
                return "redirect:/user/profile";
        }
        return "redirect:/login";
    }

    @GetMapping("/lookup")
    public String lookup() {
        return "general/user/lookup.html";
    }
}
