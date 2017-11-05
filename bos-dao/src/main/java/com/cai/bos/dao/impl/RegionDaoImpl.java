/**
 * 
 */
package com.cai.bos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cai.bos.dao.RegionDao;
import com.cai.bos.dao.base.impl.BaseDaoImpl;
import com.cai.bos.domain.Region;

/**
 * @author crc
 *	@date 2017年11月4日 下午8:56:55
 */
@Repository
public class RegionDaoImpl extends BaseDaoImpl<Region> implements RegionDao{

	/* 
	 *根据条件模糊查询区域列表的方法 
	 */
	public List<Region> findListByq(String q) {
		String hql = "FROM Region r WHERE r.shortcode LIKE ? "
				+ "	OR r.citycode LIKE ? OR r.province LIKE ? "
				+ "OR r.city LIKE ? OR r.district LIKE ?";
		List<Region> list=(List<Region>) this.getHibernateTemplate().find(hql, "%"+q+"%","%"+q+"%","%"+q+"%","%"+q+"%","%"+q+"%");
		return list;
	}

}
