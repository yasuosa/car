package cn.rpy.sys.mapper;

import cn.rpy.sys.domain.Role;
import cn.rpy.sys.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 登陆
     */
    User login(User user);


    /**
     * 查询用户
     */

    List<User> queryAllUser(User user);

    User queryUserByUid(Integer userid);


    List<Role> queryUserRoleByUid(@Param("available") Integer availableTrue, @Param("userid") Integer userid);

    void deleteUserRoleByUid(Integer userid);

    void insertUserRoleBy(@Param("userid") Integer userid, @Param("roleId") Integer roleId);
}