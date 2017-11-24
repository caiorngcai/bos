/**
 * 
 */
package com.cai.bos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cai.bos.dao.FunctionDao;
import com.cai.bos.domain.Function;
import com.cai.bos.service.FunctionService;
import com.cai.bos.utils.PageBean;

/**
 * @author crc
 *	@date 2017年11月24日 下午8:56:08
 */
@Service
@Transactional
public class FunctionServiceImpl implements FunctionService {
@Autowired
private FunctionDao functionDao;
	/* 
	 *查找所有权限数据的方法 
	 */
	public List<Function> findAll() {
		return functionDao.findAll();
	}
	/* 
	 *保存权限数据的方法 
	 */
	public void save(Function model) {
		//获得父权限，若为空设置为顶级权限
		Function parentFunction=model.getParentFunction();
		if(parentFunction!=null&&parentFunction.getId().equals(""))
		{
			model.setParentFunction(null);
		}
		functionDao.save(model);
	}
	/* 
	 * 分页查询权限数据的方法
	 */
	public void pageQuery(PageBean pageBean) {
		functionDao.pageQuery(pageBean);
		
	}

}
