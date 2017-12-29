package com.example.apple.myapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.apple.myapplication.model.Tb_inaccount;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 2017/12/26.
 */

public class InaccountDAO {
    private DBOpenHelper helper;
    private SQLiteDatabase db;
    public InaccountDAO(Context context){               //定义构造函数
        helper = new DBOpenHelper(context);             //初始化DBOpenHelper对象
    }
    /*
      添加收入信息
     */
    public boolean add(Tb_inaccount tb_inaccount){
        db = helper.getWritableDatabase();          //初始化SQLiteDatebase对象
        ContentValues values = new ContentValues();
        values.put("_id",tb_inaccount.get_id());
        values.put("money",tb_inaccount.getMoney());
        values.put("time",tb_inaccount.getTime());
        values.put("type",tb_inaccount.getType());
        values.put("handler",tb_inaccount.getHandler());
        values.put("mark",tb_inaccount.getMark());
        long rowid = db.insert("tb_inaccount",null,values);
        if(rowid == -1){
            return false;
        }else{
            return true;
        }
    }
    /*
     更新收入信息
    */
    public boolean update(Tb_inaccount tb_inaccount){
        db = helper.getWritableDatabase();          //初始化SQLiteDatebase对象
        ContentValues values = new ContentValues();
        values.put("money",tb_inaccount.getMoney());
        values.put("time",tb_inaccount.getTime());
        values.put("type",tb_inaccount.getType());
        values.put("handler",tb_inaccount.getHandler());
        values.put("mark",tb_inaccount.getMark());
        long rowid = db.update("tb_inaccount",values,"_id=?",new String[]{String.valueOf(tb_inaccount.get_id())});
        if(rowid == -1){
            return false;
        }else{
            return true;
        }
    }
    /*
     查询收入信息
     */
    public Tb_inaccount find(int id){
        db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from tb_inaccount where _id=?",new String[]{String.valueOf(id)});
        if(cursor.moveToNext()){
            return new Tb_inaccount(cursor.getInt(cursor.getColumnIndex("_id")),cursor.getDouble(cursor.getColumnIndex("money")),
                    cursor.getString(cursor.getColumnIndex("time")),cursor.getString(cursor.getColumnIndex("type")),
                    cursor.getString(cursor.getColumnIndex("handler")),cursor.getString(cursor.getColumnIndex("mark")));
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
            db.execSQL("delete from tb_inaccount where _id in ("+sb+")",(Object[]) ids);
        }
    }
    /*
     获取收入信息
     */
    public List<Tb_inaccount> getScrollData(int start,int count){
        List<Tb_inaccount> tb_inaccount = new ArrayList<Tb_inaccount>();            //创建集合对象
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from tb_inaccount limit ?,?",new String[]{String.valueOf(start),String.valueOf(count)});
        while (cursor.moveToNext()){            //遍历所有的收入信息
            tb_inaccount.add(new Tb_inaccount(cursor.getInt(cursor.getColumnIndex("_id")),cursor.getDouble(cursor.getColumnIndex("money")),
                    cursor.getString(cursor.getColumnIndex("time")),cursor.getString(cursor.getColumnIndex("type")),
                    cursor.getString(cursor.getColumnIndex("handler")),cursor.getString(cursor.getColumnIndex("mark"))));
        }
        return tb_inaccount;            //返回集合
    }
    /*
     获取总记录数
     */
    public long getCount(){
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select count(_id) from tb_inaccount",null);   //获取总记录数
        if(cursor.moveToNext()){            //判断Cursor是否有数值
            return cursor.getLong(0);           //返回总记录数
        }else{
            return 0;           //如果没有数据，则返回0
        }
    }
    /*
    获取收入最大编码
     */
    public int getMaxId(){
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select max(_id) from tb_inaccount",null);    //获取表中最大的编码
        while (cursor.moveToLast()){            //访问Cursor中的最后一条数据
            return cursor.getInt(0);            //获取访问到的数据
        }
        return 0;           //如果没有数据，则返回0；
    }
}
