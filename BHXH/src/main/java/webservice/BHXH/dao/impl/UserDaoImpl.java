package webservice.BHXH.dao.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import webservice.BHXH.dao.UserDao;
import webservice.BHXH.entity.User;

@Repository
@Transactional
public class UserDaoImpl extends BaseDaoImpl<User, Long> implements UserDao {

}
