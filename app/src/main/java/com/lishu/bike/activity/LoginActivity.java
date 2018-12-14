package com.lishu.bike.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lishu.bike.R;
import com.lishu.bike.constant.UserPreferences;
import com.lishu.bike.http.HttpLoader;
import com.lishu.bike.model.BaseModel;
import com.lishu.bike.model.UserModel;
import com.lishu.bike.utils.StringUtil;
import com.lishu.bike.utils.ToastUtil;

public class LoginActivity extends BaseActivity {
    private EditText mLoginName, mLoginPassword;
    private Button mLoginButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        initEvent();

        // just for testing, begin =======================
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
        // just for testing, end =======================
    }

    public void initView() {
        mLoginName = findViewById(R.id.edit_name);
        mLoginPassword = findViewById(R.id.edit_password);
        mLoginButton = findViewById(R.id.login_button);
    }

    public void initEvent() {
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(mLoginName.getText().toString(), mLoginPassword.getText().toString());
            }
        });
    }

    private void login(String name, String pwd) {
        if (StringUtil.isEmpty(name)) {
            ToastUtil.showShort(R.string.please_input_login_name);
            return;
        } else if (StringUtil.isEmpty(pwd)) {
            ToastUtil.showShort(R.string.please_input_password);
            return;
        } else if (pwd.length() < 6 || pwd.length() > 20) {
            ToastUtil.showShort(R.string.password_length_wrong);
            return;
        }

        show("登录中，请稍候...");
        HttpLoader.login(name, pwd, new HttpLoader.IResponseListener() {
            @Override
            public void onResponse(BaseModel model) {
                hide();
                if (model == null) {
                    ToastUtil.showShort("登录失败，请检查网络");
                    return;
                }

                if (!model.success()) {
                    ToastUtil.showShort("登录失败：" + model.getResMsg());
                    return;
                }

                UserModel.UserBean userBean = ((UserModel)model).getDataList();
                if (userBean == null) {
                    ToastUtil.showShort("登录失败：无效的登录信息");
                    return;
                }

                //保存返回的用户信息
                UserPreferences.getInstance().setUserId(userBean.getId());
                UserPreferences.getInstance().setUserName(userBean.getName());
                UserPreferences.getInstance().setUserPhone(userBean.getPhone());
                UserPreferences.getInstance().setUserAddress(userBean.getAddress());
                UserPreferences.getInstance().setUserUrl(userBean.getUrl());
                UserPreferences.getInstance().setUserOrganization(userBean.getOrganizationName());
                UserPreferences.getInstance().setAlreadyLoginFlag(true);

                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}
