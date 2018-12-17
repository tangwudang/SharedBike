package com.lishu.bike.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.lishu.bike.R;
import com.lishu.bike.http.HttpBase;
import com.lishu.bike.http.HttpLoader;
import com.lishu.bike.model.BaseModel;
import com.lishu.bike.model.TaskDetailModel;
import com.lishu.bike.utils.ToastUtil;

public class TaskDetailActivity extends BaseActivity{
    private TextView taskName, taskAddress, taskContent, sendTime, resultStatus;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        initView();
        initEvent();

        int taskId = getIntent().getIntExtra("taskId", -1);
        getTaskDetail(taskId);
    }

    private void initView() {
        setTopTitle("任务详情");

        taskName = findViewById(R.id.taskName);
        taskAddress = findViewById(R.id.taskAddress);
        taskContent = findViewById(R.id.taskContent);
        sendTime = findViewById(R.id.sendTime);
        resultStatus = findViewById(R.id.resultStatus);
    }

    private void initEvent() {
        findViewById(R.id.process_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    /**
     * 获取详情
     */
    private void getTaskDetail(int taskId) {
        HttpLoader.getTaskDetail(taskId, new HttpBase.IResponseListener() {
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

                TaskDetailModel taskDetail = (TaskDetailModel) model;
                if(taskDetail != null){
                    taskName.setText(taskDetail.getTaskName());
                    taskAddress.setText(taskDetail.getTaskAddress());
                    taskContent.setText(taskDetail.getTaskContent());
                    sendTime.setText(taskDetail.getSendTime());
                    resultStatus.setText(taskDetail.getResultStatus());
                }
            }
        });
    }
}
