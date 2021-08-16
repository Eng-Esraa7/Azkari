package com.example.azkary.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class cnt extends SQLiteOpenHelper {
    public static final String DatabaseName="app2.dp"; //name of database
    public cnt(Context context) {
        super(context, DatabaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table cnt(id integer primary key autoincrement, num Integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists cnt");
        onCreate(db);
    }
    public String insertnewnum(int num){
        SQLiteDatabase s = this.getWritableDatabase();

        ContentValues c = new ContentValues();
        c.put("num",num);
        long res = s.insert("cnt",null,c);
        if(res == -1)
            return "Erorr";
        else
            return "Inserted row";
    }

    public ArrayList<Integer> Getdata(){
        ArrayList <Integer> arr = new ArrayList<Integer>();
        SQLiteDatabase s = this.getReadableDatabase();

        Cursor c = s.rawQuery("select * from cnt",null);

        c.moveToFirst();

        while (c.isAfterLast()==false){
            arr.add(c.getInt(1));
            c.moveToNext();
        }
        s.close();
        return arr;
    }
}
