/**
 * 
 */
package com.cai.bos.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cai.bos.domain.Function;
import com.cai.bos.service.FunctionService;

/**
 * @author crc
 *	@date 2017年11月24日 下午8:42:24
 */
@Controller
@Scope("prototype")
public class FunctionAction extends BaseAction<Function>{
	@Autowired
	private FunctionService functionService;
	/*
	 * 查询所有权限
	 */
	public String listajax() {
		List<Function> list=functionService.findAll();
		this.java2Json(list,new String[]{"parentFunction","roles","children"});
		return NONE;
	}
	/*
	 * 保存权限数据的方法
	 */
	public String add() {
		functionService.save(model);
		return LIST;
	}
	/*
	 * 分页查询权限数据的方法
	 */
	public String pageQuery() {
	//此处是个坑要注意！！！
	//因为function也有一个setPage对象，故代表页数的page属性被优先封装到了function的page上
	//发生这个的原因是当模型驱动和属性驱动的set方法一致时，会把参数优先封装到模型驱动中
	String page=model.getPage();
	pageBean.setCurrentPage(Integer.parseInt(page));
	functionService.pageQuery(pageBean);
	this.java2json(pageBean,new String[]{"parentFunction","roles","children"});
	return NONE;
	}

}
