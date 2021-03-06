package com.lishu.bike.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lishu.bike.R;
import com.lishu.bike.model.TaskModel;
import com.lishu.bike.utils.TimeUtil;

import java.util.ArrayList;
import java.util.List;

public class TaskListAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<TaskModel.TaskBean> taskList = new ArrayList<>();

    public TaskListAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    public void setData(List<TaskModel.TaskBean> taskList){
        this.taskList = taskList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return taskList.size();
    }

    @Override
    public Object getItem(int arg0) {
        return taskList.get(arg0);
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
      
        viewHolder.content.setText(taskList.get(position).getTaskTitle());
        TaskModel.TaskBean taskBean = taskList.get(position);
        viewHolder.content.setText(taskBean.getTaskTitle());
        viewHolder.type.setText(taskBean.getTaskType());

        String taskTime = TimeUtil.getMessageTime(taskBean.getTaskTime());
        if(taskTime != null) {
            viewHolder.time.setText(taskTime);
        }

        return view;
    }

    class ViewHolder {
        TextView content;
        TextView type;
        TextView time;
    }
}
