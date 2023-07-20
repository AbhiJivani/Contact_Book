package com.example.contact_book;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MydataBase extends SQLiteOpenHelper {
    public MydataBase(@Nullable Context context) {
        super(context, "Contacts",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="create table ContactTable(ID Integer primary key autoincrement,NAME text,NUMBER text,EMAIL text)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void add(String name, String email, String number) {
        String query="insert into ContactTable(NAME,NUMBER,EMAIL) values('"+name+"','"+number+"','"+email+"')";
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL(query);
    }

    public Cursor showdata() {
        String query="select * from ContactTable";
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.rawQuery(query,null);
        return cursor;
    }

    public void updateData(int id, String number, String email, String name) {
        String query="update ContactTable set NAME='"+name+"',NUMBER='"+number+"',EMAIL='"+email+"'";
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL(query);
    }

    public void deleteData(int id) {
        String query="delete from ContactTable where ID="+id+"";
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL(query);
    }
}
