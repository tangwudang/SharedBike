package com.lishu.bike.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.lishu.bike.R;
import com.lishu.bike.adapter.PictureGridViewAdapter;
import com.lishu.bike.http.HttpBase;
import com.lishu.bike.http.HttpLoader;
import com.lishu.bike.model.BaseModel;
import com.lishu.bike.model.TaskDetailModel;
import com.lishu.bike.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class TaskDetailActivity extends BaseActivity{
    private TextView taskName, taskAddress, taskContent, sendTime, resultStatus;
    private int taskId;
    //现场图片
    private GridView mPictureGridView;
    private PictureGridViewAdapter mPictureGridViewAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        initView();
        initEvent();

        taskId = getIntent().getIntExtra("taskId", -1);
        getTaskDetail(taskId);
    }

    private void initView() {
        setTopTitle("任务详情");

        taskName = findViewById(R.id.taskName);
        taskAddress = findViewById(R.id.taskAddress);
        taskContent = findViewById(R.id.taskContent);
        sendTime = findViewById(R.id.sendTime);
        resultStatus = findViewById(R.id.resultStatus);
        //现场照片
        mPictureGridView = findViewById(R.id.picture_gridview);
        mPictureGridViewAdapter = new PictureGridViewAdapter(this);
        mPictureGridView.setAdapter(mPictureGridViewAdapter);

        //@@@@@@@@@@@@@@@@@@ just for testing, begin @@@@@@@@@@@@@@@@@
        List<String> testList = new ArrayList<>();
        testList.add("1");
        testList.add("2");
        testList.add("3");
        testList.add("4");
        mPictureGridViewAdapter.setData(testList);
        //@@@@@@@@@@@@@@@@@@ just for testing, end @@@@@@@@@@@@@@@@@
    }

    private void initEvent() {
        findViewById(R.id.process_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TaskDetailActivity.this, TaskDisposeActivity.class);
                intent.putExtra("taskId", taskId);
                startActivity(intent);
            }
        });
        mPictureGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                Intent intent = new Intent(TaskDetailActivity.this, PhotoViewActivity.class);
                intent.putExtra("imageUrl", (String)parent.getItemAtPosition(position));
                startActivity(intent);
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

                    String []imgs = taskDetail.getTaskImages().split(",");
                    List<String> imageList = new ArrayList<>();
                    for(int i = 0; i < imgs.length; i++){
                        imageList.add(imgs[i]);
                    }
                    mPictureGridViewAdapter.setData(imageList);
                }
            }
        });
    }
}
