package com.lishu.bike.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.lishu.bike.R;
import com.lishu.bike.activity.PhotoViewActivity;
import com.lishu.bike.entity.LivePictureEntity;
import com.lishu.bike.listener.TakePhotoListener;
import com.lishu.bike.utils.ImageUtil;
import com.lishu.bike.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class LivePictureGridAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<LivePictureEntity> imageList = new ArrayList<>();
    private TakePhotoListener photoListener;

    public LivePictureGridAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    public void setData(List<LivePictureEntity> imageList){
        this.imageList = imageList;
        notifyDataSetChanged();
    }

    public void setPhotoListener(TakePhotoListener photoListener) {
        this.photoListener = photoListener;
    }

    @Override
    public int getItemViewType(int position) {
        if (imageList.get(position).getType() == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public Object getItem(int position) {
        if (imageList.size() > 0) {
            return imageList.get(position);
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
        int type = getItemViewType(position);
        if(view == null) {
            viewHolder = new ViewHolder();
            view = mInflater.inflate(R.layout.adapter_pictrue_gridview_edit, null);
            viewHolder.icon = view.findViewById(R.id.live_picture);
            viewHolder.delete = view.findViewById(R.id.delete_picture);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        LivePictureEntity entity = imageList.get(position);
        String imagePath = entity.getIcon();
        viewHolder.icon.setTag(entity);
        viewHolder.delete.setTag(entity);

        if (type == 0) { //加一张现场图片到gridview
            if(TextUtils.isEmpty(imagePath)){
                ToastUtil.showShort("获取图片路径失败");
            }else{
                ImageUtil.bindImageView(viewHolder.icon, imagePath, R.drawable.ic_default_photo);

                viewHolder.icon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, PhotoViewActivity.class);
                        intent.putExtra("localPath",((LivePictureEntity)v.getTag()).getIcon());
                        mContext.startActivity(intent);
                    }
                });
                viewHolder.delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        imageList.remove(v.getTag());
                        notifyDataSetChanged();
                    }
                });
            }
        } else if (type == 1) {//“+图片”的图标
            viewHolder.icon.setBackgroundResource(R.drawable.image_add_photo);
            viewHolder.delete.setVisibility(View.GONE);
            viewHolder.icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //判断是否达到4张
                    if(imageList.size() > 4){
                        ToastUtil.showShort("最多上传4张照片！");
                    }else{
                        photoListener.takePhoto();
                    }
                }
            });
        }

        return view;
    }

    class ViewHolder {
        ImageView icon;
        ImageView delete;
    }
    
}
