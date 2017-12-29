package com.example.apple.myapplication.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.apple.myapplication.model.Tb_pwd;

/**
 * Created by apple on 2017/12/27.
 */

public class PwdDAO {
    private DBOpenHelper helper;
    private SQLiteDatabase db;
    public PwdDAO(Context context){
        helper = new DBOpenHelper(context);
    }
    /*
     查询收入信息
     */
    public Tb_pwd find(){
        db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from tb_psw",null);
        if(cursor.moveToNext()){
            return new Tb_pwd(cursor.getString(cursor.getColumnIndex("password")));
        }
        return null;
    }
    /*
     获取总记录数
     */
    public long getCount(){
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select count(password) from tb_psw",null);   //获取总记录数
        if(cursor.moveToNext()){            //判断Cursor是否有数值
            return cursor.getLong(0);           //返回总记录数
        }else{
            return 0;           //如果没有数据，则返回0
        }
    }
}
