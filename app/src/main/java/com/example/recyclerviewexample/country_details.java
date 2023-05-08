package com.example.recyclerviewexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class country_details extends AppCompatActivity {

    TextView nameTv;
    TextView pupTv;
    ImageView flagimg;
    String data1;
    long data2;
    int data3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_details);
        nameTv = findViewById(R.id.country1);
        pupTv = findViewById(R.id.pup1);
        flagimg = findViewById(R.id.country_flag1);
        getData();
        setData();
    }
    void getData(){
        data1 = getIntent().getStringExtra("data1");
        data2 = getIntent().getLongExtra("data2",1);
        data3 = getIntent().getIntExtra("data3",1);

    }

    void setData(){
        nameTv.setText(data1);
        pupTv.setText(String.valueOf(data2));
        flagimg.setImageResource(data3);

    }
}