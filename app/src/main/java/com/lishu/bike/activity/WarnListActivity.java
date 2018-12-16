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
import com.lishu.bike.adapter.WarnListAdapter;
import com.lishu.bike.http.HttpBase;
import com.lishu.bike.http.HttpLoader;
import com.lishu.bike.listener.DateSearchListener;
import com.lishu.bike.model.BaseModel;
import com.lishu.bike.model.WarnModel;
import com.lishu.bike.utils.DateSearchUtil;
import com.lishu.bike.utils.TimeUtil;
import com.lishu.bike.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class WarnListActivity extends BaseSearchActivity implements View.OnClickListener {
    private EditText begin_time_ev, end_time_ev;
    private ImageView search_icon;
    private ListView warn_list;
    private WarnListAdapter mWarnListAdapter;
    private int curPage = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warn_list);

        initView();
        initEvent();
    }

    private void initView() {
        setTopTitle(R.string.warnings);
        begin_time_ev = findViewById(R.id.begin_time_ev);
        end_time_ev = findViewById(R.id.end_time_ev);
        warn_list = findViewById(R.id.warn_list);
        mWarnListAdapter = new WarnListAdapter(this);
        warn_list.setAdapter(mWarnListAdapter);
        //@@@@@@@@@@@@@@@@@@ just for testing, begin @@@@@@@@@@@@@@@@@
        List<WarnModel.WarnBean> testList = new ArrayList<>();
        testList.add(new WarnModel().new WarnBean(1,"我公司在今天下午进行员工总结大会", "20181209121514", "1"));
        testList.add(new WarnModel().new WarnBean(2,"城管局如何做好巡检工作", "20181209101510", "2"));
        testList.add(new WarnModel().new WarnBean(3,"冬季如何做好防护工作", "20181209111836", "1"));
        testList.add(new WarnModel().new WarnBean(4,"我公司在今天下午进行员工总结大会，请全体人员准时参加", "20181216091842", "2"));
        mWarnListAdapter.setData(testList);
        //@@@@@@@@@@@@@@@@@@ just for testing, end @@@@@@@@@@@@@@@@@@
    }

    private void initEvent() {
        begin_time_ev.setOnClickListener(this);
        end_time_ev.setOnClickListener(this);
        findViewById(R.id.search_button).setOnClickListener(this);
        warn_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                int inspectId = ((WarnModel.WarnBean)parent.getItemAtPosition(position)).getId();
                Intent intent = new Intent(WarnListActivity.this, WarnDetailActivity.class);
                intent.putExtra("inspectId", inspectId);
                startActivity(intent);
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
        }
    }

    private void search() {
        final String beginDate = begin_time_ev.getText().toString();
        final String endDate = end_time_ev.getText().toString();
        DateSearchUtil.searchByDate(beginDate, endDate, new DateSearchListener() {
            @Override
            public void searchByDefaultDate() {
                getWarnList(TimeUtil.getCurDatetime() + "000000",
                        TimeUtil.getCurDatetime() + "235959",
                        1, COUNT_PER_PAGE);
            }

            @Override
            public void searchByChooseDate() {
                getWarnList(beginDate + "000000",
                        endDate + "235959",
                        1, COUNT_PER_PAGE);
            }
        });
    }

    private void getWarnList(String beginTime, String endTime, int curPage, int count){
        HttpLoader.getWarnings(beginTime, endTime, curPage, count, new HttpBase.IResponseListener() {
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

                List<WarnModel.WarnBean> warnList = ((WarnModel) model).getDataList();
                if (warnList != null) {
                    mWarnListAdapter.setData(warnList);
                }
            }
        });
    }
}
