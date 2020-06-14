package webservice.BHXH.service;

import webservice.BHXH.exception.InternalServerException;
import webservice.BHXH.model.dto.UserDto;
import webservice.BHXH.model.dto.UserPaymentMoneyDto;

public interface UserService extends BaseService<UserDto, Long> {

	UserPaymentMoneyDto getPaymentMoney(Long userId);
	
	void setBaseSalary(Long userId, Double baseSalary) throws InternalServerException;
}
