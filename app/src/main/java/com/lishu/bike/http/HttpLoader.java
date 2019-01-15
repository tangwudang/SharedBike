package com.lishu.bike.http;

import com.lishu.bike.constant.HttpAddress;
import com.lishu.bike.constant.UserPreferences;
import com.lishu.bike.model.AddressBookDetailModel;
import com.lishu.bike.model.AddressBookModel;
import com.lishu.bike.model.AnalyzesModel;
import com.lishu.bike.model.AppInfoDetailModel;
import com.lishu.bike.model.AppInfoModel;
import com.lishu.bike.model.BaseModel;
import com.lishu.bike.model.FenceDetailModel;
import com.lishu.bike.model.FenceGISModel;
import com.lishu.bike.model.FenceModel;
import com.lishu.bike.model.InspectDetailModel;
import com.lishu.bike.model.InspectIdModel;
import com.lishu.bike.model.InspectImageModel;
import com.lishu.bike.model.InspectModel;
import com.lishu.bike.model.InspectTypeModel;
import com.lishu.bike.model.StationDetailModel;
import com.lishu.bike.model.StationModel;
import com.lishu.bike.model.StreetModel;
import com.lishu.bike.model.TaskDetailModel;
import com.lishu.bike.model.TaskIdModel;
import com.lishu.bike.model.TaskImageModel;
import com.lishu.bike.model.TaskModel;
import com.lishu.bike.model.UserModel;
import com.lishu.bike.model.VersionModel;
import com.lishu.bike.model.WarnDetailModel;
import com.lishu.bike.model.WarnModel;
import com.lishu.bike.utils.MD5;

import org.xutils.common.util.KeyValue;
import org.xutils.http.RequestParams;
import org.xutils.http.body.MultipartBody;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * http普通接口
 */
public class HttpLoader extends HttpBase {

    //---------------------------------- 登录与设置接口 ---------------------------------------------
    //用户登录
    public static void login(String username, String password, IResponseListener listener) {
        RequestParams params = new RequestParams(HttpAddress.LOGIN);
        params.addBodyParameter("username", username);
        params.addBodyParameter("password", password);

        doPost(params, UserModel.class, listener);
    }
    //个人信息（同：通讯录详情）
    //+++详见 addressBookDetail()+++

    //修改密码
    public static void changePassword(String oldPassword, String newPassword, IResponseListener listener) {
        RequestParams params = new RequestParams(HttpAddress.CHANGE_PASSWORD);
        params.addQueryStringParameter("userId", UserPreferences.getInstance().getUserId());
        params.addBodyParameter("oldPassword", new MD5().getMd5(oldPassword));
        params.addBodyParameter("newPassword", new MD5().getMd5(newPassword));

        doPost(params, BaseModel.class, listener);
    }
    //获取APP最新版本
    public static void latestVersion(String versionName, String versionCode, IResponseListener listener) {
        RequestParams params = new RequestParams(HttpAddress.LATEST_VERSION);
        params.addQueryStringParameter("userId", UserPreferences.getInstance().getUserId());
        params.addBodyParameter("versionName", "app");
        //params.addBodyParameter("versionNo", versionName);
        params.addBodyParameter("versionCode", versionCode);

        doPost(params, VersionModel.class, listener);
    }

    //---------------------------------- 电子围栏接口 -----------------------------------------------
    //电子围栏街道信息(同：街道信息列表)
    //+++详见 getStreets()+++

    //电子围栏列表
    public static void getFences(int streetId, IResponseListener listener) {
        RequestParams params = new RequestParams(HttpAddress.GET_FENCES);
        params.addQueryStringParameter("userId", UserPreferences.getInstance().getUserId());
        params.addBodyParameter("streetId", String.valueOf(streetId));

        doPost(params, FenceModel.class, listener);
    }
    //电子围栏详情
    public static void getFenceDetail(int fenceId, IResponseListener listener) {
        RequestParams params = new RequestParams(HttpAddress.GET_FENCE_DETAIL);
        params.addQueryStringParameter("userId", UserPreferences.getInstance().getUserId());
        params.addBodyParameter("fenceId", String.valueOf(fenceId));

        doPost(params, FenceDetailModel.class, listener);
    }
    //GIS地图详情
    public static void getGisMap(int fenceId, IResponseListener listener) {
        RequestParams params = new RequestParams(HttpAddress.GET_GIS_MAP);
        params.addQueryStringParameter("userId", UserPreferences.getInstance().getUserId());
        params.addBodyParameter("fenceId", String.valueOf(fenceId));

        doPost(params, FenceGISModel.class, listener);
    }

    //---------------------------------- 通讯录接口 ------------------------------------------------
    //通讯录
    public static void addressBooks(IResponseListener listener) {
        RequestParams params = new RequestParams(HttpAddress.ADDRESS_BOOKS);
        params.addQueryStringParameter("userId", UserPreferences.getInstance().getUserId());

        doPost(params, AddressBookModel.class, listener);
    }
    //通讯录详情
    public static void addressBookDetail(String contactId, IResponseListener listener) {
        RequestParams params = new RequestParams(HttpAddress.ADDRESS_BOOK_DETAIL);
        params.addQueryStringParameter("userId", contactId);

        doPost(params, AddressBookDetailModel.class, listener);
    }

    //---------------------------------- 站点查询接口 ----------------------------------------------
    //街道信息列表
    public static void getStreets(IResponseListener listener) {
        RequestParams params = new RequestParams(HttpAddress.GET_STREETS);
        params.addQueryStringParameter("userId", UserPreferences.getInstance().getUserId());

        doPost(params, StreetModel.class, listener);
    }
    //停车站点列表
    public static void getStations(int streetId, IResponseListener listener) {
        RequestParams params = new RequestParams(HttpAddress.GET_STATIONS);
        params.addQueryStringParameter("userId", UserPreferences.getInstance().getUserId());
        params.addBodyParameter("streetId", String.valueOf(streetId));

        doPost(params, StationModel.class, listener);
    }
    //停车站点信息
    public static void getStationDetail(int stationId, IResponseListener listener) {
        RequestParams params = new RequestParams(HttpAddress.GET_STATION_DETAIL);
        params.addQueryStringParameter("userId", UserPreferences.getInstance().getUserId());
        params.addBodyParameter("stationId", String.valueOf(stationId));

        doPost(params, StationDetailModel.class, listener);
    }

    //---------------------------------- 巡检信息接口 ----------------------------------------------
    //巡检信息列表
    public static void getInspections(String startTime, String endTime, int appPageNO,
                                      int appPageCount, IResponseListener listener) {
        RequestParams params = new RequestParams(HttpAddress.GET_INSPECTIONS);
        params.addQueryStringParameter("userId", UserPreferences.getInstance().getUserId());
        params.addBodyParameter("startTime", startTime);
        params.addBodyParameter("endTime", endTime);
        params.addBodyParameter("appPageNO", String.valueOf(appPageNO));
        params.addBodyParameter("appPageCount", String.valueOf(appPageCount));

        doPost(params, InspectModel.class, listener);
    }
    //巡检历史详情
    public static void getInspectDetail(int inspectId, IResponseListener listener) {
        RequestParams params = new RequestParams(HttpAddress.GET_INSPECTIONS_DETAIL);
        params.addQueryStringParameter("userId", UserPreferences.getInstance().getUserId());
        params.addBodyParameter("inspectId", String.valueOf(inspectId));

        doPost(params, InspectDetailModel.class, listener);
    }
    //巡检上报违规类型
    public static void getDictionaryTypes(IResponseListener listener) {
        RequestParams params = new RequestParams(HttpAddress.GET_INSPECTIONS_TYPES);
        params.addQueryStringParameter("userId", UserPreferences.getInstance().getUserId());

        doPost(params, InspectTypeModel.class, listener);
    }
    //巡检上报
    public static void addInspect(int mobike, int ofobike, int hellobike, int dictionaryTypeId,
                                  String remark, String inspectAddress, String longitude, String latitude,
                                  String inspectTime, String inspectImageName, IResponseListener listener) {
        RequestParams params = new RequestParams(HttpAddress.ADD_INSPECT);
        params.addQueryStringParameter("userId", UserPreferences.getInstance().getUserId());
        params.addBodyParameter("mobike", String.valueOf(mobike));
        params.addBodyParameter("ofobike", String.valueOf(ofobike));
        params.addBodyParameter("hellobike", String.valueOf(hellobike));
        params.addBodyParameter("dictionaryTypeId", String.valueOf(dictionaryTypeId));
        params.addBodyParameter("remark", remark);
        params.addBodyParameter("inspectAddress", inspectAddress);
        params.addBodyParameter("longitude", longitude);
        params.addBodyParameter("latitude", latitude);
        params.addBodyParameter("inspectTime", inspectTime);
        params.addBodyParameter("inspectImageName", inspectImageName);

        doPost(params, InspectIdModel.class, listener);
    }
    //巡检图片上传
    public static void addInspectImage(String filePath, IResponseListener listener) {
        RequestParams params = new RequestParams(HttpAddress.ADD_INSPECT_IMAGE);
        params.addQueryStringParameter("userId", UserPreferences.getInstance().getUserId());
        //params.addBodyParameter("inspectImage", new File(filePath));

        List<KeyValue> list = new ArrayList<>();
        File imageFile = new File(filePath);
        if (imageFile.exists()) {
            list.add(new KeyValue("inspectImage", imageFile));
        }
        MultipartBody body = new MultipartBody(list, "UTF-8");
        params.setRequestBody(body);

        doPost(params, InspectImageModel.class, listener);
    }

    //---------------------------------- 任务单接口 ----------------------------------------------
    //任务单列表
    public static void getTasks(String startTime, String endTime, int appPageNO,
                                int appPageCount, IResponseListener listener) {
        RequestParams params = new RequestParams(HttpAddress.GET_TASKS);
        params.addQueryStringParameter("userId", UserPreferences.getInstance().getUserId());
        params.addBodyParameter("startTime", startTime);
        params.addBodyParameter("endTime", endTime);
        params.addBodyParameter("appPageNO", String.valueOf(appPageNO));
        params.addBodyParameter("appPageCount", String.valueOf(appPageCount));

        doPost(params, TaskModel.class, listener);
    }
    //任务单详情
    public static void getTaskDetail(int taskId, IResponseListener listener) {
        RequestParams params = new RequestParams(HttpAddress.GET_TASK_DETAIL);
        params.addQueryStringParameter("userId", UserPreferences.getInstance().getUserId());
        params.addBodyParameter("taskId",  String.valueOf(taskId));

        doPost(params, TaskDetailModel.class, listener);
    }
    //任务处理反馈
    public static void addTaskResponse(int taskId, String taskContent, String taskImageName, IResponseListener listener) {
        RequestParams params = new RequestParams(HttpAddress.ADD_TASK_RESPONSE);
        params.addQueryStringParameter("userId", UserPreferences.getInstance().getUserId());
        params.addBodyParameter("taskId",  String.valueOf(taskId));
        params.addBodyParameter("taskResponseContent", taskContent);
        params.addBodyParameter("taskResponseImageName", taskImageName);

        doPost(params, TaskIdModel.class, listener);
    }
    //任务处理图片上传
    public static void addTaskDisposeImage(String filePath, IResponseListener listener) {
        RequestParams params = new RequestParams(HttpAddress.ADD_TASK_DISPOSE_IMAGE);
        params.addQueryStringParameter("userId", UserPreferences.getInstance().getUserId());
        //params.addBodyParameter("taskResponseImage", new File(filePath));

        List<KeyValue> list = new ArrayList<>();
        File imageFile = new File(filePath);
        if (imageFile.exists()) {
            list.add(new KeyValue("taskResponseImage", imageFile));
        }
        MultipartBody body = new MultipartBody(list, "UTF-8");
        params.setRequestBody(body);

        doPost(params, TaskImageModel.class, listener);
    }

    //---------------------------------- 工作动态接口 ----------------------------------------------
    //工作动态列表
    public static void getAppInfos(int appPageNO, int appPageCount, IResponseListener listener) {
        RequestParams params = new RequestParams(HttpAddress.GET_APP_INFOS);
        params.addQueryStringParameter("userId", UserPreferences.getInstance().getUserId());
        params.addBodyParameter("appPageNO", String.valueOf(appPageNO));
        params.addBodyParameter("appPageCount", String.valueOf(appPageCount));

        doPost(params, AppInfoModel.class, listener);
    }
    //工作动态详情
    public static void getAppInfoDetail(int infoId, IResponseListener listener) {
        RequestParams params = new RequestParams(HttpAddress.GET_APP_INFO_DETAIL);
        params.addQueryStringParameter("userId", UserPreferences.getInstance().getUserId());
        params.addBodyParameter("infoId", String.valueOf(infoId));

        doPost(params, AppInfoDetailModel.class, listener);
    }

    //---------------------------------- 营运分析接口 ----------------------------------------------
    //营运分析
    public static void getAnalyzes(int type, String time, IResponseListener listener) {
        RequestParams params = new RequestParams(HttpAddress.GET_ANALYZES);
        params.addQueryStringParameter("userId", UserPreferences.getInstance().getUserId());
        params.addBodyParameter("type", String.valueOf(type));
        params.addBodyParameter("time", time);

        doPost(params, AnalyzesModel.class, listener);
    }

    //---------------------------------- 告警信息接口 ----------------------------------------------
    //告警信息列表
    public static void getWarnings(String startTime, String endTime, int appPageNO,
                                   int appPageCount, IResponseListener listener) {
        RequestParams params = new RequestParams(HttpAddress.GET_WARNINGS);
        params.addQueryStringParameter("userId", UserPreferences.getInstance().getUserId());
        params.addBodyParameter("startTime", startTime);
        params.addBodyParameter("endTime", endTime);
        params.addBodyParameter("appPageNO", String.valueOf(appPageNO));
        params.addBodyParameter("appPageCount", String.valueOf(appPageCount));

        doPost(params, WarnModel.class, listener);
    }
    //告警信息详情
    public static void getWarnDetail(int warnId, IResponseListener listener) {
        RequestParams params = new RequestParams(HttpAddress.GET_WARN_DETAIL);
        params.addQueryStringParameter("userId", UserPreferences.getInstance().getUserId());
        params.addBodyParameter("warnId", String.valueOf(warnId));

        doPost(params, WarnDetailModel.class, listener);
    }
}
