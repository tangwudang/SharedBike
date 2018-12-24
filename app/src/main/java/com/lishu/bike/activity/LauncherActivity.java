package com.lishu.bike.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.WindowManager;

import com.lishu.bike.R;
import com.lishu.bike.constant.UserPreferences;

public class LauncherActivity extends BaseActivity {
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (UserPreferences.getInstance().getAlreadyLoginFlag()) {
                gotoMain();
            }else {
                gotoLogin();
            }

            finish();
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
        startActivity(new Intent(LauncherActivity.this, LoginActivity.class));
    }

    private void gotoMain() {
        startActivity(new Intent(LauncherActivity.this, MainActivity.class));
    }
}
