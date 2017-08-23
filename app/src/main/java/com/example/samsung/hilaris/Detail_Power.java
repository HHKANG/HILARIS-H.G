package com.example.samsung.hilaris;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;



import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class shows six Power values for using json.
 */

public class Detail_Power  extends AppCompatActivity {

    double PowerLowest;
    int PowerMean;
    int PowerPeak;
    double PowerPeakWKG;
    double FatigueIndex;
    double FatigueSlope;

    LinearLayout LinearLayout;
    TextView TextView_PowerPeak;
    TextView TextView_PowerMean;
    TextView TextView_FatigueIndex;
    TextView TextView_FatigueSlope;
    TextView TextView_PowerPeakWKG;
    TextView TextView_PowerLowest;
    JSON json;
    JSONObject profile;



     @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            setContentView(R.layout.activity_detail__power);

            Intent intent = getIntent();
            LinearLayout = (LinearLayout) findViewById(R.id.power); // Linear Layout containing the table


            TextView_FatigueIndex = (TextView) findViewById(R.id.FatigueIndex);
            TextView_FatigueSlope = (TextView) findViewById(R.id.FatigueSlope);
            TextView_PowerMean = (TextView) findViewById(R.id.PowerMean);
            TextView_PowerPeak = (TextView) findViewById(R.id.PowerPeak);
            TextView_PowerPeakWKG = (TextView) findViewById(R.id.PowerPeakWKG);
            TextView_PowerLowest= (TextView) findViewById(R.id.PowerLowest);



            try {
                profile = new JSONObject(intent.getStringExtra("SelectedProfile"));
                json = new JSON(profile);
                FatigueIndex=json.getFatigueINdex();
                FatigueSlope=json.getFatigueSlope();
                PowerMean = json.getPowerMean();
                PowerPeak = json.getPowerPeak();
                PowerLowest=json.getPowerLowest();
                PowerPeakWKG=json.getPowerPeakWKG();
                TextView_PowerPeak.setText(""+PowerPeak);
                TextView_PowerMean.setText(""+PowerMean);
                TextView_FatigueSlope.setText(""+FatigueSlope);
                TextView_FatigueIndex.setText(""+FatigueIndex);
                TextView_PowerLowest.setText(""+PowerLowest);
                TextView_PowerPeakWKG.setText(""+PowerPeakWKG);



            } catch (JSONException e) {
                e.printStackTrace();
            }
//Getting Data and Saving into TextView and Array of Data


         Button next_button = (Button)findViewById(R.id.button_next);
         next_button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(getApplicationContext(), Detail_bar_graph.class);
                 intent.putExtra("SelectedProfile", profile.toString());
                 intent.putExtra("position", 0);
                 startActivity(intent);
                 finish();
             }
         });

         Button prev_button = (Button)findViewById(R.id.button_prev);
         prev_button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(getApplicationContext(), Detail_Flexibility_Graph.class);
                 intent.putExtra("SelectedProfile", profile.toString());
                 startActivity(intent);
                 finish();
             }
         });

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
                    Intent intent = new Intent(Detail_Power.this, Login.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                    return true;
            }
            return super.onOptionsItemSelected(item);
        }
    }



