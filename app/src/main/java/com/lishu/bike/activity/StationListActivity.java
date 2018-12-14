package com.lishu.bike.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lishu.bike.R;
import com.lishu.bike.adatper.StationListAdapter;
import com.lishu.bike.adatper.StreetListAdapter;
import com.lishu.bike.http.HttpBase;
import com.lishu.bike.http.HttpLoader;
import com.lishu.bike.model.BaseModel;
import com.lishu.bike.model.StationModel;
import com.lishu.bike.model.StreetModel;
import com.lishu.bike.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class StationListActivity extends BaseActivity {
    private ListView mStreetListView, mStationListView;
    private StreetListAdapter mStreetListAdapter;
    private StationListAdapter mStationListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_list);

        initView();
        initEvent();

        getStreets();
    }

    private void initView() {
        setTopTitle("站点查询");

        mStreetListView = findViewById(R.id.street_lv);
        mStationListView = findViewById(R.id.station_lv);
        mStreetListAdapter = new StreetListAdapter(this);
        mStationListAdapter = new StationListAdapter(this);
        mStreetListView.setAdapter(mStreetListAdapter);
        mStationListView.setAdapter(mStationListAdapter);

        //@@@@@@@@@@@@@@@@@@ just for testing, begin @@@@@@@@@@@@@@@@@
        List<StreetModel.StreetBean> streetList = new ArrayList<>();
        streetList.add(new StreetModel().new StreetBean(1, "东山街道", 1,1));
        streetList.add(new StreetModel().new StreetBean(2, "禄口街道", 1,1));
        streetList.add(new StreetModel().new StreetBean(3, "秣陵街道", 1,1));
        streetList.add(new StreetModel().new StreetBean(1, "谷里街道", 1,1));
        streetList.add(new StreetModel().new StreetBean(2, "江宁街道", 1,1));
        streetList.add(new StreetModel().new StreetBean(3, "汤山街道", 1,1));
        mStreetListAdapter.setData(streetList);

        List<StationModel.StationBean> stationList = new ArrayList<>();
        stationList.add(new StationModel().new StationBean(1, "翠屏山"));
        stationList.add(new StationModel().new StationBean(2, "百家湖"));
        stationList.add(new StationModel().new StationBean(3, "秣陵"));
        stationList.add(new StationModel().new StationBean(1, "将军山"));
        mStationListAdapter.setData(stationList);
        //@@@@@@@@@@@@@@@@@@ just for testing, end @@@@@@@@@@@@@@@@@@
    }

    private void initEvent() {
        mStreetListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                int streetId = ((StreetModel.StreetBean)parent.getItemAtPosition(position)).getId();
                getStations(streetId);
            }
        });
        mStationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                int stationId = ((StationModel.StationBean)parent.getItemAtPosition(position)).getId();
                Intent intent = new Intent(StationListActivity.this, StationDetailActivity.class);
                intent.putExtra("stationId", stationId);
                startActivity(intent);
            }
        });
    }

    /**
     * 获取street列表
     */
    private void getStreets() {
        HttpLoader.getStreets(new HttpBase.IResponseListener() {
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

                List<StreetModel.StreetBean> streetList = ((StreetModel) model).getDataList();
                if (streetList != null) {
                    //设置street的ListView
                    mStreetListAdapter.setData(streetList);
                    //默认获取第一个street的station列表
                    getStations(streetList.get(0).getId());
                }
            }
        });
    }

    /**
     * 获取station列表
     */
    private void getStations(int streetId) {
        HttpLoader.getStations(streetId, new HttpBase.IResponseListener() {
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

                List<StationModel.StationBean> stationList = ((StationModel) model).getDataList();
                if(stationList != null){
                    //设置station的ListView
                    mStationListAdapter.setData(stationList);
                }
            }
        });
    }
}
