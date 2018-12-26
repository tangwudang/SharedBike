package com.lishu.bike.activity;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.lishu.bike.R;
import com.lishu.bike.constant.UserPreferences;
import com.lishu.bike.http.HttpLoader;
import com.lishu.bike.model.BaseModel;
import com.lishu.bike.model.UserModel;
import com.lishu.bike.utils.StringUtil;
import com.lishu.bike.utils.ToastUtil;

import java.util.List;

public class LoginActivity extends BaseActivity {
    private EditText mLoginName, mLoginPassword;
    private Button mLoginButton;
    private LinearLayout mLoginLayout;
    private boolean first_get_scroll_height = true;;
    private int[] location = new int[2];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        initView();
        initEvent();

        // just for testing, begin =======================
        //startActivity(new Intent(LoginActivity.this, MainActivity.class));
        //finish();
        // just for testing, end =======================
    }

    public void initView() {
        mLoginName = findViewById(R.id.edit_name);
        mLoginPassword = findViewById(R.id.edit_password);
        mLoginButton = findViewById(R.id.login_button);
        mLoginLayout = findViewById(R.id.login_layout);
        controlKeyboardLayout(mLoginLayout, mLoginButton);
    }

    public void initEvent() {
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // just for testing, begin =======================
                //startActivity(new Intent(LoginActivity.this, MainActivity.class));
                //finish();
                // just for testing, end =======================
                login(mLoginName.getText().toString(), mLoginPassword.getText().toString());
            }
        });
    }

    private void login(String name, String pwd) {
        if (StringUtil.isEmpty(name)) {
            ToastUtil.showShort(R.string.please_input_login_name);
            return;
        } else if (StringUtil.isEmpty(pwd)) {
            ToastUtil.showShort(R.string.please_input_password);
            return;
        } else if (pwd.length() < 4 || pwd.length() > 20) {
            ToastUtil.showShort(R.string.password_length_wrong);
            return;
        }

        show("登录中，请稍候...");
        HttpLoader.login(name, pwd, new HttpLoader.IResponseListener() {
            @Override
            public void onResponse(BaseModel model) {
                hide();
                if (model == null) {
                    ToastUtil.showShort("登录失败，请检查网络");
                    return;
                }

                if (!model.success()) {
                    ToastUtil.showShort("登录失败：" + model.getResMsg());
                    return;
                }

                List<UserModel.UserBean> userBeanList = ((UserModel)model).getDataList();
                if (userBeanList == null) {
                    ToastUtil.showShort("登录失败：无效的登录信息");
                    return;
                }
                UserModel.UserBean userBean = userBeanList.get(0);
                //保存返回的用户信息
                UserPreferences.getInstance().setUserId(userBean.getId());
                UserPreferences.getInstance().setUserName(userBean.getName());
                UserPreferences.getInstance().setUserAge(userBean.getAge());
                UserPreferences.getInstance().setUserPhone(userBean.getPhone());
                UserPreferences.getInstance().setUserAddress(userBean.getAddress());
                UserPreferences.getInstance().setUserUrl(userBean.getUrl());
                UserPreferences.getInstance().setUserOrganization(userBean.getOrganizationName());
                UserPreferences.getInstance().setAlreadyLoginFlag(true);

                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    /**
     * 滚动root,使scrollToView在root可视区域的底部
     */
    private void controlKeyboardLayout(final View root, final View scrollToView) {
        root.getViewTreeObserver().addOnGlobalLayoutListener( new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                //获取root在窗体的可视区域
                root.getWindowVisibleDisplayFrame(rect);
                //获取root在窗体的不可视区域高度(被其他View遮挡的区域高度)
                int rootInvisibleHeight = root.getRootView().getHeight() - rect.bottom;
                //若不可视区域高度大于100，则键盘显示
                if (rootInvisibleHeight > 220) {
                    //获取scrollToView在窗体的坐标
                    if (first_get_scroll_height) {
                        scrollToView.getLocationInWindow(location);
                        first_get_scroll_height = false;
                    }
                    //计算root滚动高度，使scrollToView在可见区域
                    int scrollHeight = (location[1] + scrollToView.getHeight()) - rect.bottom;
                    root.scrollTo(0, scrollHeight);
                } else {
                    //键盘隐藏
                    root.scrollTo(0, 0);
                }
            }
        });
    }
}
