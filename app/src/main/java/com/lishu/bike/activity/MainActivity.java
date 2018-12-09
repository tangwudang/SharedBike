package com.lishu.bike.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lishu.bike.R;
import com.lishu.bike.widget.CustomGridView;

public class MainActivity extends AppCompatActivity {
    private CustomGridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initEvent();
    }

    private void initView() {
        gridView = (CustomGridView)findViewById(R.id.gridView);
    }

    private void initEvent() {
    }
}
