package com.example.samsung.hilaris;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class Test extends AppCompatActivity {
    String test;
    int size;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Intent intent = getIntent();
        test = intent.getExtras().getString("test");//vector 전체 다 받아오기
        size = intent.getExtras().getInt("size");//vector size 받아오기

        String array[] = new String[size];//받아온 vector 각각의 object를 저장할 array

        //vector를 object 하나씩 받아오기
        for(int i = 0; i < size; i++){
            array[i] = intent.getExtras().getString("test2"+i);

            Toast.makeText(getApplicationContext(),""+array[i],Toast.LENGTH_LONG).show();
        }

        Toast.makeText(getApplicationContext(),""+size,Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(),""+test,Toast.LENGTH_LONG).show();
    }
}