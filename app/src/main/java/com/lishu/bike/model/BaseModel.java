package com.lishu.bike.model;


public class BaseModel {

    private String resCode;
    private String resMsg;

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public String getResMsg() {
        return resMsg;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }

    public boolean success(){
        if (resCode != null && (resCode.equalsIgnoreCase("000000") || resCode.equalsIgnoreCase("0"))){
            return true;
        }

        return false;
    }
}
