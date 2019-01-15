package com.lishu.bike.activity;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lishu.bike.R;
import com.lishu.bike.constant.HttpAddress;
import com.lishu.bike.http.HttpBase;
import com.lishu.bike.http.HttpLoader;
import com.lishu.bike.model.AddressBookDetailModel;
import com.lishu.bike.model.AddressBookModel;
import com.lishu.bike.model.BaseModel;
import com.lishu.bike.utils.ImageUtil;
import com.lishu.bike.utils.ToastUtil;

public class AddressBookDetailActivity extends BaseActivity implements View.OnClickListener{
    private ImageView avatar;
    private TextView name, sex, age, no, phone, address;
    private String phoneNum;
    private AddressBookModel.AddressBook contactEntity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_book_detail);

        initView();

        String contactId = getIntent().getStringExtra("contactId");
        contactEntity = (AddressBookModel.AddressBook)getIntent().getSerializableExtra("contact");
        setDefaultDetail();
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

    private void getAddressBookDetail(String contactId) {
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
                    String pSex = bookDetail.getSex();
                    if("1".equals(pSex)){
                        sex.setText("女");
                    }else{
                        sex.setText("男");
                    }
                    age.setText(bookDetail.getAge());
                    no.setText(bookDetail.getNo());
                    phoneNum = bookDetail.getPhone();
                    phone.setText(phoneNum);
                    address.setText(bookDetail.getAddress());
                    phone.setOnClickListener(AddressBookDetailActivity.this);

                    String path = bookDetail.getUrl();
                    if(!TextUtils.isEmpty(path)){
                        String realPath = HttpAddress.ROOT + path;
                        ImageUtil.setUserAvatar(avatar, realPath, R.drawable.person_default);
                    }
                }
            }
        });
    }

    private void setDefaultDetail() {
        if (contactEntity != null) {
            name.setText(contactEntity.getName());
        }
    }

    @Override
    public void onClick(View view) {
        checkPermissions(new String[]{Manifest.permission.CALL_PHONE}, 100);
    }

    protected void onRequestPermissionsGranted(int requestCode) {
        if (requestCode == 100) {
            try {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNum));
                startActivity(intent);
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }
    }
}
