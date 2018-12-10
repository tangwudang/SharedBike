package com.lishu.bike.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;

import com.lishu.bike.interf.IActivity;

public class BaseActivity extends AppCompatActivity implements IActivity {
    private ProgressDialog progressDialog;

    /*protected void setTopTitle(String title) {
        TextView titleView = (TextView) findViewById(R.id.title);
        if (titleView != null) {
            titleView.setText(title);
        }
    }

    protected void setTopTitle(int titleId) {
        TextView titleView = (TextView) findViewById(R.id.title);
        if (titleView != null) {
            titleView.setText(titleId);
        }
    }

    protected void setTopLeft(int drawableId) {
        View left = findViewById(R.id.title_left);
        if (left != null) {
            if (drawableId != -1) {
                left.setVisibility(View.VISIBLE);
                ((ImageView) left).setImageResource(drawableId);
            } else {
                left.setVisibility(View.GONE);
            }
        }
    }

    public void onTopLeftClick(View view) {
        this.finish();
    }

    protected void setTopRight(int drawableId) {
        View right = findViewById(R.id.title_right);
        if (right != null) {
            if (drawableId != -1) {
                right.setVisibility(View.VISIBLE);
                ((ImageView) right).setImageResource(drawableId);
            } else {
                right.setVisibility(View.GONE);
            }
        }
    }

    protected void setTopRight(String titleRight) {
        TextView rightView = (TextView) findViewById(R.id.title_right);
        if (rightView != null) {
            rightView.setVisibility(View.VISIBLE);
            rightView.setText(titleRight);
        }
    }

    protected void setTopRightVisible() {
        View right = findViewById(R.id.title_right);
        if (right != null) {
            right.setVisibility(View.VISIBLE);
        }
    }

    protected void setTopRightGone() {
        View right = findViewById(R.id.title_right);
        if (right != null) {
            right.setVisibility(View.GONE);
        }
    }

    protected void setTopLeftVisible() {
        View right = findViewById(R.id.title_left);
        if (right != null) {
            right.setVisibility(View.VISIBLE);
        }
    }

    protected void setTopLeftGone() {
        View right = findViewById(R.id.title_left);
        if (right != null) {
            right.setVisibility(View.GONE);
        }
    }*/

    protected void show(String msg) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage(msg);
            progressDialog.setCancelable(true);
        }

        try {
            progressDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void hide() {
        if (progressDialog != null) {
            try {
                progressDialog.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                progressDialog = null;
            }
        }
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
    }

    @Override
    public void initEvent() {
    }
}
