/**
 * 
 */
package com.cai.bos.dao;

import java.util.List;

import com.cai.bos.dao.base.BaseDao;
import com.cai.bos.domain.Region;

/**
 * @author crc
 *	@date 2017年11月4日 下午8:55:16
 */
public interface RegionDao extends BaseDao<Region> {

	public void saveOrUpdate(Region region);

	/**
	 * 
	 */
	public List<Region> findListByq(String q);

}
