package com.lishu.bike.constant;

/**
 * 请求地址
 */
public class HttpAddress {
    public static String ROOT;

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
    //public static String  = ROOT + "/xxx";
    //修改密码
    public static String CHANGE_PASSWORD = ROOT + "/changePassword";
    //获取APP最新版本
    public static String LATEST_VERSION = ROOT + "/latestVersion";

    /**
     * 电子围栏接口
     */
    //电子围栏街道信息(同：街道信息列表)
    //public static String  = ROOT + "/XXX";
    //电子围栏列表
    public static String GET_FENCES = ROOT + "/getFences";
    //电子围栏详情
    public static String GET_FENCE_DETAIL = ROOT + "/getFenceDetail";

    /**
     * 通讯录接口
     */
    //通讯录
    public static String ADDRESS_BOOKS = ROOT + "/addressBooks";
    //通讯录详情
    public static String ADDRESS_BOOK_DETAIL = ROOT + "/addressBookDetail";

    /**
     * 站点查询接口
     */
    //街道信息列表
    public static String GET_STREETS = ROOT + "/getStreets";
    //停车站点列表
    public static String GET_STATIONS = ROOT + "/getStations";
    //停车站点信息
    public static String GET_STATION_DETAIL = ROOT + "/getStationDetail";

    /**
     * 巡检信息接口
     */
    //巡检信息列表
    public static String GET_INSPECTIONS = ROOT + "/getInspections";
    //巡检历史详情
    public static String GET_INSPECTIONS_DETAIL = ROOT + "/getInspectDetail";
    //巡检上报违规类型
    public static String GET_INSPECTIONS_TYPES = ROOT + "/getDictionaryTypes";
    //巡检上报
    public static String ADD_INSPECT = ROOT + "/addInspect";
    //巡检图片上传
    public static String ADD_INSPECT_IMAGE = ROOT + "/addInspectImage";

    /**
     * 任务单接口
     */
    //任务单列表
    public static String GET_TASKS = ROOT + "/getTasks";
    //任务单详情
    public static String GET_TASK_DETAIL = ROOT + "/getTaskDetail";
    //任务处理反馈
    public static String ADD_TASK_RESPOSE = ROOT + "/addTaskResponse";
    //任务处理图片上传
    public static String ADD_TASK_DISPOSE_IMAGE = ROOT + "/addTaskDisposeImage";

    /**
     * 工作动态接口
     */
    //工作动态列表
    public static String GET_APP_INFOS = ROOT + "/getAppInfos";
    // 工作动态详情
    public static String GET_APP_INFO_DETAIL = ROOT + "/getAppInfoDetail";

    /**
     * 营运分析接口
     */
    //营运分析
    public static String GET_ANALYZES = ROOT + "/getAnalyzes";

    /**
     * 告警信息接口
     */
    //告警信息列表
    public static String GET_WARNINGS = ROOT + "/getWarnings";
    //告警信息详情
    public static String GET_WARN_DETAIL = ROOT + "/getWarnDetail";
}
