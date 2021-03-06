package com.example.survingandmapping;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.survingandmapping.db.ShuiZhunCeLiang;

public class ShuizhunResult extends AppCompatActivity {

    private TextView hsj;
    private TextView qsj;
    private TextView dqsj;
    private TextView ljc;
    private TextView T12;
    private TextView T22;
    private TextView T32;
    private TextView T14;
    private TextView T24;
    private TextView T34;
    private TextView Eighteen;
    private TextView station;

    private Button button1;
    private Button button2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shuizhui_result);
        hsj=(TextView)findViewById(R.id.twelve);
        qsj=(TextView)findViewById(R.id.thirteen);
        dqsj=(TextView)findViewById(R.id.fourteen);
        ljc=(TextView)findViewById(R.id.fifteen);
        T12=(TextView)findViewById(R.id.t12);
        T22=(TextView)findViewById(R.id.t22);
        T32=(TextView)findViewById(R.id.t32);
        T14=(TextView)findViewById(R.id.t14);
        T24=(TextView)findViewById(R.id.t24);
        T34=(TextView)findViewById(R.id.t34);
        Eighteen=(TextView)findViewById(R.id.eighteen);
        station=(TextView)findViewById(R.id.station);
        button1=(Button)findViewById(R.id.bt1);
        button2=(Button)findViewById(R.id.bt2);

        final Intent intent=getIntent();
        hsj.setText(String.valueOf(intent.getFloatExtra("后视距",0)));
        qsj.setText(String.valueOf(intent.getFloatExtra("前视距",0)));
        dqsj.setText(String.valueOf(intent.getFloatExtra("当前视差",0)));
        ljc.setText(String.valueOf(intent.getFloatExtra("累积视差",0)));
        T12.setText(String.valueOf(intent.getIntExtra("前黑红读差",0)));
        T22.setText(String.valueOf(intent.getIntExtra("后黑红读差",0)));
        T32.setText(String.valueOf(intent.getIntExtra("高差之差",0)));
        T14.setText(String.valueOf(intent.getIntExtra("黑面高差",0)));
        T24.setText(String.valueOf(intent.getIntExtra("红面高差",0)));
        T34.setText(String.valueOf(intent.getIntExtra("高差之差",0)));
        Eighteen.setText("平均高差:"+intent.getIntExtra("平均高差",0)+"mm");
        station.setText("第"+intent.getStringExtra("测站")+"测站");

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //该实例对应ShuiZhunCeLiang表中的一条记录；
                ShuiZhunCeLiang shuiZhunCeLiang=new ShuiZhunCeLiang();
                shuiZhunCeLiang.setCezhan(intent.getStringExtra("cezhan"));
                shuiZhunCeLiang.setK1(intent.getIntExtra("K1",0));
                shuiZhunCeLiang.setK2(intent.getIntExtra("K2",0));
                shuiZhunCeLiang.setHhs(intent.getIntExtra("one",0));
                shuiZhunCeLiang.setHhz(intent.getIntExtra("two",0));
                shuiZhunCeLiang.setHhx(intent.getIntExtra("three",0));
                shuiZhunCeLiang.setQhs(intent.getIntExtra("five",0));
                shuiZhunCeLiang.setQhz(intent.getIntExtra("six",0));
                shuiZhunCeLiang.setQhx(intent.getIntExtra("four",0));
                shuiZhunCeLiang.setHhongz(intent.getIntExtra("eight",0));
                shuiZhunCeLiang.setQhongz(intent.getIntExtra("seven",0));
                //将该条记录到数据库
                shuiZhunCeLiang.save();

                Toast.makeText(ShuizhunResult.this,"本站数据已保存如数据库",Toast.LENGTH_SHORT).show();
                Log.d("sqpolar","本站数据已保存如数据库");

                Intent intent2=new Intent(ShuizhunResult.this,ShuizhunActivity.class);
                intent2.putExtra("累积视差",intent.getFloatExtra("累积视差",0));
                intent2.putExtra("测站",intent.getStringExtra("测站"));
                startActivity(intent2);
            }
        });
    }
}
