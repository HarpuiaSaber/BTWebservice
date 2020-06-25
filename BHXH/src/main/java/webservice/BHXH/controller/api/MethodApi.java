package webservice.BHXH.controller.api;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import webservice.BHXH.enums.Bank;
import webservice.BHXH.model.dto.MethodDto;
import webservice.BHXH.model.dto.ResponseDto;
import webservice.BHXH.service.MethodService;

@RestController
@RequestMapping("/api/method")
@CrossOrigin(origins = "*", maxAge = -1)
public class MethodApi {
    @Autowired
    private MethodService methodService;

    @GetMapping("/getAll")
    public @ResponseBody
    ResponseDto<MethodDto> getAllMethod() {
        List<MethodDto> dtos = methodService.getAll();
        return new ResponseDto<MethodDto>(dtos.size(), dtos);
    }

}
