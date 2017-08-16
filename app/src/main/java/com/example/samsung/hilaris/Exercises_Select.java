package com.example.samsung.hilaris;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Exercises_Select extends AppCompatActivity {
    LinearLayout layout_warmup, layout_workout, layout_cooldown;

    String ImageUri;
    ListView listView_warmup;
    ListView listView_workout;
    ListView listView_cooldown;
    String test;
    int size;
    JSONObject E_Unit;
    Exercise_unit unit;
    String array[] = null;
    Exercise_unit warmup_array[] = null;
    String workout_array[] = null;
    String cooldown_array[] = null;
    int index1= 0;
    int index2= 0;
    int index3= 0;




    ArrayList<ExerciseItem> list_warmup=new ArrayList<ExerciseItem>();
    ArrayList<ExerciseItem> list_workout=new ArrayList<ExerciseItem>();
    ArrayList<ExerciseItem> list_cooldown=new ArrayList<ExerciseItem>();

    Button button_cooldown,button_warmup,button_workout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises__select);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        button_warmup = (Button) findViewById(R.id.button_warmup);
        button_workout = (Button) findViewById(R.id.button_workout);
        button_cooldown = (Button) findViewById(R.id.button_cooldown);
        layout_warmup = (LinearLayout) findViewById(R.id.layout_warmup);
        layout_workout = (LinearLayout) findViewById(R.id.layout_workout);
        layout_cooldown = (LinearLayout) findViewById(R.id.layout_cooldown);

        listView_warmup = (ListView) findViewById(R.id.listview_exercise1);
        listView_workout = (ListView) findViewById(R.id.listview_exercise2);
        listView_cooldown = (ListView) findViewById(R.id.listview_exercise3);

        setVisibility();
        //어댑터 만들기
        ExerciseListViewAdapter adapter1 = new ExerciseListViewAdapter(this, R.layout.exercise_item, list_warmup);
        ExerciseListViewAdapter adapter2 = new ExerciseListViewAdapter(this, R.layout.exercise_item, list_workout);
        ExerciseListViewAdapter adapter3 = new ExerciseListViewAdapter(this, R.layout.exercise_item, list_cooldown);

        Intent intent = getIntent();
        test = intent.getExtras().getString("test");//vector 전체 다 받아오기
        size = intent.getExtras().getInt("size");//vector size 받아오기

       array = new String[size];//받아온 vector 각각의 object를 저장할 array

        //vector를 object 하나씩 받아오기
        for (int i = 0; i < size; i++) {
            array[i] = intent.getExtras().getString("test2" + i);
            try {
                E_Unit = new JSONObject(array[i]);
                unit = new Exercise_unit(E_Unit);
                //배열에 값 저장하기

                if (unit.phase.equals("Warm Up")) {
                        // Toast.makeText(getApplicationContext(),unit.phase+" "+unit.title+" "+unit.type+unit.image,Toast.LENGTH_LONG).show();
                        ImageUri = "http://221.153.186.186:3100/" + unit.image;
                        list_warmup.add(new ExerciseItem(ImageUri, unit.title, unit.type));


                        //리스트뷰와 어댑터 연결하기
                        listView_warmup.setAdapter(adapter1);

                        listView_warmup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position1, long id) {
                                Intent intent = new Intent(getApplicationContext(), Detailview.class);
                                for(int index = 0; index < size; index++)
                                {
                                    intent.putExtra("test2"+index, array[index]);
                                }
                                intent.putExtra("size", size);
                                intent.putExtra("position", position1);
                            //    Toast.makeText(getApplicationContext(),array[position1],Toast.LENGTH_LONG).show();
                                startActivity(intent);
                            }


                        });

                }
                index1  = list_warmup.size();
                if (unit.phase.equals("Work Out")) {
                   // Toast.makeText(getApplicationContext(),unit.phase+" "+unit.title+" "+unit.type+unit.image,Toast.LENGTH_LONG).show();
                    ImageUri = "http://221.153.186.186:3100/" + unit.image;
                    list_workout.add(new ExerciseItem(ImageUri, unit.title, unit.type));

                    listView_workout.setAdapter(adapter2);

                    listView_workout.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position2, long id) {
                            Intent intent = new Intent(getApplicationContext(), Detailview.class);
                            for(int index = 0; index < size; index++)
                            {
                                intent.putExtra("test2"+index, array[index]);
                            }
                            intent.putExtra("size", size);
                            intent.putExtra("position", index1+position2);
                          //  Toast.makeText(getApplicationContext(),array[index1+position2],Toast.LENGTH_LONG).show();
                            startActivity(intent);
                        }


                    });


                }
                index2  = list_workout.size();

                if (unit.phase.equals("Cool Down")) {
                    ImageUri = "http://221.153.186.186:3100/" + unit.image;
                    list_cooldown.add(new ExerciseItem(ImageUri, unit.title, unit.type));
                    listView_cooldown.setAdapter(adapter3);
                    index3=list_cooldown.size();

                    listView_cooldown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position3, long id) {
                            Intent intent = new Intent(getApplicationContext(), Detailview.class);
                            for(int index = 0; index < size; index++)
                            {
                                intent.putExtra("test2"+index, array[index]);
                            }
                            intent.putExtra("position",index1+index2+position3);
                            intent.putExtra("size", size);
                         //   Toast.makeText(getApplicationContext(),array[index1+index3+position3],Toast.LENGTH_LONG).show();
                            startActivity(intent);
                        }


                    });


                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    public void setVisibility()
    {
        button_warmup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_warmup.setVisibility(v.VISIBLE);
                layout_workout.setVisibility(v.INVISIBLE);
                layout_cooldown.setVisibility(v.INVISIBLE);
              //  Toast.makeText(Exercises_Select.this,"Warm Up", Toast.LENGTH_SHORT).show();

            }
        });
        button_workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_warmup.setVisibility(v.INVISIBLE);
                layout_workout.setVisibility(v.VISIBLE);
                layout_cooldown.setVisibility(v.INVISIBLE);
           //     Toast.makeText(Exercises_Select.this,"Work Out", Toast.LENGTH_SHORT).show();

            }
        });
        button_cooldown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_warmup.setVisibility(v.INVISIBLE);
                layout_workout.setVisibility(v.INVISIBLE);
                layout_cooldown.setVisibility(v.VISIBLE);
            //    Toast.makeText(Exercises_Select.this,"Cool Down", Toast.LENGTH_SHORT).show();

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
                Intent intent = new Intent(Exercises_Select.this, Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }


}

