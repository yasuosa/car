package cn.rpy.sys.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 页面跳转的控制器 作用：路由
 */

@Controller
@RequestMapping("sys")
public class SysController {

    /**
     *跳转菜单管理
     */

    @RequestMapping("toMenuManager")
    private String toMenuManager(){
        return "system/menu/menuManager";
    }


    /**
     *跳转左边菜单管理
     */

    @RequestMapping("toMenuLeft")
    private String toMenuLeft(){
        return "system/menu/menuLeft";
    }



    /**
     *跳转右边菜单管理
     */

    @RequestMapping("toMenuRight")
    private String toMenuRight(){
        return "system/menu/menuRight";
    }


    /**
     * 跳转到角色管理
     *
     */
    @RequestMapping("toRoleManager")
    private String toRoleManager(){
        return "system/role/roleManager";
    }



    /**
     * 跳转到用户管理
     *
     */
    @RequestMapping("toUserManager")
    private String toUserManager(){
        return "system/user/userManager";
    }


    /**
     * 跳转到日志管理
     */

    @RequestMapping("toLogInfoManager")
    public String toLogInfoManager(){
        return "system/logInfo/logInfoManager";
    }

    /**
     * 跳转到公告管理
     */

    @RequestMapping("toNewsManager")
    public String toNewsManager(){
        return "system/news/newsManager";
    }

}
