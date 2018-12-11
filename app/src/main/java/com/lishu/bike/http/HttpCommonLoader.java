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
 * http普通接口
 */
public class HttpCommonLoader extends HttpLoader {

    //用户登录
    public static void login(String username, String password, IResponseListener listener) {
        JSONObject request = new JSONObject();
        request.put("username", username);
        request.put("password", password);

        RequestParams params = new RequestParams(HttpAddress.LOGIN);
        params.setAsJsonContent(true);
        List<KeyValue> list = new ArrayList<>();
        list.add(new KeyValue("params", request.toString()));
        MultipartBody body = new MultipartBody(list, "UTF-8");
        params.setRequestBody(body);

        doPost(params, UserModel.class, listener);
    }
    //个人信息
    public static void addressBookDetail(String phone, String type, IResponseListener listener) {
        JSONObject request = new JSONObject();
        request.put("userId", "");

        RequestParams params = new RequestParams(HttpAddress.ADDRESS_BOOK_DETAIL);
        params.setAsJsonContent(true);
        List<KeyValue> list = new ArrayList<>();
        list.add(new KeyValue("params", request.toString()));
        MultipartBody body = new MultipartBody(list, "UTF-8");
        params.setRequestBody(body);

        doPost(params, VerifyCodeModel.class, listener);
    }
    //修改密码
    public static void changePassword(String phone, String oldPassword, String newPassword, IResponseListener listener) {
        JSONObject request = new JSONObject();
        request.put("userId", "");
        request.put("oldPassword", oldPassword);
        request.put("newPassword", newPassword);

        RequestParams params = new RequestParams(HttpAddress.CHANGE_PASSWORD);
        params.setAsJsonContent(true);
        List<KeyValue> list = new ArrayList<>();
        list.add(new KeyValue("params", request.toString()));
        MultipartBody body = new MultipartBody(list, "UTF-8");
        params.setRequestBody(body);

        doPost(params, BaseModel.class, listener);
    }
}
