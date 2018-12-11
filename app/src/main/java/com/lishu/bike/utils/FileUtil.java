package com.lishu.bike.utils;

import android.os.Environment;

import java.io.File;

public class FileUtil {

    public static String getRootPath(){
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/bike");
        if(!file.exists()){
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }

    public static String getTempPath(){
        File file = new File(getRootPath() + "/temp");
        if(!file.exists()){
            file.mkdirs();
        }

        return file.getAbsolutePath();
    }

    public static String getImagePath(){
        File file = new File(getRootPath() + "/image");
        if(!file.exists()){
            file.mkdirs();
        }

        return file.getAbsolutePath();
    }

    public static String getFilePath(){
        File file = new File(getRootPath() + "/file");
        if(!file.exists()){
            file.mkdirs();
        }

        return file.getAbsolutePath();
    }

    public static boolean deleteFile(File file) {
        if (file != null && file.exists()) {
            boolean isDelete = file.delete();
            return isDelete;
        }

        return true;
    }


    public static void deleteFiles(File file) {
        if (file.exists() == false) {
            return;
        } else {
            if (file.isFile()) {
                file.delete();
                return;
            }
            if (file.isDirectory()) {
                File[] childFile = file.listFiles();
                if (childFile == null || childFile.length == 0) {
                    file.delete();
                    return;
                }
                for (File f : childFile) {
                    deleteFile(f);
                }
                file.delete();
            }
        }
    }

    public static boolean isExist(String path){
        if (StringUtil.isEmpty(path)){
            return false;
        }

        if (!new File(path).exists()){
            return false;
        }

        return true;
    }
}
