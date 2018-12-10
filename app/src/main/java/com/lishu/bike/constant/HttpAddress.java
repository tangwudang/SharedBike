package com.lishu.bike.constant;

/**
 * 请求地址
 */
public class HttpAddress {
    public static String ROOT;

    static{
        if(AppConfig.IS_DEBUG){
            //测试地址
            //ROOT = "https://www.yunnotice.com/cloudNotice";
        }else{
            //现网地址
            //ROOT = "https://60.205.251.177/cloudNotice";
        }
    }

    /**
     * 用户信息接口
     */
    //登录
    public static String LOGIN = ROOT + "/outIn/userLogin.do";
    //注册
    public static String REGISTER = ROOT + "/outIn/userRegister.do";
    //获取验证码
    public static String CHECK_CODE = ROOT + "/outIn/userVerifyCode.do";
    //忘记密码
    public static String FORGET_PASSWORD = ROOT + "/outIn/userForgetPWD.do";
    //修改密码
    public static String UPDATE_PASSWORD = ROOT + "/outIn/userUpdatePWD.do";
    //修改用户信息
    public static String UPDATE_USER_INFO = ROOT + "/outIn/userUpdateInfo.do";

    /**
     * 通知模块接口
     */
    //发送通知
    public static String SEND_NOTICE = ROOT + "/outIn/sendNotice.do";
    //重发通知
    public static String RESEND_NOTICE = ROOT + "/outIn/sendNoticeAgain.do";
    //获取剩余次数
    public static String REMAIN_COUNT = ROOT + "/outIn/userNoticeCount.do";
    //查询公司
    public static String QUERY_COMPANY = ROOT + "/outIn/queryCompany.do";
    //提交通知模板
    public static String ADD_TEMPLATE = ROOT + "/outIn/addTemplate.do";
    //修改通知模板
    public static String MODIFY_TEMPLATE = ROOT + "/outIn/modifyTemplate.do";
    //删除通知模板
    public static String DELETE_TEMPLATE = ROOT + "/outIn/deleteTemplate.do";
    //查询模板审核状态
    public static String QUERY_TEMPLATE_STATE = ROOT + "/outIn/templateState.do";
    //查询通知列表
    public static String QUERY_NOTICE = ROOT + "/outIn/queryNotices.do";
    //查看通知详情
    public static String VIEW_NOTICE_DETAIL = ROOT + "/outIn/noticeInfo.do";

    /**
     * 业务模块接口
     */
    //获取套餐
    public static String GET_PACKAGES = ROOT + "/outIn/queryPackages.do";
    //购买套餐
    public static String BUY_PACKAGE = ROOT + "/outIn/buyPackage.do";
    //支付宝购买套餐
    public static String BUY_ALI = ROOT + "/outIn/buyByAlipay.do";
    //微信购买套餐
    public static String BUY_WX = ROOT + "/outIn/buyByWeChartInfo.do";

    /**
     * 我的模块接口
     */
    //支付宝充值
    public static String RECHARGE_WALLET = ROOT + "/outIn/addRechargeInfo.do";
    // 微信充值
    public static String RECHARGE_WX = ROOT + "/outIn/addWeChartInfo.do";
    //钱包明细
    public static String WALLET_DETAIL = ROOT + "/outIn/queryOrderInfo.do";
    //检查版本
    public static String CHECK_VERSION = ROOT + "/outIn/checkVersion.do";
    //关于我们
    public static String ABOUT_US = ROOT + "/outIn/aboutUs.do";
    //帮助
    public static String HELP = ROOT + "/outIn/useHelp.do";
    //意见反馈
    public static String FEEDBACK = ROOT + "/outIn/addComplaint.do";
    //用户协议说明
    public static String USER_PROTOCOL = ROOT + "/outIn/protocolDetail.do";
    //我的消息
    public static String MY_MSG = ROOT + "/outIn/myClientPush.do";
    //获取用户信息
    public static String GET_USER_INFO = ROOT + "/outIn/userGetInfo.do";
    //获取分享链接
    public static String GET_SHARE = ROOT + "/outIn/clientShare.do";

    /*
    * 上传图片*/
    public static String UPLOAD = ROOT + "/outIn/upload.do";
}
