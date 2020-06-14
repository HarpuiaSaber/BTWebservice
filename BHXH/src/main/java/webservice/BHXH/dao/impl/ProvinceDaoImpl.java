package webservice.BHXH.dao.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import webservice.BHXH.dao.ProvinceDao;
import webservice.BHXH.entity.Province;

@Repository
@Transactional
public class ProvinceDaoImpl extends BaseDaoImpl<Province, String> implements ProvinceDao{

}
