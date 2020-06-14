package webservice.BHXH.dao.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import webservice.BHXH.dao.MethodDao;
import webservice.BHXH.entity.Method;

@Repository
@Transactional
public class MethodDaoImpl extends BaseDaoImpl<Method, Integer> implements MethodDao{

}
