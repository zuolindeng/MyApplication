package com.example.apple.myapplication.model;

/**
 * Created by apple on 2017/12/27.
 */

public class Tb_pwd {           //密码信息实体类
    private String password;            //存储密码
    public Tb_pwd(){            //默认构造函数
        super();
    }
    //定义有参构造函数，用来初始化密码信息实体类中的各个字段
    public Tb_pwd(String password){
        super();
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
