package webservice.BHXH.service;

import java.util.List;

import webservice.BHXH.model.dto.LocationDto;

public interface DistrictService extends BaseService<LocationDto, String> {
	
	public List<LocationDto> getByProvince(String provinceId);
}
