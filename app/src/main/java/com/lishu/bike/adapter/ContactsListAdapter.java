package com.lishu.bike.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lishu.bike.R;
import com.lishu.bike.constant.HttpAddress;
import com.lishu.bike.model.AddressBookModel;
import com.lishu.bike.utils.ImageUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 联系人列表adapter
 */
public class ContactsListAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<AddressBookModel.AddressBook> mContactsList = new ArrayList();
    private boolean mShowFirstPinyin = false;

    public ContactsListAdapter(Context context, boolean showFirstPinyin) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mShowFirstPinyin = showFirstPinyin;
    }

    public void setData(List<AddressBookModel.AddressBook> contactsList){
        if(contactsList != null) {
            mContactsList = contactsList;
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return mContactsList.size();
    }

    @Override
    public Object getItem(int arg0) {
        return mContactsList.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(view == null) {
            viewHolder = new ViewHolder();
            view = mInflater.inflate(R.layout.adapter_of_contacts_list, null);
            viewHolder.userAvatar = (ImageView) view.findViewById(R.id.avatar);
            viewHolder.userName = (TextView) view.findViewById(R.id.name);
            viewHolder.introduce = (TextView) view.findViewById(R.id.introduce);
            viewHolder.tv_tag = (TextView) view.findViewById(R.id.tv_lv_item_tag);
            viewHolder.split_line_view = (View) view.findViewById(R.id.split_line_view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        AddressBookModel.AddressBook entity = mContactsList.get(position);
        viewHolder.userName.setText(entity.getName());
        viewHolder.introduce.setText(entity.getOrganizationName());

        String path = entity.getUrl();
        if(!TextUtils.isEmpty(path)){
            //Uri photoUri = Uri.parse(path);
            //LogUtils.d("displayPhotoUri= "+photoUri);
            //LogUtils.d("displayPhotoUri= " + ContentUriUtil.uriToRealPath(photoUri));
            //LogUtils.d("displayPhotoUri= " + UriToPathUtil.getRealFilePath(mContext, photoUri));
            String realPath = HttpAddress.ROOT + path;
            ImageUtil.setUserAvatar(viewHolder.userAvatar, realPath, R.drawable.person_default);

            //viewHolder.userAvatar.setImageURI(Uri.parse(path));
            //InputStream input = ContactsContract.Contacts.openContactPhotoInputStream(NoticeApplication.mContext.getContentResolver(), photoUri);
            //Bitmap photo = BitmapFactory.decodeStream(input);
            //viewHolder.userAvatar.setImageBitmap(BitmapFactory.decodeStream(input));
            //viewHolder.userAvatar.setImageBitmap(BitmapUtil.getBitmapFromUri(mContext, photoUri));
        }

        int selection = entity.getFirstPinYin().charAt(0);

        int positionForSelection = getPositionForSelection(selection);
        if (position == positionForSelection && mShowFirstPinyin) {
            viewHolder.tv_tag.setVisibility(View.VISIBLE);
            viewHolder.tv_tag.setText(entity.getFirstPinYin());
            viewHolder.split_line_view.setVisibility(View.GONE);
        } else {
            viewHolder.tv_tag.setVisibility(View.GONE);
            viewHolder.split_line_view.setVisibility(View.VISIBLE);
        }

        return view;
    }

    public int getPositionForSelection(int selection) {
        for (int i = 0; i < mContactsList.size(); i++) {
            String Fpinyin = mContactsList.get(i).getFirstPinYin();
            char first = Fpinyin.toUpperCase().charAt(0);
            if (first == selection) {
                return i;
            }
        }
        return -1;

    }

    class ViewHolder {
        ImageView userAvatar;
        TextView userName;
        TextView introduce;

        TextView tv_tag;
        View split_line_view;
    }

}
