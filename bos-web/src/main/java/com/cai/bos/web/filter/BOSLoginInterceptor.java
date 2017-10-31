package com.cai.bos.web.filter;

import com.cai.bos.domain.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.apache.struts2.ServletActionContext;

/**
 * 登陆过滤器类，继承自 MethodFilterInterceptor 可以自定义拦截的方法，比较灵活
 *
 * @author crc
 * @create 2017-10-30 14:40
 **/

public class BOSLoginInterceptor extends MethodFilterInterceptor {
    @Override
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
        User user=(User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
        if(user==null)
        {
            return "login";
        }else {
            return actionInvocation.invoke();
        }
    }
}
