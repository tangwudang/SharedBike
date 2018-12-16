package com.lishu.bike.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lishu.bike.R;
import com.lishu.bike.model.WarnModel;
import com.lishu.bike.utils.TimeUtil;

import java.util.ArrayList;
import java.util.List;

public class WarnListAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<WarnModel.WarnBean> warnList = new ArrayList<>();

    public WarnListAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    public void setData(List<WarnModel.WarnBean> warnList){
        this.warnList = warnList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return warnList.size();
    }

    @Override
    public Object getItem(int arg0) {
        return warnList.get(arg0);
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
      
        WarnModel.WarnBean warnBean = warnList.get(position);
        viewHolder.content.setText(warnBean.getWarnTitle());
        String warnType = warnBean.getAskType();
        if("1".equals(warnType)){
            viewHolder.type.setText("员工风采");
        }else if("2".equals(warnType)){
            viewHolder.type.setText("会议通知");
        }
        String warnTime = TimeUtil.getMessageTime(warnBean.getWarnTime());
        if(warnTime != null) {
            viewHolder.time.setText(warnTime);
        }

        return view;
    }

    class ViewHolder {
        TextView content;
        TextView type;
        TextView time;
    }
}
