package com.lishu.bike.constant;

/**
 * 请求地址
 */
public class HttpAddress {
    public static String ROOT;

    static{
        if(AppConfig.IS_DEBUG){
            //测试地址
            ROOT = "http://153.37.97.170:9002/bicycle-jn";
        }else{
            //现网地址
            ROOT = "http://153.37.97.170:9002/bicycle-jn";
        }
    }

    /**
     * 登录与设置接口
     */
    //用户登录
    public static String LOGIN = ROOT + "/personInfoApp!login.action";
    //个人信息（同：通讯录详情）
    //public static String ADDRESS_BOOK_DETAIL = ROOT + "!xxx";
    //修改密码
    public static String CHANGE_PASSWORD = ROOT + "/personInfoApp!changePassword.action";
    //获取APP最新版本
    public static String LATEST_VERSION = ROOT + "/softApp!latestVersion.action";

    /**
     * 电子围栏接口
     */
    //电子围栏街道信息(同：街道信息列表)
    //public static String GET_STREETS = ROOT + "!XXX";
    //电子围栏列表
    public static String GET_FENCES = ROOT + "/deviceStationApp!getFences.action";
    //电子围栏详情
    public static String GET_FENCE_DETAIL = ROOT + "/deviceStationApp!getFenceDetail.action";
    //GIS地图 $$$$$$$$$$$$$$$$$
    public static String GET_GIS_MAP = ROOT + "/deviceStationApp!getGisMap.action";

    /**
     * 通讯录接口
     */
    //通讯录
    public static String ADDRESS_BOOKS = ROOT + "/personInfoApp!addressBooks.action";
    //通讯录详情
    public static String ADDRESS_BOOK_DETAIL = ROOT + "/personInfoApp!addressBookDetail.action";

    /**
     * 站点查询接口
     */
    //街道信息列表
    public static String GET_STREETS = ROOT + "/deviceStationApp!getStreets.action";
    //停车站点列表
    public static String GET_STATIONS = ROOT + "/deviceStationApp!getStations.action";
    //停车站点信息
    public static String GET_STATION_DETAIL = ROOT + "/deviceStationApp!getStationDetail.action";

    /**
     * 巡检信息接口
     */
    //巡检信息列表
    public static String GET_INSPECTIONS = ROOT + "/inspectionApp!getInspections.action";
    //巡检历史详情
    public static String GET_INSPECTIONS_DETAIL = ROOT + "/inspectionApp!getInspectDetail.action";
    //巡检上报违规类型
    public static String GET_INSPECTIONS_TYPES = ROOT + "/inspectionApp!getDictionaryTypes.action";
    //巡检上报
    public static String ADD_INSPECT = ROOT + "/inspectionApp!addInspect.action";
    //巡检图片上传
    public static String ADD_INSPECT_IMAGE = ROOT + "/inspectionApp!addInspectImage.action";

    /**
     * 任务单接口
     */
    //任务单列表
    public static String GET_TASKS = ROOT + "/taskInfoApp!getTasks.action";
    //任务单详情
    public static String GET_TASK_DETAIL = ROOT + "/taskInfoApp!getTaskDetail.action";
    //任务处理反馈 $$$$$$$$$$$$$$$$$
    public static String ADD_TASK_RESPONSE = ROOT + "/taskInfoApp!addTaskResponse.action";
    //任务处理图片上传
    public static String ADD_TASK_DISPOSE_IMAGE = ROOT + "/taskInfoApp!addTaskDisposeImage.action";

    /**
     * 工作动态接口
     */
    //工作动态列表
    public static String GET_APP_INFOS = ROOT + "/appInfoApp!getAppInfos.action";
    //工作动态详情
    public static String GET_APP_INFO_DETAIL = ROOT + "/appInfoApp!getAppInfoDetail.action";

    /**
     * 营运分析接口
     */
    //营运分析
    public static String GET_ANALYZES = ROOT + "/operationAnalysisApp!getAnalyzes.action";

    /**
     * 告警信息接口
     */
    //告警信息列表
    public static String GET_WARNINGS = ROOT + "/wsarnHistoryApp!getWarnings.action";
    //告警信息详情
    public static String GET_WARN_DETAIL = ROOT + "/wsarnHistoryApp!getWarnDetail.action";

}
