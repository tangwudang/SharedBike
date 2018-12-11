package com.lishu.bike.model;


import java.util.List;

public class AppInfoModel extends BaseModel {

    private List<AppInfoBean> dataList;

    public List<AppInfoBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<AppInfoBean> dataList) {
        this.dataList = dataList;
    }

    public class AppInfoBean {
        private int id;//id,
        private String infoTitle;//标题,
        private String publishTime;//发布时间，yyyyMMddHHmmss,
        private String imageUrl;//标题图片,
        private int appInfoType;//类型（员工风采、会议通知）

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getInfoTitle() {
            return infoTitle;
        }

        public void setInfoTitle(String infoTitle) {
            this.infoTitle = infoTitle;
        }

        public String getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(String publishTime) {
            this.publishTime = publishTime;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public int getAppInfoType() {
            return appInfoType;
        }

        public void setAppInfoType(int appInfoType) {
            this.appInfoType = appInfoType;
        }
    }
}
