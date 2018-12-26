package com.lishu.bike.model;


import java.io.Serializable;
import java.util.List;

public class StreetModel extends BaseModel implements Serializable{
    private List<StreetBean> dataList;

    public List<StreetBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<StreetBean> dataList) {
        this.dataList = dataList;
    }

    public class StreetBean implements Serializable{
        private int id;
        private String streetName;//街道名
        private double latitude;//纬度
        private double longitude;//经度

        public StreetBean(){

        }

        public StreetBean(int id, String streetName, double latitude, double longitude) {
            this.id = id;
            this.streetName = streetName;
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getStreetName() {
            return streetName;
        }

        public void setStreetName(String streetName) {
            this.streetName = streetName;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }
    }
}
