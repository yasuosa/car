package cn.rpy.sys.service.impl;

import cn.rpy.sys.domain.LogInfo;
import cn.rpy.sys.mapper.LogInfoMapper;
import cn.rpy.sys.service.LogInfoService;
import cn.rpy.sys.utils.DataGridView;
import cn.rpy.sys.vo.LogInfoVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LogInfoServiceImpl implements LogInfoService {


    @Autowired
    private LogInfoMapper logInfoMapper;

    @Override
    public DataGridView queryAllLogInfoForList(LogInfoVo logInfoVo) {
        Page<Object> page= PageHelper.startPage(logInfoVo.getPage(),logInfoVo.getLimit());
        List<LogInfo> data = logInfoMapper.queryAllLogInfo(logInfoVo);
        if(page.getPages() < logInfoVo.getPage()){
            page.setPageNum(page.getPages());
            data.clear();
            data= logInfoMapper.queryAllLogInfo(logInfoVo);
        }
        return new DataGridView(page.getTotal(),data);
    }

    @Override
    public void addLogInfo(LogInfoVo LogInfoVo) {
        logInfoMapper.insert(LogInfoVo);
    }

    @Override
    public void deleteLogInfo(Integer logInfoId) {
        logInfoMapper.deleteByPrimaryKey(logInfoId);
    }

    @Override
    public void deleteBatchLogInfo(Integer[] logInfoIds) {
        for (Integer logInfoId : logInfoIds) {
            deleteLogInfo(logInfoId);
        }
    }

    @Override
    public void saverLogInfo(LogInfoVo logInfoVo) {
        this.logInfoMapper.insertSelective(logInfoVo);
    }
}
