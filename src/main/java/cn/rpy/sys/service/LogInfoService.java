package cn.rpy.sys.service;


import cn.rpy.sys.domain.LogInfo;
import cn.rpy.sys.utils.DataGridView;
import cn.rpy.sys.vo.LogInfoVo;

import java.util.List;

/**
 *  日志管理的服务接口
 */
public interface LogInfoService {


    /**
     * 查询所有可用日志，返回list集合
     */

    DataGridView queryAllLogInfoForList(LogInfoVo logInfoVo);




    void addLogInfo(LogInfoVo LogInfoVo);

    /**
     * 根据id删除日志
     * @param logInfoId
     */
    void deleteLogInfo(Integer logInfoId);

    /**
     * 批量删除日志
     * @param logInfoIds
     */
    void deleteBatchLogInfo(Integer[] logInfoIds);

    /**
     * 加入用户登陆信息
     * @param logInfoVo
     */
    void saverLogInfo(LogInfoVo logInfoVo);
}
