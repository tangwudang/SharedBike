package com.lishu.bike.model;


import java.io.Serializable;
import java.util.List;

public class FenceModel extends BaseModel {

    private List<FenceBean> dataList;

    public List<FenceBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<FenceBean> dataList) {
        this.dataList = dataList;
    }

    public class FenceBean implements Serializable {
        private int id;
        private String fenceName;
        private double latitude;//纬度
        private double longitude;//经度

        public FenceBean() {
        }

        public FenceBean(int id, String streetName, double latitude, double longitude) {
            this.id = id;
            this.fenceName = streetName;
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getFenceName() {
            return fenceName;
        }

        public void setFenceName(String fenceName) {
            this.fenceName = fenceName;
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
