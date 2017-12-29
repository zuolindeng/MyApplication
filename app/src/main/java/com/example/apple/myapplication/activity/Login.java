package com.example.apple.myapplication.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apple.myapplication.R;
import com.example.apple.myapplication.dao.PwdDAO;

/**
 * Created by apple on 2017/12/29.
 */

public class Login extends AppCompatActivity implements View.OnClickListener{
    private EditText txtlogin;
    private Button btnlogin;
    private Button btnclose;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initView();
    }
    //初始化控件
    private void initView(){
        txtlogin = (EditText) findViewById(R.id.txtLogin);
        btnlogin = (Button) findViewById(R.id.btnLogin);
        btnclose = (Button) findViewById(R.id.btnClose);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLogin:
                Intent intent = new Intent(Login.this,MainActivity.class);
                PwdDAO pwdDAO = new PwdDAO(Login.this);
                //判断是否有密码表及密码
                if((pwdDAO.getCount()==0 | pwdDAO.find().getPassword().isEmpty()) && txtlogin.getText().toString().isEmpty()){
                    startActivity(intent);          //启动主Activity
                }else if(pwdDAO.find().getPassword().equals(txtlogin.getText().toString())){         //判断输入的密码是否与数据库中的密码一致
                    startActivity(intent);
                } else {
                    //弹出信息提示
                    Toast.makeText(Login.this,"请输入正确的密码！",Toast.LENGTH_SHORT).show();
                }
                txtlogin.setText("");           //清空密码文本框
                break;
            case R.id.btnClose:
                finish();
                break;          //退出当前程序
        }
    }
}
