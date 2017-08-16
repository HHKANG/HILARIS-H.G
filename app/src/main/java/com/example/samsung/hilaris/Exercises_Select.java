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
    ImageView Imageview;

    private String url;
    String ImageUri;
    int responseLength;
    ListView listView;
    ArrayList<ExerciseItem> list=new ArrayList<ExerciseItem>();
    Button button_cooldown,button_warmup,button_workout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises__select);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        ImageUri = "http://221.153.186.186:3100/";

        button_warmup = (Button) findViewById(R.id.button_warmup);
        button_workout = (Button) findViewById(R.id.button_workout);
        button_cooldown = (Button) findViewById(R.id.button_cooldown);
        //layout_warmup = (LinearLayout) findViewById(R.id.layout_warmup);
        //layout_workout = (LinearLayout) findViewById(R.id.layout_workout);
        //layout_cooldown = (LinearLayout) findViewById(R.id.layout_cooldown);

        listView=(ListView)findViewById(R.id.listview_exercise);
        //배열에 값 저장하기
        list.add(new ExerciseItem(R.drawable.exercise1,"Arm Cicles","Stretching"));
        list.add(new ExerciseItem(R.drawable.exercise2,"Backward Drag","Strength"));
        list.add(new ExerciseItem(R.drawable.exercise3,"Barbell Ab Rollout","Strength"));
        list.add(new ExerciseItem(R.drawable.exercise4,"Barbell Deadlift","Strength"));

        //어댑터 만들기
        ExerciseListViewAdapter adapter=new ExerciseListViewAdapter(this,R.layout.exercise_item, list);


        //리스트뷰와 어댑터 연결하기
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), Detailview.class);
                startActivity(intent);
            }


        });



    }

    /*public void setImageView(String uriPath)
    {
        Imageview.setImageURI(Uri.parse(uriPath));
    }


    public void setVisibility()
    {
        button_warmup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_warmup.setVisibility(v.VISIBLE);
                layout_workout.setVisibility(v.INVISIBLE);
                layout_cooldown.setVisibility(v.INVISIBLE);
            }
        });
        button_workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_warmup.setVisibility(v.INVISIBLE);
                layout_workout.setVisibility(v.VISIBLE);
                layout_cooldown.setVisibility(v.INVISIBLE);
            }
        });
        button_cooldown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_warmup.setVisibility(v.INVISIBLE);
                layout_workout.setVisibility(v.INVISIBLE);
                layout_cooldown.setVisibility(v.VISIBLE);
            }
        });
    }*/
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

