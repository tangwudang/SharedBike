package com.lishu.bike.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.lishu.bike.R;
import com.lishu.bike.adatper.TaskListAdapter;
import com.lishu.bike.http.HttpBase;
import com.lishu.bike.http.HttpLoader;
import com.lishu.bike.listener.DateSearchListener;
import com.lishu.bike.model.BaseModel;
import com.lishu.bike.model.TaskModel;
import com.lishu.bike.utils.DateSearchUtil;
import com.lishu.bike.utils.TimeUtil;
import com.lishu.bike.utils.ToastUtil;
import com.lishu.bike.widget.MyDatePickerDialog;

import java.util.Calendar;
import java.util.List;

public class TaskListActivity extends BaseActivity implements View.OnClickListener {
    private Calendar calender;
    private EditText begin_time_ev, end_time_ev;
    private ImageView search_icon;
    private ListView task_list;
    private TaskListAdapter mTaskListAdapter;

    private int curPage = 1;
    private final int COUNT_PER_PAGE = 10;//每页条数

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        calender = Calendar.getInstance();

        initView();
        initEvent();
    }

    private void initView() {
        setTopTitle(R.string.task);
        begin_time_ev = findViewById(R.id.begin_time_ev);
        end_time_ev = findViewById(R.id.end_time_ev);
        task_list = findViewById(R.id.task_list);
        mTaskListAdapter = new TaskListAdapter(this);
        task_list.setAdapter(mTaskListAdapter);
    }

    private void initEvent() {
        begin_time_ev.setOnClickListener(this);
        end_time_ev.setOnClickListener(this);
        findViewById(R.id.search_icon).setOnClickListener(this);
        task_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                int taskId = ((TaskModel.TaskBean)parent.getItemAtPosition(position)).getId();
                Intent intent = new Intent(TaskListActivity.this, TaskDetailActivity.class);
                intent.putExtra("inspectId", taskId);
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
            case R.id.search_icon:
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

    /**
     * 时间选择对话框
     * @param type:1、开始时间 2、结束时间
     */
    private void showDatePickerDialog(final int type) {
        new MyDatePickerDialog(TaskListActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        monthOfYear = monthOfYear + 1;
                        //修改时间显示，例如 7月8日，显示为 07月08日
                        String myMonth, myDay;
                        if(monthOfYear < 10){
                            myMonth = "0" + monthOfYear;
                        }else{
                            myMonth = "" + monthOfYear;
                        }
                        if(dayOfMonth < 10){
                            myDay = "0" + dayOfMonth;
                        }else{
                            myDay = "" + dayOfMonth;
                        }
                        if (type == 0) {
                            //begin_time_ev.setText(year + "年" + monthOfYear + "月" + dayOfMonth + "日");
                            begin_time_ev.setText("" + year + monthOfYear + dayOfMonth);
                        } else if (type == 1) {
                            //end_time_ev.setText(year + "年" + monthOfYear + "月" + dayOfMonth + "日");
                            end_time_ev.setText("" + year + monthOfYear + dayOfMonth);
                        }
                    }
                },
                calender.get(Calendar.YEAR), // 传入年份
                calender.get(Calendar.MONTH), // 传入月份
                calender.get(Calendar.DAY_OF_MONTH) // 传入天数
        ).show();
    }
}
