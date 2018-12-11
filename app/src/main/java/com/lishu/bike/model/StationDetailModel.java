package com.lishu.bike.model;


public class StationDetailModel extends BaseModel {
    private String no;// 编号,
    private String name;// 名称,
    private String installTime ;// yyyyMMddHHmmss,
    private String installAddress;// 安装位置,
    private String dutyPersonId;//责任人id,
    private String dutyPersonName;//责任人,
    private int mobikeNum;//摩拜数量，
    private int ofoNum;//ofo数量，
    private int hellobikeNum;//hellobike数量

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

    public int getMobikeNum() {
        return mobikeNum;
    }

    public void setMobikeNum(int mobikeNum) {
        this.mobikeNum = mobikeNum;
    }

    public int getOfoNum() {
        return ofoNum;
    }

    public void setOfoNum(int ofoNum) {
        this.ofoNum = ofoNum;
    }

    public int getHellobikeNum() {
        return hellobikeNum;
    }

    public void setHellobikeNum(int hellobikeNum) {
        this.hellobikeNum = hellobikeNum;
    }
}
