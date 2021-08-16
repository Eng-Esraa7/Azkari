package com.example.azkary.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;

public class prayers extends SQLiteOpenHelper {
    public static final String DatabaseName="app.dp"; //name of database


    public prayers(Context context) {
        super(context, DatabaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table prayers(id integer primary key autoincrement, pray Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists prayers");
        onCreate(db);
    }
    public String insertNewPray(String pray){
        SQLiteDatabase s = this.getWritableDatabase();

        ContentValues c = new ContentValues();
        c.put("pray",pray);
        long res = s.insert("prayers",null,c);
        if(res == -1)
            return "Erorr";
        else
            return "Inserted row";
    }

    public ArrayList<prayerclass> Getdata(){
        ArrayList <prayerclass> arr = new ArrayList<prayerclass>();
        SQLiteDatabase s = this.getReadableDatabase();

        Cursor c = s.rawQuery("select * from prayers",null);

        c.moveToFirst();

        while (c.isAfterLast()==false){
            arr.add(new prayerclass(c.getInt(0),c.getString(1)));
            c.moveToNext();
        }
        s.close();
        return arr;
    }

    public void deletepray(String id){
        SQLiteDatabase s = this.getWritableDatabase();
        s.delete("prayers","id=?",new String[]{id});
        s.close();
    }
}
