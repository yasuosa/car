package cn.rpy.sys.utils;

import cn.rpy.sys.constant.SysConstant;

public class ResultObj {
    
    private Integer code=0;
    private String msg;

    private ResultObj(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private ResultObj(Integer code) {
        this.code = code;
    }


    /**
     * 状态码1
     * @return
     */
    public static ResultObj STATUS_TRUE(){
        return new ResultObj(SysConstant.CODE_SUCCESS);
    }


    /**
     * 状态码1
     * @return
     */
    public static ResultObj STATUS_FALSE(){
        return new ResultObj(SysConstant.CODE_ERROR);
    }





    /**
     * 添加成功
     * @return
     */
    public static ResultObj ADD_SUCCESS(){
        return new ResultObj(SysConstant.CODE_SUCCESS,SysConstant.ADD_SUCCESS);
    }

    /**
     * 添加失败
     * @return
     */
    public static ResultObj ADD_ERROR(){
        return new ResultObj(SysConstant.CODE_ERROR,SysConstant.ADD_ERROR);
    }

    /**
     * 更新成功
     * @return
     */
    public static ResultObj UPDATE_SUCCESS(){
        return new ResultObj(SysConstant.CODE_SUCCESS,SysConstant.UPDATE_SUCCESS);
    }

    /**
     * 更新失败
     * @return
     */
    public static ResultObj UPDATE_ERROR(){
        return new ResultObj(SysConstant.CODE_ERROR,SysConstant.UPDATE_ERROR);
    }

    /**
     * 删除成功
     * @return
     */
    public static ResultObj DELETE_SUCCESS(){
        return new ResultObj(SysConstant.CODE_SUCCESS,SysConstant.DELETE_SUCCESS);
    }

    /**
     * 删除失败
     * @return
     */
    public static ResultObj DELETE_ERROR(){
        return new ResultObj(SysConstant.CODE_ERROR,SysConstant.DELETE_ERROR);
    }

    /**
     * 重置成功
     * @return
     */
    public static ResultObj RESET_SUCCESS(){
        return new ResultObj(SysConstant.CODE_SUCCESS,SysConstant.RESET_SUCCESS);
    }

    /**
     * 充值失败
     * @return
     */
    public static ResultObj RESET_ERROR(){
        return new ResultObj(SysConstant.CODE_ERROR,SysConstant.RESET_ERROR);
    }


    /**
     * 分配成功
     * @return
     */
    public static ResultObj DISPATCH_SUCCESS(){
        return new ResultObj(SysConstant.CODE_SUCCESS,SysConstant.DISPATCH_SUCCESS);
    }

    /**
     * 分配失败
     * @return
     */
    public static ResultObj DISPATCH_ERROR(){
        return new ResultObj(SysConstant.CODE_ERROR,SysConstant.DISPATCH_ERROR);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
