package com.cai.bos.utils;

import com.cai.bos.domain.User;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;

/**
 * 获得登陆用户的方法
 *
 * @author crc
 * @create 2017-10-30 9:42
 **/
public class BOSUtil {
    public static HttpSession getHeetpSession()
    {
        return ServletActionContext.getRequest().getSession();
    }
    public static User getLoginUser()
    {
        return (User) getHeetpSession().getAttribute("loginUser");
    }
}
