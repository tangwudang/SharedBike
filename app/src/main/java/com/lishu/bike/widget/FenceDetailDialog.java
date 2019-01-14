package com.lishu.bike.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lishu.bike.R;
import com.lishu.bike.activity.FenceDetailActivity;
import com.lishu.bike.model.FenceDetailModel;
import com.lishu.bike.utils.TimeUtil;

public class FenceDetailDialog extends AlertDialog {
    private AlertDialog alertDialog;
    private Context mContext;
    private LinearLayout fenceNameLayout, installAddressLayout, remarkLayout, longitudeLayout, installTimeLayout, streetNameLayout, amountLayout, versionLayout;
    private TextView fenceName, installAddress, remark, latitude, longitude, installTime, streetName, amount, version;
    private Button detailMapButton;

    public FenceDetailDialog(Context context) {
        super(context);
        mContext = context;
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
        latitude = window.findViewById(R.id.latitude);
        longitude = window.findViewById(R.id.longitude);
        installTime = window.findViewById(R.id.installTime);
        streetName = window.findViewById(R.id.streetName);
        amount = window.findViewById(R.id.amount);
        version = window.findViewById(R.id.version);
        fenceNameLayout = window.findViewById(R.id.fenceName_layout);
        installAddressLayout = window.findViewById(R.id.installAddress_layout);
        remarkLayout = window.findViewById(R.id.remark_layout);
        longitudeLayout = window.findViewById(R.id.longitude_layout);
        installTimeLayout = window.findViewById(R.id.installTime_layout);
        streetNameLayout = window.findViewById(R.id.streetName_layout);
        amountLayout = window.findViewById(R.id.amount_layout);
        versionLayout = window.findViewById(R.id.version_layout);
        detailMapButton = window.findViewById(R.id.gis_map_button);
    }

    public void setDialogData(final FenceDetailModel fenceDetailModel){
        String fenceNameV = fenceDetailModel.getFenceName();
        String installAddressV = fenceDetailModel.getInstallAddress();
        String remarkV = fenceDetailModel.getRemark();
        String latitudeV = fenceDetailModel.getLatitude();
        String longitudeV = fenceDetailModel.getLongitude();
        String installTimeV = fenceDetailModel.getInstallTime();
        String streetNameV = fenceDetailModel.getStreetName();
        String amountV = "" + fenceDetailModel.getAmount();
        String versionV = fenceDetailModel.getVersion();

        if(TextUtils.isEmpty(fenceNameV)){
            fenceNameLayout.setVisibility(View.GONE);
        }else{
            fenceNameLayout.setVisibility(View.VISIBLE);
            fenceName.setText(fenceNameV);
        }
        if(TextUtils.isEmpty(installAddressV)){
            installAddressLayout.setVisibility(View.GONE);
        }else{
            installAddressLayout.setVisibility(View.VISIBLE);
            installAddress.setText(installAddressV);
        }
        if(TextUtils.isEmpty(remarkV)){
            remarkLayout.setVisibility(View.GONE);
        }else{
            remarkLayout.setVisibility(View.VISIBLE);
            remark.setText(remarkV);
        }
        if(TextUtils.isEmpty(latitudeV) && TextUtils.isEmpty(longitudeV)){
            longitudeLayout.setVisibility(View.GONE);
        }else{
            longitudeLayout.setVisibility(View.VISIBLE);
            latitude.setText("纬度" + latitudeV);
            longitude.setText("经度" + longitudeV);
        }
        if(TextUtils.isEmpty(installTimeV)){
            installTimeLayout.setVisibility(View.GONE);
        }else{
            installTimeLayout.setVisibility(View.VISIBLE);
            installTime.setText(TimeUtil.getMessageTime(installTimeV));
        }
        if(TextUtils.isEmpty(streetNameV)){
            streetNameLayout.setVisibility(View.GONE);
        }else{
            streetNameLayout.setVisibility(View.VISIBLE);
            streetName.setText(streetNameV);
        }
        if(TextUtils.isEmpty(amountV)){
            amountLayout.setVisibility(View.GONE);
        }else{
            amountLayout.setVisibility(View.VISIBLE);
            amount.setText(amountV);
        }
        if(TextUtils.isEmpty(versionV)){
            versionLayout.setVisibility(View.GONE);
        }else{
            versionLayout.setVisibility(View.VISIBLE);
            version.setText(versionV);
        }
        detailMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, FenceDetailActivity.class);
                if(!TextUtils.isEmpty(fenceDetailModel.getLatitude())) {
                    intent.putExtra("latitude", Double.valueOf(fenceDetailModel.getLatitude()));
                }
                if(!TextUtils.isEmpty(fenceDetailModel.getLongitude())) {
                    intent.putExtra("longitude", Double.valueOf(fenceDetailModel.getLongitude()));
                }
                intent.putExtra("streetId", fenceDetailModel.getId());
                mContext.startActivity(intent);
            }
        });
    }

    /*public void dismissDialog(){
        alertDialog.dismiss();
    }*/
}
