package com.lishu.bike.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.lishu.bike.R;
import com.lishu.bike.adapter.SpinnerListAdapter;
import com.lishu.bike.model.InspectTypeModel;

import java.util.List;

public class SpinerPopWindow extends PopupWindow {
    private ListView mListView;
    private SpinnerListAdapter mAdapter;

    public SpinerPopWindow(Context context, AdapterView.OnItemClickListener clickListener) {
        View view = LayoutInflater.from(context).inflate(R.layout.spiner_window_layout, null);
        setContentView(view);

        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setFocusable(true);
        setBackgroundDrawable(new ColorDrawable(0x00));

        mListView = view.findViewById(R.id.listview);
        mAdapter = new SpinnerListAdapter(context);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(clickListener);
    }

    public void setData(List<InspectTypeModel.InspectTypeBean> newList){
        mAdapter.setData(newList);
    }

}
