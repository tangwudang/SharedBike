package com.lishu.bike.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lishu.bike.R;
import com.lishu.bike.adapter.ContactsListAdapter;
import com.lishu.bike.http.HttpBase;
import com.lishu.bike.http.HttpLoader;
import com.lishu.bike.model.AddressBookModel;
import com.lishu.bike.model.BaseModel;
import com.lishu.bike.utils.PinyinUtils;
import com.lishu.bike.utils.ToastUtil;
import com.lishu.bike.widget.SideBar;

import java.util.ArrayList;
import java.util.List;

public class AddressBookActivity extends BaseActivity{
    //联系人
    private RelativeLayout layout_contacts;
    private EditText mSearchKeyword;//搜索
    private LinearLayout mSearchHintLayout;
    private List<AddressBookModel.AddressBook> mShowContactList = new ArrayList<>(); //界面上显示出来的好友列表
    private ListView contactsListView;
    private ContactsListAdapter contactsListAdapter;
    private SideBar sidebar;
    private TextView dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_book);

        initView();
        initEvent();

        getAddressBook();

        testData();
    }

    private void getAddressBook() {
        HttpLoader.addressBooks(new HttpBase.IResponseListener() {
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

                List<AddressBookModel.AddressBook> bookList = ((AddressBookModel) model).getDataList();
                if (bookList != null) {
                    mShowContactList = bookList;
                    contactsListAdapter.setData(mShowContactList);
                }
            }
        });
    }

    //@@@@@@@@@@@@@@@@@@ just for testing, begin @@@@@@@@@@@@@@@@@
    private void testData(){
        for(int i = 0; i < 10; i++) {
            AddressBookModel.AddressBook contactBean = new AddressBookModel().new AddressBook();
            contactBean.setName("刘中华");
            contactBean.setOrganizationName("江宁街道管理局副局长兼执行队长");
            contactBean.setId(1);

            try {
                String pinyin = PinyinUtils.getPingYin(contactBean.getName());
                String Fpinyin = pinyin.substring(0, 1).toUpperCase();
                contactBean.setPinYin(pinyin);
                if (Fpinyin.matches("[A-Z]")) {
                    contactBean.setFirstPinYin(Fpinyin);
                } else {
                    contactBean.setFirstPinYin("#");
                }
            } catch (Exception e) {
                e.printStackTrace();
                contactBean.setFirstPinYin("#");
            }

            mShowContactList.add(contactBean);
        }
        for(int i = 0; i < 10; i++) {
            AddressBookModel.AddressBook contactBean = new AddressBookModel().new AddressBook();
            contactBean.setName("张小爱");
            contactBean.setOrganizationName("江宁街道管理局副局长兼执行队长江宁街道管理局副局长兼执行队长");
            contactBean.setId(2);

            try {
                String pinyin = PinyinUtils.getPingYin(contactBean.getName());
                String Fpinyin = pinyin.substring(0, 1).toUpperCase();
                contactBean.setPinYin(pinyin);
                if (Fpinyin.matches("[A-Z]")) {
                    contactBean.setFirstPinYin(Fpinyin);
                } else {
                    contactBean.setFirstPinYin("#");
                }
            } catch (Exception e) {
                e.printStackTrace();
                contactBean.setFirstPinYin("#");
            }

            mShowContactList.add(contactBean);
        }
        for(int i = 0; i < 10; i++) {
            AddressBookModel.AddressBook contactBean = new AddressBookModel().new AddressBook();
            contactBean.setName("司马光");
            contactBean.setOrganizationName("江宁街道管理局副局长兼执行队长江宁街道管理局副局长兼执行队长");
            contactBean.setId(3);

            try {
                String pinyin = PinyinUtils.getPingYin(contactBean.getName());
                String Fpinyin = pinyin.substring(0, 1).toUpperCase();
                contactBean.setPinYin(pinyin);
                if (Fpinyin.matches("[A-Z]")) {
                    contactBean.setFirstPinYin(Fpinyin);
                } else {
                    contactBean.setFirstPinYin("#");
                }
            } catch (Exception e) {
                e.printStackTrace();
                contactBean.setFirstPinYin("#");
            }

            mShowContactList.add(contactBean);
        }
        contactsListAdapter.setData(mShowContactList);
    }
    //@@@@@@@@@@@@@@@@@@ just for testing, end @@@@@@@@@@@@@@@@@

    @Override
    public void onResume() {
        super.onResume();
        mSearchKeyword.getText().clear();
    }

    private void initView() {
        setTopTitle("通讯录");

        //搜索
        mSearchKeyword = findViewById(R.id.search_edit);
        mSearchHintLayout = findViewById(R.id.search_hint);
        //拼音引导条
        dialog = findViewById(R.id.dialog);
        sidebar = findViewById(R.id.sidebar);
        sidebar.setTextView(dialog);
        //联系人列表
        layout_contacts = findViewById(R.id.layout_contacts);
        contactsListView = findViewById(R.id.contacts_list);
        contactsListAdapter = new ContactsListAdapter(this, true);
        contactsListView.setAdapter(contactsListAdapter);
    }

    private void initEvent() {
        contactsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AddressBookModel.AddressBook entity = (AddressBookModel.AddressBook) parent.getItemAtPosition(position);
                Intent intent = new Intent(AddressBookActivity.this, AddressBookDetailActivity.class);
                intent.putExtra("contactId", entity.getId());
                startActivity(intent);
            }
        });

        mSearchKeyword.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s)) {
                    mSearchHintLayout.setVisibility(View.VISIBLE);
                    contactsListAdapter.setData(mShowContactList);
                } else {
                    mSearchHintLayout.setVisibility(View.GONE);
                    contactsListAdapter.setData(getMatch(mShowContactList, mSearchKeyword.getText().toString()));
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        sidebar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                int position = contactsListAdapter.getPositionForSelection(s.charAt(0));

                if (position != -1) {
                    contactsListView.setSelection(position);
                }
            }
        });
    }


    private static List<AddressBookModel.AddressBook> getMatch(List<AddressBookModel.AddressBook> beanList, String input) {
        List<AddressBookModel.AddressBook> resultList = new ArrayList();
        for (AddressBookModel.AddressBook bean : beanList) {
            if (bean.getName().contains(input) || bean.getFirstPinYin().equalsIgnoreCase(input.substring(0, 1))) {
                resultList.add(bean);
            }
        }
        return resultList;
    }
}
