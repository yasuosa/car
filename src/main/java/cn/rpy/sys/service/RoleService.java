package cn.rpy.sys.service;


import cn.rpy.sys.domain.Role;
import cn.rpy.sys.utils.DataGridView;
import cn.rpy.sys.vo.RoleVo;

import java.util.List;

/**
 *  角色管理的服务接口
 */
public interface RoleService {


    /**
     * 查询所有可用角色，返回list集合
     */

    DataGridView queryAllRoleForList(RoleVo roleVo);


    /**
     * 根据用户id查询用户的可用菜单
     */

    List<Role> queryRoleByUserIdForList(RoleVo roleVo, Integer userId);




    void addRole(RoleVo RoleVo);

    Integer queryRoleByPid(Integer id);


    /**
     * 根据id删除角色
     * @param roleId
     */
    void deleteRole(Integer roleId);

    /**
     * 批量删除角色
     * @param roleIds
     */
    void deleteBatchRole(Integer[] roleIds);


    void updateRole(RoleVo roleVo);


    DataGridView initRoleMenuTreeJson(Integer roleid);

    void saveRoleMenu(RoleVo roleVo);
}
