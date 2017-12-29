package com.example.apple.myapplication.model;

/**
 * Created by apple on 2017/12/27.
 */

public class Tb_flag {          //便签信息实体类
    private int _id;            //存储便签编号
    private String flag;            //存储便签内容
    public Tb_flag(){           //默认构造函数
        super();
    }
    public Tb_flag(int id,String flag){
        super();
        //赋值
        this._id = id;
        this.flag = flag;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
