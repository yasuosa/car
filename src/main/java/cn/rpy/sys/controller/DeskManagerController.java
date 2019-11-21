package cn.rpy.sys.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 工作台的控制器
 */

@Controller
@RequestMapping("desk")
public class DeskManagerController {

    /**
     * 跳转工作台页面
     *
     *
     */
    @RequestMapping("toDeskManager")
    public String toDeskManager(){
        return "system/main/deskManager";
    }
}
