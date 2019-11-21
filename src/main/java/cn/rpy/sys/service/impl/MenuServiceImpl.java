package cn.rpy.sys.service.impl;

import cn.rpy.sys.constant.SysConstant;
import cn.rpy.sys.domain.Menu;
import cn.rpy.sys.domain.Role;
import cn.rpy.sys.mapper.MenuMapper;
import cn.rpy.sys.mapper.RoleMapper;
import cn.rpy.sys.mapper.UserMapper;
import cn.rpy.sys.service.MenuService;
import cn.rpy.sys.utils.DataGridView;
import cn.rpy.sys.vo.MenuVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;


    @Autowired
    private RoleMapper roleMapper;
    
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Menu> queryAllMenuForList(MenuVo menuVo) {
        return menuMapper.queryAllMenu(menuVo);
    }


    /***
     * 后期权限管理完成再写
     * @param menuVo
     * @param userId
     * @return
     */
    @Override
    public List<Menu> queryMenuByUserIdForList(MenuVo menuVo, Integer userId) {
        return this.menuMapper.queryMenuByUid(SysConstant.AVAILABLE_TRUE,userId);
    }

    @Override
    public DataGridView queryAllMenu(MenuVo menuVo) {
        Page<Object> page= PageHelper.startPage(menuVo.getPage(),menuVo.getLimit());
        List<Menu> data = this.menuMapper.queryAllMenu(menuVo);
        if(page.getPages() < menuVo.getPage()){
            data.clear();
            page.setPageNum(page.getPages());
            data=this.menuMapper.queryAllMenu(menuVo);
        }
        return new DataGridView(page.getTotal(),data);
    }

    @Override
    public void addMenu(MenuVo menuVo) {
        this.menuMapper.insert(menuVo);
    }

    @Override
    public Integer queryMenuByPid(Integer id) {
        return this.menuMapper.queryMenuByPid(id);
    }

    @Override
    public void deleteMenu(MenuVo menuVo) {
        //删除菜单表数据
        this.menuMapper.deleteByPrimaryKey(menuVo.getId());

        //根据菜单id删除关系表的数据
        this.menuMapper.deleteRoleMenuByMid(menuVo.getId());
    }

    @Override
    public void updateMenu(MenuVo menuVo) {
        this.menuMapper.updateByPrimaryKeySelective(menuVo);
    }

    @Override
    public void deleteBatchMenu(Integer[] ids) {
        for (Integer id : ids) {
            MenuVo menuVo = new MenuVo();
            menuVo.setId(id);
            deleteMenu(menuVo);
        }
    }
}
