package com.example.mymovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import java.util.Timer;
import java.util.TimerTask;

public class SplachAcitvity extends AppCompatActivity {
Timer timer ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splach_acitvity);
        timer = new Timer() ;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(SplachAcitvity.this , LoginActivity.class) ;
                startActivity(intent);
                finish();

            }
        },2000);
    }
}
