package com.eenie.mob.ormlitedemo.dao;

import android.content.Context;

import com.eenie.mob.ormlitedemo.bean.Talk;
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
public class TalkDao {

    DatabaseHelper helper;
    Dao<Talk, Integer> dao;

    public TalkDao(Context context) {
        helper = DatabaseHelper.getInstance(context);
        try {
            dao = helper.getBeanDao(Talk.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void add(Talk talk) {
        try {
            dao.create(talk);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(final List<Talk> talks) {
        try {
            TransactionManager.callInTransaction(helper.getConnectionSource(), new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    long s = System.currentTimeMillis();
                    for (Talk t : talks) {
                        t.getUser().insert();
                        dao.create(t);
                    }
                    System.out.println("insert complete " + (System.currentTimeMillis() - s));
                    return null;
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
