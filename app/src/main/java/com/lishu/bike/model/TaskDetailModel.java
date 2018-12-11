package com.lishu.bike.model;


public class TaskDetailModel extends BaseModel {
    private int sendUserId;//任务下发人id,
    private String sendUserName;//任务下发人姓名，
    private String sendTime;// 任务下发时间，yyyyMMddHHmmss,
    private String resultStatus;//任务处理状态(0:未处理，1;已处理)
    private String taskNo;//任务编号，
    private String taskName;//任务名称，
    private String taskContent;//任务内容，
    private String taskImages;//现在图片,
    private String taskAddress;//地址

    public int getSendUserId() {
        return sendUserId;
    }

    public void setSendUserId(int sendUserId) {
        this.sendUserId = sendUserId;
    }

    public String getSendUserName() {
        return sendUserName;
    }

    public void setSendUserName(String sendUserName) {
        this.sendUserName = sendUserName;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(String resultStatus) {
        this.resultStatus = resultStatus;
    }

    public String getTaskNo() {
        return taskNo;
    }

    public void setTaskNo(String taskNo) {
        this.taskNo = taskNo;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskContent() {
        return taskContent;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }

    public String getTaskImages() {
        return taskImages;
    }

    public void setTaskImages(String taskImages) {
        this.taskImages = taskImages;
    }

    public String getTaskAddress() {
        return taskAddress;
    }

    public void setTaskAddress(String taskAddress) {
        this.taskAddress = taskAddress;
    }
}
