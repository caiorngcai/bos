package com.cai.bos.service;

import com.cai.bos.domain.Staff;
import com.cai.bos.utils.PageBean;

public interface StaffService {

	void save(Staff model);
	void pageQuery(PageBean pageBean);

}
