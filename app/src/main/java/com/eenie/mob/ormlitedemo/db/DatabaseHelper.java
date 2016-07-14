package com.eenie.mob.ormlitedemo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.eenie.mob.ormlitedemo.bean.Talk;
import com.eenie.mob.ormlitedemo.bean.User;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Project: ORMLiteDemo
 * Desc:
 * Author:Eenie
 * Email:472279981@qq.com
 * Data:2016/7/5
 * Backup:
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {


    private static final String DB_NAME = "orivon.db";
    private static final int DB_VERSION = 2;
    private static DatabaseHelper instance;
    private Map<String, Dao> daoMap = new HashMap<>();
    private DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    /**
     * 获取Data单例
     *
     * @param context
     * @return
     */
    public static synchronized DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            synchronized (DatabaseHelper.class) {
                if (instance == null) {
                    instance = new DatabaseHelper(context);
                }
            }
        }
        return instance;
    }


    /**
     * 创建数据表
     *
     * @param database
     * @param connectionSource
     */
    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, User.class);
            TableUtils.createTable(connectionSource, Talk.class);



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * 数据表更新的时候调用
     *
     * @param database
     * @param connectionSource
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, User.class, true);
            TableUtils.dropTable(connectionSource, Talk.class, true);


            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取实例Dao
     *
     * @return
     * @throws SQLException
     */
    public Dao getBeanDao(Class clazz) throws SQLException {
        String k = clazz.getSimpleName();
        if (daoMap.containsKey(k)) {
            return daoMap.get(k);
        }
        Dao dao = getDao(clazz);
        daoMap.put(k, dao);
        return dao;
    }


    /**
     * 释放资源
     *
     */
    public void close() {
        super.close();
        for (String k : daoMap.keySet()) {
            Dao dao = daoMap.get(k);
            dao = null;
        }
    }
}
