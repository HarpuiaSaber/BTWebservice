package webservice.BHXH.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InsuranceController {
	@GetMapping("/user/getInurance")
	public String getInurance() {
		return "admin/fixcost/list-fixcost.html"; //return path of html page
	}
}
