package com.lishu.bike.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lishu.bike.R;
import com.lishu.bike.adapter.AppInfoListAdapter;
import com.lishu.bike.http.HttpBase;
import com.lishu.bike.http.HttpLoader;
import com.lishu.bike.model.AppInfoModel;
import com.lishu.bike.model.BaseModel;
import com.lishu.bike.utils.ToastUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

public class AppInfoListActivity extends BaseActivity{
    private ListView app_info_list;
    private AppInfoListAdapter mAppInfoListAdapter;
    private SmartRefreshLayout refreshLayout;
    private List<AppInfoModel.AppInfoBean> mAppInfoList;

    private int curPage = 1;
    private final int COUNT_PER_PAGE = 10;//每页条数

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_info);

        initView();
        initEvent();

        mAppInfoList = new ArrayList<>();
        getAppInfoList(curPage, COUNT_PER_PAGE);
    }

    private void initView() {
        setTopTitle(R.string.app_info);
        app_info_list = findViewById(R.id.app_info_list);
        mAppInfoListAdapter = new AppInfoListAdapter(this);
        app_info_list.setAdapter(mAppInfoListAdapter);
        refreshLayout = findViewById(R.id.refreshLayout);
        refreshLayout.setEnableRefresh(false);

        //@@@@@@@@@@@@@@@@@@ just for testing, begin @@@@@@@@@@@@@@@@@
        /*List<AppInfoModel.AppInfoBean> appInfoList = new ArrayList<>();
        appInfoList.add(new AppInfoModel().new AppInfoBean("我公司在今天下午进行员工总结大会", "20181209121514", "通知"));
        appInfoList.add(new AppInfoModel().new AppInfoBean("城管局如何做好巡检工作", "20181209101510", "员工风采"));
        appInfoList.add(new AppInfoModel().new AppInfoBean("冬季如何做好防护工作", "20181209111836", "通知"));
        appInfoList.add(new AppInfoModel().new AppInfoBean("我公司在今天下午进行员工总结大会，请全体人员准时参加", "20181216091842", "通知"));
        mAppInfoListAdapter.setData(appInfoList);*/
        //@@@@@@@@@@@@@@@@@@ just for testing, end @@@@@@@@@@@@@@@@@@
    }

    private void initEvent() {
        app_info_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                int infoId = ((AppInfoModel.AppInfoBean)parent.getItemAtPosition(position)).getId();
                Intent intent = new Intent(AppInfoListActivity.this, AppInfoDetailActivity.class);
                intent.putExtra("infoId", infoId);
                startActivity(intent);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                curPage++;
                getAppInfoList(curPage, COUNT_PER_PAGE);
                refreshLayout.finishLoadMore();
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
                    mAppInfoList.addAll(appInfoList);
                    mAppInfoListAdapter.setData(mAppInfoList);
                }else{
                    ToastUtil.showShort(R.string.no_more_data);
                }
            }
        });
    }  
}
