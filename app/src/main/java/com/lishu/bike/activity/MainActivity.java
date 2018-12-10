package com.lishu.bike.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.GridView;

import com.lishu.bike.R;
import com.lishu.bike.utils.ToastUtil;

public class MainActivity extends BaseActivity {
    private GridView gridView;
    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initView() {
        gridView = (GridView) findViewById(R.id.gridView);
    }

    @Override
    public void initEvent() {
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                ToastUtil.showShort("再按一次退出");
                exitTime = System.currentTimeMillis();
            } else {
                this.finish();
            }
            return false;
        }

        return super.onKeyDown(keyCode, event);
    }
}
