package com.lishu.bike.model;


public class AnalyzesModel extends BaseModel {
    private int ofoCount;//ofo投放数量,
    private int mobikeCount;//摩拜投放数量,
    private int hellobikeCount;//hellobik投放数量，
    private int ofoIllegalCount;//ofo违停数量,
    private int mobikeIllegalCount;// 摩拜违停数量,
    private int hellobikeIllegalCount;//hellobik违停数量，
    private int ofoComplainCount;//ofo投诉数量,
    private int mobikeComplainCount;// 摩拜投诉数量,
    private int hellobikeComplainCount;// hellobik投诉数量，

    public int getOfoCount() {
        return ofoCount;
    }

    public void setOfoCount(int ofoCount) {
        this.ofoCount = ofoCount;
    }

    public int getMobikeCount() {
        return mobikeCount;
    }

    public void setMobikeCount(int mobikeCount) {
        this.mobikeCount = mobikeCount;
    }

    public int getHellobikeCount() {
        return hellobikeCount;
    }

    public void setHellobikeCount(int hellobikeCount) {
        this.hellobikeCount = hellobikeCount;
    }

    public int getOfoIllegalCount() {
        return ofoIllegalCount;
    }

    public void setOfoIllegalCount(int ofoIllegalCount) {
        this.ofoIllegalCount = ofoIllegalCount;
    }

    public int getMobikeIllegalCount() {
        return mobikeIllegalCount;
    }

    public void setMobikeIllegalCount(int mobikeIllegalCount) {
        this.mobikeIllegalCount = mobikeIllegalCount;
    }

    public int getHellobikeIllegalCount() {
        return hellobikeIllegalCount;
    }

    public void setHellobikeIllegalCount(int hellobikeIllegalCount) {
        this.hellobikeIllegalCount = hellobikeIllegalCount;
    }

    public int getOfoComplainCount() {
        return ofoComplainCount;
    }

    public void setOfoComplainCount(int ofoComplainCount) {
        this.ofoComplainCount = ofoComplainCount;
    }

    public int getMobikeComplainCount() {
        return mobikeComplainCount;
    }

    public void setMobikeComplainCount(int mobikeComplainCount) {
        this.mobikeComplainCount = mobikeComplainCount;
    }

    public int getHellobikeComplainCount() {
        return hellobikeComplainCount;
    }

    public void setHellobikeComplainCount(int hellobikeComplainCount) {
        this.hellobikeComplainCount = hellobikeComplainCount;
    }
}
