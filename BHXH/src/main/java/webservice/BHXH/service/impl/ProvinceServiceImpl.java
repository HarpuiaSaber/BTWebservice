package webservice.BHXH.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webservice.BHXH.dao.ProvinceDao;
import webservice.BHXH.entity.Province;
import webservice.BHXH.model.dto.LocationDto;
import webservice.BHXH.service.ProvinceService;

@Service
@Transactional
public class ProvinceServiceImpl implements ProvinceService {

	@Autowired
	private ProvinceDao provinceDao;

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
		return toDto(provinceDao.getById(k));
	}

	@Override
	public List<LocationDto> getAll() {
		List<LocationDto> dtos = new ArrayList<LocationDto>();
		List<Province> provinces = provinceDao.getAll();
		for (Province province : provinces) {
			dtos.add(toDto(province));
		}
		return dtos;
	}

	public LocationDto toDto(Province province) {
		LocationDto dto = new LocationDto();
		dto.setId(province.getId());
		dto.setName(province.getName());
		return dto;
	}

}
