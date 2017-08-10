package com.example.samsung.hilaris;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Week_Day_Select extends AppCompatActivity {
    TextView week1_day1, week1_day2, week1_day3, week1_day4, week1_day5, week1_day6, week1_day7;
    TextView week2_day1, week2_day2, week2_day3, week2_day4, week2_day5, week2_day6, week2_day7;
    TextView week3_day1, week3_day2, week3_day3, week3_day4, week3_day5, week3_day6, week3_day7;
    TextView week4_day1, week4_day2, week4_day3, week4_day4, week4_day5, week4_day6, week4_day7;
    TextView[][] week_day = new TextView[4][7];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_week__day__select);

        /*week_day[4][7] = {{findViewById(R.id.week1_day1), week1_day2.findViewById(R.id.week1_day2), week1_day3.findViewById(R.id.week1_day3), week1_day4.findViewById(R.id.week1_day4), week1_day5.findViewById(R.id.week1_day5), week1_day6.findViewById(R.id.week1_day6), week1_day7.findViewById(R.id.week1_day7)},
                {week2_day1.findViewById(R.id.week2_day1), week2_day2.findViewById(R.id.week2_day2), week2_day3.findViewById(R.id.week2_day3), week2_day4.findViewById(R.id.week2_day4), week2_day5.findViewById(R.id.week2_day5), week2_day6.findViewById(R.id.week2_day6), week2_day7.findViewById(R.id.week2_day7)},
                {week3_day1.findViewById(R.id.week3_day1), week3_day2.findViewById(R.id.week3_day2), week3_day3.findViewById(R.id.week3_day3), week3_day4.findViewById(R.id.week3_day4), week3_day5.findViewById(R.id.week3_day5), week3_day6.findViewById(R.id.week3_day6), week3_day7.findViewById(R.id.week3_day7)},
                {week4_day1.findViewById(R.id.week4_day1), week4_day2.findViewById(R.id.week4_day2), week4_day3.findViewById(R.id.week4_day3), week4_day4.findViewById(R.id.week4_day4), week4_day5.findViewById(R.id.week4_day5), week4_day6.findViewById(R.id.week4_day6), week4_day7.findViewById(R.id.week4_day7)}};
*/

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