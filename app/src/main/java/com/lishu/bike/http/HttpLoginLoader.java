package com.lishu.bike.http;

import com.alibaba.fastjson.JSONObject;
import com.lishu.bike.constant.HttpAddress;
import com.lishu.bike.model.BaseModel;
import com.lishu.bike.model.UserModel;
import com.lishu.bike.model.VerifyCodeModel;

import org.xutils.common.util.KeyValue;
import org.xutils.http.RequestParams;
import org.xutils.http.body.MultipartBody;
import java.util.ArrayList;
import java.util.List;

/**
 * 登录注册接口
 */
public class HttpLoginLoader extends HttpLoader {

    //登录
    public static void login(String phone, String password, IResponseListener listener) {
        JSONObject request = new JSONObject();
        request.put("phone", phone);
        request.put("password", password);

        RequestParams params = new RequestParams(HttpAddress.LOGIN);
        params.setAsJsonContent(true);
        List<KeyValue> list = new ArrayList<>();
        list.add(new KeyValue("params", request.toString()));
        MultipartBody body = new MultipartBody(list, "UTF-8");
        params.setRequestBody(body);

        doPost(params, UserModel.class, listener);
    }
    //获取验证码
    public static void checkCode(String phone, String type, IResponseListener listener) {
        JSONObject request = new JSONObject();
        request.put("phone", phone);

        RequestParams params = new RequestParams(HttpAddress.CHECK_CODE);
        params.setAsJsonContent(true);
        List<KeyValue> list = new ArrayList<>();
        list.add(new KeyValue("params", request.toString()));
        MultipartBody body = new MultipartBody(list, "UTF-8");
        params.setRequestBody(body);

        doPost(params, VerifyCodeModel.class, listener);
    }
    //注册
    public static void register(String phone, String password, String verifyCode,
                                String inviteCode, IResponseListener listener) {
        JSONObject request = new JSONObject();
        request.put("phone", phone);
        request.put("password", password);
        request.put("verifyCode", verifyCode);
        request.put("inviteCode", inviteCode);

        RequestParams params = new RequestParams(HttpAddress.REGISTER);
        params.setAsJsonContent(true);
        List<KeyValue> list = new ArrayList<>();
        list.add(new KeyValue("params", request.toString()));
        MultipartBody body = new MultipartBody(list, "UTF-8");
        params.setRequestBody(body);

        doPost(params, BaseModel.class, listener);
    }
    //忘记密码
    public static void forget(String phone, String newPassword, String verifyCode, IResponseListener listener) {
        JSONObject request = new JSONObject();
        request.put("phone", phone);
        request.put("newPassword", newPassword);
        request.put("verifyCode", verifyCode);

        RequestParams params = new RequestParams(HttpAddress.FORGET_PASSWORD);
        params.setAsJsonContent(true);
        List<KeyValue> list = new ArrayList<>();
        list.add(new KeyValue("params", request.toString()));
        MultipartBody body = new MultipartBody(list, "UTF-8");
        params.setRequestBody(body);

        doPost(params, BaseModel.class, listener);
    }
    //修改密码
    public static void updatePassword(String phone, String oldPassword, String newPassword, IResponseListener listener) {
        JSONObject request = new JSONObject();
        request.put("phone", phone);
        request.put("oldPassword", oldPassword);
        request.put("newPassword", newPassword);

        RequestParams params = new RequestParams(HttpAddress.UPDATE_PASSWORD);
        params.setAsJsonContent(true);
        List<KeyValue> list = new ArrayList<>();
        list.add(new KeyValue("params", request.toString()));
        MultipartBody body = new MultipartBody(list, "UTF-8");
        params.setRequestBody(body);

        doPost(params, BaseModel.class, listener);
    }
}
