package com.lishu.bike.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.lishu.bike.R;
import com.lishu.bike.http.HttpBase;
import com.lishu.bike.http.HttpLoader;
import com.lishu.bike.model.BaseModel;
import com.lishu.bike.model.StreetModel;
import com.lishu.bike.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class FenceStreetActivity extends BaseActivity {
    private MapView mMapView;
    private BaiduMap mBaiduMap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fence);

        initView();

        // 定位使用者位置
        //。。。。
        // 设置地图中心点
        setMapCenter();
        // 获取围栏街道列表
        getStreets();
        // Marker点击事件监听
        setMarkerListener();
    }

    private void initView() {
        setTopTitle(R.string.fence);
        mMapView = findViewById(R.id.mapView);
        mBaiduMap = mMapView.getMap();
    }

    private void setMapCenter() {
        //设定中心点坐标
        LatLng centerPos = new LatLng(31.935572,118.839467);//小龙湾地铁站
        //地图状态创建者
        MapStatus.Builder builder = new MapStatus.Builder();
        //设定中心
        builder.target(centerPos).zoom(14.0f);
        //改变地图状态
        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
        mBaiduMap.setIndoorEnable(true);
    }

    private void getStreets() {
        HttpLoader.getStreets(new HttpBase.IResponseListener() {
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

                List<StreetModel.StreetBean> streetList = ((StreetModel) model).getDataList();
                if(streetList != null){
                    // 添加marker
                    addMapMarkers(streetList);
                }
            }
        });

        //@@@@@@@@@@@@@@@@@@ just for testing, begin @@@@@@@@@@@@@@@@@
        /*List<StreetModel.StreetBean> streetList = new ArrayList<>();
        streetList.add(new StreetModel().new StreetBean(1, "东南大学", 31.89493,118.828256));
        streetList.add(new StreetModel().new StreetBean(2, "小龙湾", 31.935572,118.839467));
        streetList.add(new StreetModel().new StreetBean(3, "南京航空航天大学", 31.94458,118.799079));
        addMapMarkers(streetList);*/
        //@@@@@@@@@@@@@@@@@@ just for testing, end @@@@@@@@@@@@@@@@@@
    }

    private void addMapMarkers(List<StreetModel.StreetBean> streetList) {
        //创建OverlayOptions的集合
        List<OverlayOptions> options = new ArrayList<OverlayOptions>();
        //构建Marker图标
        //BitmapDescriptor markerIcon = BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher);
        View view = View.inflate(getApplicationContext(), R.layout.marker_street, null);
        TextView title = view.findViewById(R.id.marker_title);

        for(StreetModel.StreetBean street : streetList){
            //设置坐标点
            LatLng point = new LatLng(street.getLatitude(), street.getLongitude());
            //将View转化为Bitmap
            title.setText(street.getStreetName());
            BitmapDescriptor markerIcon = BitmapDescriptorFactory.fromView(view);
            //创建OverlayOptions属性
            OverlayOptions option = new MarkerOptions().position(point).icon(markerIcon);
            //添加数据
            Bundle bundle = new Bundle();
            bundle.putSerializable("street", street);
            ((MarkerOptions) option).extraInfo(bundle);
            //将OverlayOptions添加到list
            options.add(option);
        }

        //在地图上批量添加
        mBaiduMap.addOverlays(options);
    }

    private void setMarkerListener() {
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            // 参数为被点击的marker
            @Override
            public boolean onMarkerClick(Marker marker) {
                //StreetModel.StreetBean street = (StreetModel.StreetBean)marker.getExtraInfo().get("street");
                Intent intent = new Intent(FenceStreetActivity.this, FenceListActivity.class);
                intent.putExtras(marker.getExtraInfo());
                startActivity(intent);
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }
}
