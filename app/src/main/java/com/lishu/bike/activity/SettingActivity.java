package com.lishu.bike.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lishu.bike.R;
import com.lishu.bike.constant.UserPreferences;
import com.lishu.bike.utils.ImageUtil;
import com.lishu.bike.utils.SystemUtil;

public class SettingActivity extends BaseActivity{
    private ImageView user_avatar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        initView();
        initEvent();
        setData();
    }

    private void initView() {
        setTopTitle(R.string.settings);
        ((TextView)findViewById(R.id.user_name)).setText(UserPreferences.getInstance().getUserName());
        ((TextView)findViewById(R.id.user_age)).setText(UserPreferences.getInstance().getUserAge());
        ((TextView)findViewById(R.id.user_organization)).setText(UserPreferences.getInstance().getUserOrganization());
        ((TextView)findViewById(R.id.user_phone)).setText(UserPreferences.getInstance().getUserPhone());
        ((TextView)findViewById(R.id.app_version)).setText(SystemUtil.getVersionName(mContext));
        user_avatar = findViewById(R.id.user_avatar);
    }

    private void initEvent() {
        findViewById(R.id.change_password).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingActivity.this, ChangePwdActivity.class));
            }
        });
        findViewById(R.id.logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserPreferences.getInstance().logout();

                Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    private void setData() {
        String imagePath = UserPreferences.getInstance().getUserUrl();
        if(!TextUtils.isEmpty(imagePath)) {
            ImageUtil.setUserAvatar(user_avatar, imagePath, R.drawable.person_default);
        }
    }
}
