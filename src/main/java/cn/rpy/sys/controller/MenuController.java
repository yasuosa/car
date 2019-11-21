package cn.rpy.sys.controller;


import cn.rpy.sys.constant.SysConstant;
import cn.rpy.sys.domain.Menu;
import cn.rpy.sys.domain.User;
import cn.rpy.sys.service.MenuService;
import cn.rpy.sys.utils.*;
import cn.rpy.sys.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 菜单管理的控制器
 */

@RestController
@RequestMapping("menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("loadIndexLeftMenu")
    public List<TreeNode> loadIndexLeftMenuJson(MenuVo menuVo){
        //得到当前登陆的用户对象
        User user = (User) WebUtils.getHttpSession().getAttribute("user");

        List<Menu> list=null;
        menuVo.setAvailable(1);//只查询可用的
        if(user.getType() == SysConstant.USER_ADMIN_ROOT_TYPE){
            list=this.menuService.queryAllMenuForList(menuVo);
        }else {
            list=this.menuService.queryMenuByUserIdForList(menuVo,user.getUserid());
        }

        //把list放入nodes
        List<TreeNode> nodes=new ArrayList<>();

        for (Menu menu : list) {
            nodes.add(new TreeNode(menu.getId(),
                                    menu.getPid(),
                                    menu.getTitle(),
                                    menu.getIcon(),
                                    menu.getHref(),
                                    menu.getSpread()==1,
                                    menu.getTarget()));
        }


        Integer topPid=1;

        return TreeNodeBuilder.builder(nodes,topPid);

    }

    /**
     * 查询菜单管理左边的菜单树
     */

    @RequestMapping("loadMenuManagerLeftTreeJson")
    public DataGridView loadManagerLeftTreeJson(MenuVo menuVo){
        menuVo.setAvailable(SysConstant.AVAILABLE_TRUE);
        List<Menu> list=this.menuService.queryAllMenuForList(menuVo);

        //把list放入nodes
        List<TreeNode> nodes=new ArrayList<>();

        for (Menu menu : list) {
            nodes.add(new TreeNode(menu.getId(),
                    menu.getPid(),
                    menu.getTitle(),
                    menu.getIcon(),
                    menu.getHref(),
                    menu.getSpread()==1,
                    menu.getTarget()));
        }
        return new DataGridView(nodes);
    }


    /**
     * 加载菜单列表返回DataGridView
     */
    @RequestMapping("loadAllMenu")
    public DataGridView loadAllMenu(MenuVo menuVo){
        return this.menuService.queryAllMenu(menuVo);
    }


    /**
     * 添加菜单
     */

    @RequestMapping("addMenu")
    public ResultObj addMenu(MenuVo menuVo){
        try {
            this.menuService.addMenu(menuVo);
            return ResultObj.ADD_SUCCESS();
        }catch (Exception e){
            return ResultObj.ADD_ERROR();
        }
    }


    /**
     * 根据id判断当前菜单有没有子节点
     * 有返回code>=0
     * 没有 返回code<0
     */

    @RequestMapping("checkMenuHasChildren")
    public ResultObj checkMenuHasChildren(MenuVo menuVo){
        //根据pid查询菜单数量
        Integer count=this.menuService.queryMenuByPid(menuVo.getId());
        if(count>0){
            return ResultObj.STATUS_TRUE();
        }else {
            return ResultObj.STATUS_FALSE();
        }
    }

    /**
     * 删除菜单
     */
    @RequestMapping("deleteMenu")
    public ResultObj deleteMenu(MenuVo menuVo){
        try {
            this.menuService.deleteMenu(menuVo);
            return ResultObj.DELETE_SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR();
        }
    }


    /**
     * 编辑菜单
     */
    @RequestMapping("updateMenu")
    public ResultObj updateMenu(MenuVo menuVo){
        try {
            this.menuService.updateMenu(menuVo);
            return ResultObj.UPDATE_SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR();
        }
    }

    /**
     * 批量删除
     */
    @RequestMapping("deleteBatchMenu")
    public ResultObj deleteBatchMenu(MenuVo menuVo){
        try {
            this.menuService.deleteBatchMenu(menuVo.getIds());
            return ResultObj.DELETE_SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR();
        }
    }

}
