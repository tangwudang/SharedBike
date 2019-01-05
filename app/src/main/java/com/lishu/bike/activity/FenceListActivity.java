package com.lishu.bike.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
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
import com.lishu.bike.model.FenceDetailModel;
import com.lishu.bike.model.FenceModel;
import com.lishu.bike.model.StreetModel;
import com.lishu.bike.utils.ToastUtil;
import com.lishu.bike.widget.FenceDetailDialog;

import java.util.ArrayList;
import java.util.List;

public class FenceListActivity extends BaseActivity {
    private MapView mMapView;
    private BaiduMap mBaiduMap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fence);

        initView();

        StreetModel.StreetBean street = (StreetModel.StreetBean)getIntent().getExtras().get("street");

        // 设置地图中心点
        setMapCenter(street.getLatitude(), street.getLongitude());
        // 获取电子围栏列表
        getFences(street.getId());
        // Marker点击事件监听
        setMarkerListener();
    }

    private void initView() {
        setTopTitle("站点信息");
        mMapView = findViewById(R.id.mapView);
        mBaiduMap = mMapView.getMap();

    }

    private void setMapCenter(double latitude, double longitude) {
        //设定中心点坐标
        LatLng centerPos = new LatLng(latitude, longitude);
        //地图状态创建者
        MapStatus.Builder builder = new MapStatus.Builder();
        //设定中心
        builder.target(centerPos).zoom(16.0f);
        //改变地图状态
        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
        mBaiduMap.setIndoorEnable(true);
    }

    private void getFences(int streetId) {
        HttpLoader.getFences(streetId, new HttpBase.IResponseListener() {
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

                List<FenceModel.FenceBean> fenceList = ((FenceModel) model).getDataList();
                if(fenceList != null){
                    // 添加marker
                    addMapMarkers(fenceList);
                }
            }
        });

        //@@@@@@@@@@@@@@@@@@ just for testing, begin @@@@@@@@@@@@@@@@@
        /*List<FenceModel.FenceBean> fenceList = new ArrayList<>();
        fenceList.add(new FenceModel().new FenceBean(1, "东南大学围栏", 31.89493,118.828256));
        fenceList.add(new FenceModel().new FenceBean(2, "小龙湾地铁站小龙湾围栏", 31.935572,118.839467));
        fenceList.add(new FenceModel().new FenceBean(3, "南京航空航天大学航空航天围栏", 31.94458,118.799079));
        addMapMarkers(fenceList);*/
        //@@@@@@@@@@@@@@@@@@ just for testing, end @@@@@@@@@@@@@@@@@@
    }

    private void addMapMarkers(List<FenceModel.FenceBean> fenceList) {
        //创建OverlayOptions的集合
        List<OverlayOptions> options = new ArrayList<OverlayOptions>();
        //构建Marker图标
        //BitmapDescriptor markerIcon = BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher);
        View view = View.inflate(getApplicationContext(), R.layout.marker_fence, null);
        TextView title = view.findViewById(R.id.marker_title);

        for(FenceModel.FenceBean fence : fenceList){
            //设置坐标点
            LatLng point = new LatLng(fence.getLatitude(),fence.getLongitude());
            //将View转化为Bitmap
            title.setText(fence.getFenceName());
            BitmapDescriptor markerIcon = BitmapDescriptorFactory.fromView(view);
            //创建OverlayOptions属性
            OverlayOptions option = new MarkerOptions().position(point).icon(markerIcon);
            //添加数据
            Bundle bundle = new Bundle();
            bundle.putSerializable("fence", fence);
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
                FenceModel.FenceBean fence = (FenceModel.FenceBean)marker.getExtraInfo().get("fence");
                getFenceDetail(fence.getId());
                return false;
            }
        });
        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                //点击地图收起弹出层
            }

            @Override
            public boolean onMapPoiClick(MapPoi mapPoi) {
                return false;
            }
        });
    }

    private void getFenceDetail(int fenceId) {
        //@@@@@@@@@@@@@@@@@@ just for testing, begin @@@@@@@@@@@@@@@@@
        FenceDetailModel fenceDetailModel = new FenceDetailModel();
        /*fenceDetailModel.setFenceName("沙利亚");
        fenceDetailModel.setInstallAddress("江宁区东山街道122号");
        fenceDetailModel.setStreetName("东山街道");
        fenceDetailModel.setRemark("江宁区东山街道122号江宁区东山街道122号江宁区东山街道122号");*/

        if (fenceDetailModel != null) {
            FenceDetailDialog fenceDetailDialog = new FenceDetailDialog(FenceListActivity.this);
            fenceDetailDialog.setDialogData(fenceDetailModel);
        }
        //@@@@@@@@@@@@@@@@@@ just for testing, end @@@@@@@@@@@@@@@@@
        HttpLoader.getFenceDetail(fenceId, new HttpBase.IResponseListener() {
            @Override
            public void onResponse(BaseModel model) {
                if (model == null) {
                    hide();
                    ToastUtil.showShort(R.string.please_check_network);
                    return;
                }
                if (!model.success()) {
                    hide();
                    ToastUtil.showShort(getString(R.string.get_data_fail) + model.getResMsg());
                    return;
                }

                FenceDetailModel fenceDetailModel = (FenceDetailModel) model;
                if (fenceDetailModel != null) {
                    FenceDetailDialog fenceDetailDialog = new FenceDetailDialog(FenceListActivity.this);
                    fenceDetailDialog.setDialogData(fenceDetailModel);
                }
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
