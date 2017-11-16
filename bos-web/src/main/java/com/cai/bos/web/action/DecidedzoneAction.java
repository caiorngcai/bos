/**
 * 
 */
package com.cai.bos.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cai.bos.crm.Customer;
import com.cai.bos.crm.ICustomerService;
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
	//属性驱动接受客户id的数组
	private List<Integer> customerIds;
	public void setCustomerIds(List<Integer> customerIds) {
		this.customerIds = customerIds;
	}
	@Autowired
	private DecidedzoneService decidedzoneService;
	@Autowired
	private ICustomerService proxy;
	/*
	 * 保存定区数据的方法
	 */
	public String add() {
		decidedzoneService.save(model,subareaid);
		return LIST;
	}
	/*
	 * 分页查询定区数据的方法
	 */
	public String pageQuery() {
		decidedzoneService.pageQuery(pageBean);
		//定区关联的分区需要去除，但是展示的grid里有staff的数据，故staff不能去除，但是staff里的decidedzones属性要去除，不然会死循环
		this.java2json(pageBean,new String[]{"currentPage","detachedCriteria","pageSize","subareas","decidedzones"});
		return NONE;
	}
	/*
	 * 查询未关联定区的客户对象，此方法调用webservice接口
	 */
	public String findListNotAssociation() {
		List<Customer> list=proxy.findListNotAssociation();
		this.java2Json(list,new String[]{});
		return NONE;
	}
	public String findListHasAssociation() {
		String id=model.getId();
		List<Customer> list=proxy.findListAssociation(id);
		this.java2Json(list,new String[]{});
		return NONE;
	}
	public String assigncustomerstodecidedzone() {
		proxy.assignCustomerToDecidedzone(model.getId(),customerIds);
		return LIST;
	}
	

}
