package com.lishu.bike.model;


public class FenceDetailModel extends BaseModel {
    private String no;// 编号,
    private String name;// 名称,
    private String installTime ;// yyyyMMddHHmmss,
    private String installAddress;// 安装位置,
    private String dutyPersonId;//责任人id,
    private String dutyPersonName;//责任人,
    private String remark;//围栏描述

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

    public String getInstallTime() {
        return installTime;
    }

    public void setInstallTime(String installTime) {
        this.installTime = installTime;
    }

    public String getInstallAddress() {
        return installAddress;
    }

    public void setInstallAddress(String installAddress) {
        this.installAddress = installAddress;
    }

    public String getDutyPersonId() {
        return dutyPersonId;
    }

    public void setDutyPersonId(String dutyPersonId) {
        this.dutyPersonId = dutyPersonId;
    }

    public String getDutyPersonName() {
        return dutyPersonName;
    }

    public void setDutyPersonName(String dutyPersonName) {
        this.dutyPersonName = dutyPersonName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
