package com.cai.bos.web.action;

import java.io.IOException;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
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
		this.java2json(pageBean,new String[]{"currentPage","detachedCriteria","pageSize","decidedzones"});
		return NONE;
	}
	@RequiresPermissions("staff-delete")
	public String deleteBatch() {
		staffService.deleteBatch(ids);
		return LIST;
	}
	@RequiresPermissions("staff-edit")
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
	/*
	 * 定区下拉栏取派员选择的数据提供方法
	 */
	public String listajax() {
		List<Staff> list=staffService.findListNotDelete();
		this.java2Json(list, new String[]{"decidedzones"});
		return NONE;
	}
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

}

