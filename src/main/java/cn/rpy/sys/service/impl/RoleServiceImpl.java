package cn.rpy.sys.service.impl;

import cn.rpy.sys.constant.SysConstant;
import cn.rpy.sys.domain.Menu;
import cn.rpy.sys.domain.Role;
import cn.rpy.sys.mapper.MenuMapper;
import cn.rpy.sys.mapper.RoleMapper;
import cn.rpy.sys.service.RoleService;
import cn.rpy.sys.utils.DataGridView;
import cn.rpy.sys.utils.TreeNode;
import cn.rpy.sys.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public DataGridView queryAllRoleForList(RoleVo roleVo) {
        return new DataGridView(roleMapper.queryAllRole(roleVo));
    }

    @Override
    public List<Role> queryRoleByUserIdForList(RoleVo RoleVo, Integer userId) {
        return null;
    }

    @Override
    public void addRole(RoleVo roleVo) {
        roleMapper.insert(roleVo);
    }

    @Override
    public Integer queryRoleByPid(Integer id) {
        return null;
    }

    @Override
    public void deleteRole(Integer roleId) {
        //删除角色数据
        this.roleMapper.deleteByPrimaryKey(roleId);

        //根据角色删除sys_role里面的数据
        this.roleMapper.deleteRoleMenuByRid(roleId);

        //根据角色删除sys_role_user里面的数据
        this.roleMapper.deleteRoleUserByRid(roleId);
    }

    @Override
    public void deleteBatchRole(Integer[] roleIds) {
        for (Integer roleId : roleIds) {
            deleteRole(roleId);
        }
    }


    @Override
    public void updateRole(RoleVo roleVo) {
        this.roleMapper.updateByPrimaryKeySelective(roleVo);
    }

    @Override
    public DataGridView initRoleMenuTreeJson(Integer roleid) {
        //查询所有可用的菜单
        Menu menu = new Menu();
        menu.setAvailable(SysConstant.AVAILABLE_TRUE);
        List<Menu> allMenu = menuMapper.queryAllMenu(menu);
        //根据角色查询菜单
        final List<Menu> roleMenu = menuMapper.queryMenuByRoleId(SysConstant.AVAILABLE_TRUE, roleid);
        List<TreeNode> treeNodes=new ArrayList<>();

        System.out.println(allMenu+"\n\n"+roleMenu);
        for (Menu m1 : allMenu) {
            String  checkArr=SysConstant.CODE_ZERO+"";
            for (Menu m2 : roleMenu) {
                if(m1.getId() == m2.getId()){
                    checkArr=SysConstant.CODE_ONE+"";
                    break;
                }
            }
            treeNodes.add((new TreeNode(m1.getId(),
                    m1.getPid(),
                    m1.getTitle(),
                    m1.getSpread()==1,checkArr)));
        }
        return new DataGridView(treeNodes);
    }

    @Override
    public void saveRoleMenu(RoleVo roleVo) {
        Integer id=roleVo.getRoleid();
        Integer[] mids=roleVo.getIds();
        this.roleMapper.deleteRoleMenuByRid(id);
        for (Integer mid : mids) {
            this.roleMapper.insertRoleMenu(id,mid);
        }

    }
}
