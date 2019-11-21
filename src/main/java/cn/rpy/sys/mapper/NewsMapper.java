package cn.rpy.sys.mapper;

import cn.rpy.sys.domain.News;
import cn.rpy.sys.vo.NewsVo;

import java.util.List;

public interface NewsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(News record);

    int insertSelective(News record);

    News selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKey(News record);

    List<News> queryList(NewsVo newsVo);
}