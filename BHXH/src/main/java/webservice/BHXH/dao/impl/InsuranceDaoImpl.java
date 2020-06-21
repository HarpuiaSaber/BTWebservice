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

import webservice.BHXH.dao.InsuranceDao;
import webservice.BHXH.entity.District;
import webservice.BHXH.entity.Insurance;
import webservice.BHXH.entity.Province;
import webservice.BHXH.entity.User;
import webservice.BHXH.entity.Village;
import webservice.BHXH.model.search.InsuranceSearch;
import webservice.BHXH.utils.DateTimeUtils;

@Repository
@Transactional
public class InsuranceDaoImpl extends BaseDaoImpl<Insurance, Long> implements InsuranceDao {

	@Override
	public List<Insurance> search(InsuranceSearch search) {
		// create returns the data type of critetia query
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Insurance> criteriaQuery = criteriaBuilder.createQuery(Insurance.class);

		// from and join entity
		Root<Insurance> root = criteriaQuery.from(Insurance.class);
		Join<Insurance, User> user = root.join("user");
		Join<User, Village> village = user.join("village");
		Join<Village, District> district = village.join("district");
		Join<District, Province> province = district.join("province");

		// add predicate
		List<Predicate> predicates = new ArrayList<>();
		if (StringUtils.isNotBlank(search.getVillageId())) {
			Predicate predicate = criteriaBuilder.equal(village.get("id"), search.getVillageId());
			predicates.add(predicate);
		} else if (StringUtils.isNotBlank(search.getDistrictId())) {
			Predicate predicate = criteriaBuilder.equal(district.get("id"), search.getDistrictId());
			predicates.add(predicate);
		} else if (StringUtils.isNotBlank(search.getProvinceId())) {
			Predicate predicate = criteriaBuilder.equal(province.get("id"), search.getProvinceId());
			predicates.add(predicate);
		}
		if (search.getIdentity() != null) {
			Predicate predicate = criteriaBuilder.equal(user.get("identity"), search.getIdentity());
			predicates.add(predicate);
		}
		if (StringUtils.isNotBlank(search.getName())) {
			Predicate predicate = criteriaBuilder.equal(user.get("name"), search.getName());
			predicates.add(predicate);
		}
		if (StringUtils.isNotBlank(search.getDob())) {
			try {
				Predicate predicate = criteriaBuilder.equal(user.get("dob"),
						DateTimeUtils.parseDate(search.getToDate(), DateTimeUtils.DD_MM_YYYY));
				predicates.add(predicate);
			} catch (RuntimeException ignored) {
			}
		}
		if (StringUtils.isNotBlank(search.getFromDate())) {
			try {
				Predicate predicate = criteriaBuilder.greaterThanOrEqualTo(root.get("regDate"),
						DateTimeUtils.parseDate(search.getFromDate(), DateTimeUtils.DD_MM_YYYY));
				predicates.add(predicate);
			} catch (RuntimeException ignored) {
			}
		}
		if (StringUtils.isNotBlank(search.getToDate())) {
			try {
				Predicate predicate = criteriaBuilder.lessThanOrEqualTo(root.get("regDate"),
						DateTimeUtils.parseDate(search.getToDate(), DateTimeUtils.DD_MM_YYYY));
				predicates.add(predicate);
			} catch (RuntimeException ignored) {
			}
		}
		criteriaQuery.where(predicates.toArray(new Predicate[] {}));

		// order
		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("regDate")));

		// create query
		TypedQuery<Insurance> typedQuery = entityManager.createQuery(criteriaQuery.select(root));

		return typedQuery.getResultList();
	}

	@Override
	public List<Insurance> searchWithPaging(InsuranceSearch search) {
		// create returns the data type of critetia query
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Insurance> criteriaQuery = criteriaBuilder.createQuery(Insurance.class);

		// from and join entity
		Root<Insurance> root = criteriaQuery.from(Insurance.class);
		Join<Insurance, User> user = root.join("user");
		Join<User, Village> village = user.join("village");
		Join<Village, District> district = village.join("district");
		Join<District, Province> province = district.join("province");

		// add predicate
		List<Predicate> predicates = new ArrayList<>();
		if (StringUtils.isNotBlank(search.getVillageId())) {
			Predicate predicate = criteriaBuilder.equal(village.get("id"), search.getVillageId());
			predicates.add(predicate);
		} else if (StringUtils.isNotBlank(search.getDistrictId())) {
			Predicate predicate = criteriaBuilder.equal(district.get("id"), search.getDistrictId());
			predicates.add(predicate);
		} else if (StringUtils.isNotBlank(search.getProvinceId())) {
			Predicate predicate = criteriaBuilder.equal(province.get("id"), search.getProvinceId());
			predicates.add(predicate);
		}
		if (search.getIdentity() != null) {
			Predicate predicate = criteriaBuilder.equal(user.get("identity"), search.getIdentity());
			predicates.add(predicate);
		}
		if (StringUtils.isNotBlank(search.getName())) {
			Predicate predicate = criteriaBuilder.equal(user.get("name"), search.getName());
			predicates.add(predicate);
		}
		if (StringUtils.isNotBlank(search.getDob())) {
			try {
				Predicate predicate = criteriaBuilder.equal(user.get("dob"),
						DateTimeUtils.parseDate(search.getToDate(), DateTimeUtils.DD_MM_YYYY));
				predicates.add(predicate);
			} catch (RuntimeException ignored) {
			}
		}
		if (StringUtils.isNotBlank(search.getFromDate())) {
			try {
				Predicate predicate = criteriaBuilder.greaterThanOrEqualTo(root.get("regDate"),
						DateTimeUtils.parseDate(search.getFromDate(), DateTimeUtils.DD_MM_YYYY));
				predicates.add(predicate);
			} catch (RuntimeException ignored) {
			}
		}
		if (StringUtils.isNotBlank(search.getToDate())) {
			try {
				Predicate predicate = criteriaBuilder.lessThanOrEqualTo(root.get("regDate"),
						DateTimeUtils.parseDate(search.getToDate(), DateTimeUtils.DD_MM_YYYY));
				predicates.add(predicate);
			} catch (RuntimeException ignored) {
			}
		}
		criteriaQuery.where(predicates.toArray(new Predicate[] {}));

		// order
		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("regDate")));

		// paging
		// create query
		TypedQuery<Insurance> typedQuery = entityManager.createQuery(criteriaQuery.select(root));
		if (search.getStartCount() != null) {
			typedQuery.setFirstResult((search.getStartCount()));
		}

		typedQuery.setMaxResults(search.getMaxResultCount());
		return typedQuery.getResultList();
	}

	@Override
	public long countTotal(InsuranceSearch search) {
		// create returns the data type of critetia query
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);

		// from and join entity
		Root<Insurance> root = criteriaQuery.from(Insurance.class);
		Join<Insurance, User> user = root.join("user");
		Join<User, Village> village = user.join("village");
		Join<Village, District> district = village.join("district");
		Join<District, Province> province = district.join("province");

		// add predicate
		List<Predicate> predicates = new ArrayList<>();
		if (StringUtils.isNotBlank(search.getVillageId())) {
			Predicate predicate = criteriaBuilder.equal(village.get("id"), search.getVillageId());
			predicates.add(predicate);
		} else if (StringUtils.isNotBlank(search.getDistrictId())) {
			Predicate predicate = criteriaBuilder.equal(district.get("id"), search.getDistrictId());
			predicates.add(predicate);
		} else if (StringUtils.isNotBlank(search.getProvinceId())) {
			Predicate predicate = criteriaBuilder.equal(province.get("id"), search.getProvinceId());
			predicates.add(predicate);
		}
		if (search.getIdentity() != null) {
			Predicate predicate = criteriaBuilder.equal(user.get("identity"), search.getIdentity());
			predicates.add(predicate);
		}
		if (StringUtils.isNotBlank(search.getName())) {
			Predicate predicate = criteriaBuilder.equal(user.get("name"), search.getName());
			predicates.add(predicate);
		}
		if (StringUtils.isNotBlank(search.getDob())) {
			try {
				Predicate predicate = criteriaBuilder.equal(user.get("dob"),
						DateTimeUtils.parseDate(search.getToDate(), DateTimeUtils.DD_MM_YYYY));
				predicates.add(predicate);
			} catch (RuntimeException ignored) {
			}
		}
		if (StringUtils.isNotBlank(search.getFromDate())) {
			try {
				Predicate predicate = criteriaBuilder.greaterThanOrEqualTo(root.get("regDate"),
						DateTimeUtils.parseDate(search.getFromDate(), DateTimeUtils.DD_MM_YYYY));
				predicates.add(predicate);
			} catch (RuntimeException ignored) {
			}
		}
		if (StringUtils.isNotBlank(search.getToDate())) {
			try {
				Predicate predicate = criteriaBuilder.lessThanOrEqualTo(root.get("regDate"),
						DateTimeUtils.parseDate(search.getToDate(), DateTimeUtils.DD_MM_YYYY));
				predicates.add(predicate);
			} catch (RuntimeException ignored) {
			}
		}
		criteriaQuery.where(predicates.toArray(new Predicate[] {}));

		// create query
		TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery.select(criteriaBuilder.count(root)));
		return typedQuery.getSingleResult();
	}

	@Override
	public Insurance getByUser(Long userId) {
		// create returns the data type of critetia query
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Insurance> criteriaQuery = criteriaBuilder.createQuery(Insurance.class);

		// from and join entity
		Root<Insurance> root = criteriaQuery.from(Insurance.class);
		Join<Insurance, User> user = root.join("user");

		// add predicate
		List<Predicate> predicates = new ArrayList<>();
		if (userId != null) {
			Predicate predicate = criteriaBuilder.equal(user.get("identity"), userId);
			predicates.add(predicate);
		}
		criteriaQuery.where(predicates.toArray(new Predicate[] {}));

		// create query
		TypedQuery<Insurance> typedQuery = entityManager.createQuery(criteriaQuery.select(root));

		return typedQuery.getSingleResult();
	}

}
