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
    //private final String USER_NO = "user_no";
    private final String USER_NAME = "user_name";
    private final String USER_AGE = "user_age";
    private final String USER_PHONE = "user_phone";
    private final String USER_ADDRESS = "user_address";
    private final String USER_URL = "user_url";
    private final String USER_ORGANIZATION = "user_organization";

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
     * 用户Id
     */
    public void setUserId(String userId) {
        if (sharePreferences != null) {
            sharePreferences.edit().putString(USER_ID, userId).commit();
        }
    }

    public String getUserId() {
        if (sharePreferences != null) {
            return sharePreferences.getString(USER_ID, "0");
        }
        return "";
    }

    /**
     * 用户名
     */
    public void setUserName(String userName) {
        if (sharePreferences != null) {
            sharePreferences.edit().putString(USER_NAME, userName).commit();
        }
    }

    public String getUserName() {
        if (sharePreferences != null) {
            return sharePreferences.getString(USER_NAME, "");
        }
        return "";
    }

    /**
     * 年龄
     */
    public void setUserAge(String userAge) {
        if (sharePreferences != null) {
            sharePreferences.edit().putString(USER_AGE, userAge).commit();
        }
    }

    public String getUserAge() {
        if (sharePreferences != null) {
            return sharePreferences.getString(USER_AGE, "");
        }
        return "";
    }

    /**
     * 用户手机号
     */
    public void setUserPhone(String userPhone) {
        if (sharePreferences != null) {
            sharePreferences.edit().putString(USER_PHONE, userPhone).commit();
        }
    }

    public String getUserPhone() {
        if (sharePreferences != null) {
            return sharePreferences.getString(USER_PHONE, "");
        }
        return "";
    }

    /**
     * 用户地址
     */
    public void setUserAddress(String userAddress) {
        if (sharePreferences != null) {
            sharePreferences.edit().putString(USER_ADDRESS, userAddress).commit();
        }
    }

    public String getUserAddress() {
        if (sharePreferences != null) {
            return sharePreferences.getString(USER_ADDRESS, "");
        }
        return "";
    }

    /**
     * 用户头像
     */
    public void setUserUrl(String userUrl) {
        if (sharePreferences != null) {
            sharePreferences.edit().putString(USER_URL, userUrl).commit();
        }
    }

    public String getUserUrl() {
        if (sharePreferences != null) {
            return sharePreferences.getString(USER_URL, "");
        }
        return "";
    }

    /**
     * 用户所属机构
     */
    public void setUserOrganization(String userOrganization) {
        if (sharePreferences != null) {
            sharePreferences.edit().putString(USER_ORGANIZATION, userOrganization).commit();
        }
    }

    public String getUserOrganization() {
        if (sharePreferences != null) {
            return sharePreferences.getString(USER_ORGANIZATION, "");
        }
        return "";
    }

    /**
     * 是否已经登录
     */
    public void setAlreadyLoginFlag(boolean is_already_login) {
        if (sharePreferences != null) {
            sharePreferences.edit().putBoolean(IS_ALREADY_LOGIN, is_already_login).commit();
        }
    }

    public boolean getAlreadyLoginFlag() {
        if (sharePreferences != null) {
            return sharePreferences.getBoolean(IS_ALREADY_LOGIN, false);
        }
        return false;
    }

    /**
     * 数据库版本
     */
    public void setDbVersion(int version) {
        if (sharePreferences != null) {
            sharePreferences.edit().putInt(DB_VERSION, version).commit();
        }
    }

    public int getDbVersion() {
        if (sharePreferences != null) {
            return sharePreferences.getInt(DB_VERSION, 1);
        }

        return 1;
    }

    /**
     * 清除登录信息
     */
    public void logout() {
        if (sharePreferences != null) {
            SharedPreferences.Editor editor = sharePreferences.edit();
            editor.remove(USER_ID);
            editor.remove(USER_AGE);
            editor.remove(USER_NAME);
            editor.remove(USER_PHONE);
            editor.remove(USER_ADDRESS);
            editor.remove(USER_URL);
            editor.remove(USER_ORGANIZATION);
            editor.remove(IS_ALREADY_LOGIN);
            editor.commit();
        }
    }
}
