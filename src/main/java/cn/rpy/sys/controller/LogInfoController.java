package cn.rpy.sys.controller;


import cn.rpy.sys.domain.LogInfo;
import cn.rpy.sys.service.LogInfoService;
import cn.rpy.sys.utils.DataGridView;
import cn.rpy.sys.utils.ResultObj;
import cn.rpy.sys.vo.LogInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("logInfo")
public class LogInfoController {



    @Autowired
    private LogInfoService logInfoService;

    @RequestMapping("loadAllLogInfo")
    public DataGridView loadAllLogInfo(LogInfoVo logInfoVo){
        return  logInfoService.queryAllLogInfoForList(logInfoVo);
    }


    @RequestMapping("addLogInfo")
    public void  addLogInfo(LogInfoVo logInfoVo){
        logInfoService.addLogInfo(logInfoVo);
    }



    @RequestMapping("deleteLogInfo")
    public ResultObj  deleteLogInfo(Integer id){
        try {
            logInfoService.deleteLogInfo(id);
            return  ResultObj.DELETE_SUCCESS();
        } catch (Exception e) {
            return ResultObj.DELETE_ERROR();
        }
    }


    @RequestMapping("deleteBatchLogInfo")
    public ResultObj deleteBatchLogInfo(LogInfoVo logInfoVo){
        try {
            logInfoService.deleteBatchLogInfo(logInfoVo.getIds());
            return ResultObj.DELETE_SUCCESS();
        } catch (Exception e) {
            return ResultObj.DELETE_ERROR();
        }
    }
}
