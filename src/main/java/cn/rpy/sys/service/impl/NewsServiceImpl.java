package cn.rpy.sys.service.impl;

import cn.rpy.sys.domain.News;
import cn.rpy.sys.mapper.NewsMapper;
import cn.rpy.sys.service.NewsService;
import cn.rpy.sys.utils.DataGridView;
import cn.rpy.sys.vo.NewsVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NewsServiceImpl implements NewsService {


    @Autowired
    private NewsMapper newsMapper;

    @Override
    public DataGridView queryAllNewsForList(NewsVo newsVo) {
        Page<Object> page= PageHelper.startPage(newsVo.getPage(),newsVo.getLimit());
        List<News> data = newsMapper.queryList(newsVo);
        if(page.getPages() < newsVo.getPage()){
            page.setPageNum(page.getPages());
            data.clear();
            data= newsMapper.queryList(newsVo);
        }
        return new DataGridView(page.getTotal(),data);
    }

    @Override
    public void addNews(NewsVo NewsVo) {
        newsMapper.insert(NewsVo);
    }

    @Override
    public void deleteNews(Integer newsId) {
        newsMapper.deleteByPrimaryKey(newsId);
    }

    @Override
    public void deleteBatchNews(Integer[] newsIds) {
        for (Integer newsId : newsIds) {
            deleteNews(newsId);
        }
    }

    @Override
    public void saverNews(NewsVo newsVo) {
        this.newsMapper.insertSelective(newsVo);
    }

    @Override
    public void updateNews(NewsVo newsVo) {
        this.newsMapper.updateByPrimaryKeySelective(newsVo);
    }

    @Override
    public News queryById(Integer id) {
        return this.newsMapper.selectByPrimaryKey(id);
    }
}
