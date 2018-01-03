package com.example.apple.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apple.myapplication.R;
import com.example.apple.myapplication.dao.InaccountDAO;
import com.example.apple.myapplication.dao.OutaccountDAO;
import com.example.apple.myapplication.model.Tb_inaccount;
import com.example.apple.myapplication.model.Tb_outaccount;

/**
 * Created by apple on 2018/1/2.
 */

public class InfoManage extends AppCompatActivity{
    protected static final int DATE_DIALOG_ID = 0;
    TextView tvtitle,textView;
    EditText txtMoney,txtTime,txtHA,txtMark;
    Spinner spType;
    Button btnEdit,btnDel;
    String[] strInfos;
    String strid,strType;
    private int mYear;
    private int mMonth;
    private int mDay;
    OutaccountDAO outaccountDAO = new OutaccountDAO(InfoManage.this);
    InaccountDAO inaccountDAO = new InaccountDAO(InfoManage.this);

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infomanage);

        tvtitle=(TextView) findViewById(R.id.inouttitle);           //获取标题标签对象
        textView=(TextView) findViewById(R.id.tvInOut);             //获取“地点/付款方”标签对象
        txtMoney=(EditText) findViewById(R.id.txtInOutMoney);           //获取“金额”文本框
        txtTime=(EditText) findViewById(R.id.txtInOutTime);             //获取“时间”文本框
        spType=(Spinner) findViewById(R.id.spInOutType);            //获取“类别”下拉列表
        txtHA=(EditText) findViewById(R.id.txtInOut);           //获取“地点/付款方”文本框
        txtMark=(EditText) findViewById(R.id.txtInOutMark);             //获取“备注”文本框
        btnEdit=(Button) findViewById(R.id.btnInOutEdit);           //获取“修改”按钮
        btnDel=(Button) findViewById(R.id.btnInOutDelete);          //获取“删除”按钮

        Intent intent=getIntent();          //创建Intent对象
        Bundle bundle=intent.getExtras();           //获取传入的数据，并使用Bundle记录
        strInfos=bundle.getStringArray(Showinfo.FLAG);          //获取Bundle中记录的信息
        strid=strInfos[0];          //记录id
        strType=strInfos[1];            //记录类型
        if(strType.equals("btnoutinfo"))            //如果类型是btnoutinfo
        {
            tvtitle.setText("支出管理");            //设置标题为“支出管理”
            textView.setText("地 点：");               //设置“地点/付款方”标签文本为“地 点：”
//根据编号查找支出信息，并存储到Tb_outaccount对象中
            Tb_outaccount tb_outaccount=outaccountDAO.find(Integer.parseInt(strid));
            txtMoney.setText(String.valueOf(tb_outaccount.getMoney()));             //显示金额
            txtTime.setText(tb_outaccount.getTime());           //显示时间
            spType.setPrompt(tb_outaccount.getType());          //显示类别
            txtHA.setText(tb_outaccount.getAddress());          //显示地点
            txtMark.setText(tb_outaccount.getMark());           //显示备注
        }else if(strType.equals("btnininfo")){           //如果类型是btnininfo
            tvtitle.setText("收入管理");            //设置标题为“收入管理”
            textView.setText("付款方：");           //设置“地点/付款方”标签文本为“付款方：”
            //根据编号查找收入信息，并存储到Tb_outaccount对象中
            Tb_inaccount tb_inaccount= inaccountDAO.find(Integer.parseInt(strid));
            txtMoney.setText(String.valueOf(tb_inaccount.getMoney()));          //显示金额
            txtTime.setText(tb_inaccount.getTime());            //显示时间
            spType.setPrompt(tb_inaccount.getType());           //显示类别
            txtHA.setText(tb_inaccount.getHandler());           //显示付款方
            txtMark.setText(tb_inaccount.getMark());            //显示备注

            btnEdit.setOnClickListener(new View.OnClickListener() {             //为“修改”按钮设置监听事件
                @Override
                public void onClick(View view) {
                    if(strType.equals("btnoutinfo")){               //判断类型如果是btnoutinfo
                        Tb_outaccount tb_outaccount=new Tb_outaccount();            //创建Tb_outaccount对象
                        tb_outaccount.set_id(Integer.parseInt(strid));           //设置编号
                        tb_outaccount.setMoney(Double.parseDouble(txtMoney.getText().toString()));          //设置金额
                        tb_outaccount.setTime(txtTime.getText().toString());            //设置时间
                        tb_outaccount.setType(spType.getSelectedItem().toString());             //设置类别
                        tb_outaccount.setAddress(txtHA.getText().toString());           //设置地点
                        tb_outaccount.setMark(txtMark.getText().toString());            //设置备注
                        outaccountDAO.update(tb_outaccount);            //更新支出信息
                    }else if(strType.equals("btnininfo")){          //判断类型如果是btnininfo
                        Tb_inaccount tb_inaccount=new Tb_inaccount();           //创建Tb_inaccount对象
                        tb_inaccount.set_id(Integer.parseInt(strid));           //设置编号
                        tb_inaccount.setMoney(Double.parseDouble(txtMoney.getText().toString()));           //设置金额tb_inaccount.setTime(txtTime.getText().toString()); //设置时间
                        tb_inaccount.setType(spType.getSelectedItem().toString());              //设置类别
                        tb_inaccount.setHandler(txtHA.getText().toString());            //设置付款方
                        tb_inaccount.setMark(txtMark.getText().toString());             //设置备注
                        inaccountDAO.update(tb_inaccount);          //更新收入信息
                    }
                    //弹出信息提示
                    Toast.makeText(InfoManage.this,"【数据】修改成功！",Toast.LENGTH_SHORT).show();
                }
            });

            btnDel.setOnClickListener(new View.OnClickListener() {          //为“删除”按钮设置监听按钮
                @Override
                public void onClick(View view) {
                    if(strType.equals("btnoutinfo")){
                        outaccountDAO.delete(Integer.parseInt(strid));
                    }else if(strType.equals("btnininfo")){
                        inaccountDAO.delete(Integer.parseInt(strid));
                    }
                    Toast.makeText(InfoManage.this,"【数据】删除成功！",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
