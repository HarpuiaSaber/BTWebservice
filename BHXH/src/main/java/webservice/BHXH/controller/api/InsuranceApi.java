package webservice.BHXH.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import webservice.BHXH.model.UserPrincipal;
import webservice.BHXH.model.dto.InsuranceDto;
import webservice.BHXH.model.dto.ResponseDto;
import webservice.BHXH.model.search.InsuranceSearch;
import webservice.BHXH.service.InsuranceService;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = -1)
public class InsuranceApi {
    @Autowired
    private InsuranceService insuranceService;

    @GetMapping("/user/getMyInsurance")
    public @ResponseBody
    ResponseDto<InsuranceDto> getMyInsurance() {
        UserPrincipal currentUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        List<InsuranceDto> dtos = new ArrayList<InsuranceDto>();
        dtos.add(insuranceService.getByUser(currentUser.getId()));
        return new ResponseDto<InsuranceDto>(1, dtos);
    }

}
