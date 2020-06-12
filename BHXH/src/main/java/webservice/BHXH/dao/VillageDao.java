package webservice.BHXH.dao;

import java.util.List;

import webservice.BHXH.entity.Village;

public interface VillageDao extends BaseDao<Village, String>{

	public List<Village> getByDistrict(String districtId);
}
