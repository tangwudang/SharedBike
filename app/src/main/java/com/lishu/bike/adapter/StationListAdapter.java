package com.lishu.bike.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lishu.bike.R;
import com.lishu.bike.model.StationModel;

import java.util.ArrayList;
import java.util.List;

public class StationListAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<StationModel.StationBean> stationList = new ArrayList<>();

    public StationListAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    public void setData(List<StationModel.StationBean> stationList){
        this.stationList = stationList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return stationList.size();
    }

    @Override
    public Object getItem(int arg0) {
        return stationList.get(arg0);
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
            view = mInflater.inflate(R.layout.adapter_station_list, null);
            viewHolder.stationName = view.findViewById(R.id.station_name);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
      
        viewHolder.stationName.setText(stationList.get(position).getFenceName());

        return view;
    }

    class ViewHolder {
        TextView stationName;
    }
}
