package com.lishu.bike.model;


import java.util.List;

public class StationModel extends BaseModel {
    private List<Station> dataList;

    public List<Station> getDataList() {
        return dataList;
    }

    public void setDataList(List<Station> dataList) {
        this.dataList = dataList;
    }

    public class Station{
        private int id;
        private String stationName;//站点名

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
