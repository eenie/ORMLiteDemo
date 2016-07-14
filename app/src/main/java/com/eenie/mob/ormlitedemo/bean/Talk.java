package com.eenie.mob.ormlitedemo.bean;

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
@DatabaseTable(tableName = "talk")
public class Talk {

    @DatabaseField(columnName = "_id", generatedId = true)
    int _id;
    @DatabaseField(columnName = "content")
    String content;
    @DatabaseField(columnName = "user_id", foreign = true)
    User user;

    public Talk() {

    }

    public int get_id() {
        return _id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
