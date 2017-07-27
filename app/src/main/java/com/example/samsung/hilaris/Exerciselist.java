package com.example.samsung.hilaris;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Exerciselist extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_exerciselist);

        Button warmingup = (Button) findViewById(R.id.button_warmup);

        warmingup.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             Intent intent = new Intent (getApplicationContext(), Exercise.class);
                                             startActivity(intent);
                                         }
                                     }
        );
        Button exercise = (Button) findViewById(R.id.button_workout);

        exercise.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent intent = new Intent (getApplicationContext(), Exercise.class);
                                            startActivity(intent);
                                        }
                                    }
        );
        Button cooldown = (Button) findViewById(R.id.button_cooldown);

        cooldown.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent intent = new Intent (getApplicationContext(), Exercise.class);
                                            startActivity(intent);
                                        }
                                    }
        );
    }
    /**
     * Action Bar에 메뉴를 생성
     * @param menu
     * @return
     */

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
                Intent intent = new Intent(Exerciselist.this, Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }}