package com.my.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

public class MyRealm1 implements Realm{

	public MyRealm1() {
	}

	@Override
	public String getName() {
		return "zxm";
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof UsernamePasswordToken;
	}

	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token)
			throws AuthenticationException {
		String username = (String)token.getPrincipal();
		String password = new String((char[])token.getCredentials());
		if(!"zengxm".equals(username)){
			throw new UnknownAccountException();
		}
		if(!"123".equals(password)){
			throw new IncorrectCredentialsException();
		}
		
		return new SimpleAuthenticationInfo("zxm", password, getName());
	}
	
}
