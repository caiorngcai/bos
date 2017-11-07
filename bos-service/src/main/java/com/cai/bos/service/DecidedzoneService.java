/**
 * 
 */
package com.cai.bos.service;

import com.cai.bos.domain.Decidedzone;
import com.cai.bos.utils.PageBean;

/**
 * @author crc
 *	@date 2017年11月7日 下午1:57:04
 */
public interface DecidedzoneService {

	/**
	 * 
	 */
	public void save(Decidedzone model, String[] subareaid);

	/**
	 * 
	 */
	public void pageQuery(PageBean pageBean);

}
