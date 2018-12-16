package com.lishu.bike.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lishu.bike.R;
import com.lishu.bike.http.HttpBase;
import com.lishu.bike.http.HttpLoader;
import com.lishu.bike.model.BaseModel;
import com.lishu.bike.model.StationDetailModel;
import com.lishu.bike.utils.ToastUtil;

public class StationDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_detail);

        initView();

        int stationId = getIntent().getIntExtra("stationId", -1);
        getStationDetail(stationId);
    }

    private void initView() {
        setTopTitle("停车点信息");
    }

    /**
     * 获取station详情
     */
    private void getStationDetail(int stationId) {
        HttpLoader.getStationDetail(stationId, new HttpBase.IResponseListener() {
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

                StationDetailModel stationDetail = (StationDetailModel) model;
                if(stationDetail != null){


                }
            }
        });
    }
}
