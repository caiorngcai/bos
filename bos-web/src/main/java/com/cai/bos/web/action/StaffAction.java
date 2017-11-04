package com.cai.bos.web.action;

import java.io.IOException;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cai.bos.domain.Staff;
import com.cai.bos.domain.User;
import com.cai.bos.service.StaffService;
import com.cai.bos.utils.PageBean;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff>{
	//应该为属性设置set和get方法，不然正确用属性驱动获得对象。
	private int page;
	private int rows;
	private String ids;
	
	@Autowired
	private StaffService staffService;

	public String add() {
			
		staffService.save(model);
		return LIST;
	}
	
	public String pageQuery() throws IOException {
		//此处的pageBean来自父类baseaction，为已经封装好page和row,查询对象的pagebean。
		staffService.pageQuery(pageBean);
		this.java2json(pageBean,new String[]{"currentPage","detachedCriteria","pageSize"});
		return NONE;
	}
	
	public String deleteBatch() {
		staffService.deleteBatch(ids);
		return LIST;
	}
	public String edit() {
		Staff staff=staffService.findById(model.getId());
		staff.setName(model.getName());
		staff.setTelephone(model.getTelephone());
		staff.setHaspda(model.getHaspda());
		staff.setStandard(model.getStandard());
		staff.setStation(model.getStandard());
		staffService.update(staff);
		return LIST;
	}
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

}

