package com.lishu.bike.task;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.lishu.bike.R;
import com.lishu.bike.http.HttpLoader;
import com.lishu.bike.model.BaseModel;
import com.lishu.bike.model.VersionModel;
import com.lishu.bike.utils.SystemUtil;
import com.lishu.bike.utils.ToastUtil;

public class Upgrade {
    private Context context;
    private ProgressDialog progressDialog;
    private boolean isShow = false;

    public Upgrade(Context context, boolean isShow) {
        this.context = context;
        this.isShow = isShow;
    }

    private void show(String msg) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage(msg);
            progressDialog.setCancelable(false);
        }

        try {
            progressDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void hide() {
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

    /**
     * 检查版本更新
     */
    public void updateVersion() {
        if (isShow) {
            show("正在检查版本...");
        }
        final int localVersionCode = SystemUtil.getVersionCode(context);
        HttpLoader.latestVersion(String.valueOf(localVersionCode),
                SystemUtil.getVersionName(context), new HttpLoader.IResponseListener() {
                    @Override
                    public void onResponse(BaseModel model) {
                        hide();
                        if (model == null) {
                            if (isShow) {
                                ToastUtil.showShort(R.string.please_check_network);
                            }
                            return;
                        }
                        if (!model.success()) {
                            if (isShow) {
                                ToastUtil.showShort("检查版本失败：" + model.getResMsg());
                            }
                            return;
                        }

                        VersionModel versionModel = (VersionModel) model;
                        if (versionModel != null) {
                            String serverVersionCode = versionModel.getVersionCode();
                            if (serverVersionCode != null && (Integer.parseInt(serverVersionCode) > localVersionCode)) {
                                showUpdateDialog(versionModel);
                            }
                        } else {
                            if (isShow) {
                                ToastUtil.showShort("当前已经是最新版本");
                            }
                        }
                    }
                });
    }

    private void showUpdateDialog(final VersionModel versionModel) {
        final AlertDialog showUpdateDialog = new AlertDialog.Builder(context).create();
        showUpdateDialog.show();
        Window window = showUpdateDialog.getWindow();
        window.setContentView(R.layout.dialog_update_version);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView present_version = window.findViewById(R.id.present_version);
        TextView latest_version = window.findViewById(R.id.latest_version);
        TextView tvDesc = window.findViewById(R.id.tv_desc);

        present_version.setText(SystemUtil.getVersionName(context));
        latest_version.setText("V" + versionModel.getVersionName());
        tvDesc.setText(versionModel.getVersionDesc());

        window.findViewById(R.id.btn_not_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showUpdateDialog.dismiss();
            }
        });

        window.findViewById(R.id.btn_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showUpdateDialog.dismiss();
                new DowloadApkTask(context).execute(versionModel.getDownUrl());
            }
        });

    }
}
