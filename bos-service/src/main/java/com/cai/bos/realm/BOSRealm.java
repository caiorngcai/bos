/**
 * 
 */
package com.cai.bos.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.cai.bos.dao.UserDao;
import com.cai.bos.domain.User;

/**
 * @author crc
 *	@date 2017年11月24日 上午10:34:22
 */
public class BOSRealm extends AuthorizingRealm{
	@Autowired
	private UserDao userDao;
	/* 
	 * 认证方法
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken passwordToken=(UsernamePasswordToken) token;
		//从令牌中获得用户名，查询相应密码
		String userName=passwordToken.getUsername();
		User user=userDao.findUserByUsername(userName);
		if(user==null)
		{
			return null;
		}
		AuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(user,user.getPassword(),this.getName());
		return authenticationInfo;
	}

	/* 
	 * 授权方法
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
		info.addStringPermission("staff-list");
		info.addStringPermission("staff-delete");
		return info;
	}

}
