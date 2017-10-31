package com.cai.bos.service.impl;

import com.cai.bos.dao.UserDao;
import com.cai.bos.domain.User;
import com.cai.bos.service.UserService;
import com.cai.bos.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户相关的逻辑服务类
 *
 * @author crc
 * @create 2017-10-26 19:41
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public User login(User model) {
        //使用md5加密密码
        String md5Password=MD5Utils.md5(model.getPassword());
        return userDao.findUserByUsernameAndPassword(model.getUsername(),md5Password);
    }
	//用户修改密码的方法
	public void editPassword(String id, String password) {
		//加盐之后直接调用dao层的方法修改即可
		String md5password=MD5Utils.md5(password);
		userDao.executeUpdate("user.editpassword",md5password,id);
	}


}
