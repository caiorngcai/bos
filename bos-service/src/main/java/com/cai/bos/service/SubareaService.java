/**
 * 
 */
package com.cai.bos.service;

import java.util.List;

import com.cai.bos.domain.Subarea;
import com.cai.bos.utils.PageBean;

/**
 * @author crc
 *	@date 2017年11月6日 上午9:39:37
 */
public interface SubareaService {

	/**
	 * 
	 */
	public void save(Subarea model);

	/**
	 * 
	 */
	public void pageQuery(PageBean pageBean);

	/**
	 * 
	 */
	public List<Subarea> findAll();

	/**
	 * 
	 */
	public List<Subarea> findListNotAssociation();

	/**
	 * 
	 */
	public List<Subarea> findListByDecidedzoneId(String decidedzoneId);




}
