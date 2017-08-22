package com.example.samsung.hilaris;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Adapter class for Day_list and this layout is day_listview.
 * @paramgetCount(): Count num of Dayitem
 */


public class Detail_BloodPressureGraph extends Graph {
    public static final int BP_TEST_SITES = 2;      // Left Hand = 0 & right Hand = 1
    public static final int BP_SUBJECT_STATES = 3; // Rest State, Stimulus State, Recovery State
    public static final int BP_VALUE_PAIR = 2;      // Systolic Pressure, Diastolic Pressure

    int[][][] intBloodPress = new int[BP_TEST_SITES][BP_VALUE_PAIR][BP_SUBJECT_STATES];
    TextView[][][] textViews_BloodPressure = new TextView[BP_TEST_SITES][BP_VALUE_PAIR][BP_SUBJECT_STATES];

    LinearLayout LinearLayout;

    String[] strTestSites = {"Left", "Right"}; //BP_TEST_SITES
    String[]strSubjectStates = {"Rest", "Stim", "Recv"}; //BP_SUBJECT_STATES
    String[] strBpValuePair = {"Systolic", "Diastolic"}; //BP_VALUE_PAIR

    JSON json;

    int TagindexOfTable = 0;

    JSONObject profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_detail_blood_pressure_graph);
        final Intent intent = getIntent();

         LinearLayout = (LinearLayout) findViewById(R.id.linearLayout_table); // Linear Layout containing the table
        try {
            profile = new JSONObject(intent.getStringExtra("SelectedProfile"));
            json = new JSON(profile);

            for(int testSites=0; testSites<BP_TEST_SITES; testSites++) {
                for (int valuePair = 0; valuePair < BP_VALUE_PAIR; valuePair++)
                    for (int subjectStates = 0; subjectStates < BP_SUBJECT_STATES; subjectStates++)
                    {
                        intBloodPress[testSites][valuePair][subjectStates] = json.getBloodPressure(strTestSites[testSites], strSubjectStates[subjectStates], strBpValuePair[valuePair]);
                        textViews_BloodPressure[testSites][valuePair][subjectStates] = (TextView) LinearLayout.findViewWithTag(""+TagindexOfTable);
                        textViews_BloodPressure[testSites][valuePair][subjectStates].setText("" + intBloodPress[testSites][valuePair][subjectStates]);
                        TagindexOfTable++;
                    }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
//Getting Data and Saving into TextView and Array of Data

//Setting graph UI of Graph 1 (Left)
                    GraphView graph1 = (GraphView) findViewById(R.id.graph1);
                    StaticLabelsFormatter staticLabelsFormatter1 = new StaticLabelsFormatter(graph1);
                    staticLabelsFormatter1.setHorizontalLabels(strSubjectStates);
                    graph1.setTitle("Left Blood Pressure");
                    graph1.getLegendRenderer().setVisible(true);
                    graph1.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
                    graph1.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter1);
                    graph1.addSeries(addLineSeriesData(intBloodPress[0][0], "BLUE", "BloodPressureLeftSystolic"));
                    graph1.addSeries(addLineSeriesData(intBloodPress[0][1], "GREEN", "BloodPressureLeftDiastolic"));
//Setting graph UI of Graph 1 (Right)
                    GraphView graph2= (GraphView) findViewById(R.id.graph2);
                    StaticLabelsFormatter staticLabelsFormatter2 = new StaticLabelsFormatter(graph2);
                    staticLabelsFormatter2.setHorizontalLabels(strSubjectStates);
                    graph2.setTitle("Right Blood Pressure");
                    graph2.getLegendRenderer().setVisible(true);
                    graph2.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
                    graph2.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter2);
                    graph2.addSeries(addLineSeriesData(intBloodPress[1][0], "BLUE", "BloodPressureRightSystolic"));
                    graph2.addSeries(addLineSeriesData(intBloodPress[1][1], "GREEN", "BloodPressureRightDiastolic"));

//Putting Data in Graph (sys) in First Graph

        Button next_button = (Button)findViewById(R.id.button_next);
        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Detail_HeartRateGraph.class);
                intent.putExtra("SelectedProfile", profile.toString());
                startActivity(intent);
                finish();
            }
        });

        Button prev_button = (Button)findViewById(R.id.button_prev);
        prev_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Detail_bar_graph.class);
                intent.putExtra("SelectedProfile", profile.toString());
                intent.putExtra("position", 3);
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
                Intent intent = new Intent(Detail_BloodPressureGraph.this, Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}