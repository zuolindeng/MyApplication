package com.example.apple.myapplication.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.apple.myapplication.model.Tb_outaccount;

/**
 * Created by apple on 2017/12/27.
 */

public class OutaccountDAO {
    private DBOpenHelper helper;
    private SQLiteDatabase db;
    public OutaccountDAO(Context context){
        helper = new DBOpenHelper(context);
    }
    /*
     添加支出消息
     */
    public void add(Tb_outaccount tb_outaccount){
        db = helper.getWritableDatabase();
        //执行添加支出信息操作
        db.execSQL("insert into tb_outaccount (_id,money,time,type,address,mark) values (?,?,?,?,?,?)",new Object[]{
                tb_outaccount.get_id(),tb_outaccount.getMoney(),tb_outaccount.getTime(),tb_outaccount.getType(),tb_outaccount.getAddress(),tb_outaccount.getMark()
        });
    }
    /*
     更新支出信息
     */
    public void update(Tb_outaccount tb_outaccount){
        db = helper.getWritableDatabase();
        //执行修改支出信息操作
        db.execSQL("update tb_outaccount set money = ?,time = ?,type = ?,address = ?,mark = ? where _id = ?",new Object[]{
                tb_outaccount.getMoney(),tb_outaccount.getTime(),tb_outaccount.getType(),tb_outaccount.getAddress(),tb_outaccount.getMark(),tb_outaccount.get_id()
        });
    }
}
