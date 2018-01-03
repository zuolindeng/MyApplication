package com.example.apple.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apple.myapplication.R;
import com.example.apple.myapplication.dao.FlagDAO;
import com.example.apple.myapplication.model.Tb_flag;

/**
 * Created by apple on 2017/12/29.
 */

public class Accountflag extends AppCompatActivity{
    EditText txtFlag;;
    Button btnflagSaveButton;
    Button btnflagCancelButton;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.accountflag);

        txtFlag=(EditText) findViewById(R.id.txtFlag);
        btnflagSaveButton=(Button) findViewById(R.id.btnflagSave);
        btnflagCancelButton=(Button) findViewById(R.id.btnflagCancel);

        btnflagSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strFlag = txtFlag.getText().toString();
                if(!strFlag.isEmpty()){         //判断获取的值不为空
                    FlagDAO flagDAO = new FlagDAO(Accountflag.this);
                    Tb_flag tb_flag = new Tb_flag(flagDAO.getMaxId()+1,strFlag);            //创建Tb_flag对象
                    flagDAO.add(tb_flag); //添加便签信息
                    //弹出信息提示
                    Toast.makeText(Accountflag.this, "【新增便签】数据添加成功！",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Accountflag.this,"请输入便签！",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnflagCancelButton.setOnClickListener(new View.OnClickListener() {             //为“取消”按钮设置监听事件
            @Override
            public void onClick(View view) {
                txtFlag.setText("");            //清空便签文本框
            }
        });
    }
}
