package com.example.samsung.hilaris;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
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
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.samsung.hilaris.Detail_BloodPressureGraph.BP_SUBJECT_STATES;
import static com.example.samsung.hilaris.Detail_BloodPressureGraph.BP_TEST_SITES;

public class Detail_Flexibility_Graph extends AppCompatActivity {
    public static final int FX_TEST_SITES = 2;      // Left Hand = 0 & right Hand = 1
    public static final int FX_VALUE_ITEMS= 2;      // Rotation, Bending

    int[][] intFlex = new int[FX_VALUE_ITEMS][FX_TEST_SITES];

    String[] strTestSites = {"Left", "Right"};
    String[] strValueItems = {"Rotation", "Bending"};
    LinearLayout LinearLayout;
    TextView[] RotationBending = new TextView[4];

    JSON json;
    String get_mb_id;
    String get_GUID;
    int TagindexOfTable = 0;
    //    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail__flexibility__graph);

        Intent intent = getIntent();
        get_mb_id = intent.getExtras().getString("mb_id");
        get_GUID = intent.getExtras().getString("GUID");
        LinearLayout = (LinearLayout) findViewById(R.id.RotationBending); // Linear Layout containing the table


        String url ="http://221.153.186.186/cooperadvisormobilews/WSCooperAdvisor.svc/GetMedifitTestByUser/"+get_mb_id+"/"+get_GUID;
        RequestQueue queue = Volley.newRequestQueue(this);
        // Request a string response from the provided URL.
        JsonObjectRequest objRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    json = new JSON(response);
//Getting Data and Saving into TextView and Array of Data
                    int Extension, Flextion;

                        for (int valueItem = 0; valueItem < FX_VALUE_ITEMS; valueItem++)
                            for(int testSites=0; testSites<FX_TEST_SITES; testSites++)
                          {
                                intFlex[valueItem][testSites] = json.getFlexLR(strValueItems[valueItem], strTestSites[testSites]);
                             RotationBending[TagindexOfTable] = (TextView) LinearLayout.findViewWithTag(""+TagindexOfTable);
                              RotationBending[TagindexOfTable].setText("" + intFlex[valueItem][testSites]);
                              TagindexOfTable++;
                            }
                           //Setting graph UI of Graph 1 (Left)
                            GraphView Rotation = (GraphView) findViewById(R.id.graph_Rotation);
                    StaticLabelsFormatter staticLabelsFormatter1 = new StaticLabelsFormatter(Rotation);
                    staticLabelsFormatter1.setHorizontalLabels(strTestSites);
                    Rotation.setTitle(strValueItems[0]);//Rotation
                    Rotation.getLegendRenderer().setVisible(true);
                    Rotation.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
                    Rotation.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter1);
//Setting graph UI of Graph 1 (Right)
                    GraphView Bending= (GraphView) findViewById(R.id.graph_Bending);
                    StaticLabelsFormatter staticLabelsFormatter2 = new StaticLabelsFormatter(Bending);
                    staticLabelsFormatter2.setHorizontalLabels(strTestSites);
                    Bending.setTitle(strValueItems[1]); //Bending
                    Bending.getLegendRenderer().setVisible(true);
                    Bending.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
                    Bending.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter2);
                    //Putting Data in Graph by Using intBloodPress Array ( Array of Data )

                   // valueItem = 0; //valueItem = 0 : Rotation, valueItem 1 : Bending
                        BarGraphSeries<DataPoint> RotationSeries = new BarGraphSeries<>(new DataPoint[]{});
                        for (int testSites = 0; testSites < FX_TEST_SITES; testSites++) {
                            RotationSeries.appendData(new DataPoint(testSites, intFlex[0][testSites]), true, 10000);
                        }

                        BarGraphSeries<DataPoint> BendingSeries = new BarGraphSeries<>(new DataPoint[]{});
                        for (int testSites = 0; testSites < FX_TEST_SITES; testSites++) {
                            BendingSeries.appendData(new DataPoint(testSites, intFlex[1][testSites]), true, 10000);
                        }

                        BendingSeries.setAnimated(true);
                        RotationSeries.setAnimated(true);
                        Rotation.addSeries(RotationSeries);
                        Bending.addSeries(BendingSeries);

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
    }
}
