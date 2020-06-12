package webservice.BHXH.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webservice.BHXH.dao.DistrictDao;
import webservice.BHXH.entity.District;
import webservice.BHXH.model.dto.LocationDto;
import webservice.BHXH.service.DistrictService;

@Service
@Transactional
public class DistrictServiceImpl implements DistrictService {

	@Autowired
	private DistrictDao districtDao;

	@Override
	public void add(LocationDto t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(LocationDto t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String k) {
		// TODO Auto-generated method stub

	}

	@Override
	public LocationDto getById(String k) {
		return toDto(districtDao.getById(k));
	}

	@Override
	public List<LocationDto> getAll() {
		List<LocationDto> dtos = new ArrayList<LocationDto>();
		List<District> districts = districtDao.getAll();
		for (District district : districts) {
			dtos.add(toDto(district));
		}
		return dtos;
	}


	@Override
	public List<LocationDto> getByProvince(String provinceId) {
		List<LocationDto> dtos = new ArrayList<LocationDto>();
		List<District> districts = districtDao.getByProvince(provinceId);
		for (District district : districts) {
			dtos.add(toDto(district));
		}
		return dtos;
	}
	
	public LocationDto toDto(District district) {
		LocationDto dto = new LocationDto();
		dto.setId(district.getId());
		dto.setName(district.getName());
		return dto;
	}

}
