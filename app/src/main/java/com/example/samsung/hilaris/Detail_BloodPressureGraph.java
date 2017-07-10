package com.example.samsung.hilaris;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import org.json.JSONException;
import org.json.JSONObject;

public class Detail_BloodPressureGraph extends AppCompatActivity {
    public static final int BP_TEST_SITES = 2;      // Left Hand = 0 & right Hand = 1
    public static final int BP_SUBJECT_STATES = 3; // Rest State, Stimulus State, Recovery State
    public static final int BP_VALUE_PAIR = 2;      // Systolic Pressure, Diastolic Pressure

    int[][][] intBloodPress = new int[BP_TEST_SITES][BP_SUBJECT_STATES][BP_VALUE_PAIR];
    TextView[][][] textViews_BloodPressure = new TextView[BP_TEST_SITES][BP_SUBJECT_STATES][BP_VALUE_PAIR];

    String[] strTestSites = {"Left", "Right"}; //BP_TEST_SITES
    String[]strSubjectStates = {"Rest", "Stim", "Recv"}; //BP_SUBJECT_STATES
    String[] strBpValuePair = {"Systolic", "Diastolic"}; //BP_VALUE_PAIR

    JSON json;
    String get_mb_id;
    String get_GUID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail_blood_pressure_graph);
        Intent intent = getIntent();

        get_mb_id = intent.getExtras().getString("mb_id");
        get_GUID = intent.getExtras().getString("GUID");
/*
        for(int testSites=0; testSites<BP_TEST_SITES; testSites++)
            for(int subjectStates=0; subjectStates<BP_SUBJECT_STATES;subjectStates++)
                for(int valuePair =0; valuePair<BP_VALUE_PAIR; valuePair++)
                {
                    int index =0;
                     textViews_BloodPressure[testSites][subjectStates][valuePair] = (TextView) findViewByID(R.id.??????)
                }
*/
//Later find the way to change the bellow array into for loop;


        textViews_BloodPressure[0][0][0] = (TextView) findViewById(R.id.textView_BPL_BEFORE_SYS);
        textViews_BloodPressure[0][0][1] = (TextView) findViewById(R.id.textView_BPL_BEFORE_DIA);

        textViews_BloodPressure[0][1][0] = (TextView) findViewById(R.id.textView_BPL_DURING_SYS);
        textViews_BloodPressure[0][1][1]= (TextView) findViewById(R.id.textView_BPL_DURING_DIA);

        textViews_BloodPressure[0][2][0] = (TextView) findViewById(R.id.textView_BPL_AFTER_SYS);
        textViews_BloodPressure[0][2][1] = (TextView) findViewById(R.id.textView_BPL_AFTER_DIA);

        textViews_BloodPressure[1][0][0]= (TextView) findViewById(R.id.textView_BPR_BEFORE_SYS);
        textViews_BloodPressure[1][0][1] = (TextView) findViewById(R.id.textView_BPR_BEFORE_DIA);

        textViews_BloodPressure[1][1][0] = (TextView) findViewById(R.id.textView_BPR_DURING_SYS);
        textViews_BloodPressure [1][1][1] = (TextView) findViewById(R.id.textView_BPR_DURING_DIA);

        textViews_BloodPressure [1][2][0]= (TextView) findViewById(R.id.textView_BPR_AFTER_SYS);
        textViews_BloodPressure [1][2][1] = (TextView) findViewById(R.id.textView_BPR_AFTER_DIA);


        String url ="http://221.153.186.186/cooperadvisormobilews/WSCooperAdvisor.svc/GetMedifitTestByUser/"+get_mb_id+"/"+get_GUID;
        RequestQueue queue = Volley.newRequestQueue(this);
        // Request a string response from the provided URL.
        JsonObjectRequest objRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    json = new JSON(response);
//Getting Data and Saving into TextView and Array of Data
                    for(int testSites=0; testSites<BP_TEST_SITES; testSites++)
                        for(int subjectStates=0; subjectStates<BP_SUBJECT_STATES;subjectStates++)
                            for(int valuePair =0; valuePair<BP_VALUE_PAIR; valuePair++)
                            {
                                intBloodPress[testSites][subjectStates][valuePair] = json.getBloodPressure(strTestSites[testSites], strSubjectStates[subjectStates], strBpValuePair[valuePair]);
                                textViews_BloodPressure[testSites][subjectStates][valuePair].setText(""+intBloodPress[testSites][subjectStates][valuePair]);
                            }
//Setting graph UI of Graph 1 (Left)
                    GraphView graph1 = (GraphView) findViewById(R.id.graph1);
                    StaticLabelsFormatter staticLabelsFormatter1 = new StaticLabelsFormatter(graph1);
                    staticLabelsFormatter1.setHorizontalLabels(new String[] {"Before", "During", "After"});
                    graph1.setTitle("Left Blood Pressure");
                    graph1.getLegendRenderer().setVisible(true);
                    graph1.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
//Setting graph UI of Graph 1 (Right)
                    GraphView graph2= (GraphView) findViewById(R.id.graph2);
                    StaticLabelsFormatter staticLabelsFormatter2 = new StaticLabelsFormatter(graph2);
                    staticLabelsFormatter2.setHorizontalLabels(new String[] {"Before", "During", "After"});
                    graph2.setTitle("Right Blood Pressure");
                    graph2.getLegendRenderer().setVisible(true);
                    graph2.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
                    //Putting Data in Graph by Using intBloodPress Array ( Array of Data )
                    for(int BpValue=0 ; BpValue<BP_VALUE_PAIR; BpValue++)
                    {
                        int testSitesL =0; //TestSites for LEFT
                        int testSitesR =1; // TestSites for RIGHT

                        LineGraphSeries<DataPoint> BloodPressureLeft = new LineGraphSeries<>(new DataPoint[]{});
                        for (int subjectType = 0; subjectType < BP_SUBJECT_STATES; subjectType++) {
                            BloodPressureLeft.appendData(new DataPoint(subjectType, intBloodPress[testSitesL][subjectType][BpValue]), true, 10000);
                        }
                        LineGraphSeries<DataPoint> BloodPressureRight = new LineGraphSeries<>(new DataPoint[]{});
                        for (int subjectType = 0; subjectType < BP_SUBJECT_STATES; subjectType++) {
                            BloodPressureRight.appendData(new DataPoint(subjectType, intBloodPress[testSitesR][subjectType][BpValue]), true, 10000);
                        }
                        if(BpValue==0) //Setting Color & Title
                        {
                            BloodPressureLeft.setColor(Color.BLUE);
                            BloodPressureLeft.setTitle("BloodPressureLeftSystolic");
                            BloodPressureRight.setColor(Color.BLUE);
                            BloodPressureRight.setTitle("BloodPressureLeftSystolic");

                        }
                        else
                        {
                            BloodPressureLeft.setColor(Color.GREEN);
                            BloodPressureLeft.setTitle("BloodPressureLeftDiatolic");
                            BloodPressureRight.setColor(Color.GREEN);
                            BloodPressureRight.setTitle("BloodPressureLeftDiatolic");
                        }
                        graph1.addSeries(BloodPressureLeft);
                        graph2.addSeries(BloodPressureRight);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                };
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();
            }
        });

        // Add the request to the RequestQueue.}
        queue.add(objRequest);
//Putting Data in Graph (sys) in First Graph
    }
}
