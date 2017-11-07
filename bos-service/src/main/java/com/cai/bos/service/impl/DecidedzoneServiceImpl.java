/**
 * 
 */
package com.cai.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cai.bos.dao.DecidedzoneDao;
import com.cai.bos.dao.SubareaDao;
import com.cai.bos.domain.Decidedzone;
import com.cai.bos.domain.Subarea;
import com.cai.bos.service.DecidedzoneService;
import com.cai.bos.utils.PageBean;

/**
 * @author crc
 *	@date 2017年11月7日 下午1:57:38
 */
@Service
@Transactional
public class DecidedzoneServiceImpl implements DecidedzoneService{
	@Autowired
	private DecidedzoneDao decidedzoneDao;
	@Autowired
	private SubareaDao subareaDao;

	/* 
	 * 保存定区数据的方法
	 */
	public void save(Decidedzone model, String[] subareaid) {
			decidedzoneDao.save(model);
			for (String id : subareaid) {
				Subarea subarea=subareaDao.findById(id);
				subarea.setDecidedzone(model);
			}
	}

	/* 
	 * 
	 */
	public void pageQuery(PageBean pageBean) {
		decidedzoneDao.pageQuery(pageBean);
	}

}
