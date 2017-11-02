package com.cai.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cai.bos.dao.StaffDao;
import com.cai.bos.domain.Staff;
import com.cai.bos.service.StaffService;
import com.cai.bos.utils.PageBean;

@Service
@Transactional
public class StaffServiceImpl implements StaffService{
	@Autowired
	private StaffDao staffDao;
	public void save(Staff model) {
		staffDao.save(model);
		
	}

	
	public void pageQuery(PageBean pageBean) {
	
		staffDao.pageQuery(pageBean);
	}

}
