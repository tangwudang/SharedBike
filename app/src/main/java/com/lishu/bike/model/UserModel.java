package com.lishu.bike.model;


import java.util.List;

public class UserModel extends BaseModel {
    private String userId;
    private List<UserBean> dataList;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<UserBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<UserBean> dataList) {
        this.dataList = dataList;
    }

    public class UserBean{
        private String id;
        private String no;//编号
        private String name;//姓名
        private String age;//年龄
        private String phone;//手机号
        private String address;//住址
        private String url;//头像URL
        private String organizationName;//机构名

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
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
    }
}
