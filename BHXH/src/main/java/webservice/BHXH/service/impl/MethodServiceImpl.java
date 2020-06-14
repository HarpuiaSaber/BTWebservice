package webservice.BHXH.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webservice.BHXH.dao.MethodDao;
import webservice.BHXH.entity.Method;
import webservice.BHXH.model.dto.MethodDto;
import webservice.BHXH.service.MethodService;

@Service
@Transactional
public class MethodServiceImpl implements MethodService {

	@Autowired
	private MethodDao methodDao;

	@Override
	public void add(MethodDto t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(MethodDto t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Integer k) {
		// TODO Auto-generated method stub

	}

	@Override
	public MethodDto getById(Integer k) {
		return toDto(methodDao.getById(k));
	}

	@Override
	public List<MethodDto> getAll() {
		List<MethodDto> dtos = new ArrayList<MethodDto>();
		List<Method> methods = methodDao.getAll();
		for (Method method : methods) {
			dtos.add(toDto(method));
		}
		return dtos;
	}

	public MethodDto toDto(Method method) {
		MethodDto dto = new MethodDto();
		dto.setId(method.getId());
		dto.setName(method.getName());
		dto.setMonth(method.getMonth());
		return dto;
	}

}
