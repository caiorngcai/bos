package com.cai.bos.dao.impl;

import com.cai.bos.dao.UserDao;
import com.cai.bos.dao.base.BaseDao;
import com.cai.bos.dao.base.impl.BaseDaoImpl;
import com.cai.bos.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户dao的实现类
 *
 * @author crc
 * @create 2017-10-26 20:58
 **/
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{

	  public User findUserByUsernameAndPassword(String username, String md5Password) {
	        String hql = "FROM User u WHERE u.username = ? AND u.password = ?";
	        List<User> users= (List<User>) this.getHibernateTemplate().find(hql,username,md5Password);
	        if(users!=null&&users.size()>0) {
	            return users.get(0);
	        }
	        return null;
	    }

	/* 
	 *根据用户名查询密码的方法，返回给realm使用 
	 */
	public User findUserByUsername(String userName) {
		String hql="FROM User u WHERE u.username=?";
		List<User> list = (List<User>) this.getHibernateTemplate().find(hql, userName);
		if(list!=null&&list.size()>0)
		{
			return list.get(0);
		}
		return null;
	}

}
