package com.lishu.bike.model;


import java.util.List;

public class InspectTypeModel extends BaseModel {

    private List<InspectTypeBean> dataList;

    public List<InspectTypeBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<InspectTypeBean> dataList) {
        this.dataList = dataList;
    }

    public class InspectTypeBean{
        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }


    }
}
