package webservice.BHXH.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webservice.BHXH.dao.InsuranceDao;
import webservice.BHXH.model.dto.InsuranceDto;
import webservice.BHXH.model.search.InsuranceSearch;
import webservice.BHXH.service.InsuranceService;

@Service
@Transactional
public class InsuranceServiceImpl implements InsuranceService {
	@Autowired
	private InsuranceDao insuranceDao;

	@Override
	public void add(InsuranceDto t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(InsuranceDto t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Long k) {
		// TODO Auto-generated method stub

	}

	@Override
	public InsuranceDto getById(Long k) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InsuranceDto> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InsuranceDto> search(InsuranceSearch search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InsuranceDto> searchWithPaging(InsuranceSearch search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long countTotal(InsuranceSearch search) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
