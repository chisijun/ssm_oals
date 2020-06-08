package org.study.oals.config;

/**
 * @Description: 存放静态变量
 */
public class Constants {
    /**
     * 存储当前登录用户id的字段名
     */
    public static final String CURRENT_USER_ID = "CURRENT_USER_ID";

    /**
     * token有效期（小时）
     */
    public static final int TOKEN_EXPIRES_HOUR = 72;
    /**
     * 存放Authorization的header字段
     */
    public static final String AUTHORIZATION = "authorization";

    /**
     * 管理员Code
     */
    public static final String ADMIN = "admin";

    /**
     * 用户Code
     */
    public static final String USER = "user";

    /**
     * 用户信息
     */
    public static final String USERMSG = "userMsg";

    /**
     * 认证审核 0-待审核
     */
    public static final Integer PASS_APPLY = 0;


    /**
     * 认证审核 0-审核通过
     */
    public static final Integer PASS_YES = 1;

    /**
     * 认证审核 0-审核不通过
     */
    public static final Integer PASS_NO = 2;

    /**
     * 任务 - 待发布
     */
    public static final Integer TASK_WAIT = 0;

    /**
     * 任务 - 已发布
     */
    public static final Integer TASK_APPLY = 1;

    /**
     * 任务 - 已接收
     */
    public static final Integer TASK_RECV = 2;

    /**
     * 任务 - 已完成
     */
    public static final Integer TASK_FISH = 3;

    /**
     * 任务 - 确认完成
     */
    public static final Integer TASK_CHECK = 4;

    /**
     * 账单类型 - 充值
     */
    public static final Integer ORDER_IN = 0;

    /**
     * 账单类型 - 消费
     */
    public static final Integer ORDER_XF = 1;

    /**
     * 账单类型 - 收入
     */
    public static final Integer ORDER_SR = 2;

    /**
     * 账单类型 - 体现
     */
    public static final Integer ORDER_OUT = 3;

}
