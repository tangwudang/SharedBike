package com.lishu.bike.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lishu.bike.R;
import com.lishu.bike.http.HttpLoader;

public class TaskDisposeActivity extends BaseActivity {
    private int taskId;
    private EditText disposeContent;
    private Button submit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_dispose);

        taskId = getIntent().getIntExtra("taskId", -1);

        initView();
        initEvent();
    }

    private void initView() {
        setTopTitle("任务处理");
        disposeContent = findViewById(R.id.disposeContent);
    }

    private void initEvent() {
        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitTaskDispose();
            }
        });
    }

    private void submitTaskDispose() {
        //HttpLoader.addTaskDisposeImage();
    }


}
