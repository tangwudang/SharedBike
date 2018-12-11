package com.lishu.bike.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.lishu.bike.R;
import com.lishu.bike.utils.ToastUtil;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fence_layout:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.station_layout:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.address_book_layout:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.inspection_layout:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.task_layout:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.app_info_layout:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.analyzes_layout:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.warnings_layout:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.settings_layout:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                ToastUtil.showShort("再按一次退出");
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return false;
        }

        return super.onKeyDown(keyCode, event);
    }
}
