package com.example.mymovies;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {


    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    private  static final int DATABASE_VERSION = 1 ;
    private  static final  String DATABASE_NAME = "Mydb.db" ;
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table contacts" + "( id integer primary key , name text , phone text , email text , street text )" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       db.execSQL("Drop Table IF EXISTS  contacts");

    }
}
