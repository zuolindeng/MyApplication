package com.example.apple.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.apple.myapplication.R;
import com.example.apple.myapplication.dao.FlagDAO;
import com.example.apple.myapplication.dao.InaccountDAO;
import com.example.apple.myapplication.dao.OutaccountDAO;
import com.example.apple.myapplication.model.Tb_flag;
import com.example.apple.myapplication.model.Tb_inaccount;
import com.example.apple.myapplication.model.Tb_outaccount;

import java.util.List;


/**
 * Created by apple on 2017/12/29.
 */

public class Showinfo extends AppCompatActivity {
    Button btnflaginfo;
    ListView lvinfo;
    String strType = "";
    public static final String FLAG = "id";

    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.showinfo);

        btnflaginfo = (Button) findViewById(R.id.btnflaginfo);


        btnflaginfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowInfo(R.id.btnflaginfo);
            }
        });

        lvinfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String strInfo=String.valueOf(((TextView) view).getText());             //记录单击的项信息
                String strid=strInfo.substring(0, strInfo.indexOf('|'));            //从项信息中截取编号
                Intent intent = null;           //创建Intent对象
                if (strType=="btnoutinfo" | strType=="btnininfo") {             //判断如果是支出或者收入信息
                    intent=new Intent(Showinfo.this, InfoManage.class);             //使用InfoManage窗口初始化Intent对象
                    intent.putExtra(FLAG, new String[]{strid,strType});             //设置要传递的数据
                }else if (strType=="btnflaginfo") {             //判断如果是便签信息
                    intent=new Intent(Showinfo.this, FlagManage.class);             //使用FlagManage窗口初始化Intent对象
                    intent.putExtra(FLAG, strid);           //设置要传递的数据
                }
                startActivity(intent); //执行Intent，打开相应的Activity
            }
        });
    }

    private void ShowInfo(int intType) {            //用来根据传入的管理类型显示相应的信息
        String[] strInfos = null;           //定义字符串数组，用来存储收入信息
        ArrayAdapter<String> arrayAdapter = null;           //创建ArrayAdapter对象
        switch (intType) {              //以intType为条件进行判断
            /*case R.id.btnoutinfo:           //如果是btnoutinfo按钮
                strType="btnoutinfo";           //为strType变量赋值
                OutaccountDAO outaccountinfo=new OutaccountDAO(Showinfo.this);
                //获取所有支出信息，并存储到List泛型集合
                strInfos=new String[listoutinfos.size()];       //设置字符串数组的长度
                int i = 0;          //定义一个开始标识
                for (Tb_outaccount tb_outaccount:listoutinfos) {            //遍历List泛型集合
                    //将支出相关信息组合成一个字符串，存储到字符串数组的相应位置
                    strInfos[i]=tb_outaccount.get_id()+"|"+tb_outaccount.getType()+" "+String.valueOf(tb_outaccount. getMoney())+"元 "+tb_outaccount.getTime();
                    i++;
                }
                break;*/
            case R.id.btnininfo:            //如果是btnininfo按钮
                strType="btnininfo";            //为strType变量赋值
                InaccountDAO inaccountinfo=new InaccountDAO(Showinfo.this);             //创建InaccountDAO对象
                //获取所有收入信息，并存储到List泛型集合中
                List<Tb_inaccount> listinfos=inaccountinfo.getScrollData(0, (int) inaccountinfo.getCount());
                strInfos=new String[listinfos.size()];          //设置字符串数组的长度
                int m = 0;          //定义一个开始标识
                for (Tb_inaccount tb_inaccount:listinfos) {             //遍历List泛型集合
                    //将收入相关信息组合成一个字符串，存储到字符串数组的相应位置
                    strInfos[m]=tb_inaccount.get_id()+"|"+tb_inaccount.getType()+" "+String.valueOf(tb_inaccount. getMoney())+"元 "+tb_inaccount.getTime();
                    m++;
                }
                break;
            case R.id.btnflaginfo:          //如果是btnflaginfo按钮
                strType="btnflaginfo";          //为strType变量赋值
                FlagDAO flaginfo=new FlagDAO(Showinfo.this);            //创建FlagDAO对象
                //获取所有便签信息，并存储到List泛型集合中
                List<Tb_flag> listFlags=flaginfo.getScrollData(0, (int) flaginfo.getCount());
                strInfos=new String[listFlags.size()];          //设置字符串数组的长度
                int n = 0;          //定义一个开始标识
                for (Tb_flag tb_flag:listFlags) {           //遍历List泛型集合
                    //将便签相关信息组合成一个字符串，存储到字符串数组的相应位置
                    strInfos[n]=tb_flag.get_id()+"|"+tb_flag.getFlag();
                    if(strInfos[n].length()>15)             //判断便签信息的长度是否大于15
                        //将位置大于15之后的字符串用"……"代替
                        strInfos[n]=strInfos[n].substring(0,15)+"……";
                        n++;
                }
                break;
        }
                //使用字符串数组初始化ArrayAdapter对象
                arrayAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strInfos);
                lvinfo.setAdapter(arrayAdapter);            //为ListView列表设置数据源
    }
}
