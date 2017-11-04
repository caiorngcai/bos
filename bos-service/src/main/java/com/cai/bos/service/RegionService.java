/**
 * 
 */
package com.cai.bos.service;

import java.util.ArrayList;

import com.cai.bos.domain.Region;
import com.cai.bos.utils.PageBean;

/**
 * @author crc
 *	@date 2017年11月4日 下午8:51:04
 */
public interface RegionService {

	/**
	 * 批量保存区域数据的方法
	 */
	public void saveBatch(ArrayList<Region> regionList);

	/**
	 * 
	 */
	public void pageQuery(PageBean pageBean);

}
