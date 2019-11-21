package cn.rpy.sys.constant;


/**
 * 常量接口
 */
public interface SysConstant {
    public static final String USER_LOGIN_ERROR_MSG = "用户名或密码不正确";


    /**
     * 可用状态
     */
    public static final Integer AVAILABLE_TRUE=1;
    public static final Integer AVAILABLE_FAIL=0;


    /**
     * 是否展开
     */
    public static final Integer SPEREAD_TRUE=1;
    public static final Integer SPEREAD_FAIL=0;


    /**
     * 超级管理员
     */
    public static final Integer USER_ADMIN_ROOT_TYPE=1;


    /**
     * 一般用户
     */
    public static final Integer USER_PERSON_ROOT_TYPE=2;


    /**
     * 操作状态
     */
    String ADD_SUCCESS="添加成功";
    String ADD_ERROR="添加失败";

    String UPDATE_SUCCESS="更新成功";
    String UPDATE_ERROR="更新失败";

    String DELETE_SUCCESS="删除成功";
    String DELETE_ERROR="删除失败";

    String RESET_SUCCESS="重置成功";
    String RESET_ERROR="重置失败";


    String DISPATCH_SUCCESS="分配成功";
    String DISPATCH_ERROR="分配失败";


    Integer CODE_SUCCESS=0;//操作成功
    Integer CODE_ERROR=-1;//操作失败


    Integer CODE_ONE =1 ;
    Integer CODE_ZERO =0 ;
    Integer CODE_TWO =2 ;
    Integer CODE_THREE =3 ;


    /**
     * 默认密码
     */
    String DEFAULT_PWD = "123456";
    Integer USER_TYPE_NORMAL = 2 ;
}
