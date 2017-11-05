/**
 * 
 */
package com.cai.bos.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cai.bos.dao.RegionDao;
import com.cai.bos.domain.Region;
import com.cai.bos.service.RegionService;
import com.cai.bos.utils.PageBean;

/**
 * @author crc
 *	@date 2017年11月4日 下午8:52:53
 */
@Service
@Transactional
public class RegionServiceImpl implements RegionService{
	@Autowired
	private RegionDao regionDao;
	/* 
	 * 批量保存区域数据的方法
	 */
	public void saveBatch(ArrayList<Region> regionList) {
		for (Region region : regionList) {
			regionDao.saveOrUpdate(region);
		}
	}
	/**
	 * 区域分页查询
	 */
	public void pageQuery(PageBean pageBean) {
		regionDao.pageQuery(pageBean);
		
	}
	/* 
	 * 根据查询条件过滤区域的方法
	 */
	
	public List<Region> findListByq(String q) {
		return regionDao.findListByq(q);
	}
	/* 
	 * 查询所有区域的方法
	 */
	public List<Region> findAll() {
		return regionDao.findAll();
	}

}
