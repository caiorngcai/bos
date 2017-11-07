package com.cai.bos.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
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


	/* 
	 * 批量删除取派员的方法
	 */
	public void deleteBatch(String ids) {
			if(StringUtils.isNotBlank(ids))
			{
				String[] staffIds=ids.split(",");
					for (String id : staffIds) {
						//调用dao层逻辑删除取派员
					staffDao.executeUpdate("staff.delete", id);
				}
			}
	}


	/* 
	 * 根据id查找取派员的方法
	 */
	
	public Staff findById(String id) {
		return staffDao.findById(id);
		
	}


	/* 
	 * 更新取派员的方法
	 */
	@Override
	public void update(Staff staff) {
		staffDao.update(staff);
		
	}


	/* 
	 *查询未删除取派员的方法 
	 */
	public List<Staff> findListNotDelete() {
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(Staff.class);
		detachedCriteria.add(Restrictions.eq("deltag", "0"));
		return staffDao.findByCriteria(detachedCriteria);
	}


	
}
