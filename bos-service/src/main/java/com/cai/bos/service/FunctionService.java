/**
 * 
 */
package com.cai.bos.service;

import java.util.List;

import com.cai.bos.domain.Function;
import com.cai.bos.utils.PageBean;

/**
 * @author crc
 *	@date 2017年11月24日 下午8:44:10
 */
public interface FunctionService {

	List<Function> findAll();
	void save(Function model);
	void pageQuery(PageBean pageBean);

}
