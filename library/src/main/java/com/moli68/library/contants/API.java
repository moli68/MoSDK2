package com.moli68.library.contants;

public interface API {

    /**
     * 通用接口
     */
      public static String COMMON_URL = "http://ap.moli68.com/app/";//正式接口
    // public static String COMMON_URL = "http://test.moli68.com/app/";//测试接口

    /**
     * 设备注册
     */
    public static String REGIST_DEVICE = "reg_device";

    /**
     *  更新数据
     */
    public static String UPDATE = "info_sync";

    /**
     * 新版检测
     */
    public static String GETNEW = "version_update";

    /**
     *意见反馈
     */
    public static String FEEDBACK = "msgbug";


    /**
     * 获取反馈建议接口
     */
    public static String GETFEEDBACK = "get_msgbug";

    /**
     * 老订单接口(支付宝，微信)
     */
    public static String ORDER_ONE = "payorder";

    /**
     * 获取帮助文档
     *
     */
    public static String GET_DOC = "get_help";

    /**
     * 发送短信
     */
    public static String SEND_SMS = "sendsms";

    /**
     * 短信登录
     */
    public static String SMS_LOGIN = "smslogin";

    /**
    * 微信登录
    */
    public static String WC_LOGIN = "wechat.login";






}
