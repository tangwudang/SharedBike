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
import com.lishu.bike.model.InspectDetailModel;
import com.lishu.bike.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class InspectDetailActivity extends BaseActivity{
    private TextView dutyName, inspectTime, inspectAddress, inspectType, inspectContent;
    //现场图片
    private GridView mPictureGridView;
    private PictureGridViewAdapter mPictureGridViewAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspect_detail);

        initView();
        initEvent();

        int inspectId = getIntent().getIntExtra("inspectId", -1);
        getInspectDetail(inspectId);
    }

    private void initView() {
        setTopTitle("巡检信息详情");

        dutyName = findViewById(R.id.dutyName);
        inspectTime = findViewById(R.id.inspectTime);
        inspectAddress = findViewById(R.id.inspectAddress);
        inspectType = findViewById(R.id.inspectType);
        inspectContent = findViewById(R.id.inspectContent);

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
        mPictureGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                Intent intent = new Intent(InspectDetailActivity.this, PhotoViewActivity.class);
                intent.putExtra("imageUrl", (String)parent.getItemAtPosition(position));
                startActivity(intent);
            }
        });
    }

    /**
     * 获取巡检详情
     */
    private void getInspectDetail(int inspectId) {
        HttpLoader.getInspectDetail(inspectId, new HttpBase.IResponseListener() {
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

                InspectDetailModel inspectDetail = (InspectDetailModel) model;
                if(inspectDetail != null){
                    dutyName.setText(inspectDetail.getName());
                    inspectTime.setText(inspectDetail.getInspectTime());
                    inspectAddress.setText(inspectDetail.getInspectAddress());
                    inspectType.setText(inspectDetail.getTypeName());
                    inspectContent.setText(inspectDetail.getInspectContent());

                    String []imgs = inspectDetail.getInspectImages().split(",");
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
