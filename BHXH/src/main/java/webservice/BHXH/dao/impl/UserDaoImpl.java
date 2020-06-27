package webservice.BHXH.dao.impl;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import webservice.BHXH.dao.UserDao;
import webservice.BHXH.entity.User;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class UserDaoImpl extends BaseDaoImpl<User, Long> implements UserDao {

    @Override
    public User getByUsername(String username) {
        // create returns the data type of critetia query
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

        // from and join entity
        Root<User> root = criteriaQuery.from(User.class);

        // add predicate
        List<Predicate> predicates = new ArrayList<>();
        if (StringUtils.isNotBlank(username)) {
            Predicate predicate = criteriaBuilder.equal(root.get("username"), username);
            predicates.add(predicate);
        }
        criteriaQuery.where(predicates.toArray(new Predicate[]{}));

        // create query
        TypedQuery<User> typedQuery = entityManager.createQuery(criteriaQuery.select(root));
        try {
            return typedQuery.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
