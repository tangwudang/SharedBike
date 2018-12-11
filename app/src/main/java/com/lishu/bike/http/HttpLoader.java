package com.lishu.bike.http;

import com.alibaba.fastjson.JSONObject;
import com.lishu.bike.constant.HttpAddress;
import com.lishu.bike.model.AddressBookDetailModel;
import com.lishu.bike.model.AddressBookModel;
import com.lishu.bike.model.AnalyzesModel;
import com.lishu.bike.model.AppInfoDetailModel;
import com.lishu.bike.model.AppInfoModel;
import com.lishu.bike.model.BaseModel;
import com.lishu.bike.model.FenceDetailModel;
import com.lishu.bike.model.FenceModel;
import com.lishu.bike.model.InspectDetailModel;
import com.lishu.bike.model.InspectIdModel;
import com.lishu.bike.model.InspectModel;
import com.lishu.bike.model.InspectTypeModel;
import com.lishu.bike.model.StationDetailModel;
import com.lishu.bike.model.StationModel;
import com.lishu.bike.model.StreetModel;
import com.lishu.bike.model.TaskDetailModel;
import com.lishu.bike.model.TaskIdModel;
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
        JSONObject request = new JSONObject();
        request.put("username", username);
        request.put("password", new MD5().getMd5(password));
        RequestParams params = getParams(request, HttpAddress.LOGIN);

        doPost(params, UserModel.class, listener);
    }
    //个人信息（同：通讯录详情）
    //+++详见 addressBookDetail()+++

    //修改密码
    public static void changePassword(String oldPassword, String newPassword, IResponseListener listener) {
        JSONObject request = new JSONObject();
        request.put("oldPassword", new MD5().getMd5(oldPassword));
        request.put("newPassword", new MD5().getMd5(newPassword));
        RequestParams params = getParams(request, HttpAddress.CHANGE_PASSWORD);

        doPost(params, BaseModel.class, listener);
    }
    //获取APP最新版本
    public static void latestVersion(String versionName, String versionCode, IResponseListener listener) {
        JSONObject request = new JSONObject();
        request.put("versionName", versionName);
        request.put("versionCode", versionCode);
        RequestParams params = getParams(request, HttpAddress.LATEST_VERSION);

        doPost(params, VersionModel.class, listener);
    }

    //---------------------------------- 电子围栏接口 -----------------------------------------------
    //电子围栏街道信息(同：街道信息列表)
    //+++详见 getStreets()+++

    //电子围栏列表
    public static void getFences(int streetId, IResponseListener listener) {
        JSONObject request = new JSONObject();
        request.put("streetId", streetId);
        RequestParams params = getParams(request, HttpAddress.GET_FENCES);

        doPost(params, FenceModel.class, listener);
    }
    //电子围栏详情
    public static void getFenceDetail(int fenceId, IResponseListener listener) {
        JSONObject request = new JSONObject();
        request.put("fenceId", fenceId);
        RequestParams params = getParams(request, HttpAddress.GET_FENCE_DETAIL);

        doPost(params, FenceDetailModel.class, listener);
    }

    //---------------------------------- 通讯录接口 ------------------------------------------------
    //通讯录
    public static void addressBooks(IResponseListener listener) {
        JSONObject request = new JSONObject();
        RequestParams params = getParams(request, HttpAddress.ADDRESS_BOOKS);

        doPost(params, AddressBookModel.class, listener);
    }
    //通讯录详情
    public static void addressBookDetail(IResponseListener listener) {
        JSONObject request = new JSONObject();
        RequestParams params = getParams(request, HttpAddress.ADDRESS_BOOK_DETAIL);

        doPost(params, AddressBookDetailModel.class, listener);
    }

    //---------------------------------- 站点查询接口 ----------------------------------------------
    //街道信息列表
    public static void getStreets(IResponseListener listener) {
        JSONObject request = new JSONObject();
        RequestParams params = getParams(request, HttpAddress.GET_STREETS);

        doPost(params, StreetModel.class, listener);
    }
    //停车站点列表
    public static void getStations(int streetId, IResponseListener listener) {
        JSONObject request = new JSONObject();
        request.put("streetId", streetId);
        RequestParams params = getParams(request, HttpAddress.GET_STATIONS);

        doPost(params, StationModel.class, listener);
    }
    //停车站点信息
    public static void getStationDetail(int stationId, IResponseListener listener) {
        JSONObject request = new JSONObject();
        request.put("stationId", stationId);
        RequestParams params = getParams(request, HttpAddress.GET_STATION_DETAIL);

        doPost(params, StationDetailModel.class, listener);
    }

    //---------------------------------- 巡检信息接口 ----------------------------------------------
    //巡检信息列表
    public static void getInspections(String startTime, String endTime, int appPageNO,
                                      int appPageCount, IResponseListener listener) {
        JSONObject request = new JSONObject();
        request.put("startTime", startTime);
        request.put("endTime", endTime);
        request.put("appPageNO", appPageNO);
        request.put("appPageCount", appPageCount);
        RequestParams params = getParams(request, HttpAddress.GET_INSPECTIONS);

        doPost(params, InspectModel.class, listener);
    }
    //巡检历史详情
    public static void getInspectDetail(int inspectId, IResponseListener listener) {
        JSONObject request = new JSONObject();
        request.put("inspectId", inspectId);
        RequestParams params = getParams(request, HttpAddress.GET_INSPECTIONS_DETAIL);

        doPost(params, InspectDetailModel.class, listener);
    }
    //巡检上报违规类型
    public static void getDictionaryTypes(IResponseListener listener) {
        JSONObject request = new JSONObject();
        RequestParams params = getParams(request, HttpAddress.GET_INSPECTIONS_TYPES);

        doPost(params, InspectTypeModel.class, listener);
    }
    //巡检上报
    public static void addInspect(int mobike, int ofobike, int hellobike, int dictionaryTypeId,
                                  String remark, String inspectAddress, String inspectTime, IResponseListener listener) {
        JSONObject request = new JSONObject();
        request.put("mobike", mobike);
        request.put("ofobike", ofobike);
        request.put("hellobike", hellobike);
        request.put("dictionaryTypeId", dictionaryTypeId);
        request.put("remark", remark);
        request.put("inspectAddress", inspectAddress);
        request.put("inspectTime", inspectTime);
        RequestParams params = getParams(request, HttpAddress.ADD_INSPECT);

        doPost(params, InspectIdModel.class, listener);
    }
    //巡检图片上传
    public static void addInspectImage(int inspectId, String filePath, IResponseListener listener) {
        JSONObject request = new JSONObject();
        request.put("inspectId", inspectId);

        RequestParams params = new RequestParams(HttpAddress.ADD_INSPECT_IMAGE);
        params.setAsJsonContent(true);
        List<KeyValue> list = new ArrayList<>();
        list.add(new KeyValue("params", request.toString()));
        File avatarImg = new File(filePath);
        if (avatarImg.exists()) {
            list.add(new KeyValue("inspectImage", avatarImg));
        }
        MultipartBody body = new MultipartBody(list, "UTF-8");
        params.setRequestBody(body);

        doPost(params, BaseModel.class, listener);
    }

    //---------------------------------- 任务单接口 ----------------------------------------------
    //任务单列表
    public static void getTasks(String startTime, String endTime, int appPageNO,
                                int appPageCount, IResponseListener listener) {
        JSONObject request = new JSONObject();
        request.put("startTime", startTime);
        request.put("endTime", endTime);
        request.put("appPageNO", appPageNO);
        request.put("appPageCount", appPageCount);
        RequestParams params = getParams(request, HttpAddress.GET_TASKS);

        doPost(params, TaskModel.class, listener);
    }
    //任务单详情
    public static void getTaskDetail(int taskId, IResponseListener listener) {
        JSONObject request = new JSONObject();
        request.put("taskId", taskId);
        RequestParams params = getParams(request, HttpAddress.GET_TASK_DETAIL);

        doPost(params, TaskDetailModel.class, listener);
    }
    //任务处理反馈
    public static void addTaskResponse(int taskId, String taskContent, IResponseListener listener) {
        JSONObject request = new JSONObject();
        request.put("taskId", taskId);
        request.put("taskContent", taskContent);
        RequestParams params = getParams(request, HttpAddress.ADD_TASK_RESPONSE);

        doPost(params, TaskIdModel.class, listener);
    }
    //任务处理图片上传
    public static void addTaskDisposeImage(int taskResponseId, String filePath, IResponseListener listener) {
        JSONObject request = new JSONObject();
        request.put("TaskResponseId", taskResponseId);

        RequestParams params = new RequestParams(HttpAddress.ADD_INSPECT_IMAGE);
        params.setAsJsonContent(true);
        List<KeyValue> list = new ArrayList<>();
        list.add(new KeyValue("params", request.toString()));
        File avatarImg = new File(filePath);
        if (avatarImg.exists()) {
            list.add(new KeyValue("TaskResponseImage", avatarImg));
        }
        MultipartBody body = new MultipartBody(list, "UTF-8");
        params.setRequestBody(body);

        doPost(params, BaseModel.class, listener);
    }

    //---------------------------------- 工作动态接口 ----------------------------------------------
    //工作动态列表
    public static void getAppInfos(int appPageNO, int appPageCount, IResponseListener listener) {
        JSONObject request = new JSONObject();
        request.put("appPageNO", appPageNO);
        request.put("appPageCount", appPageCount);
        RequestParams params = getParams(request, HttpAddress.GET_APP_INFOS);

        doPost(params, AppInfoModel.class, listener);
    }
    //工作动态详情
    public static void getAppInfoDetail(int infoId, IResponseListener listener) {
        JSONObject request = new JSONObject();
        request.put("infoId", infoId);
        RequestParams params = getParams(request, HttpAddress.GET_APP_INFO_DETAIL);

        doPost(params, AppInfoDetailModel.class, listener);
    }

    //---------------------------------- 营运分析接口 ----------------------------------------------
    //营运分析
    public static void getAnalyzes(int type, String time, IResponseListener listener) {
        JSONObject request = new JSONObject();
        request.put("type", type);
        request.put("time", time);
        RequestParams params = getParams(request, HttpAddress.GET_ANALYZES);

        doPost(params, AnalyzesModel.class, listener);
    }

    //---------------------------------- 告警信息接口 ----------------------------------------------
    //告警信息列表
    public static void getWarnings(String startTime, String endTime, int appPageNO,
                                   int appPageCount, IResponseListener listener) {
        JSONObject request = new JSONObject();
        request.put("startTime", startTime);
        request.put("endTime", endTime);
        request.put("appPageNO", appPageNO);
        request.put("appPageCount", appPageCount);
        RequestParams params = getParams(request, HttpAddress.GET_WARNINGS);

        doPost(params, WarnModel.class, listener);
    }
    //告警信息详情
    public static void getWarnDetail(int warnId, IResponseListener listener) {
        JSONObject request = new JSONObject();
        request.put("warnId", warnId);
        RequestParams params = getParams(request, HttpAddress.GET_WARN_DETAIL);

        doPost(params, WarnDetailModel.class, listener);
    }
}
