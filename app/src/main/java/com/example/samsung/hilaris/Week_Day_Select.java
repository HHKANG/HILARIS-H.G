package com.example.samsung.hilaris;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class Week_Day_Select extends AppCompatActivity {
    Day_list_adapter day_adapter;
    Week_list_adapter week_adapter;
    ListView day_listView;
    ListView week_listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_week__day__select);

        day_adapter = new Day_list_adapter();
        week_adapter = new Week_list_adapter();

        day_listView = (ListView)findViewById(R.id.day_list);
        day_listView.setAdapter(day_adapter);

        week_listView = (ListView)findViewById(R.id.week_list);
        week_listView.setAdapter(week_adapter);

        day_adapter.addItem("Day 1");
        day_adapter.addItem("Day 2");
        day_adapter.addItem("Day 3");
        day_adapter.addItem("Day 4");
        day_adapter.addItem("Day 5");
        day_adapter.addItem("Day 6");
        day_adapter.addItem("Day 7");

        week_adapter.addItem("Week1");
        week_adapter.addItem("Week2");
        week_adapter.addItem("Week3");
        week_adapter.addItem("Week4");
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