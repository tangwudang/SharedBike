package com.lishu.bike.model;

import java.util.List;

public class FenceGISModel extends BaseModel {
    private String longitude; //经度
    private String latitude; //纬度
    private int bikeAmount; //电子围栏内自行车数量
    private List<GISBean> dataList;

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public int getBikeAmount() {
        return bikeAmount;
    }

    public void setBikeAmount(int bikeAmount) {
        this.bikeAmount = bikeAmount;
    }

    public List<GISBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<GISBean> dataList) {
        this.dataList = dataList;
    }

    public class GISBean{
        private String bikeMac; //车辆MAC,
        private String longitude; //经度（预留，目前值为“0”），
        private String latitude; //纬度（预留，目前值为“0”），
        private String inTime; //进入区域时间yyyyMMddHHmmss

        public String getBikeMac() {
            return bikeMac;
        }

        public void setBikeMac(String bikeMac) {
            this.bikeMac = bikeMac;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getInTime() {
            return inTime;
        }

        public void setInTime(String inTime) {
            this.inTime = inTime;
        }
    }
}
