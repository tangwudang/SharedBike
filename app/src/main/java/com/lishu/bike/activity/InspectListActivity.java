package com.lishu.bike.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.lishu.bike.R;
import com.lishu.bike.adapter.InspectionListAdapter;
import com.lishu.bike.http.HttpBase;
import com.lishu.bike.http.HttpLoader;
import com.lishu.bike.listener.DateSearchListener;
import com.lishu.bike.model.BaseModel;
import com.lishu.bike.model.InspectModel;
import com.lishu.bike.utils.DateSearchUtil;
import com.lishu.bike.utils.StringUtil;
import com.lishu.bike.utils.ToastUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

public class InspectListActivity extends BaseSearchActivity implements View.OnClickListener {
    private EditText begin_time_ev, end_time_ev;
    private String chooseBeginTime, chooseEndTime;
    private ImageView search_icon;
    private ListView inspection_list;
    private InspectionListAdapter mInspectionListAdapter;
    private int curPage;
    private SmartRefreshLayout refreshLayout;
    private List<InspectModel.InspectBean> mInspectList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection_list);

        initView();
        initEvent();

        mInspectList = new ArrayList<>();
        chooseBeginTime = "";//TimeUtil.getCurDatetime();
        chooseEndTime = "";//TimeUtil.getCurDatetime();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getInspectionListByTime(1, chooseBeginTime, chooseEndTime);
    }

    private void initView() {
        setTopTitle(R.string.inspection);
        setTopRight(R.drawable.icon_add);

        begin_time_ev = findViewById(R.id.begin_time_ev);
        end_time_ev = findViewById(R.id.end_time_ev);
        inspection_list = findViewById(R.id.inspection_list);
        mInspectionListAdapter = new InspectionListAdapter(this);
        inspection_list.setAdapter(mInspectionListAdapter);
        refreshLayout = findViewById(R.id.refreshLayout);
        refreshLayout.setEnableRefresh(false);
        //@@@@@@@@@@@@@@@@@@ just for testing, begin @@@@@@@@@@@@@@@@@
       /* List<InspectModel.InspectBean> testList = new ArrayList<>();
        testList.add(new InspectModel().new InspectBean(1,"我公司在今天下午进行员工总结大会", "20181209121514", "通知"));
        testList.add(new InspectModel().new InspectBean(2,"城管局如何做好巡检工作", "20181209101510", "会议"));
        testList.add(new InspectModel().new InspectBean(3,"冬季如何做好防护工作", "20181209111836", "员工风采"));
        testList.add(new InspectModel().new InspectBean(4,"我公司在今天下午进行员工总结大会，请全体人员准时参加", "20181216091842", "员工风采"));
        mInspectionListAdapter.setData(testList);*/
        //@@@@@@@@@@@@@@@@@@ just for testing, end @@@@@@@@@@@@@@@@@@
    }

    private void initEvent() {
        begin_time_ev.setOnClickListener(this);
        end_time_ev.setOnClickListener(this);
        findViewById(R.id.search_button).setOnClickListener(this);
        findViewById(R.id.title_right).setOnClickListener(this);
        inspection_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                int inspectId = ((InspectModel.InspectBean)parent.getItemAtPosition(position)).getId();
                Intent intent = new Intent(InspectListActivity.this, InspectDetailActivity.class);
                intent.putExtra("inspectId", inspectId);
                startActivity(intent);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                curPage++;
                getInspectionList(chooseBeginTime + "000000",
                        chooseEndTime + "235959",
                        curPage, COUNT_PER_PAGE);
                refreshLayout.finishLoadMore();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.begin_time_ev:
                showDatePickerDialog(0);
                break;
            case R.id.end_time_ev:
                showDatePickerDialog(1);
                break;
            case R.id.search_button:
                search();
                break;
            case R.id.title_right:
                startActivity(new Intent(InspectListActivity.this, InspectDisposeActivity.class));
                break;
        }
    }

    private void search() {
        final String beginDate = begin_time_ev.getText().toString();
        final String endDate = end_time_ev.getText().toString();
        DateSearchUtil.searchByDate(beginDate, endDate, new DateSearchListener() {
            @Override
            public void searchByDefaultDate() {
                getInspectionListByTime(1, "", "");
            }

            @Override
            public void searchByChooseDate(String beginDate, String endDate) {
                getInspectionListByTime(1, beginDate, endDate);
            }
        });
    }

    private void getInspectionListByTime(int page, String beginDate, String endDate){
        if(page == 1){
            curPage = 1;
            mInspectList.clear();
        }
        chooseBeginTime = beginDate;
        chooseEndTime = endDate;

        if (StringUtil.isEmpty(beginDate) && StringUtil.isEmpty(endDate)){
            getInspectionList("", "", curPage, COUNT_PER_PAGE);
        }else {
            getInspectionList(beginDate + "000000",
                    endDate + "235959",
                    curPage, COUNT_PER_PAGE);
        }
    }

    private void getInspectionList(String beginTime, String endTime, int curPage, int count){
        HttpLoader.getInspections(beginTime, endTime, curPage, count, new HttpBase.IResponseListener() {
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

                List<InspectModel.InspectBean> inspectList = ((InspectModel) model).getDataList();
                if (inspectList != null) {
                    mInspectList.addAll(inspectList);
                    mInspectionListAdapter.setData(mInspectList);
                }else{
                    ToastUtil.showShort(R.string.no_more_data);
                }
            }
        });
    }
}
