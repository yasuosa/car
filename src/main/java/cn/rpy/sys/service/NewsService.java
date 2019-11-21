package cn.rpy.sys.service;


import cn.rpy.sys.domain.News;
import cn.rpy.sys.utils.DataGridView;
import cn.rpy.sys.vo.NewsVo;

/**
 *  文章管理的服务接口
 */
public interface NewsService {


    /**
     * 查询所有可用文章，返回list集合
     */

    DataGridView queryAllNewsForList(NewsVo logInfoVo);




    void addNews(NewsVo NewsVo);

    /**
     * 根据id删除文章
     * @param logInfoId
     */
    void deleteNews(Integer logInfoId);

    /**
     * 批量删除文章
     * @param logInfoIds
     */
    void deleteBatchNews(Integer[] logInfoIds);

    /**
     * 加入文章
     * @param logInfoVo
     */
    void saverNews(NewsVo logInfoVo);


    /**
     * 更新文章
     */
    void updateNews(NewsVo newsVo);

    News queryById(Integer id);
}

