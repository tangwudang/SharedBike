package com.lishu.bike.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.lishu.bike.R;
import com.lishu.bike.http.HttpBase;
import com.lishu.bike.http.HttpLoader;
import com.lishu.bike.model.BaseModel;
import com.lishu.bike.model.InspectDetailModel;
import com.lishu.bike.utils.ToastUtil;

public class InspectDetailActivity extends BaseActivity{
    private TextView dutyName, inspectTime, inspectAddress, inspectType, inspectContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspect_detail);

        initView();

        int inspectId = getIntent().getIntExtra("inspectId", -1);
        getInspectDetail(inspectId);
    }

    private void initView() {
        setTopTitle("巡检信息详情");

        dutyName = findViewById(R.id.dutyName);
        inspectTime = findViewById(R.id.inspectTime);
        inspectAddress = findViewById(R.id.inspectAddress);
        inspectType = findViewById(R.id.inspectType);
        inspectContent = findViewById(R.id.inspectContent);
    }

    /**
     * 获取巡检详情
     */
    private void getInspectDetail(int inspectId) {
        HttpLoader.getInspectDetail(inspectId, new HttpBase.IResponseListener() {
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

                InspectDetailModel inspectDetail = (InspectDetailModel) model;
                if(inspectDetail != null){
                    dutyName.setText(inspectDetail.getName());
                    inspectTime.setText(inspectDetail.getInspectTime());
                    inspectAddress.setText(inspectDetail.getInspectAddress());
                    inspectType.setText(inspectDetail.getTypeName());
                    inspectContent.setText(inspectDetail.getInspectContent());
                }
            }
        });
    }
}
