package com.my.service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.entity.User;
import com.my.repository.jdbc.UserDao;

/**
 * <p>
 * User: Zeng Xiang Min
 * <p>
 * Date: 14-6-06
 * <p>
 * Version: 1.0
 */
@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;

	@Autowired
	private RoleService roleService;

	@Override
	public User createUser(User user) {

		return null;
	}

	@Override
	public User updateUser(User user) {

		return null;
	}

	@Override
	public void deleteUser(Long userId) {

	}

	@Override
	public void changePassword(Long userId, String newPassword) {

	}

	@Override
	public User findOne(Long userId) {

		return null;
	}

	@Override
	public List<User> findAll() {

		return null;
	}

	@Override
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public Set<String> findRoles(String username) {

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<String> findPermissions(String username) {
		User user = findByUsername(username);
		if (user == null) {
			return Collections.EMPTY_SET;
		}
		return roleService.findPermissions(user.getRoleIds().toArray(
				new Long[0]));
	}

}
