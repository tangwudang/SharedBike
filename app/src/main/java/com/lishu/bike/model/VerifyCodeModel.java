package com.lishu.bike.model;


public class VerifyCodeModel extends BaseModel {

    private VerifyCodeBean data;

    public VerifyCodeBean getData() {
        return data;
    }

    public void setData(VerifyCodeBean data) {
        this.data = data;
    }

    public class VerifyCodeBean{
        private String invalidTime;

        public String getInvalidTime() {
            return invalidTime;
        }

        public void setInvalidTime(String invalidTime) {
            this.invalidTime = invalidTime;
        }
    }
}
