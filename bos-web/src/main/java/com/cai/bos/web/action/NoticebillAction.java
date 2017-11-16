/**
 * 
 */
package com.cai.bos.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cai.bos.crm.Customer;
import com.cai.bos.crm.ICustomerService;
import com.cai.bos.domain.Noticebill;
import com.cai.bos.service.NoticebillService;

/**
 * @author crc
 *	@date 2017年11月16日 下午7:22:52
 */
@Controller
@Scope("prototype")
public class NoticebillAction extends BaseAction<Noticebill> {
	@Autowired
	private ICustomerService customerService;
	@Autowired
	private NoticebillService noticebillService;
	/*
	 * 远程调用crm服务，根据电话号码查询客户，用户通知单添加时数据回显
	 */
	public String findCustomerByTelephone() {
		String telephone=model.getTelephone();
		Customer customer = customerService.findCustomerByTelephone(telephone);
		this.java2json(customer, new String[]{});
		return NONE;
	}
	/**
	 * 保存一个业务通知单，并尝试自动分单
	 */
	public String add() {
		
			noticebillService.save(model);
			return "noticebill_add";
	}
}
