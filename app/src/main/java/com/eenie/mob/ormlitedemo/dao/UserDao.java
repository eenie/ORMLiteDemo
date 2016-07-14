package com.eenie.mob.ormlitedemo.dao;

import android.content.Context;

import com.eenie.mob.ormlitedemo.bean.User;
import com.eenie.mob.ormlitedemo.db.DatabaseHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.misc.TransactionManager;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Project: ORMLiteDemo
 * Desc:
 * Author:Eenie
 * Email:472279981@qq.com
 * Data:2016/7/13
 * Backup:
 */
public class UserDao {

    DatabaseHelper helper;
    Dao<User, Integer> dao;

    public UserDao(Context context) {
        helper = DatabaseHelper.getInstance(context);
        try {
            dao = helper.getBeanDao(User.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(User user) {
        try {
            dao.create(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(final List<User> users) {
        try {
            TransactionManager.callInTransaction(helper.getConnectionSource(), new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    for (User u : users) {
                        dao.create(u);
                    }
                    return null;
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public User get(int id) {
        try {
            return dao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getAll() {
        try {
            return dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
