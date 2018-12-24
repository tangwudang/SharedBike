package com.lishu.bike.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.lishu.bike.R;
import com.lishu.bike.model.FenceDetailModel;

public class FenceDetailDialog extends AlertDialog {
    private AlertDialog alertDialog;
    //private Context mContext;
    private TextView fenceName, installAddress, remark, longitude, installTime, streetName, amount, version;

    public FenceDetailDialog(Context context) {
        super(context);
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.show();

        initDialog();
    }

    private void initDialog(){
        Window window = alertDialog.getWindow();
        window.setGravity(Gravity.CENTER_HORIZONTAL);
        window.setContentView(R.layout.layout_fence_detail_dialog);
        window.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        fenceName = window.findViewById(R.id.fenceName);
        installAddress = window.findViewById(R.id.installAddress);
        remark = window.findViewById(R.id.remark);
        longitude = window.findViewById(R.id.longitude);
        installTime = window.findViewById(R.id.installTime);
        streetName = window.findViewById(R.id.streetName);
        amount = window.findViewById(R.id.amount);
        version = window.findViewById(R.id.version);
    }

    public void setDialogData(FenceDetailModel fenceDetailModel){
        fenceName.setText(fenceDetailModel.getFenceName());
        installAddress.setText(fenceDetailModel.getInstallAddress());
        remark.setText(fenceDetailModel.getRemark());
        longitude.setText("纬度" + fenceDetailModel.getLatitude() + "经度" + fenceDetailModel.getLongitude());
        installTime.setText(fenceDetailModel.getInstallTime());
        streetName.setText(fenceDetailModel.getStreetName());
        amount.setText("" + fenceDetailModel.getAmount());
        version.setText(fenceDetailModel.getVersion());
    }

    /*public void dismissDialog(){
        alertDialog.dismiss();
    }*/
}
