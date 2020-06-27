package webservice.BHXH.dao.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import webservice.BHXH.dao.PaymentHistoryDao;
import webservice.BHXH.entity.*;
import webservice.BHXH.model.search.PaymentHistorySearch;
import webservice.BHXH.utils.DateTimeUtils;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class PaymentHistoryDaoImpl extends BaseDaoImpl<PaymentHistory, Long> implements PaymentHistoryDao {

    @Override
    public List<PaymentHistory> search(PaymentHistorySearch search) {
        // create returns the data type of critetia query
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PaymentHistory> criteriaQuery = criteriaBuilder.createQuery(PaymentHistory.class);

        // from and join entity
        Root<PaymentHistory> root = criteriaQuery.from(PaymentHistory.class);
        Join<PaymentHistory, Insurance> insurance = root.join("insurance");
        Join<PaymentHistory, User> user = insurance.join("user");
        Join<User, Village> village = user.join("village");
        Join<Village, District> district = village.join("district");
        Join<District, Province> province = district.join("province");

        // add predicate
        List<Predicate> predicates = new ArrayList<>();
        if (search.getId() != null) {
            Predicate predicate = criteriaBuilder.equal(root.get("id"), search.getId());
            predicates.add(predicate);
        }
        if (search.getUserId() != null) {
            Predicate predicate = criteriaBuilder.equal(user.get("id"), search.getUserId());
            predicates.add(predicate);
        }
        if (search.getCode() != null) {
            Predicate predicate = criteriaBuilder.equal(root.get("code"), search.getCode());
            predicates.add(predicate);
        }
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
                Predicate predicate = criteriaBuilder.greaterThanOrEqualTo(root.get("time"),
                        DateTimeUtils.parseDate(search.getFromDate(), DateTimeUtils.DD_MM_YYYY));
                predicates.add(predicate);
            } catch (RuntimeException ignored) {
            }
        }
        if (StringUtils.isNotBlank(search.getToDate())) {
            try {
                Predicate predicate = criteriaBuilder.lessThanOrEqualTo(root.get("time"),
                        DateTimeUtils.parseDate(search.getToDate(), DateTimeUtils.DD_MM_YYYY));
                predicates.add(predicate);
            } catch (RuntimeException ignored) {
            }
        }
        criteriaQuery.where(predicates.toArray(new Predicate[]{}));

        // order
        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("time")));

        // create query
        TypedQuery<PaymentHistory> typedQuery = entityManager.createQuery(criteriaQuery.select(root));

        return typedQuery.getResultList();
    }

    @Override
    public List<PaymentHistory> searchWithPaging(PaymentHistorySearch search) {
        // create returns the data type of critetia query
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PaymentHistory> criteriaQuery = criteriaBuilder.createQuery(PaymentHistory.class);

        // from and join entity
        Root<PaymentHistory> root = criteriaQuery.from(PaymentHistory.class);
        Join<PaymentHistory, Insurance> insurance = root.join("insurance");
        Join<PaymentHistory, User> user = insurance.join("user");
        Join<User, Village> village = user.join("village");
        Join<Village, District> district = village.join("district");
        Join<District, Province> province = district.join("province");

        // add predicate
        List<Predicate> predicates = new ArrayList<>();
        if (search.getId() != null) {
            Predicate predicate = criteriaBuilder.equal(root.get("id"), search.getId());
            predicates.add(predicate);
        }
        if (search.getUserId() != null) {
            Predicate predicate = criteriaBuilder.equal(user.get("id"), search.getUserId());
            predicates.add(predicate);
        }
        if (search.getCode() != null) {
            Predicate predicate = criteriaBuilder.equal(root.get("code"), search.getCode());
            predicates.add(predicate);
        }
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
                Predicate predicate = criteriaBuilder.greaterThanOrEqualTo(root.get("time"),
                        DateTimeUtils.parseDate(search.getFromDate(), DateTimeUtils.DD_MM_YYYY));
                predicates.add(predicate);
            } catch (RuntimeException ignored) {
            }
        }
        if (StringUtils.isNotBlank(search.getToDate())) {
            try {
                Predicate predicate = criteriaBuilder.lessThanOrEqualTo(root.get("time"),
                        DateTimeUtils.parseDate(search.getToDate(), DateTimeUtils.DD_MM_YYYY));
                predicates.add(predicate);
            } catch (RuntimeException ignored) {
            }
        }
        criteriaQuery.where(predicates.toArray(new Predicate[]{}));

        // order
        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("time")));

        /// paging
        // create query
        TypedQuery<PaymentHistory> typedQuery = entityManager.createQuery(criteriaQuery.select(root));
        if (search.getStart() != null) {
            typedQuery.setFirstResult((search.getStart()));
        }

        typedQuery.setMaxResults(search.getLength());
        return typedQuery.getResultList();
    }

    @Override
    public long countTotal(PaymentHistorySearch search) {
        // create returns the data type of critetia query
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);

        // from and join entity
        Root<PaymentHistory> root = criteriaQuery.from(PaymentHistory.class);
        Join<PaymentHistory, Insurance> insurance = root.join("insurance");
        Join<PaymentHistory, User> user = insurance.join("user");
        Join<User, Village> village = user.join("village");
        Join<Village, District> district = village.join("district");
        Join<District, Province> province = district.join("province");

        // add predicate
        List<Predicate> predicates = new ArrayList<>();
        if (search.getId() != null) {
            Predicate predicate = criteriaBuilder.equal(root.get("id"), search.getId());
            predicates.add(predicate);
        }
        if (search.getUserId() != null) {
            Predicate predicate = criteriaBuilder.equal(user.get("id"), search.getUserId());
            predicates.add(predicate);
        }
        if (search.getCode() != null) {
            Predicate predicate = criteriaBuilder.equal(root.get("code"), search.getCode());
            predicates.add(predicate);
        }
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
                Predicate predicate = criteriaBuilder.greaterThanOrEqualTo(root.get("time"),
                        DateTimeUtils.parseDate(search.getFromDate(), DateTimeUtils.DD_MM_YYYY));
                predicates.add(predicate);
            } catch (RuntimeException ignored) {
            }
        }
        if (StringUtils.isNotBlank(search.getToDate())) {
            try {
                Predicate predicate = criteriaBuilder.lessThanOrEqualTo(root.get("time"),
                        DateTimeUtils.parseDate(search.getToDate(), DateTimeUtils.DD_MM_YYYY));
                predicates.add(predicate);
            } catch (RuntimeException ignored) {
            }
        }
        criteriaQuery.where(predicates.toArray(new Predicate[]{}));

        // create query
        TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery.select(criteriaBuilder.count(root)));
        return typedQuery.getSingleResult();
    }

    @Override
    public PaymentHistory getLatest(Long userId) {
        // create returns the data type of critetia query
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PaymentHistory> criteriaQuery = criteriaBuilder.createQuery(PaymentHistory.class);

        // from and join entity
        Root<PaymentHistory> root = criteriaQuery.from(PaymentHistory.class);
        Join<PaymentHistory, Insurance> insurance = root.join("insurance");
        Join<PaymentHistory, User> user = insurance.join("user");

        // add predicate
        List<Predicate> predicates = new ArrayList<>();
        if (userId != null) {
            Predicate predicate = criteriaBuilder.equal(user.get("id"), userId);
            predicates.add(predicate);
        }
        criteriaQuery.where(predicates.toArray(new Predicate[]{}));

        // order
        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("time")));

        // create query
        TypedQuery<PaymentHistory> typedQuery = entityManager.createQuery(criteriaQuery.select(root));
        typedQuery.setFirstResult(0);
        typedQuery.setMaxResults(1);

        try {
            return typedQuery.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}