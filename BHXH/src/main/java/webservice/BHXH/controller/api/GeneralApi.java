package webservice.BHXH.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webservice.BHXH.model.dto.InsuranceDto;
import webservice.BHXH.model.dto.ResponseDto;
import webservice.BHXH.model.dto.UserDto;
import webservice.BHXH.model.search.InsuranceSearch;
import webservice.BHXH.service.InsuranceService;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = -1)
public class GeneralApi {

    @Autowired
    private InsuranceService insuranceService;

    @PostMapping("/lookup")
    public @ResponseBody
    ResponseDto<InsuranceDto> lookup(@RequestBody InsuranceSearch insuranceSearch) {
        List<InsuranceDto> dtos = insuranceService.searchWithPaging(insuranceSearch);
        return new ResponseDto<InsuranceDto>(dtos.size(), dtos);
    }
}
