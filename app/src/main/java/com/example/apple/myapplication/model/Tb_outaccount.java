package com.example.apple.myapplication.model;

/**
 * Created by apple on 2017/12/27.
 */

public class Tb_outaccount {
    private int _id;        //存储支出编号
    private double money;       //存储支出金额
    private String time;        //存储支出时间
    private String type;        //存储支出类别
    private String address;         //存储支出地址
    private String mark;        //存储支出备注
    public Tb_outaccount(){             //默认构造函数
        super();
    }
    //定义有参构造函数，用来初始化收入信息实体类中的各个字段
    public Tb_outaccount(int id,double money,String time,String type,String address,String mark){
        super();
        this._id = id;      //为支出编号赋值
        this.money = money;         //为支出金额赋值
        this.time = time;           //为支出时间赋值
        this.type = type;           //为支出类型方赋值
        this.address = address;           //为支出地址赋值
        this.mark = mark;           //为支出备注赋值
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
