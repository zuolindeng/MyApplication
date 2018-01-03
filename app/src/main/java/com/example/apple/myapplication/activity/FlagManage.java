package com.example.apple.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.example.apple.myapplication.R;

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
    }
}
