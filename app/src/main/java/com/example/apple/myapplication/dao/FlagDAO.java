package com.example.apple.myapplication.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ListView;

import com.example.apple.myapplication.model.Tb_flag;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 2017/12/27.
 */

public class FlagDAO {
    private DBOpenHelper helper;
    private SQLiteDatabase db;
    public FlagDAO(Context context){               //定义构造函数
        helper = new DBOpenHelper(context);             //初始化DBOpenHelper对象
    }
    /*
     添加一条便签
     */
    public void add(Tb_flag tb_flag){
        db = helper.getWritableDatabase();
        db.execSQL("insert into tb_flag (_id,flag) values (?,?)",new Object[]{
                tb_flag.get_id(),tb_flag.getFlag()
        });
    }
    /*
      更新便签
     */
    public void update(Tb_flag tb_flag){
        db = helper.getWritableDatabase();
        db.execSQL("update tb_flag set flag = ? where _id = ?",new Object[]{
                tb_flag.getFlag(),tb_flag.get_id()
        });
    }
    /*
      查找便签
     */
    public Tb_flag find(int id){
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from tb_flag where _id = ?",new String[]{String.valueOf(id)});
        if(cursor.moveToNext()){
            return new Tb_flag(cursor.getInt(cursor.getColumnIndex("_id")),cursor.getString(cursor.getColumnIndex("flag")));
        }
        return null;
    }
    /*
     获取总记录数
     */
    public long getCount(){
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select count(_id) from tb_flag",null);   //获取总记录数
        if(cursor.moveToNext()){            //判断Cursor是否有数值
            return cursor.getLong(0);           //返回总记录数
        }else{
            return 0;           //如果没有数据，则返回0
        }
    }
    /*
     获取所有便签信息
     */
    public List<Tb_flag> getScrollData(int start,int count){
        List<Tb_flag> tb_flag = new ArrayList<Tb_flag>();
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from tb_flag limit ?,?",new String[]{String.valueOf(start),String.valueOf(count)});
        while (cursor.moveToNext()){
            tb_flag.add(new Tb_flag(cursor.getInt(cursor.getColumnIndex("_id")),cursor.getString(cursor.getColumnIndex("flag"))));;
        }
        return tb_flag;
    }
    /*
     删除便签
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
            db.execSQL("delete from tb_flag where _id in ("+sb+")",(Object[]) ids);
        }
    }
    /*
     获取最大便签数
     */
    public int getMaxId(){
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select max(_id) from tb_flag",null);    //获取表中最大的编码
        while (cursor.moveToLast()){            //访问Cursor中的最后一条数据
            return cursor.getInt(0);            //获取访问到的数据
        }
        return 0;           //如果没有数据，则返回0；
    }
}
