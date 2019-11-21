package cn.rpy.sys.service;


import cn.rpy.sys.domain.User;
import cn.rpy.sys.domain.User;
import cn.rpy.sys.utils.DataGridView;
import cn.rpy.sys.vo.UserVo;
import cn.rpy.sys.vo.UserVo;

import java.util.List;

/**
 * 用户服务类
 */
public interface UserService  {

    User login(UserVo userVo);


    /**
     * 查询所有可用角色，返回list集合
     */

    DataGridView queryAllUserForList(UserVo userVo);






    void addUser(UserVo UserVo);



    /**
     * 根据id删除角色
     * @param userId
     */
    void deleteUser(Integer userId);

    /**
     * 批量删除角色
     * @param userIds
     */
    void deleteBatchUser(Integer[] userIds);


    void updateUser(UserVo userVo);


    /**
     * 重置密码
     *
     */

    void resetUserPwd(Integer userid);

    DataGridView queryUserRole(Integer userid);

    void saveUserRole(UserVo userVo);
}
