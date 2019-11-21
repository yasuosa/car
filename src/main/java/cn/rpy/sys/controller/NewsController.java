package cn.rpy.sys.controller;


import cn.rpy.sys.domain.News;
import cn.rpy.sys.domain.User;
import cn.rpy.sys.service.NewsService;
import cn.rpy.sys.utils.DataGridView;
import cn.rpy.sys.utils.ResultObj;
import cn.rpy.sys.utils.WebUtils;
import cn.rpy.sys.vo.NewsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("news")
public class NewsController {



    @Autowired
    private NewsService newsService;

    @RequestMapping("loadAllNews")
    public DataGridView loadAllNews(NewsVo newsVo){
        return  newsService.queryAllNewsForList(newsVo);
    }


    @RequestMapping("addNews")
    public ResultObj  addNews(NewsVo newsVo){
        try {
            User user= (User) WebUtils.getHttpSession().getAttribute("user");
            newsVo.setOpername(user.getRealname());
            newsVo.setCreatetime(new Date());
            newsService.addNews(newsVo);
            return ResultObj.ADD_SUCCESS();
        } catch (Exception e) {
            return ResultObj.ADD_ERROR();
        }
    }

    @RequestMapping("updateNews")
    public ResultObj  updateNews(NewsVo newsVo){
        try {
            newsService.updateNews(newsVo);
            return ResultObj.DELETE_SUCCESS();
        } catch (Exception e) {
            return ResultObj.UPDATE_ERROR();
        }
    }






    @RequestMapping("deleteNews")
    public ResultObj  deleteNews(Integer id){
        try {
            newsService.deleteNews(id);
            return  ResultObj.DELETE_SUCCESS();
        } catch (Exception e) {
            return ResultObj.DELETE_ERROR();
        }
    }


    @RequestMapping("deleteBatchNews")
    public ResultObj deleteBatchNews(NewsVo newsVo){
        try {
            newsVo.setCreatetime(new Date());
            newsService.deleteBatchNews(newsVo.getIds());
            return ResultObj.DELETE_SUCCESS();
        } catch (Exception e) {
            return ResultObj.DELETE_ERROR();
        }
    }

    @RequestMapping("loadNewsById")
    public News loadNewsById(Integer id){
        return this.newsService.queryById(id);
    }
}
