package com.lishu.bike.constant;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 将用户信息保存在Preferences配置文件中
 */
public class UserPreferences {
    private static UserPreferences mUserPreferences;
    private SharedPreferences sharePreferences;
    private final String USER_ID = "user_id";
    //private final String LOGIN_NAME = "login_name";
    //private final String LOGIN_PASSWORD = "login_password";
    private final String IS_ALREADY_LOGIN = "is_already_login";
    private final String DB_VERSION = "db_version";

    public static UserPreferences getInstance() {
        if (mUserPreferences == null) {
            mUserPreferences = new UserPreferences();
        }
        return mUserPreferences;
    }

    public void init(Context context) {
        if (sharePreferences == null) {
            sharePreferences = context.getSharedPreferences("bike", Context.MODE_PRIVATE);
        }
    }

    /**
     * UserId
     */
    public int getUserId() {
        if (sharePreferences != null) {
            return sharePreferences.getInt(USER_ID, -1);
        }

        return -1;
    }
    public void setUserId(int userId) {
        if (sharePreferences != null) {
            sharePreferences.edit().putInt(USER_ID, userId).commit();
        }
    }

    /**
     * 登录账号
     */
    /*public void saveLoginName(String loginName) {
        if (sharePreferences != null) {
            sharePreferences.edit().putString(LOGIN_NAME, loginName).commit();
        }
    }
    public String getLoginName() {
        if (sharePreferences != null) {
            return sharePreferences.getString(LOGIN_NAME, "");
        }

        return "";
    }*/

    /**
     * 登录密码
     */
    /*public void saveLoginPassword(String password) {
        if (sharePreferences != null) {
            sharePreferences.edit().putString(LOGIN_PASSWORD, password).commit();
        }
    }
    public String getLoginPassword() {
        if (sharePreferences != null) {
            return sharePreferences.getString(LOGIN_PASSWORD, "");
        }
        return "";
    }*/

    /**
     * 是否已经登录
     */
    public void setAlreadyLoginFlag(boolean is_already_login){
        if (sharePreferences != null) {
            sharePreferences.edit().putBoolean(IS_ALREADY_LOGIN, is_already_login).commit();
        }
    }
    public boolean getAlreadyLoginFlag(){
        if (sharePreferences != null) {
            return sharePreferences.getBoolean(IS_ALREADY_LOGIN, false);
        }
        return false;
    }

    /**
     * 数据库版本
     */
    public int getDbVersion() {
        if (sharePreferences != null) {
            return sharePreferences.getInt(DB_VERSION, 1);
        }

        return 1;
    }
    public void setDbVersion(int version) {
        if (sharePreferences != null) {
            sharePreferences.edit().putInt(DB_VERSION, version).commit();
        }
    }

    /**
     * 清除登录信息
     */
    public void logout() {
        if (sharePreferences != null) {
            sharePreferences.edit().remove(USER_ID).commit();
            //sharePreferences.edit().remove(LOGIN_NAME).commit();
            //sharePreferences.edit().remove(LOGIN_PASSWORD).commit();
            sharePreferences.edit().remove(IS_ALREADY_LOGIN).commit();
        }
    }
}
