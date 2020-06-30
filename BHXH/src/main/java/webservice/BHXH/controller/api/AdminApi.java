package webservice.BHXH.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webservice.BHXH.exception.InternalServerException;
import webservice.BHXH.model.dto.ResponseDto;
import webservice.BHXH.model.dto.UserDto;
import webservice.BHXH.model.dto.UserPaymentMoneyDto;
import webservice.BHXH.service.InsuranceService;
import webservice.BHXH.service.PaymentHistoryService;
import webservice.BHXH.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = -1)
public class AdminApi {
	@Autowired
	private UserService userService;

//	@PostMapping
//	@ResponseBody
//	public ResponseDto<UserDto> getAll

}
