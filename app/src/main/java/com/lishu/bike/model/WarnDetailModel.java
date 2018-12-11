package com.lishu.bike.model;


public class WarnDetailModel extends BaseModel {
    private String name;//标题,
    private String warnTime;//告警时间，yyyyMMddHHmmss,
    private String warnContent;//内容

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWarnTime() {
        return warnTime;
    }

    public void setWarnTime(String warnTime) {
        this.warnTime = warnTime;
    }

    public String getWarnContent() {
        return warnContent;
    }

    public void setWarnContent(String warnContent) {
        this.warnContent = warnContent;
    }
}
