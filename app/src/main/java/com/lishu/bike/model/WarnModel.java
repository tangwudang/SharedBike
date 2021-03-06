package com.lishu.bike.model;


import java.util.List;

public class WarnModel extends BaseModel {

    private List<WarnBean> dataList;

    public List<WarnBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<WarnBean> dataList) {
        this.dataList = dataList;
    }

    public class WarnBean{
        private int id;
        private String warnTitle;//任务标题，
        private String warnTime;//告警时间 ，yyyyMMddHHmmss,
        private String warnType;//告警类型

        public WarnBean(){

        }

        public WarnBean(int id, String warnTitle, String warnTime, String warnType) {
            this.id = id;
            this.warnTitle = warnTitle;
            this.warnTime = warnTime;
            this.warnType = warnType;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getWarnTime() {
            return warnTime;
        }

        public void setWarnTime(String warnTime) {
            this.warnTime = warnTime;
        }

        public String getWarnTitle() {
            return warnTitle;
        }

        public void setWarnTitle(String warnTitle) {
            this.warnTitle = warnTitle;
        }

        public String getWarnType() {
            return warnType;
        }

        public void setWarnType(String warnType) {
            this.warnType = warnType;
        }
    }
}
