/**
 * 
 */
package com.cai.bos.web.action;

import java.io.IOException;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cai.bos.domain.Workordermanage;
import com.cai.bos.service.WorkordermanageService;

/**
 * @author crc
 *	@date 2017年11月23日 下午11:05:11
 */
@Controller
@Scope("prototype")
public class WorkordermanageAction extends BaseAction<Workordermanage>{
	@Autowired
	private WorkordermanageService workordermanageService;
	public String add() throws IOException {
		String flag="1";
		try {
			workordermanageService.save(model);
		} catch (Exception e) {
			e.printStackTrace();
			flag="0";
		}
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().write(flag);
		return NONE;
	}
	
}
