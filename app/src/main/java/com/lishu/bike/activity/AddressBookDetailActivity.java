package com.lishu.bike.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.lishu.bike.R;
import com.lishu.bike.http.HttpBase;
import com.lishu.bike.http.HttpLoader;
import com.lishu.bike.model.AddressBookDetailModel;
import com.lishu.bike.model.BaseModel;
import com.lishu.bike.utils.ImageUtil;
import com.lishu.bike.utils.ToastUtil;

public class AddressBookDetailActivity extends BaseActivity{
    private ImageView avatar;
    private TextView name, sex, age, no, phone, address;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_book_detail);

        initView();

        int contactId = getIntent().getIntExtra("contactId", -1);
        getAddressBookDetail(contactId);
    }

    private void initView() {
        setTopTitle("通讯录详情");

        avatar = findViewById(R.id.avatar);
        name = findViewById(R.id.name);
        sex = findViewById(R.id.sex);
        age = findViewById(R.id.age);
        no = findViewById(R.id.no);
        phone = findViewById(R.id.phone);
        address = findViewById(R.id.address);
    }

    private void getAddressBookDetail(int contactId) {
        HttpLoader.addressBookDetail(contactId, new HttpBase.IResponseListener() {
            @Override
            public void onResponse(BaseModel model) {
                if (model == null) {
                    ToastUtil.showShort(R.string.please_check_network);
                    return;
                }
                if (!model.success()) {
                    ToastUtil.showShort(getString(R.string.get_data_fail) + model.getResMsg());
                    return;
                }

                AddressBookDetailModel bookDetail = (AddressBookDetailModel) model;
                if (bookDetail != null) {
                    name.setText(bookDetail.getName());
                    sex.setText(bookDetail.getSex());
                    age.setText(bookDetail.getAge());
                    no.setText(bookDetail.getNo());
                    phone.setText(bookDetail.getPhone());
                    address.setText(bookDetail.getAddress());

                    String path = bookDetail.getUrl();
                    if(!TextUtils.isEmpty(path)){
                        ImageUtil.setUserAvatar(avatar, path, R.drawable.person_default);
                    }
                }
            }
        });
    }
}
