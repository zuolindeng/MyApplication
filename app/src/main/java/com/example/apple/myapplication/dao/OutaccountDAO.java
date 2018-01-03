package com.example.apple.myapplication.dao;

import android.content.Context;
import android.database.Cursor;
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
    /*
     查询收入信息
     */
    public Tb_outaccount find(int id){
        db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from tb_outaccount where _id=?",new String[]{String.valueOf(id)});
        if(cursor.moveToNext()){
            return new Tb_outaccount(cursor.getInt(cursor.getColumnIndex("_id")),cursor.getDouble(cursor.getColumnIndex("money")),
                    cursor.getString(cursor.getColumnIndex("time")),cursor.getString(cursor.getColumnIndex("type")),
                    cursor.getString(cursor.getColumnIndex("address")),cursor.getString(cursor.getColumnIndex("mark")));
        }
        return null;
    }
    /*
     删除收入信息
     */
    public void delete(Integer...ids){
        if(ids.length > 0){             //判断是否存在要删除的id
            StringBuffer sb = new StringBuffer();           //创建StringBuffer对象
            for(int i = 0;i<ids.length;i++){            //遍历要删除的id集合
                sb.append('?').append(',');             //将删除条件添加到StringBuffer对象中
            }
            sb.deleteCharAt(sb.length()-1);             //去掉最后一个“，”字符
            db = helper.getWritableDatabase();              //初始化SQLiteDatabase对象
            //执行删除收入信息操作
            db.execSQL("delete from tb_outaccount where _id in ("+sb+")",(Object[]) ids);
        }
    }
}
