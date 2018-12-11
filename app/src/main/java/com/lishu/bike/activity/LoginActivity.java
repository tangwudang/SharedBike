package com.lishu.bike.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lishu.bike.R;

public class LoginActivity extends BaseActivity {
    private EditText mLoginName, mLoginPassword;
    private Button mLoginButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        initEvent();
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

    private void login(String phone, String password) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
