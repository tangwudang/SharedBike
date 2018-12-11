package com.lishu.bike.constant;

/**
 * 请求地址
 */
public class HttpAddress {
    public static String ROOT;
    public static String USER_ID = "?userId=" + "userId";

    static{
        if(AppConfig.IS_DEBUG){
            //测试地址
            //ROOT = "http://127.0.0.1:8080/cloudNotice";
        }else{
            //现网地址
            //ROOT = "http://127.0.0.1:8080/cloudNotice";
        }
    }

    /**
     * 登录与设置接口
     */
    //用户登录
    public static String LOGIN = ROOT + "/login";
    //个人信息（同：通讯录详情）
    //public static String ADDRESS_BOOK_DETAIL = ROOT + "/xxx";
    //修改密码
    public static String CHANGE_PASSWORD = ROOT + "/changePassword" + USER_ID;
    //获取APP最新版本
    public static String LATEST_VERSION = ROOT + "/latestVersion" + USER_ID;

    /**
     * 电子围栏接口
     */
    //电子围栏街道信息(同：街道信息列表)
    //public static String GET_STREETS = ROOT + "/XXX";
    //电子围栏列表
    public static String GET_FENCES = ROOT + "/getFences" + USER_ID;
    //电子围栏详情
    public static String GET_FENCE_DETAIL = ROOT + "/getFenceDetail" + USER_ID;

    /**
     * 通讯录接口
     */
    //通讯录
    public static String ADDRESS_BOOKS = ROOT + "/addressBooks" + USER_ID;
    //通讯录详情
    public static String ADDRESS_BOOK_DETAIL = ROOT + "/addressBookDetail" + USER_ID;

    /**
     * 站点查询接口
     */
    //街道信息列表
    public static String GET_STREETS = ROOT + "/getStreets" + USER_ID;
    //停车站点列表
    public static String GET_STATIONS = ROOT + "/getStations" + USER_ID;
    //停车站点信息
    public static String GET_STATION_DETAIL = ROOT + "/getStationDetail" + USER_ID;

    /**
     * 巡检信息接口
     */
    //巡检信息列表
    public static String GET_INSPECTIONS = ROOT + "/getInspections" + USER_ID;
    //巡检历史详情
    public static String GET_INSPECTIONS_DETAIL = ROOT + "/getInspectDetail" + USER_ID;
    //巡检上报违规类型
    public static String GET_INSPECTIONS_TYPES = ROOT + "/getDictionaryTypes" + USER_ID;
    //巡检上报
    public static String ADD_INSPECT = ROOT + "/addInspect" + USER_ID;
    //巡检图片上传
    public static String ADD_INSPECT_IMAGE = ROOT + "/addInspectImage" + USER_ID;

    /**
     * 任务单接口
     */
    //任务单列表
    public static String GET_TASKS = ROOT + "/getTasks" + USER_ID;
    //任务单详情
    public static String GET_TASK_DETAIL = ROOT + "/getTaskDetail" + USER_ID;
    //任务处理反馈
    public static String ADD_TASK_RESPONSE = ROOT + "/addTaskResponse" + USER_ID;
    //任务处理图片上传
    public static String ADD_TASK_DISPOSE_IMAGE = ROOT + "/addTaskDisposeImage" + USER_ID;

    /**
     * 工作动态接口
     */
    //工作动态列表
    public static String GET_APP_INFOS = ROOT + "/getAppInfos" + USER_ID;
    //工作动态详情
    public static String GET_APP_INFO_DETAIL = ROOT + "/getAppInfoDetail" + USER_ID;

    /**
     * 营运分析接口
     */
    //营运分析
    public static String GET_ANALYZES = ROOT + "/getAnalyzes" + USER_ID;

    /**
     * 告警信息接口
     */
    //告警信息列表
    public static String GET_WARNINGS = ROOT + "/getWarnings" + USER_ID;
    //告警信息详情
    public static String GET_WARN_DETAIL = ROOT + "/getWarnDetail" + USER_ID;
}
