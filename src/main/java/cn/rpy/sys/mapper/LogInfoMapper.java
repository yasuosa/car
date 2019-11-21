package cn.rpy.sys.mapper;

import cn.rpy.sys.domain.LogInfo;
import cn.rpy.sys.vo.LogInfoVo;

import java.util.List;

public interface LogInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LogInfo record);

    int insertSelective(LogInfo record);

    LogInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LogInfo record);

    int updateByPrimaryKey(LogInfo record);

    /**
     * 查询日志
     *
     */

    List<LogInfo> queryAllLogInfo(LogInfoVo logInfoVo);
}