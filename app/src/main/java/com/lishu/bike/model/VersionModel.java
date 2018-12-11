package com.lishu.bike.model;


public class VersionModel extends BaseModel {
    private String versionName;// 版本名称, V1.0.0,
    private String versionCode;// 版本号,从1开始, 每次改一下代码递增1，
    private String downUrl ;// 版本下载地址,
    private String versionDesc;// 版本说明

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getDownUrl() {
        return downUrl;
    }

    public void setDownUrl(String downUrl) {
        this.downUrl = downUrl;
    }

    public String getVersionDesc() {
        return versionDesc;
    }

    public void setVersionDesc(String versionDesc) {
        this.versionDesc = versionDesc;
    }
}
