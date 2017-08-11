package com.example.samsung.hilaris;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Week_Day_Select extends AppCompatActivity {
    TableLayout tableLayout;
    int num = 0;

    Prescription prescription;
    Exercise_unit unit;

    //4*7 TextView array
    TextView[][] week_day = new TextView[4][7];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_week__day__select);

        tableLayout = (TableLayout)findViewById(R.id.table_layout);

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 7; j++){
                week_day[i][j] = (TextView)tableLayout.findViewWithTag(""+num);
                num++;
            }
        }

        Toast.makeText(getApplicationContext(),""+unit.week,Toast.LENGTH_SHORT).show();
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
                Intent intent = new Intent(Week_Day_Select.this, Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}