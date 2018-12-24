package com.lishu.bike.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lishu.bike.R;
import com.lishu.bike.model.InspectTypeModel;

import java.util.ArrayList;
import java.util.List;

public class SpinnerListAdapter extends BaseAdapter {
    private List<InspectTypeModel.InspectTypeBean> list = new ArrayList<>();

    private Context mContext;
    private LayoutInflater mInflater;

    public SpinnerListAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    public void setData(List<InspectTypeModel.InspectTypeBean> newList){
        list = newList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.spiner_item_layout, null);
            holder.tvName = convertView.findViewById(R.id.tv_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvName.setText(list.get(position).getParamName());
        return convertView;
    }


    private class ViewHolder {
        private TextView tvName;
    }
}