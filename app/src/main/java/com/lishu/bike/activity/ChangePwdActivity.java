package com.lishu.bike.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.lishu.bike.R;
import com.lishu.bike.http.HttpLoader;
import com.lishu.bike.model.BaseModel;
import com.lishu.bike.utils.StringUtil;
import com.lishu.bike.utils.ToastUtil;

public class ChangePwdActivity extends BaseActivity{
    private EditText oldPassword, newPassword, newPasswordAgain;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pwd);

        initView();
        initEvent();
    }

    private void initView() {
        setTopTitle("修改密码");

        oldPassword = findViewById(R.id.old_password);
        newPassword = findViewById(R.id.new_password);
        newPasswordAgain = findViewById(R.id.new_password_again);
    }

    private void initEvent() {
        findViewById(R.id.ok_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commit();
            }
        });
    }

    private void commit() {
        String oldPwd = oldPassword.getText().toString();
        String newPwd = newPassword.getText().toString();
        String newPwdAgain = newPasswordAgain.getText().toString();

        if (StringUtil.isEmpty(oldPwd)) {
            ToastUtil.showShort(R.string.input_old_password);
            return;
        }

        if (StringUtil.isEmpty(newPwd)) {
            ToastUtil.showShort(R.string.input_new_password);
            return;
        }

        if (newPwd.length() < 6 || newPwd.length() > 20) {
            ToastUtil.showShort(R.string.password_length_wrong);
            return;
        }

        if(!newPwd.equals(newPwdAgain)){
            ToastUtil.showShort("您两次输入的密码不一致");
            return;
        }

        show("正在处理，请稍候...");
        HttpLoader.changePassword(oldPwd, newPwd,new HttpLoader.IResponseListener() {
                    @Override
                    public void onResponse(BaseModel model) {
                        hide();
                        if (model == null) {
                            ToastUtil.showShort(R.string.please_check_network);
                            return;
                        }

                        if (!model.success()) {
                            ToastUtil.showShort("修改密码失败：" + model.getResMsg());
                            return;
                        }

                        ToastUtil.showShort("修改密码成功！");

                        Intent intent = new Intent(ChangePwdActivity.this, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                        finish();
                    }
                });
    }
}
