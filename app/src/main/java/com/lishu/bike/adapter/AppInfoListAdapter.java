package com.lishu.bike.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lishu.bike.R;
import com.lishu.bike.model.AppInfoModel;
import com.lishu.bike.utils.TimeUtil;

import java.util.ArrayList;
import java.util.List;

public class AppInfoListAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<AppInfoModel.AppInfoBean> appInfoList = new ArrayList<>();

    public AppInfoListAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    public void setData(List<AppInfoModel.AppInfoBean> appInfoList){
        this.appInfoList = appInfoList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return appInfoList.size();
    }

    @Override
    public Object getItem(int arg0) {
        return appInfoList.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(view == null) {
            viewHolder = new ViewHolder();
            view = mInflater.inflate(R.layout.adapter_common_list, null);
            viewHolder.content = view.findViewById(R.id.content);
            viewHolder.type = view.findViewById(R.id.type);
            viewHolder.time = view.findViewById(R.id.time);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        AppInfoModel.AppInfoBean appInfoBean = appInfoList.get(position);
        viewHolder.content.setText(appInfoBean.getInfoTitle());
        viewHolder.type.setText(appInfoBean.getAppInfoTypeName());
        String infoTime = TimeUtil.getMessageTime(appInfoBean.getPublishTime());
        if(infoTime != null) {
            viewHolder.time.setText(infoTime);
        }

        return view;
    }

    class ViewHolder {
        TextView content;
        TextView type;
        TextView time;
    }
}
