package com.cai.bos.service;

import java.util.List;

import com.cai.bos.domain.Staff;
import com.cai.bos.utils.PageBean;

public interface StaffService {

	public void save(Staff model);
	public void pageQuery(PageBean pageBean);
	public void deleteBatch(String ids);
	public Staff findById(String id);
	public void update(Staff staff);
	/**
	 * 
	 */
	public List<Staff> findListNotDelete();
}
