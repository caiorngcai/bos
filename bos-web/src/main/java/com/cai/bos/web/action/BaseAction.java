package com.cai.bos.web.action;

import com.cai.bos.utils.PageBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

/**
 * 基础action,抽象出每个action都有的getmodel方法
 *
 * @author crc
 * @create 2017-10-26 13:06
 **/
public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
	public static final String LIST="list";
	protected T model;
	//分页查询的重复代码抽取到base层
	protected PageBean pageBean=new PageBean();
	protected DetachedCriteria detachedCriteria=null;
	//有set方法既可以使struts2正确获得表单对应的参数，由此即可把分页查询的参数page,row封装到pagebean对象中。
	public void setPage(int page) {
		pageBean.setCurrentPage(page);
	}

	public void setRows(int rows) {
		pageBean.setPageSize(rows);
	}
    public BaseAction() {
        ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type[] types = parameterizedType.getActualTypeArguments();
        Class<T> entity= (Class<T>) types[0];
        detachedCriteria=DetachedCriteria.forClass(entity);//返回对应子对象的查询对象
        pageBean.setDetachedCriteria(detachedCriteria);
        try {
            //通过反射创建运行时对象
          model= entity.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public T getModel() {
        return model;
    }
    public void java2json(Object o,String[] excludes) {
    	JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.setExcludes(excludes);
		String json=JSONObject.fromObject(pageBean, jsonConfig).toString();
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		//因为实在base层，故此处的异常尽量不要往外抛
		try {
			ServletActionContext.getResponse().getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
