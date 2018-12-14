package com.lishu.bike.adatper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lishu.bike.R;
import com.lishu.bike.model.StreetModel;

import java.util.ArrayList;
import java.util.List;

public class StreetListAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<StreetModel.StreetBean> streetList = new ArrayList<>();

    public StreetListAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    public void setData(List<StreetModel.StreetBean> streetList){
        this.streetList = streetList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return streetList.size();
    }

    @Override
    public Object getItem(int arg0) {
        return streetList.get(arg0);
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
            view = mInflater.inflate(R.layout.adapter_street_list, null);
            viewHolder.streetName = view.findViewById(R.id.street_name);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
      
        viewHolder.streetName.setText(streetList.get(position).getStreetName());

        return view;
    }

    class ViewHolder {
        TextView streetName;
    }
}
