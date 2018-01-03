package com.example.apple.myapplication.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apple.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    GridView gvInfo;            //创建GridView对象
    String[] titles = new String[]{"新增支出","新增收入","我的支出","我的收入","数据管理","系统管理","收支便签","退出"};
    int[] images = new int[]{R.drawable.addoutaccount,R.drawable.addinaccount,
            R.drawable.outaccountinfo,R.drawable.inaccountinfo,R.drawable.showinfo,R.drawable.sysset,R.drawable.accountflag,R.drawable.exit};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        gvInfo = (GridView) findViewById(R.id.gvInfo);          //获取布局文件中的gnInfo组件
        pictureAdapter adapter = new pictureAdapter(titles,images,this);           //创建对象
        gvInfo.setAdapter(adapter);         //为GridView设置数据源
        gvInfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Intent intent = null;
                switch (arg2){
                    case 0:
                        intent = new Intent(MainActivity.this,AddOutaccount.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(MainActivity.this,AddInaccount.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(MainActivity.this,Outaccountinfo.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(MainActivity.this,Inaccountinfo.class);
                        startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(MainActivity.this,Showinfo.class);
                        startActivity(intent);
                        break;
                    case 5:
                        intent = new Intent(MainActivity.this,Sysset.class);
                        startActivity(intent);
                        break;
                    case 6:
                        intent = new Intent(MainActivity.this,Accountflag.class);
                        startActivity(intent);
                        break;
                    case 7:
                        finish();
                }
            }
        });
    }
}

class ViewHolder{           //创建ViewHolder类
    public TextView title;
    public ImageView image;
}

class Picture{          //创建Picture类
    private String title;
    private int imageId;
    public Picture(){
        super();
    }
    public Picture(String title,int imageId){
        super();
        this.title = title;
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}

class pictureAdapter extends BaseAdapter{            //创建基于BaseAdapter的子类
    private LayoutInflater inflater;
    private List<Picture> pictures;
    //为类创建构造函数
    public pictureAdapter(String[] titles, int[] images, Context context){
        super();
        pictures = new ArrayList<Picture>();            //初始化泛型集合对象
        inflater = LayoutInflater.from(context);            //初始化对象
        for (int i =0;i<images.length;i++){                 //遍历图像数组
            Picture picture = new Picture(titles[i],images[i]);             //使用标题和图像生成Picture对象
            pictures.add(picture);                  //将Picture对象添加到泛型集合中
        }
    }

    @Override
    public int getCount() {         //获取泛型集合的长度
        if(null != pictures){           //如果泛型集合不为空
            return pictures.size();         //返回泛型长度
        }else{
            return 0;
        }
    }

    @Override
    public Object getItem(int arg0) {
        return pictures.get(arg0);          //获取泛型集合指定索引处的项
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;            //返回泛型集合的索引
    }

    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2) {
        ViewHolder viewHolder;
        if(arg1 == null){
            arg1 = inflater.inflate(R.layout.gvitem,null);          //设置图像标识
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) arg1.findViewById(R.id.ItemTitle);
            viewHolder.image = (ImageView) arg1.findViewById(R.id.ItemImage);
            arg1.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) arg1.getTag();            //设置提示
        }
        viewHolder.title.setText(pictures.get(arg0).getTitle());            //设置图像标题
        viewHolder.image.setImageResource(pictures.get(arg0).getImageId());             //设置图像的二进制值
        return arg1;            //返回图像标识
    }
}
