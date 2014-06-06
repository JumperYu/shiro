package com.my.web.controller;

import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.my.entity.Resource;
import com.my.entity.User;
import com.my.service.ResourceService;
import com.my.service.UserService;
import com.my.web.CurrentUser;

@Controller
public class IndexController {

	private static transient final Logger log = LoggerFactory
			.getLogger(IndexController.class);

	@Autowired
	private ResourceService resourceService;
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/")
	public String success(Model model) {
		log.info("访问成功");
		Subject subject = SecurityUtils.getSubject();
		Set<String> permissions = userService.findPermissions(subject
				.getPrincipal().toString());
		List<Resource> menus = resourceService.findMenus(permissions);
		model.addAttribute("menus", menus);
		return "success";
	}

	@RequestMapping(value = "/welcome")
	public String index(@CurrentUser User loginUser) {
		log.info("访问成功");

		return "index";
	}

}
