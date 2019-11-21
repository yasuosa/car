package cn.rpy.sys.controller;


import cn.rpy.sys.constant.SysConstant;
import cn.rpy.sys.domain.Role;
import cn.rpy.sys.domain.User;
import cn.rpy.sys.service.RoleService;
import cn.rpy.sys.utils.*;
import cn.rpy.sys.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单管理的控制器
 */

@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleService roleService;


    /**
     * 加载菜单列表返回DataGridView
     */
    @RequestMapping("loadAllRole")
    public DataGridView loadAllRole(RoleVo roleVo){
        return this.roleService.queryAllRoleForList(roleVo);
    }


    /**
     * 添加菜单
     */

    @RequestMapping("addRole")
    public ResultObj addRole(RoleVo roleVo){
        try {
            this.roleService.addRole(roleVo);
            return ResultObj.ADD_SUCCESS();
        }catch (Exception e){
            return ResultObj.ADD_ERROR();
        }
    }



    /**
     * 删除菜单
     */
    @RequestMapping("deleteRole")
    public ResultObj deleteRole(RoleVo roleVo){
        System.out.println(roleVo.toString());
        try {
            this.roleService.deleteRole(roleVo.getRoleid());
            return ResultObj.DELETE_SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR();
        }
    }

    /**
     * 批量删除菜单
     */
    @RequestMapping("deleteBatchRole")
    public ResultObj deleteBatchRole(RoleVo roleVo){
        try {
            this.roleService.deleteBatchRole(roleVo.getIds());
            return ResultObj.DELETE_SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR();
        }
    }

    /**
     * 编辑角色
     */
    @RequestMapping("updateRole")
    public ResultObj updateRole(RoleVo roleVo){
        try {
            this.roleService.updateRole(roleVo);
            return ResultObj.UPDATE_SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR();
        }
    }


    /**
     * 加载角色管理分配菜单的json
     *
     */
    @RequestMapping("initRoleMenuTreeJson")
    public DataGridView initRoleMenuTreeJson(Integer roleid){
        return this.roleService.initRoleMenuTreeJson(roleid);
    }

    /**
     * 分配权限
     *
     */
    @RequestMapping("saveRoleMenu")
    public ResultObj saveRoleMenu(RoleVo roleVo){
        try {
            this.roleService.saveRoleMenu(roleVo);
            return ResultObj.UPDATE_SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR();
        }
    }
}
