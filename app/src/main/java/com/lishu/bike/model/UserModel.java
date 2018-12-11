package com.lishu.bike.model;


public class UserModel extends BaseModel {
    private String userId;
    private UserBean dataList;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UserBean getDataList() {
        return dataList;
    }

    public void setDataList(UserBean dataList) {
        this.dataList = dataList;
    }

    public class UserBean{
        private int id;
        private String no;//编号
        private String name;//姓名
        private int age;//年龄
        private String phone;//手机号
        private String address;//住址
        private String url;//头像URL

        public int getId() {
            return id;
        }

        public void setId(int id) {
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

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
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
    }
}