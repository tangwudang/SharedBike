package com.lishu.bike.model;


import java.util.List;

public class InspectModel extends BaseModel {

    private List<InspectBean> dataList;

    public List<InspectBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<InspectBean> dataList) {
        this.dataList = dataList;
    }

    public class InspectBean{
        private int id;
        private String inspectTime;
        private String inspectContent;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getInspectTime() {
            return inspectTime;
        }

        public void setInspectTime(String inspectTime) {
            this.inspectTime = inspectTime;
        }

        public String getInspectContent() {
            return inspectContent;
        }

        public void setInspectContent(String inspectContent) {
            this.inspectContent = inspectContent;
        }
    }
}
