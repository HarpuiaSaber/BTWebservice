package webservice.BHXH.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InsuranceController {
	@GetMapping("/user/getInurance")
	public String getInurance() {
		return "client/user/login.html"; //return path of html page
	}
}
