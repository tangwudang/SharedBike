package com.lishu.bike.model;


import java.util.List;

public class AddressBookModel extends BaseModel {
    private List<AddressBook> dataList;

    public List<AddressBook> getDataList() {
        return dataList;
    }

    public void setDataList(List<AddressBook> dataList) {
        this.dataList = dataList;
    }

    public class AddressBook{
        private int id;
        private String name;//姓名
        private String url;//头像URL

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
