package webservice.BHXH.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

	@GetMapping("/user/login")
	public String login() {
		return "client/user/login.html"; //return path of html page
	}
	@GetMapping("/user/information")
	public String information() {
		return "client/user/login.html"; //return path of html page
	}
}
