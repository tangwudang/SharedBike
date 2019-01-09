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

    /*private final int PRESSED_TEXT_COLOR = 0xff000000;
    private final int NORMAL_TEXT_COLOR = 0xff000000;
    private TextView fence_tv,station_tv,book_tv,inspect_tv,task_tv,appinfo_tv,analyze_tv,warn_tv,setting_tv;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        checkPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        checkPermissions(new String[]{
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.CHANGE_WIFI_STATE,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.CAMERA,
                Manifest.permission.READ_PHONE_STATE,
                "com.android.launcher.permission.READ_SETTINGS",
                "com.android.launcher.permission.WRITE_SETTINGS"}, 99);
    }

    private void initView() {
        setTopTitle("首页");
        setTopLeftGone();

       /* fence_tv = findViewById(R.id.fence_tv);
        station_tv = findViewById(R.id.station_tv);
        book_tv = findViewById(R.id.book_tv);
        inspect_tv = findViewById(R.id.inspect_tv);
        task_tv = findViewById(R.id.task_tv);
        appinfo_tv = findViewById(R.id.appinfo_tv);
        analyze_tv = findViewById(R.id.analyze_tv);
        warn_tv = findViewById(R.id.warn_tv);
        setting_tv = findViewById(R.id.setting_tv);*/
    }

    protected void onRequestPermissionsGranted(int requestCode) {
        if (requestCode == 100) {
            try {
                new Upgrade(this, false).updateVersion();
            } catch (Exception e) {
                e.printStackTrace();
            }
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
                startActivity(new Intent(this, AddressBookActivity.class));
                break;
            case R.id.inspection_layout:
                startActivity(new Intent(this, InspectListActivity.class));
                break;
            case R.id.task_layout:
                startActivity(new Intent(this, TaskListActivity.class));
                break;
            case R.id.app_info_layout:
                startActivity(new Intent(this, AppInfoListActivity.class));
                break;
            case R.id.analyzes_layout:
                startActivity(new Intent(this, AnalyzeActivity.class));
                break;
            case R.id.warnings_layout:
                startActivity(new Intent(this, WarnListActivity.class));
                break;
            case R.id.settings_layout:
                startActivity(new Intent(this, SettingActivity.class));
                break;
            default:
                break;
        }
    }

    /*private void resetTextColor(){
        fence_tv.setTextColor(NORMAL_TEXT_COLOR);
        station_tv.setTextColor(NORMAL_TEXT_COLOR);
        book_tv.setTextColor(NORMAL_TEXT_COLOR);
        inspect_tv.setTextColor(NORMAL_TEXT_COLOR);
        task_tv.setTextColor(NORMAL_TEXT_COLOR);
        appinfo_tv.setTextColor(NORMAL_TEXT_COLOR);
        analyze_tv.setTextColor(NORMAL_TEXT_COLOR);
        warn_tv.setTextColor(NORMAL_TEXT_COLOR);
        setting_tv.setTextColor(NORMAL_TEXT_COLOR);
    }*/

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
