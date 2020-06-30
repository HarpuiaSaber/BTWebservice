package webservice.BHXH.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webservice.BHXH.enums.Role;
import webservice.BHXH.exception.InternalServerException;
import webservice.BHXH.model.dto.InsuranceDto;
import webservice.BHXH.model.dto.MethodDto;
import webservice.BHXH.model.dto.ResponseDto;
import webservice.BHXH.model.dto.UserDto;
import webservice.BHXH.model.search.InsuranceSearch;
import webservice.BHXH.service.InsuranceService;
import webservice.BHXH.service.UserService;
import webservice.BHXH.utils.PasswordGenerator;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = -1)
public class GeneralApi {

    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private UserService userService;

    @PostMapping("/createUser")
    public @ResponseBody
    UserDto createUser(@RequestBody UserDto dto) throws InternalServerException {
        dto.setEnabled(true);
        dto.setRole(Role.USER);
        dto.setPassword(PasswordGenerator.getHashString(dto.getPassword()));
        userService.add(dto);
        InsuranceDto insuranceDto = new InsuranceDto();
        UserDto userDto = new UserDto();
        userDto.setId(dto.getId());
        insuranceDto.setUser(userDto);
        MethodDto methodDto = new MethodDto();
        methodDto.setId(1);
        insuranceDto.setMethod(methodDto);
        insuranceService.add(insuranceDto);
        return dto;
    }

    @PostMapping("/lookup")
    public @ResponseBody
    ResponseDto<InsuranceDto> lookup(@RequestBody InsuranceSearch insuranceSearch) {
        List<InsuranceDto> dtos = insuranceService.searchWithPaging(insuranceSearch);
        return new ResponseDto<InsuranceDto>(insuranceService.countTotal(insuranceSearch), dtos.size() + insuranceSearch.getStart(), dtos);
    }
}
