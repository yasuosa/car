package cn.rpy.sys.mapper;

import cn.rpy.sys.domain.Menu;
import cn.rpy.sys.vo.MenuVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper {


    /**
     * 查询所有
     *
     * @return
     */

    List<Menu> queryAllMenu(Menu menu);


    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    Integer queryMenuByPid(Integer pid);

    void deleteRoleMenuByMid(Integer mid);

    /**
     * 根据角色id查询菜单
     *
     * @param availableTrue
     * @param roleid
     * @return
     */
    List<Menu> queryMenuByRoleId(@Param("available") Integer availableTrue, @Param("roleid") Integer roleid);

    List<Menu> queryMenuByUid(@Param("available") Integer availableTrue, @Param("userId") Integer userId);
}