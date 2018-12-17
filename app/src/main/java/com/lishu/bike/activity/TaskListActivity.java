package com.lishu.bike.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.lishu.bike.R;
import com.lishu.bike.adapter.TaskListAdapter;
import com.lishu.bike.http.HttpBase;
import com.lishu.bike.http.HttpLoader;
import com.lishu.bike.listener.DateSearchListener;
import com.lishu.bike.model.BaseModel;
import com.lishu.bike.model.TaskModel;
import com.lishu.bike.utils.DateSearchUtil;
import com.lishu.bike.utils.TimeUtil;
import com.lishu.bike.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class TaskListActivity extends BaseSearchActivity implements View.OnClickListener {
    private EditText begin_time_ev, end_time_ev;
    private ImageView search_icon;
    private ListView task_list;
    private TaskListAdapter mTaskListAdapter;
    private int curPage = 1;
    //任务tab栏
    private final int PRESSED_TEXT_COLOR = 0xff000000;
    private final int NORMAL_TEXT_COLOR = 0xff666666;
    private LinearLayout processedLayout;
    private LinearLayout unprocessedLayout;
    private TextView processedTaskTV;
    private TextView unprocessedTaskTV;
    private View processedBar;
    private View unprocessedBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        initView();
        initEvent();
    }

    private void initView() {
        setTopTitle(R.string.task);
        begin_time_ev = findViewById(R.id.begin_time_ev);
        end_time_ev = findViewById(R.id.end_time_ev);
        task_list = findViewById(R.id.task_list);
        processedLayout = findViewById(R.id.processed_layout);
        unprocessedLayout = findViewById(R.id.unprocessed_layout);
        processedTaskTV = findViewById(R.id.processed_task);
        unprocessedTaskTV = findViewById(R.id.unprocessed_task);
        processedBar = findViewById(R.id.processed_bar);
        unprocessedBar = findViewById(R.id.unprocessed_bar);

        mTaskListAdapter = new TaskListAdapter(this);
        task_list.setAdapter(mTaskListAdapter);

        //@@@@@@@@@@@@@@@@@@ just for testing, begin @@@@@@@@@@@@@@@@@
        List<TaskModel.TaskBean> testList = new ArrayList<>();
        testList.add(new TaskModel().new TaskBean(1,"我公司在今天下午进行员工总结大会", "20181209121514", "1", "0"));
        testList.add(new TaskModel().new TaskBean(2,"城管局如何做好巡检工作", "20181209101510", "2", "0"));
        testList.add(new TaskModel().new TaskBean(3,"冬季如何做好防护工作", "20181209111836", "1", "1"));
        testList.add(new TaskModel().new TaskBean(4,"我公司在今天下午进行员工总结大会，请全体人员准时参加", "20181216091842", "2", "1"));
        mTaskListAdapter.setData(testList);
        //@@@@@@@@@@@@@@@@@@ just for testing, end @@@@@@@@@@@@@@@@@@
    }

    private void initEvent() {
        begin_time_ev.setOnClickListener(this);
        end_time_ev.setOnClickListener(this);
        processedLayout.setOnClickListener(this);
        unprocessedLayout.setOnClickListener(this);
        findViewById(R.id.search_button).setOnClickListener(this);
        task_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                int taskId = ((TaskModel.TaskBean)parent.getItemAtPosition(position)).getId();
                Intent intent = new Intent(TaskListActivity.this, TaskDetailActivity.class);
                intent.putExtra("taskId", taskId);
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
            case R.id.unprocessed_layout:
                setTab(0);
                search();
                break;
            case R.id.processed_layout:
                setTab(1);
                search();
                break;
            case R.id.search_button:
                search();
                break;
        }
    }

    private void setTab(int i) {
        switch (i) {
            case 0:
                processedBar.setVisibility(View.INVISIBLE);
                unprocessedBar.setVisibility(View.VISIBLE);
                processedTaskTV.setTextColor(NORMAL_TEXT_COLOR);
                unprocessedTaskTV.setTextColor(PRESSED_TEXT_COLOR);
                break;
            case 1:
                processedBar.setVisibility(View.VISIBLE);
                unprocessedBar.setVisibility(View.INVISIBLE);
                processedTaskTV.setTextColor(PRESSED_TEXT_COLOR);
                unprocessedTaskTV.setTextColor(NORMAL_TEXT_COLOR);
                break;
            default:
                break;
        }
    }

    private void search() {
        final String beginDate = begin_time_ev.getText().toString();
        final String endDate = end_time_ev.getText().toString();
        DateSearchUtil.searchByDate(beginDate, endDate, new DateSearchListener() {
            @Override
            public void searchByDefaultDate() {
                getTaskList(TimeUtil.getCurDatetime() + "000000",
                        TimeUtil.getCurDatetime() + "235959",
                        1, COUNT_PER_PAGE);
            }

            @Override
            public void searchByChooseDate() {
                getTaskList(beginDate + "000000",
                        endDate + "235959",
                        1, COUNT_PER_PAGE);
            }
        });
    }

    private void getTaskList(String beginTime, String endTime, int curPage, int count){
        HttpLoader.getTasks(beginTime, endTime, curPage, count, new HttpBase.IResponseListener() {
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

                List<TaskModel.TaskBean> taskList = ((TaskModel) model).getDataList();
                if (taskList != null) {
                    mTaskListAdapter.setData(taskList);
                }
            }
        });
    }
}
