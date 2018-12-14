package com.lishu.bike.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.lishu.bike.R;
import com.lishu.bike.task.Upgrade;
import com.lishu.bike.utils.ToastUtil;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.CAMERA,
                Manifest.permission.READ_PHONE_STATE,
                "com.android.launcher.permission.READ_SETTINGS",
                "com.android.launcher.permission.WRITE_SETTINGS"}, 100);
    }

    protected void onRequestPermissionsGranted(int requestCode) {
        if (requestCode == 100) {
            new Upgrade(this, false).updateVersion();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fence_layout:
                startActivity(new Intent(this, FenceStreetActivity.class));
                break;
            case R.id.station_layout:
                startActivity(new Intent(this, StationListActivity.class));
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
                startActivity(new Intent(this, SettingActivity.class));
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

    /**
     * 暂时不启用数据库
     */
    /*private void initDB() {
        //如果数据库有更新，则先删除原来的数据库
        if (AppConfig.DB_VERSION > UserPreferences.getInstance().getDbVersion()) {
            String dbPath = getDatabasePath(AppConfig.DB_NAME).getPath();
            boolean isSuccess = FileUtil.deleteFile(new File(dbPath));
            if (isSuccess) {
                UserPreferences.getInstance().setDbVersion(AppConfig.DB_VERSION);
            } else {
                ToastUtil.showShort("数据库升级失败！");
            }
        }
        //初始化数据库
        DBHelper.instance().open(BaseApplication.getAppContext());
    }*/
}
