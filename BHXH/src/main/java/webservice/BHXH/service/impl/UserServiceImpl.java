package webservice.BHXH.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webservice.BHXH.dao.UserDao;
import webservice.BHXH.entity.Role;
import webservice.BHXH.entity.User;
import webservice.BHXH.entity.Village;
import webservice.BHXH.model.dto.UserDto;
import webservice.BHXH.service.UserService;
import webservice.BHXH.utils.DateTimeUtils;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Override
	public void add(UserDto t) {
		User user = toEntity(t);
		Role role = new Role();
		role.setId(2L);
		user.setRole(role);
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

	@Override
	public List<UserDto> getAll() {
		List<UserDto> dtos = new ArrayList<UserDto>();
		List<User> users = userDao.getAll();
		for (User user : users) {
			dtos.add(toDto(user));
		}
		return dtos;
	}

	User toEntity(UserDto dto) {
		User user = new User();
		user.setName(dto.getName());
		user.setUserName(dto.getUserName());
		user.setPassword(dto.getPassword());
		user.setGender(dto.getGender());
		user.setDob(DateTimeUtils.parseDate(dto.getDob(), DateTimeUtils.DD_MM_YYYY));
		user.setIdentity(dto.getIdentity());
		user.setIsActive(dto.getIsActive());
		user.setPhone(dto.getPhone());
		Village village = new Village();
		village.setId(dto.getVillageId());
		user.setVillage(village);
		return user;
	}

	UserDto toDto(User user) {
		UserDto dto = new UserDto();
		dto.setId(user.getId());
		dto.setName(user.getName());
		dto.setUserName(user.getUserName());
		dto.setPassword(user.getPassword());
		dto.setGender(user.getGender());
		dto.setRoleId(user.getRole().getId());
		dto.setDob(DateTimeUtils.formatDate(user.getDob(), DateTimeUtils.DD_MM_YYYY));
		dto.setIdentity(user.getIdentity());
		dto.setIsActive(user.getIsActive());
		dto.setPhone(user.getPhone());
		dto.setVillageId(user.getVillage().getId());
		return dto;
	}
}
