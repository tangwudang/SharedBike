package com.lishu.bike.model;


import java.util.List;

public class StreetModel extends BaseModel {
    private List<StreetBook> dataList;

    public List<StreetBook> getDataList() {
        return dataList;
    }

    public void setDataList(List<StreetBook> dataList) {
        this.dataList = dataList;
    }

    public class StreetBook{
        private int id;
        private String streetName;//街道名

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
    }
}
