package com.hoolai.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test_01 {

	private static final transient Logger log = LoggerFactory
			.getLogger(Test_01.class);

	@Test
	public void testIniInit() {
		// 1、获取SecurityManager工厂，此处使用 Ini配置文件初始化 SecurityManager
		Factory<SecurityManager> factory = new IniSecurityManagerFactory(
				"classpath:shiro.ini");
		// 2、得到SecurityManager实例 并绑定给SecurityUtils
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		// 3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
		Subject subject = SecurityUtils.getSubject();
		// subject.getPrincipals();
		UsernamePasswordToken token = new UsernamePasswordToken("zengxm", "123");

		try {
			// 4、登录，即身份验证
			subject.login(token);
		} catch (AuthenticationException e) {
			// 5、身份验证失败
		}

		log.info("User [" + subject.getPrincipal()
				+ "] logged in successfully.");

		Assert.assertEquals(true, subject.isAuthenticated()); // 断言用户已经登录

		// test a typed permission
		if (subject.isPermitted("hoolai:changyu:jiekou")) {
			log.info("You may use a hoolai's permission");
		} else {
			log.info("Sorry, you don't have permission.");
		}

		// 6、退出
		subject.logout();
	}

	@Test
	public void testCustomRealm() {
		// 1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
		Factory<SecurityManager> factory = new IniSecurityManagerFactory(
				"classpath:shiro-realm.ini");

		// 2、得到SecurityManager实例 并绑定给SecurityUtils
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);

		// 3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("zengxm", "123");

		try {
			// 4、登录，即身份验证
			subject.login(token);
		} catch (AuthenticationException e) {
			// 5、身份验证失败
			e.printStackTrace();
		}

		Assert.assertEquals(true, subject.isAuthenticated()); // 断言用户已经登录

		// 6、退出
		subject.logout();
	}

	@Test
	public void testCustomAuthorRealm() {
	}

	@Test
	public void testJDBCRealm() {
		// 1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory(
				"classpath:shiro-jdbc-realm.ini");

		// 2、得到SecurityManager实例 并绑定给SecurityUtils
		org.apache.shiro.mgt.SecurityManager securityManager = factory
				.getInstance();
		SecurityUtils.setSecurityManager(securityManager);

		// 3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("admin",
				"qwerty");

		try {
			// 4、登录，即身份验证
			subject.login(token);
		} catch (AuthenticationException e) {
			// 5、身份验证失败
			e.printStackTrace();
		}

		Assert.assertEquals(true, subject.isAuthenticated()); // 断言用户已经登录

		// 6、退出
		subject.logout();
	}

	private void login(String configFile) {
		// 1、获取SecurityManager工厂，此处使用 Ini配置文件初始化 SecurityManager
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory(
				configFile);

		// 2、得到SecurityManager实例 并绑定给SecurityUtils
		org.apache.shiro.mgt.SecurityManager securityManager = factory
				.getInstance();
		SecurityUtils.setSecurityManager(securityManager);

		// 3、得到Subject及创建用户名/密码身份验证 Token（即用户身份/凭证）
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("zengxm", "123");

		subject.login(token);
	}

	@Test
	// (expected = UnknownAccountException.class)
	public void testAllSuccessfulStrategyWithSuccess() {
		login("classpath:shiro-authenticator-all-success.ini");
		Subject subject = SecurityUtils.getSubject();
		// 得到一个身份集合，其包含了Realm验证成功的身份信息
		PrincipalCollection principalCollection = subject.getPrincipals();
		Assert.assertEquals(2, principalCollection.asList().size());
	}

	@Test
	public void testMZD5(){

	}
}
