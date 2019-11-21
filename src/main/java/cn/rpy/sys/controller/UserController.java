package cn.rpy.sys.controller;


import cn.rpy.sys.constant.SysConstant;
import cn.rpy.sys.service.UserService;
import cn.rpy.sys.utils.DataGridView;
import cn.rpy.sys.utils.ResultObj;
import cn.rpy.sys.vo.RoleVo;
import cn.rpy.sys.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 菜单管理的控制器
 */

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * 加载菜单列表返回DataGridView
     */
    @RequestMapping("loadAllUser")
    public DataGridView loadAllUser(UserVo userVo){
        return this.userService.queryAllUserForList(userVo);
    }


    /**
     * 添加菜单
     */

    @RequestMapping("addUser")
    public ResultObj addUser(UserVo userVo){
        try {
            this.userService.addUser(userVo);
            return ResultObj.ADD_SUCCESS();
        }catch (Exception e){
            return ResultObj.ADD_ERROR();
        }
    }



    /**
     * 删除菜单
     */
    @RequestMapping("deleteUser")
    public ResultObj deleteUser(UserVo userVo){
        System.out.println(userVo.toString());
        try {
            this.userService.deleteUser(userVo.getUserid());
            return ResultObj.DELETE_SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR();
        }
    }

    /**
     * 批量删除菜单
     */
    @RequestMapping("deleteBatchUser")
    public ResultObj deleteBatchUser(UserVo userVo){
        try {
            this.userService.deleteBatchUser(userVo.getIds());
            return ResultObj.DELETE_SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR();
        }
    }

    /**
     * 编辑角色
     */
    @RequestMapping("updateUser")
    public ResultObj updateUser(UserVo userVo){
        try {
            this.userService.updateUser(userVo);
            return ResultObj.UPDATE_SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR();
        }
    }


    /**
     * 重置用户密码
     */

    @RequestMapping("resetUserPwd")
    public ResultObj restUserPwd(UserVo userVo){
        try {
            this.userService.resetUserPwd(userVo.getUserid());
            return ResultObj.RESET_SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.RESET_ERROR();
        }
    }

    /**
     * 加载用户管理的分配角色的json
     */
    @RequestMapping("initUserRole")
    public DataGridView initUserRole(UserVo userVo){
        return this.userService.queryUserRole(userVo.getUserid());
    }


    /**
     * 保存用户设置权限
     */
    @RequestMapping("saveUserRole")
    public ResultObj saveUserRole(UserVo userVo){
        try {
            this.userService.saveUserRole(userVo);
            return ResultObj.UPDATE_SUCCESS();
        } catch (Exception e) {
            return ResultObj.UPDATE_ERROR();
        }
    }
}
