package com.hoolai.shiro;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.my.entity.User;
import com.my.service.ResourceService;
import com.my.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class Test_02 {

	@Resource
	private UserService userService;
	@Autowired
	private ResourceService resourceService;

	@Test
	public void testJDBC() {
		User user = userService.findByUsername("admin");
		System.out.println(user);
		Set<String> permissions = userService.findPermissions(user
				.getUsername());
		List<com.my.entity.Resource> menus = resourceService.findMenus(permissions);
		System.out.println(menus);
	}

}
