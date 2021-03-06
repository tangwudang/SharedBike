package com.lishu.bike.model;


import java.util.List;

public class TaskModel extends BaseModel {

    private List<TaskBean> dataList;

    public List<TaskBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<TaskBean> dataList) {
        this.dataList = dataList;
    }

    public class TaskBean{
        private int id;
        private String taskTime;//任务时间 ，yyyyMMddHHmmss,
        private String taskTitle;//任务标题，
        private String taskType;//任务类型，
        private String resultStatus;//处理状态(0:未处理，1;已处理)

        public TaskBean(){

        }

        public TaskBean(int id, String taskTitle, String taskTime, String taskType, String resultStatus) {
            this.id = id;
            this.taskTime = taskTime;
            this.taskTitle = taskTitle;
            this.taskType = taskType;
            this.resultStatus = resultStatus;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTaskTime() {
            return taskTime;
        }

        public void setTaskTime(String taskTime) {
            this.taskTime = taskTime;
        }

        public String getTaskTitle() {
            return taskTitle;
        }

        public void setTaskTitle(String taskTitle) {
            this.taskTitle = taskTitle;
        }

        public String getTaskType() {
            return taskType;
        }

        public void setTaskType(String taskType) {
            this.taskType = taskType;
        }

        public String getResultStatus() {
            return resultStatus;
        }

        public void setResultStatus(String resultStatus) {
            this.resultStatus = resultStatus;
        }
    }
}
