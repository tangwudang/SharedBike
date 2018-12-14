package com.lishu.bike.adatper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lishu.bike.R;
import com.lishu.bike.model.InspectModel;

import java.util.ArrayList;
import java.util.List;

public class InspectionListAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<InspectModel.InspectBean> inspectList = new ArrayList<>();

    public InspectionListAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    public void setData(List<InspectModel.InspectBean> inspectList){
        this.inspectList = inspectList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return inspectList.size();
    }

    @Override
    public Object getItem(int arg0) {
        return inspectList.get(arg0);
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
            view = mInflater.inflate(R.layout.adapter_inspection_list, null);
            viewHolder.inspectContent = view.findViewById(R.id.content);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
      
        viewHolder.inspectContent.setText(inspectList.get(position).getInspectContent());

        return view;
    }

    class ViewHolder {
        TextView inspectContent;
    }
}
