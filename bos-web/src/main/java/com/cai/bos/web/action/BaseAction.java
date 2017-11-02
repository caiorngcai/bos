package com.cai.bos.web.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 基础action,抽象出每个action都有的getmodel方法
 *
 * @author crc
 * @create 2017-10-26 13:06
 **/
public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
	public static final String LIST="list";
	protected T model;
    public BaseAction() {
        ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type[] types = parameterizedType.getActualTypeArguments();
        Class<T> entity= (Class<T>) types[0];
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
}
