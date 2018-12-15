package com.lishu.bike.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lishu.bike.R;
import com.lishu.bike.utils.SystemUtil;
import com.lishu.bike.utils.ToastUtil;

public abstract class BaseActivity extends AppCompatActivity{
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void setTopTitle(String title) {
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

    /*protected void setTopLeft(int drawableId) {
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
    protected void setTopLeftVisible() {
        View right = findViewById(R.id.title_left);
        if (right != null) {
            right.setVisibility(View.VISIBLE);
        }
    }*/

    protected void setTopLeftGone() {
        View right = findViewById(R.id.title_left);
        if (right != null) {
            right.setVisibility(View.GONE);
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

    protected void checkPermissions(String[] permissions, int requestCode) {
        if (permissions == null) {
            return;
        }

        for (int i = 0; i < permissions.length; i++) {
            if (!(SystemUtil.selfPermissionGranted(this, permissions[i]))) {
                ActivityCompat.requestPermissions(this, permissions, requestCode);
                return;
            }
        }

        onRequestPermissionsGranted(requestCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            onRequestPermissionsGranted(requestCode);
        } else {
            onRequestPermissionsDenied(requestCode);
        }
    }

    protected void onRequestPermissionsGranted(int requestCode) {
    }

    protected void onRequestPermissionsDenied(int requestCode) {
        ToastUtil.showShort("无法获取相关权限！");
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.right_in, R.anim.right_out);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.right_in, R.anim.right_out);
    }
}
