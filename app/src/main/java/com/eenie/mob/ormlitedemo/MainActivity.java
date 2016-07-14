package com.eenie.mob.ormlitedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.eenie.mob.ormlitedemo.bean.Talk;
import com.eenie.mob.ormlitedemo.bean.User;
import com.eenie.mob.ormlitedemo.dao.TalkDao;
import com.eenie.mob.ormlitedemo.dao.UserDao;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private UserDao userDao;
    private TalkDao talkDao;

    private List<User> users = new ArrayList<>();
    private List<Talk> talks = new ArrayList<>();



    private Button btnInsert;
    private long startTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                userDao.add(users);
                talkDao.add(talks);
            }
        });

        startTime = System.currentTimeMillis();

        userDao = new UserDao(this);
        talkDao = new TalkDao(this);

        for (int i = 0; i < 10000; i++) {
            User user = new User(i + " user", userDao);
            for (int j = 0; j < 2; j++) {
                Talk t = new Talk();
                t.setContent("用户" + i + "的评论" + j);
                t.setUser(user);
                talks.add(t);
            }
        }
        System.out.println("create complete " + (System.currentTimeMillis() - startTime));



    }


}
