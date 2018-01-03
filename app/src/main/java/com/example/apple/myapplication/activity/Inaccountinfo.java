package com.example.apple.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.apple.myapplication.R;
import com.example.apple.myapplication.dao.InaccountDAO;
import com.example.apple.myapplication.model.Tb_inaccount;

import java.util.List;

/**
 * Created by apple on 2017/12/29.
 */

public class Inaccountinfo extends AppCompatActivity{
    public static final String FLAG = "id";         //定义一个常量，用来作为请求码
    ListView lvinfo;
    String strType = "";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inaccountinfo);
        initView();
    }

    private void initView(){
        lvinfo = (ListView) findViewById(R.id.lvinaccountinfo);
        ShowInfo(R.id.btnininfo);
        lvinfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String strInfo = String.valueOf(((TextView) view).getText());
                String strid = strInfo.substring(0,strInfo.indexOf('|'));
                Intent intent = new Intent(Inaccountinfo.this,InfoManage.class);
                intent.putExtra(FLAG,new String[]{strid,strType});
                startActivity(intent);
            }
        });
    }

    private void ShowInfo(int intType){
        String[] strInfos = null;
        ArrayAdapter<String> arrayAdapter = null;
        strType = "btnininfo";
        InaccountDAO inaccountinfo = new InaccountDAO(Inaccountinfo.this);
        List<Tb_inaccount> listinos = inaccountinfo.getScrollData(0,(int) inaccountinfo.getCount());
        strInfos = new String[listinos.size()];
        int m=0;
        for(Tb_inaccount tb_inaccount:listinos){
            strInfos[m]=tb_inaccount.get_id()+"|"+tb_inaccount.getType()+" "+String.valueOf(tb_inaccount.getMoney()+"元"+tb_inaccount.getTime());
            m++;
        }
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,strInfos);
        lvinfo.setAdapter(arrayAdapter);
    }
}
