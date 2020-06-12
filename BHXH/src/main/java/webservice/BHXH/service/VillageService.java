package webservice.BHXH.service;

import java.util.List;

import webservice.BHXH.model.dto.LocationDto;

public interface VillageService extends BaseService<LocationDto, String> {

	public List<LocationDto> getByDistrict(String districtId);
}
