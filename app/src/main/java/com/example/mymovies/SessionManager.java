package com.example.mymovies;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {


    private SharedPreferences mSharedPreferences ;
    private  SharedPreferences.Editor mEditor ;
    private  Context context ;

    public  SessionManager (Context context )
    {


        this.context = context ;
        mSharedPreferences =context.getSharedPreferences("moviesapp" , Context.MODE_PRIVATE) ;
        mEditor = mSharedPreferences.edit() ;

    }
}
