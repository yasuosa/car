package cn.rpy.sys.controller;


import cn.rpy.sys.constant.SysConstant;
import cn.rpy.sys.domain.User;
import cn.rpy.sys.service.LogInfoService;
import cn.rpy.sys.service.UserService;
import cn.rpy.sys.utils.WebUtils;
import cn.rpy.sys.vo.LogInfoVo;
import cn.rpy.sys.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * 用户登陆控制器
 */

@Controller
@RequestMapping("login")
public class LoginController {

    @Autowired
    private UserService userService;


    @Autowired
    private LogInfoService logInfoService;

    /**
     * 用户登录
     * @return
     */
    @RequestMapping("toLogin")
    public String toLogin(){
        return "system/main/login";
    }

    @RequestMapping("login")
    public String login(UserVo userVo, Model model, HttpSession session){
        User user=this.userService.login(userVo);
        if(user != null){
            //放入session
            session.setAttribute("user",user);
            LogInfoVo logInfoVo=new LogInfoVo();
            logInfoVo.setLoginname(user.getRealname()+"-"+user.getLoginname());
            logInfoVo.setLogintime(new Date());
            //ip地址
            logInfoVo.setLoginip(WebUtils.getHttpServletRequest().getRemoteAddr());

            logInfoService.saverLogInfo(logInfoVo);

            //记录登录日志 向sys_login_log里面插入数据

            return "system/main/index";
        }

        model.addAttribute("error", SysConstant.USER_LOGIN_ERROR_MSG);
        return "system/main/login";
    }
}
