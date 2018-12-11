package com.lishu.bike.model;


public class InspectDetailModel extends BaseModel {
    private String inspectTime;//巡检时间, yyyyMMddHHmmss,
    private int userId;//巡检人id,
    private String name;//巡检人姓名，
    private String typeName;//巡检类型，
    private String inspectContent;//巡检内容，
    private String inspectImages;//现在图片,
    private String inspectAddress;//地址

    public String getInspectTime() {
        return inspectTime;
    }

    public void setInspectTime(String inspectTime) {
        this.inspectTime = inspectTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getInspectContent() {
        return inspectContent;
    }

    public void setInspectContent(String inspectContent) {
        this.inspectContent = inspectContent;
    }

    public String getInspectImages() {
        return inspectImages;
    }

    public void setInspectImages(String inspectImages) {
        this.inspectImages = inspectImages;
    }

    public String getInspectAddress() {
        return inspectAddress;
    }

    public void setInspectAddress(String inspectAddress) {
        this.inspectAddress = inspectAddress;
    }
}
