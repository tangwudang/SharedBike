package com.lishu.bike.model;


import java.util.List;

public class FenceModel extends BaseModel {

    private List<FenceBean> dataList;

    public List<FenceBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<FenceBean> dataList) {
        this.dataList = dataList;
    }

    public class FenceBean{
        private int id;
        private String fenceName;

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
