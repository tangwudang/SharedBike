package com.lishu.bike.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lishu.bike.R;

public class AddInspectActivity extends BaseActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_inspect);

        initView();
        initEvent();
    }

    private void initView() {
        setTopTitle("巡检上报");
    }

    private void initEvent() {
    }
}
