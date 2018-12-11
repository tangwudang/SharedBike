package com.lishu.bike.http;

import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.lishu.bike.model.BaseModel;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public abstract class HttpLoader {

    public interface IResponseListener{
        public void onResponse(BaseModel model);
    }

    protected static void doPost(RequestParams params, final Class<?> cls, final IResponseListener listener) {
        Log.v("tag", "request url : " + params.getUri() + ", request params : " + params.getBodyParams());

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.v("tag", "doPost success response : " + result);
                if (listener != null) {
                    listener.onResponse(parseObject(result, cls));
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                String error = ex.getMessage();
                Log.v("tag", "doPost error errorMsg : " + error);
                if (listener != null) {
                    listener.onResponse(null);
                }
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }

    protected static void doGet(RequestParams params, final Class<?> cls, final IResponseListener listener) {
        Log.v("tag", "request url : " + params.getUri() + ", request params : " + params.getQueryStringParams());

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("tag", "doGet onSuccess result : " + result);
                if (listener != null) {
                    listener.onResponse(parseObject(result, cls));
                }
            }

            //请求异常后的回调方法
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                String error = ex.getMessage();
                Log.v("tag", "doGet error errorMsg : " + error);
                if (listener != null) {
                    listener.onResponse(null);
                }
            }

            //主动调用取消请求的回调方法
            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }

    private static BaseModel parseObject(String response, Class<?> cls){
        return (BaseModel) JSON.parseObject(response, cls);
    }
}
