package com.my.web.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListenerAdapter;

public class MySessionListener2 extends SessionListenerAdapter {

	@Override
	public void onStart(Session session) {
		System.out.println("会话创建2：" + session.getId());
	}

}
