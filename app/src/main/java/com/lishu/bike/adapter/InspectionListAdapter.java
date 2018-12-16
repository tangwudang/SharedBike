package com.lishu.bike.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lishu.bike.R;
import com.lishu.bike.model.InspectModel;
import com.lishu.bike.utils.TimeUtil;

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
            view = mInflater.inflate(R.layout.adapter_common_list, null);
            viewHolder.content = view.findViewById(R.id.content);
            viewHolder.type = view.findViewById(R.id.type);
            viewHolder.time = view.findViewById(R.id.time);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
      
        InspectModel.InspectBean inspectBean = inspectList.get(position);
        viewHolder.content.setText(inspectBean.getInspectContent());
        int infoType = inspectBean.getTypeName();
        if(1 == infoType){
            viewHolder.type.setText("员工风采");
        }else if(2 == infoType){
            viewHolder.type.setText("会议通知");
        }
        String infoTime = TimeUtil.getMessageTime(inspectBean.getInspectTime());
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
