package webservice.BHXH.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webservice.BHXH.dao.UserDao;
import webservice.BHXH.entity.User;
import webservice.BHXH.model.dto.UserDto;
import webservice.BHXH.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Override
	public void add(UserDto t) {
		User user = toEntity(t);
		userDao.add(user);
		t.setId(user.getId());

	}

	@Override
	public void update(UserDto t) {
		if (userDao.exists(t.getId())) {
			userDao.update(toEntity(t));
		}
	}

	@Override
	public void delete(Long k) {
		User user = userDao.getById(k);
		if (user != null) {
			userDao.update(user);
		}

	}

	@Override
	public UserDto getById(Long k) {
		User user = userDao.getById(k);
		if (user != null) {
			return toDto(user);
		}
		return null;
	}

	User toEntity(UserDto dto) {
		User user = new User();
		return user;
	}

	UserDto toDto(User user) {
		UserDto dto = new UserDto();
		dto.setId(user.getId());
		return dto;
	}

	@Override
	public List<UserDto> getAll() {
		List<UserDto> dtos = new ArrayList<UserDto>();
		List<User> users = userDao.getAll();
		for (User user : users) {
			dtos.add(toDto(user));
		}
		return dtos;
	}
}
