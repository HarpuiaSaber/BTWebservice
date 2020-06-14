package webservice.BHXH.dao;

import java.util.List;

import webservice.BHXH.entity.Insurance;
import webservice.BHXH.model.search.InsuranceSearch;

public interface InsuranceDao extends BaseDao<Insurance, Long> {

	List<Insurance> search(InsuranceSearch search);
	
	List<Insurance> searchWithPaging(InsuranceSearch search);

	long countTotal(InsuranceSearch search);
	
	Insurance getByUser(Long userId);

}
