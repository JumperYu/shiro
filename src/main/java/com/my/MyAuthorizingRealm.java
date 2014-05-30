package com.my;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAccount;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class MyAuthorizingRealm extends AuthorizingRealm {

	public MyAuthorizingRealm() {
	}

	protected SimpleAccount getAccount(String username) {
		// just create a dummy. A real app would construct one based on EIS
		// access.
		SimpleAccount account = new SimpleAccount(username,
				"sha256EncodedPasswordFromDatabase", getName());
		// simulate some roles and permissions:
		account.addRole("user");
		account.addRole("admin");
		account.addStringPermission("hoolai:jiekou");
		// fine-grained instance level permission:
		account.addStringPermission("hoolai:qianduan");
		return account;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		return getAccount((String) upToken.getPrincipal());
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		String username = (String)getAvailablePrincipal(principals);
		return getAccount(username);
	}

}
