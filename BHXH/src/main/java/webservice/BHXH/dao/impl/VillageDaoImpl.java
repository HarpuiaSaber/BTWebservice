package webservice.BHXH.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;

import webservice.BHXH.dao.VillageDao;
import webservice.BHXH.entity.Village;

public class VillageDaoImpl extends BaseDaoImpl<Village, String> implements VillageDao {

	@Override
	public List<Village> getByDistrict(String districtId) {
		// create returns the data type of critetia query
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Village> criteriaQuery = criteriaBuilder.createQuery(Village.class);

		// from and join entity
		Root<Village> root = criteriaQuery.from(Village.class);

		// add predicate
		List<Predicate> predicates = new ArrayList<>();
		if (StringUtils.isNotBlank(districtId)) {
			Predicate predicate = criteriaBuilder.equal(root.get("distric_id"), districtId);
			predicates.add(predicate);
		}

		// create query
		TypedQuery<Village> typedQuery = entityManager.createQuery(criteriaQuery.select(root));

		return typedQuery.getResultList();
	}

}
