/**
 * 
 */
package com.cai.bos.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cai.bos.dao.SubareaDao;
import com.cai.bos.domain.Staff;
import com.cai.bos.domain.Subarea;
import com.cai.bos.service.SubareaService;
import com.cai.bos.utils.PageBean;

/**
 * @author crc
 *	@date 2017年11月6日 上午9:40:45
 */
@Service
@Transactional
public class SubareaServiceImpl implements SubareaService {
	@Autowired
	private SubareaDao subareaDao;
	/* 
	 *保存分区数据的方法 
	 */
	public void save(Subarea model) {
		subareaDao.save(model);
	}
	/* 
	 *分区分页查询的方法 
	 */
	public void pageQuery(PageBean pageBean) {
		subareaDao.pageQuery(pageBean);
	}
	/* 
	 * 查询所有分区数据的方法
	 */
	public List<Subarea> findAll() {
		return subareaDao.findAll();
	}
	/* 
	 *查询所有未关联定区的方法 
	 */
	public List<Subarea> findListNotAssociation() {
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(Subarea.class);
		detachedCriteria.add(Restrictions.isNull("decidedzone"));
		return subareaDao.findByCriteria(detachedCriteria);
	}

}
