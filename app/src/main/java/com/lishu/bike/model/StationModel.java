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
        private String fenceName;//站点名

        public StationBean(){

        }

        public StationBean(int id, String fenceName) {
            this.id = id;
            this.fenceName = fenceName;
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
    }
}
