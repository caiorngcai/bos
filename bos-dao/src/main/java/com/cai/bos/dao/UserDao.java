package com.cai.bos.dao;

import com.cai.bos.dao.base.BaseDao;
import com.cai.bos.domain.User;

public interface UserDao extends BaseDao<User> {
    public User findUserByUsernameAndPassword(String username, String md5Password);
	public User findUserByUsername(String userName);

}
