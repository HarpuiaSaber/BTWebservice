package webservice.BHXH.controller.web;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
            Object obj = authentication.getPrincipal();
            if (obj instanceof UserPrincipal) {
                UserPrincipal principal = (UserPrincipal) obj;
                if (principal.getRole() == Role.ADMIN) {
                    return "/admin/user/home.html";
                } else {
                    return "/client/user/home.html";
                }
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
        return "general/user/login.html";
    }

    @GetMapping("/signup")
    public String signup() {
        return "general/user/signup.html";
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            Object obj = authentication.getPrincipal();
            if (obj instanceof UserPrincipal) {
                return "client/user/lookup.html";
            }
        }
        return "general/user/lookup.html";
    }

    @GetMapping("/method/list")
    public String listMethod() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            Object obj = authentication.getPrincipal();
            if (obj instanceof UserPrincipal) {
                return "client/user/list-method.html";
            }
        }
        return "general/user/list-method.html";
    }
}
