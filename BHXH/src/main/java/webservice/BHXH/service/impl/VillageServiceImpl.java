package webservice.BHXH.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webservice.BHXH.dao.VillageDao;
import webservice.BHXH.entity.Village;
import webservice.BHXH.model.dto.LocationDto;
import webservice.BHXH.service.VillageService;

@Service
@Transactional
public class VillageServiceImpl implements VillageService {

	@Autowired
	private VillageDao villageDao;

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
		return toDto(villageDao.getById(k));
	}

	@Override
	public List<LocationDto> getAll() {
		List<LocationDto> dtos = new ArrayList<LocationDto>();
		List<Village> villages = villageDao.getAll();
		for (Village village : villages) {
			dtos.add(toDto(village));
		}
		return dtos;
	}

	@Override
	public List<LocationDto> getByDistrict(String districtId) {
		List<LocationDto> dtos = new ArrayList<LocationDto>();
		List<Village> villages = villageDao.getByDistrict(districtId);
		for (Village village : villages) {
			dtos.add(toDto(village));
		}
		return dtos;
	}

	public LocationDto toDto(Village village) {
		LocationDto dto = new LocationDto();
		dto.setId(village.getId());
		dto.setName(village.getName());
		return dto;
	}

}
