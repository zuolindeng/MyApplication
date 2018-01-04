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

public class Login extends AppCompatActivity{
    private EditText txtlogin;
    private Button btnlogin;
    private Button btnclose;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        txtlogin = (EditText) findViewById(R.id.txtLogin);
        btnlogin = (Button) findViewById(R.id.btnLogin);
        btnclose = (Button) findViewById(R.id.btnClose);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Login.this, MainActivity.class);           //创建Intent对象
                PwdDAO pwdDAO=new PwdDAO(Login.this);           //创建PwdDAO对象
                if((pwdDAO.getCount()==0| pwdDAO.find().getPassword().isEmpty()) && txtlogin.getText().toString(). isEmpty()){          //判断是否有密码及是否输入了密
                    startActivity(intent);          //启动主Activity
                }else{
                    //判断输入的密码是否与数据库中的密码一致
                    if (pwdDAO.find().getPassword().equals(txtlogin.getText().toString())) {
                        startActivity(intent);      //启动主Activity
                    }else{
                        //弹出信息提示
                        Toast.makeText(Login.this, "请输入正确的密码！", Toast.LENGTH_SHORT).show();
                    }
                }
                txtlogin.setText(""); //清空密码文本框

            }
        });

        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();           //退出当前程序
            }
        });
    }


}
