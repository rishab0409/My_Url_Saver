package com.example.my_url_saver;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "url.db";
    public static final String TABLE_NAME = "url_table";
    public static final String COL_PRIMARY = "PRIMARY_ID";

    public static final String COL_2 = "FOLDER";

    public static final String COL_4 = "URL";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table " + TABLE_NAME +" (PRIMARY_ID INTEGER PRIMARY KEY AUTOINCREMENT,FOLDER TEXT,URL TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public boolean insertData( String folder,String url ){

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();

        contentValues.put(COL_2,folder);

        contentValues.put(COL_4,url);

       long result= sqLiteDatabase.insert(TABLE_NAME,null ,contentValues);

       if(result== -1){
           return false;
       }
       else
           return true;
    }
    public Cursor getAllData(){

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
    public Integer deleteData(String id){

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
       return sqLiteDatabase.delete(TABLE_NAME, "PRIMARY_ID = ?" ,new String[] {id});


    }
}
