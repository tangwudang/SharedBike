package com.lishu.bike.model;


import java.io.Serializable;
import java.util.List;

public class AddressBookModel extends BaseModel implements Serializable {
    private List<AddressBook> dataList;

    public List<AddressBook> getDataList() {
        return dataList;
    }

    public void setDataList(List<AddressBook> dataList) {
        this.dataList = dataList;
    }

    public class AddressBook implements Serializable {
        private String id;
        private String name;//姓名
        private String url;//头像URL
        private String organizationName;//机构名
        private String PinYin;//这个属性不存入数据库
        private String FirstPinYin;//这个属性不存入数据库

        public String getId() {
            return id;
        }

        public void setId(String id) {
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

        public String getOrganizationName() {
            return organizationName;
        }

        public void setOrganizationName(String organizationName) {
            this.organizationName = organizationName;
        }

        public String getPinYin() {
            return PinYin;
        }

        public void setPinYin(String pinYin) {
            PinYin = pinYin;
        }

        public String getFirstPinYin() {
            return FirstPinYin;
        }

        public void setFirstPinYin(String firstPinYin) {
            FirstPinYin = firstPinYin;
        }
    }
}
