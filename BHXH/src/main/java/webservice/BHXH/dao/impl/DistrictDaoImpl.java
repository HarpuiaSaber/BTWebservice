package webservice.BHXH.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import webservice.BHXH.dao.DistrictDao;
import webservice.BHXH.entity.District;
import webservice.BHXH.entity.Province;

@Repository
@Transactional
public class DistrictDaoImpl extends BaseDaoImpl<District, String> implements DistrictDao {

	@Override
	public List<District> getByProvince(String provinceId) {
		// create returns the data type of critetia query
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<District> criteriaQuery = criteriaBuilder.createQuery(District.class);

		// from and join entity
		Root<District> root = criteriaQuery.from(District.class);
		Join<District, Province> province = root.join("province");

		// add predicate
		List<Predicate> predicates = new ArrayList<>();
		if (StringUtils.isNotBlank(provinceId)) {
			Predicate predicate = criteriaBuilder.like(province.get("id"), provinceId);
			predicates.add(predicate);
		}
		criteriaQuery.where(predicates.toArray(new Predicate[] {}));

		// create query
		TypedQuery<District> typedQuery = entityManager.createQuery(criteriaQuery.select(root));

		return typedQuery.getResultList();
	}
}