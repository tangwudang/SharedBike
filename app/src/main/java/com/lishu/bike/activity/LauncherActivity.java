package com.lishu.bike.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.WindowManager;

import com.lishu.bike.R;
import com.lishu.bike.constant.HttpAddress;
import com.lishu.bike.constant.UserPreferences;
import com.lishu.bike.http.HttpLoader;
import com.lishu.bike.model.BaseModel;
import com.lishu.bike.model.UserModel;
import com.lishu.bike.utils.StringUtil;

import java.util.List;

public class LauncherActivity extends BaseActivity {
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            gotoLogin();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_launcher);

        handler.sendEmptyMessageDelayed(0, 1000);
    }

    private void gotoLogin() {
        String name = UserPreferences.getInstance().getLoginName();
        String password = UserPreferences.getInstance().getLoginPassword();
        if (StringUtil.isEmpty(name) || StringUtil.isEmpty(password)) {
            loginFail();
            return;
        }

        HttpLoader.login(name, password, new HttpLoader.IResponseListener() {
            @Override
            public void onResponse(BaseModel model) {
                if (model == null) {
                    loginFail();
                    return;
                }

                if (!model.success()) {
                    loginFail();
                    return;
                }

                List<UserModel.UserBean> userBeanList = ((UserModel)model).getDataList();
                if (userBeanList == null) {
                    loginFail();
                    return;
                }
                UserModel.UserBean userBean = userBeanList.get(0);
                //保存返回的用户信息
                UserPreferences.getInstance().setUserId(userBean.getId());
                UserPreferences.getInstance().setUserName(userBean.getName());
                UserPreferences.getInstance().setUserAge(userBean.getAge());
                UserPreferences.getInstance().setUserPhone(userBean.getPhone());
                UserPreferences.getInstance().setUserAddress(userBean.getAddress());
                UserPreferences.getInstance().setUserUrl(HttpAddress.ROOT + userBean.getUrl());
                UserPreferences.getInstance().setUserOrganization(userBean.getOrganizationName());

                loginSuccess();
            }
        });
    }

    private void loginFail() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    private void loginSuccess() {
        startActivity(new Intent(LauncherActivity.this, MainActivity.class));
        finish();
    }
}
