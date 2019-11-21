package cn.rpy.sys.service.impl;

import cn.rpy.sys.constant.SysConstant;
import cn.rpy.sys.domain.Role;
import cn.rpy.sys.domain.User;
import cn.rpy.sys.mapper.RoleMapper;
import cn.rpy.sys.mapper.UserMapper;
import cn.rpy.sys.service.UserService;
import cn.rpy.sys.utils.DataGridView;
import cn.rpy.sys.vo.UserVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Autowired
    private RoleMapper roleMapper;


    @Override
    public User login(UserVo userVo) {
        //明文123456
        //生成密文
        String pwd = DigestUtils.md5DigestAsHex(userVo.getPwd().getBytes());
        userVo.setPwd(pwd);
        return userMapper.login(userVo);
    }

    @Override
    public DataGridView queryAllUserForList(UserVo userVo) {
        Page<Object> page= PageHelper.startPage(userVo.getPage(),userVo.getLimit());
        List<User> data=userMapper.queryAllUser(userVo);
        if(userVo.getPage() > page.getPages()){
            page.setPageNum(page.getPages());
            data.clear();
            data=userMapper.queryAllUser(userVo);
        }

        return new DataGridView(page.getTotal(),data);
    }


    @Override
    public void addUser(UserVo userVo) {
        //设置默认密码
        userVo.setPwd(DigestUtils.md5DigestAsHex(SysConstant.DEFAULT_PWD.getBytes()));
        //设置用户类型 都是普通用户 type=2
        userVo.setType(SysConstant.USER_TYPE_NORMAL);
        userMapper.insertSelective(userVo);
    }



    @Override
    public void deleteUser(Integer userId) {
        //删除用户
        this.userMapper.deleteByPrimaryKey(userId);

        //删除sys_role_user里面数据
        this.roleMapper.deleteUserRoleUid(userId);
    }

    @Override
    public void deleteBatchUser(Integer[] userIds) {
        for (Integer userId : userIds) {
            deleteUser(userId);
        }
    }

    @Override
    public void updateUser(UserVo userVo) {
        this.userMapper.updateByPrimaryKeySelective(userVo);
    }

    @Override
    public void resetUserPwd(Integer userid) {
        User user=this.userMapper.queryUserByUid(userid);
        user.setPwd(DigestUtils.md5DigestAsHex(SysConstant.DEFAULT_PWD.getBytes()));
        this.userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public DataGridView queryUserRole(Integer userid) {
        //查询课用角色
        Role role = new Role();
        role.setAvailable(SysConstant.AVAILABLE_TRUE);
        List<Role> roles = this.roleMapper.queryAllRole(role);
        //根据用户查询已拥有的角色
        List<Role> userRoleList=this.userMapper.queryUserRoleByUid(SysConstant.AVAILABLE_TRUE,userid);

        List<Map<String,Object>> data=new ArrayList<>();
        for (Role r1 : roles) {
            Boolean LAY_CHECKED=false;
            for (Role r2 : userRoleList) {
                if(r1.getRoleid() == r2.getRoleid()){
                    LAY_CHECKED=true;
                    break;
                }
            }
            Map<String,Object> map=new HashMap<>();
            map.put("roleid",r1.getRoleid());
            map.put("rolename",r1.getRolename());
            map.put("roledesc",r1.getRoledesc());
            map.put("LAY_CHECKED",LAY_CHECKED);
            data.add(map);
        }
        return new DataGridView(data);
    }

    @Override
    public void saveUserRole(UserVo userVo) {
        //删除原来的
        this.userMapper.deleteUserRoleByUid(userVo.getUserid());
        //加入
        Integer[] roleIds = userVo.getIds();
        for (Integer roleId : roleIds) {
            this.userMapper.insertUserRoleBy(userVo.getUserid(),roleId);
        }
    }
}
