package com.my.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.my.entity.Users;
import com.my.repository.jpa.UserDao;

/**
 * 用户管理业务类.
 * 
 * @author calvin
 * @param
 */
// Spring Service Bean的标识.
//@Service
// @Transactional
public class AccountService {

	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	// private static final int SALT_SIZE = 8;

	private static Logger logger = LoggerFactory
			.getLogger(AccountService.class);

	private UserDao userDao;

	/**
	 * 在保存用户时,发送用户修改通知消息, 由消息接收者异步进行较为耗时的通知邮件发送.
	 * 
	 * 如果企图修改超级用户,取出当前操作员用户,打印其信息然后抛出异常.
	 * 
	 */
	public void saveUser(Users user) {

		if (isSupervisor(user)) {
			logger.warn("操作员{}尝试修改超级管理员用户", getCurrentUserName());
			// throws
		}

		// 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
		/*
		 * if (StringUtils.isNotBlank(user.getPlainPassword())) {
		 * entryptPassword(user); }
		 */

		userDao.save(user);

		// 业务日志演示
		Map logData = Maps.newHashMap();
		logData.put("userId", user.getId());
	}

	/**
	 * 按Id获得用户.
	 */
	public Users getUser(Long id) {
		return userDao.findOne(id);
	}

	/**
	 * 获取全部用户，并在返回前对用户的延迟加载关联角色进行初始化.
	 */
	public List<Users> getAllUserInitialized() {
		List<Users> result = (List<Users>) userDao.findAll();
		for (Users user : result) {
			// Hibernates.initLazyProperty(user.getRoleList());
		}
		return result;
	}

	/**
	 * 按登录名查询用户.
	 */
	public Users findUserByLoginName(String loginName) {
		return userDao.findByLoginName(loginName);
	}

	/**
	 * 按名称查询用户, 并在返回前对用户的延迟加载关联角色进行初始化.
	 */
	public Users findUserByNameInitialized(String name) {
		Users user = userDao.findByName(name);
		if (user != null) {
			// Hibernates.initLazyProperty(user.getRoleList());
		}
		return user;
	}

	/**
	 * 按页面传来的查询条件查询用户.
	 */
	public List<Users> searchUser(Map<String, Object> searchParams) {
		List<Users> userList = null;
		return userList;
	}

	/**
	 * 获取当前用户数量.
	 */
	public Long getUserCount() {
		return userDao.count();
	}

	/**
	 * 判断是否超级管理员.
	 */
	private boolean isSupervisor(Users user) {
		return ((user.getId() != null) && (user.getId() == 1L));
	}

	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 */
	private void entryptPassword(Users user) {
		/*
		 * byte[] salt = Digests.generateSalt(SALT_SIZE);
		 * user.setSalt(Encodes.encodeHex(salt));
		 * 
		 * byte[] hashPassword =
		 * Digests.sha1(user.getPlainPassword().getBytes(), salt,
		 * HASH_INTERATIONS); user.setPassword(Encodes.encodeHex(hashPassword));
		 */
	}

	/**
	 * 取出Shiro中的当前用户LoginName.
	 */
	private String getCurrentUserName() {
		/*
		 * ShiroUser user = (ShiroUser)
		 * SecurityUtils.getSubject().getPrincipal(); return user.loginName;
		 */
		return "";
	}

	// -----------------//
	// Setter methods //
	// -----------------//

	@Resource
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
