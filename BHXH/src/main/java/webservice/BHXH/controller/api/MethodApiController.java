package webservice.BHXH.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import webservice.BHXH.model.dto.MethodDto;
import webservice.BHXH.service.MethodService;

@RestController
@RequestMapping("/api/method")
@CrossOrigin(origins = "*", maxAge = -1)
public class MethodApiController {
	@Autowired
	private MethodService methodService;


	@GetMapping("/getAll")
	public @ResponseBody List<MethodDto> getAllMethod() {
		return methodService.getAll();
	}

}
