package webservice.BHXH.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import webservice.BHXH.model.dto.LocationDto;
import webservice.BHXH.service.DistrictService;
import webservice.BHXH.service.ProvinceService;
import webservice.BHXH.service.VillageService;

@RestController
@RequestMapping("/api/location")
@CrossOrigin(origins = "*", maxAge = -1)
public class LoactionApiController {
	@Autowired
	private ProvinceService provinceService;

	@Autowired
	private DistrictService districtService;

	@Autowired
	private VillageService villageService;

	@GetMapping("/getAllProvince")
	public @ResponseBody List<LocationDto> getAllProvince() {
		return provinceService.getAll();
	}

	@GetMapping("/getDistrictByProvince")
	public @ResponseBody List<LocationDto> getDistrictByProvince(@RequestParam("provinceId") String provinceId) {
		return districtService.getByProvince(provinceId);
	}

	@GetMapping("/getVillageByDistrict")
	public @ResponseBody List<LocationDto> getVillageByDistrict(@RequestParam("districtId") String districtId) {
		return villageService.getByDistrict(districtId);
	}

}
