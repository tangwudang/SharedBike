package com.lishu.bike.model;


import java.util.List;

public class StationModel extends BaseModel {
    private List<StationBean> dataList;

    public List<StationBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<StationBean> dataList) {
        this.dataList = dataList;
    }

    public class StationBean{
        private int id;
        private String stationName;//站点名

        public StationBean(int id, String stationName) {
            this.id = id;
            this.stationName = stationName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getStationName() {
            return stationName;
        }

        public void setStationName(String stationName) {
            this.stationName = stationName;
        }
    }
}
