package com.example.apple.myapplication.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.apple.myapplication.R;
import com.example.apple.myapplication.dao.InaccountDAO;
import com.example.apple.myapplication.model.Tb_inaccount;

import java.util.Calendar;

/**
 * Created by apple on 2017/12/29.
 */

public class AddInaccount extends AppCompatActivity implements View.OnClickListener{
    protected static final int DATE_DIALOG_ID = 0;
    EditText txtInMoney,txtInTime,txtInHandler,txtInMark;
    Spinner spInType;
    Button btnInSaveButton;
    Button btnInCancelButton;
    private int mYear;
    private int mMonth;
    private int mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addinaccount);
        initView();
    }

    //初始化控件
    private void initView(){
        txtInMoney = (EditText) findViewById(R.id.txtInMoney);
        txtInTime = (EditText) findViewById(R.id.txtInTime);
        txtInHandler = (EditText) findViewById(R.id.txtInHandler);
        txtInMark = (EditText) findViewById(R.id.txtInMark);
        spInType = (Spinner) findViewById(R.id.spInType);
        btnInSaveButton = (Button) findViewById(R.id.btnInSave);
        btnInCancelButton = (Button) findViewById(R.id.btnInCancel);

        txtInTime.setOnClickListener(this);
        btnInSaveButton.setOnClickListener(this);
        btnInCancelButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.txtInTime:
                showDialog(DATE_DIALOG_ID);             //显示日期选择对话框
                final Calendar c = Calendar.getInstance();          //获取当前系统日期
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                updateDispaly();            //显示当前系统时间
                break;
            case R.id.btnInSave:
                String strInMoney = txtInMoney.getText().toString();        //获取“金额”文本框的值
                if(!strInMoney.isEmpty()){          //判断金额不为空
                    InaccountDAO inaccountDAO = new InaccountDAO(AddInaccount.this);
                    Tb_inaccount tb_inaccount = new Tb_inaccount(inaccountDAO.getMaxId()+1,Double.parseDouble(strInMoney),
                            txtInTime.getText().toString(),spInType.getSelectedItem().toString(),txtInHandler.getText().toString(),
                            txtInMark.getText().toString());
                    inaccountDAO.add(tb_inaccount);         //添加收入信息
                    //弹出信息提示
                    Toast.makeText(AddInaccount.this,"【新增收入】数据添加成功！",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(AddInaccount.this,"请输入收入金额！",Toast.LENGTH_SHORT).show();
                }
            case R.id.btnInCancel:
                txtInMoney.setText("");
                txtInMoney.setHint("0.00");
                txtInTime.setText("");
                txtInTime.setHint("2011-01-01");
                txtInHandler.setText("");
                txtInMark.setText("");
                spInType.setSelection(0);

        }
    }

    private void updateDispaly(){       //显示设置的时间
        txtInTime.setText(new StringBuilder().append(mYear).append("-").append(mMonth+1).append("-").append(mDay));
    }

    @Override
    protected Dialog onCreateDialog(int id){
        switch (id){
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,mDateSetListener,mYear,mMonth,mDay);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfyear, int dayOfMonth) {
            mYear = year;           //为年份赋值
            mMonth = monthOfyear;           //为月份赋值
            mDay = dayOfMonth;          //为天赋值
            updateDispaly();            //显示设置的日期
        }
    };
}
