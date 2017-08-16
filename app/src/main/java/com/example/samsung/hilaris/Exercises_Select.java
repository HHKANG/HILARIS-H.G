package com.example.samsung.hilaris;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Exercises_Select extends AppCompatActivity {
    private RequestQueue queue;
    LinearLayout layout_warmup, layout_workout, layout_cooldown;

    String ImageUri;
    ListView listView_warmup;
    ListView listView_workout;
    ListView listView_cooldown;

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

        listView_warmup=(ListView)findViewById(R.id.listview_exercise1);
        listView_workout=(ListView)findViewById(R.id.listview_exercise2);
        listView_cooldown=(ListView)findViewById(R.id.listview_exercise3);

        setVisibility();

/*
        Intent intent = getIntent();
        try {
            JSONObject E_Unit = new JSONObject(intent.getStringExtra("exercises"));
            for(int i=0;i<exerecises.length;i++}{
            Exercise_unit unit = new Exercise_unit(E_Unit);

            ImageUri = "http://221.153.186.186:3100/" + unit.image;
            //setValues(unit);}
        } catch (JSONException e) {
            e.printStackTrace();
        }
*/

        //어댑터 만들기
        ExerciseListViewAdapter adapter1 = new ExerciseListViewAdapter(this,R.layout.exercise_item, list_warmup);
        ExerciseListViewAdapter adapter2 = new ExerciseListViewAdapter(this,R.layout.exercise_item, list_workout);
        ExerciseListViewAdapter adapter3 = new ExerciseListViewAdapter(this,R.layout.exercise_item, list_cooldown);

        //배열에 값 저장하기
        ImageUri = "http://221.153.186.186:3100/airbike.jpg";
        list_warmup.add(new ExerciseItem(ImageUri,"Arm Cicles","Stretching"));
       // list_workout.add(new ExerciseItem("b","Backward Drag","Strength"));
       // list_cooldown.add(new ExerciseItem("c","Barbell Ab Rollout","Strength"));
//        list.add(new ExerciseItem(R.drawable.exercise4,"Barbell Deadlift","Strength"));


        //리스트뷰와 어댑터 연결하기
        listView_warmup.setAdapter(adapter1);

        listView_warmup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), Detailview.class);
                startActivity(intent);
            }


        });
        listView_workout.setAdapter(adapter2);

        listView_workout.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), Detailview.class);
                startActivity(intent);
            }


        });
        listView_cooldown.setAdapter(adapter3);

        listView_cooldown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), Detailview.class);
                startActivity(intent);
            }


        });


    }



    public void setVisibility()
    {
        button_warmup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_warmup.setVisibility(v.VISIBLE);
                layout_workout.setVisibility(v.INVISIBLE);
                layout_cooldown.setVisibility(v.INVISIBLE);
                Toast.makeText(Exercises_Select.this,"warm up", Toast.LENGTH_SHORT).show();

            }
        });
        button_workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_warmup.setVisibility(v.INVISIBLE);
                layout_workout.setVisibility(v.VISIBLE);
                layout_cooldown.setVisibility(v.INVISIBLE);
                Toast.makeText(Exercises_Select.this,"work out", Toast.LENGTH_SHORT).show();

            }
        });
        button_cooldown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_warmup.setVisibility(v.INVISIBLE);
                layout_workout.setVisibility(v.INVISIBLE);
                layout_cooldown.setVisibility(v.VISIBLE);
                Toast.makeText(Exercises_Select.this,"cool down", Toast.LENGTH_SHORT).show();

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

