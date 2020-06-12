package webservice.BHXH.dao;

import java.util.List;

import webservice.BHXH.entity.District;

public interface DistrictDao extends BaseDao<District, String>{

	public List<District> getByProvince(String provinceId);
}
