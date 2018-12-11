package com.lishu.bike.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lishu.bike.constant.AppConfig;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class DBHelper {
    private static DBHelper dbHelper = null;
    private SQLiteDatabase mDatabase = null;
    public String DB_PATH;

    public static DBHelper instance() {
        if (dbHelper == null) {
            dbHelper = new DBHelper();
        }

        return dbHelper;
    }

    private void copyDB(Context cxt) {
        try {
            File file = new File(DB_PATH);
            if (!file.exists()) {
                new File(DB_PATH).getParentFile().mkdirs();
                FileOutputStream fos = new FileOutputStream(file);
                InputStream is = cxt.getAssets().open(AppConfig.DB_NAME);
                byte[] buffer = new byte[512 * 1024];
                int count = 0;
                while ((count = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                }
                is.close();
                fos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void open(Context cxt) {
        DB_PATH = cxt.getDatabasePath(AppConfig.DB_NAME).getPath();

        copyDB(cxt);

        initDatabase();
    }

    private void initDatabase() {
        try {
            mDatabase = SQLiteDatabase.openDatabase(DB_PATH,
                    null, SQLiteDatabase.OPEN_READWRITE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insert(String sql) {
        if (mDatabase == null) {
            initDatabase();
        }
        mDatabase.execSQL(sql);
    }

    public long insert(String table, ContentValues values) {
        if (mDatabase == null) {
            initDatabase();
        }
        return mDatabase.insert(table, null, values);
    }

    public int update(String table, ContentValues values, String whereClause,
                      String[] whereArgs) {
        if (mDatabase == null) {
            initDatabase();
        }
        return mDatabase.update(table, values, whereClause, whereArgs);
    }

    public Cursor query(String table, String[] columns, String selection,
                        String[] selectionArgs, String groupBy, String having,
                        String orderBy) {
        if (mDatabase == null) {
            initDatabase();
        }

        return mDatabase.query(table, columns, selection, selectionArgs,
                groupBy, having, orderBy);
    }

    public Cursor query(String sql) {
        if (mDatabase == null) {
            initDatabase();
        }
        if (mDatabase == null) {
            return null;
        }
        return mDatabase.rawQuery(sql, null);
    }

    public int delete(String table, String whereClause, String[] whereArgs) {
        if (mDatabase == null) {
            initDatabase();
        }
        return mDatabase.delete(table, whereClause, whereArgs);
    }

    public void close() {
        if (mDatabase != null) {
            mDatabase.close();
            mDatabase = null;
        }
    }
}
