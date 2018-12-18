package com.lishu.bike.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.lishu.bike.R;
import com.lishu.bike.utils.ImageUtil;

import java.util.ArrayList;
import java.util.List;

public class PictureGridViewAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<String> imageUrlList = new ArrayList<>();

    public PictureGridViewAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    public void setData(List<String> imageUrlList){
        this.imageUrlList = imageUrlList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return imageUrlList.size();
    }

    @Override
    public Object getItem(int position) {
        if (imageUrlList.size() > 0) {
            return imageUrlList.get(position);
        } else {
            return null;
        }
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
            view = mInflater.inflate(R.layout.adapter_picture_gridview, null);
            viewHolder.imageView = view.findViewById(R.id.imageView);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        String path = imageUrlList.get(position);
        if(!TextUtils.isEmpty(path)) {
            ImageUtil.bindImageView(viewHolder.imageView, path, R.drawable.img_mobike);
        }

        return view;
    }

    class ViewHolder {
        ImageView imageView;
    }
    
}
