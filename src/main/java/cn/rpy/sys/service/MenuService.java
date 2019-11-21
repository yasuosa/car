package cn.rpy.sys.service;


import cn.rpy.sys.domain.Menu;
import cn.rpy.sys.utils.DataGridView;
import cn.rpy.sys.vo.MenuVo;

import java.util.List;

/**
 * 菜单管理的服务接口
 */
public interface MenuService {


    /**
     * 查询所有可用菜单，返回list集合
     */

    List<Menu> queryAllMenuForList(MenuVo menuVo);


    /**
     * 根据用户id查询用户的可用菜单
     */

    List<Menu> queryMenuByUserIdForList(MenuVo menuVo,Integer userId);


    DataGridView queryAllMenu(MenuVo menuVo);

    void addMenu(MenuVo menuVo);

    Integer queryMenuByPid(Integer id);


    void deleteMenu(MenuVo menuVo);

    void updateMenu(MenuVo menuVo);

    void deleteBatchMenu(Integer[] ids);
}
