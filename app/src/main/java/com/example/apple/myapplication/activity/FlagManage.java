package com.example.apple.myapplication.activity;

import android.content.Intent;
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
 * Created by apple on 2018/1/3.
 */

public class FlagManage extends AppCompatActivity {
    EditText txtFlag;
    Button btnEdit,btnDel;
    String strid;

    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.flagmanage);

        txtFlag = (EditText) findViewById(R.id.txtFlagManage);
        btnEdit = (Button) findViewById(R.id.btnFlagManageEdit);
        btnDel = (Button) findViewById(R.id.btnFlagManageDelete);

        Intent intent=getIntent();          //创建Intent对象
        Bundle bundle=intent.getExtras();           //获取便签id
        strid=bundle.getString(Showinfo.FLAG);          //将便签id转换为字符串
        final FlagDAO flagDAO=new FlagDAO(FlagManage.this);             //创建FlagDAO对象
        txtFlag.setText(flagDAO.find(Integer.parseInt(strid)).getFlag());           //根据便签id查找便签信息，并显示在文本框中

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tb_flag tb_flag=new Tb_flag();          //创建Tb_flag对象
                tb_flag.set_id(Integer.parseInt(strid));            //设置便签id
                tb_flag.setFlag(txtFlag.getText().toString());          //设置便签值
                flagDAO.update(tb_flag);            //修改便签信息
                //弹出信息提示
                Toast.makeText(FlagManage.this, "〖便签数据〗修改成功！", Toast.LENGTH_SHORT).show();
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flagDAO.delete(Integer.parseInt(strid));                //根据指定的id删除便签信息
                Toast.makeText(FlagManage.this, "〖便签数据〗删除成功！", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
