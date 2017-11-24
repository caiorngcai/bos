/**
 * 
 */
package com.cai.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cai.bos.dao.WorkordermanageDao;
import com.cai.bos.domain.Workordermanage;
import com.cai.bos.service.WorkordermanageService;

/**
 * @author crc
 *	@date 2017年11月23日 下午11:15:46
 */
@Service
@Transactional
public class WorkordermanageServiceImpl implements WorkordermanageService{
	@Autowired
	private WorkordermanageDao workordermanageDao;
	/* 
	 *保存工作单的方法 
	 */
	public void save(Workordermanage model) {
		workordermanageDao.save(model);
	}

}
