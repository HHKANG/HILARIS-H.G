package com.example.samsung.hilaris;


import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class Exercise extends Exerciselist {
    ListView listView;
    ArrayList<ExerciseItem> list=new ArrayList<ExerciseItem>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        listView=(ListView)findViewById(R.id.listview_exercise);
        //배열에 값 저장하기
        list.add(new ExerciseItem(R.drawable.exercise1,"Arm Cicles","Stretching"));
        list.add(new ExerciseItem(R.drawable.exercise2,"Backward Drag","Strength"));
        list.add(new ExerciseItem(R.drawable.exercise3,"Barbell Ab Rollout","Strength"));
        list.add(new ExerciseItem(R.drawable.exercise4,"Barbell Deadlift","Strength"));

        //어댑터 만들기
        ExerciseListViewAdapter adapter=new ExerciseListViewAdapter(this,R.layout.activity_exercise_item, list);


        //리스트뷰와 어댑터 연결하기
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplication(), Detailview.class);
                startActivity(intent);
            }


        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.logout_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            case R.id.logout:
                Intent intent = new Intent(Exercise.this, Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

