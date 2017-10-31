package com.cai.bos.web.action;

import com.cai.bos.domain.User;
import com.cai.bos.service.UserService;
import com.cai.bos.utils.BOSUtil;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * 用户action类，继承自基础action类，用于用户相关的操作，如登陆注册等。
 *
 * @author crc
 * @create 2017-10-26 19:34
 **/
@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {
    private static final String HOME = "home";
    //属性驱动方式获得验证码值
    private String checkcode;

    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }

    //自动注入userservice对象
    @Autowired
    private UserService userService;

    /**
     * 处理用户登陆的请求方法
     */
    public String login() {
        //从Session中获取生成的验证码
        String validateCode = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
        //校验验证码是否输入正确
        if (StringUtils.isNotBlank(validateCode) && checkcode.equals(validateCode)) {
            //输入的验证码正确,去数据库查找用户
            User user = userService.login(model);
            if (user != null) {
                ServletActionContext.getRequest().getSession().setAttribute("loginUser", user);
                //登录成功,将user对象放入session，跳转到首页
                return HOME;
            } else {
                //登录失败，,设置提示信息，跳转到登录页面
                this.addActionError("用户名或者密码错误，请重新输入");
                return LOGIN;
            }

        } else {
            //输入的验证码错误,设置提示信息，跳转到登录页面
            this.addActionError("验证码不正确，请再输一次");
            return LOGIN;
        }

    }

    public String logout() {
        ServletActionContext.getRequest().getSession().invalidate();
        return LOGIN;
    }
/*
 * 接受用户修改密码的请求
 */
  public String editPassword() throws IOException {
	  //修改密码是否成功的标志
	  String flag="1";
	//获取当前登录用户
	  User user=BOSUtil.getLoginUser();
	  try{
	  userService.editPassword(user.getId(),model.getPassword());
	  }catch(Exception e)
	  {
		  flag="0";
		  e.printStackTrace();
	  }
	  ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
	  ServletActionContext.getResponse().getWriter().print(flag);
	  return NONE;
}

}