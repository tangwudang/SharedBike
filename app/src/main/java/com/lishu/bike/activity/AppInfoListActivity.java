package com.lishu.bike.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lishu.bike.R;
import com.lishu.bike.adatper.AppInfoListAdapter;
import com.lishu.bike.http.HttpBase;
import com.lishu.bike.http.HttpLoader;
import com.lishu.bike.model.AppInfoModel;
import com.lishu.bike.model.BaseModel;
import com.lishu.bike.model.InspectModel;
import com.lishu.bike.utils.ToastUtil;

import java.util.List;

public class AppInfoListActivity extends BaseActivity{
    private ListView app_info_list;
    private AppInfoListAdapter mAppInfoListAdapter;

    private int curPage = 1;
    private final int COUNT_PER_PAGE = 10;//每页条数

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_info);

        initView();
        initEvent();

        getAppInfoList(curPage, COUNT_PER_PAGE);
    }

    private void initView() {
        setTopTitle(R.string.app_info);
        app_info_list = findViewById(R.id.app_info_list);
        mAppInfoListAdapter = new AppInfoListAdapter(this);
        app_info_list.setAdapter(mAppInfoListAdapter);
    }

    private void initEvent() {
        app_info_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                int inspectId = ((InspectModel.InspectBean)parent.getItemAtPosition(position)).getId();
                Intent intent = new Intent(AppInfoListActivity.this, AppInfoDetailActivity.class);
                intent.putExtra("inspectId", inspectId);
                startActivity(intent);
            }
        });
    }

    private void getAppInfoList(int curPage, int count){
        HttpLoader.getAppInfos(curPage, count, new HttpBase.IResponseListener() {
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

                List<AppInfoModel.AppInfoBean> appInfoList = ((AppInfoModel) model).getDataList();
                if (appInfoList != null) {
                    mAppInfoListAdapter.setData(appInfoList);
                }
            }
        });
    }  
}
