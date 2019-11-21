package cn.rpy.sys.mapper;

import cn.rpy.sys.domain.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer roleid);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleid);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> queryAllRole(Role role);

    void deleteRoleMenuByRid(Integer roleId);

    void deleteRoleUserByRid(Integer roleId);

    void insertRoleMenu(@Param("id") Integer id, @Param("mid") Integer mid);

    void deleteUserRoleUid(Integer userId);
}