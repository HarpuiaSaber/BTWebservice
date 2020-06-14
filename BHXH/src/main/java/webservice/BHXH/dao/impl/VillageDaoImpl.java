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

import webservice.BHXH.dao.VillageDao;
import webservice.BHXH.entity.District;
import webservice.BHXH.entity.Village;

@Repository
@Transactional
public class VillageDaoImpl extends BaseDaoImpl<Village, String> implements VillageDao {

	@Override
	public List<Village> getByDistrict(String districtId) {
		// create returns the data type of critetia query
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Village> criteriaQuery = criteriaBuilder.createQuery(Village.class);

		// from and join entity
		Root<Village> root = criteriaQuery.from(Village.class);
		Join<District, District> district = root.join("district");

		// add predicate
		List<Predicate> predicates = new ArrayList<>();
		if (StringUtils.isNotBlank(districtId)) {
			Predicate predicate = criteriaBuilder.equal(district.get("id"), districtId);
			predicates.add(predicate);
		}

		// create query
		TypedQuery<Village> typedQuery = entityManager.createQuery(criteriaQuery.select(root));

		return typedQuery.getResultList();
	}

}
