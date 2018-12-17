package com.lishu.bike.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.lishu.bike.R;
import com.lishu.bike.http.HttpBase;
import com.lishu.bike.http.HttpLoader;
import com.lishu.bike.model.BaseModel;
import com.lishu.bike.model.WarnDetailModel;
import com.lishu.bike.utils.ToastUtil;

public class WarnDetailActivity extends BaseActivity{
    private TextView warnTitle, warnTime, warnContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warn_detail);

        initView();

        int warnId = getIntent().getIntExtra("warnId", -1);
        getWarnDetail(warnId);
    }

    private void initView() {
        setTopTitle("告警信息详情");

        warnTitle = findViewById(R.id.warnTitle);
        warnTime = findViewById(R.id.warnTime);
        warnContent = findViewById(R.id.warnContent);
    }

    /**
     * 获取详情
     */
    private void getWarnDetail(int warnId) {
        HttpLoader.getWarnDetail(warnId, new HttpBase.IResponseListener() {
            @Override
            public void onResponse(BaseModel model) {
                if (model == null) {
                    ToastUtil.showShort(R.string.please_check_network);
                    return;
                }
                if (!model.success()) {
                    ToastUtil.showShort(getString(R.string.get_data_fail) + model.getResMsg());
                    return;
                }

                WarnDetailModel warnDetail = (WarnDetailModel) model;
                if(warnDetail != null){
                    warnTitle.setText(warnDetail.getName());
                    warnTime.setText(warnDetail.getWarnTime());
                    warnContent.setText(warnDetail.getWarnContent());
                }
            }
        });
    }
}
