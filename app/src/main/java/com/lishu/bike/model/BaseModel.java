package com.lishu.bike.model;


public class BaseModel {

    private String code;
    private String desc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean success(){
        if (code != null && (code.equalsIgnoreCase("000000") || code.equalsIgnoreCase("0"))){
            return true;
        }

        return false;
    }
}
