package com.eenie.mob.ormlitedemo.bean;

import com.eenie.mob.ormlitedemo.dao.UserDao;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Project: ORMLiteDemo
 * Desc:
 * Author:Eenie
 * Email:472279981@qq.com
 * Data:2016/7/13
 * Backup:
 */


@DatabaseTable(tableName = "user")
public class User {


    private UserDao dao;

    @DatabaseField(columnName = "_id", generatedId = true)
    int _id;
    @DatabaseField(columnName = "name")
    String name;

    public User(String name) {
        this.name = name;
    }

    public User(String name, UserDao dao) {
        this.name = name;
        this.dao = dao;
    }

    public User() {

    }

    public void insert() {
        if (dao == null) {
            throw new NullPointerException("userDao is null");
        }
        if (this._id == 0) {
            dao.add(this);
        }
    }

    public UserDao getDao() {
        return dao;
    }

    public void setDao(UserDao dao) {
        this.dao = dao;
    }
}
