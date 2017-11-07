/**
 * 
 */
package com.cai.bos.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cai.bos.domain.Decidedzone;
import com.cai.bos.service.DecidedzoneService;

/**
 * @author crc
 *	@date 2017年11月7日 下午1:55:01
 */
@Controller
@Scope("prototype")
public class DecidedzoneAction extends BaseAction<Decidedzone>{
	//属性驱动接受参数，用来接受多个分区id
	private String[] subareaid;
	public void setSubareaid(String[] subareaid) {
		this.subareaid = subareaid;
	}
	@Autowired
	private DecidedzoneService decidedzoneService;
	public String add() {
		decidedzoneService.save(model,subareaid);
		return LIST;
	}
	
	public String pageQuery() {
		decidedzoneService.pageQuery(pageBean);
		//定区关联的分区需要去除，但是展示的grid里有staff的数据，故staff不能去除，但是staff里的decidedzones属性要去除，不然会死循环
		this.java2json(pageBean,new String[]{"currentPage","detachedCriteria","pageSize","subareas","decidedzones"});
		return NONE;
	}
	

}
