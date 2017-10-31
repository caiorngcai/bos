package com.cai.bos.service;

import com.cai.bos.domain.User;

public interface UserService {
    public  User login(User model);

	public void editPassword(String id, String password);
    
}
