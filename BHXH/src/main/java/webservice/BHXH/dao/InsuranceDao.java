package webservice.BHXH.dao;

import java.util.List;

import webservice.BHXH.entity.Insurance;
import webservice.BHXH.pagination.GridParam;

public interface InsuranceDao extends BaseDao<Insurance, Long> {

	List<Insurance> find(GridParam gridParam);

	long count(GridParam gridParam);

}
