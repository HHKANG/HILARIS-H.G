package com.example.samsung.hilaris;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
    String[] strValueItems = {"Rotation", "LateralFlextion"};
    LinearLayout LinearLayout;
    TextView[] RotationBending = new TextView[4];
    TextView TextView_Extension;
    TextView TextView_Flexion;
    JSON json;
    String get_mb_id;
    String get_GUID;
    int TagindexOfTable = 0;
    //    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
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

                    Extension = json.getExtension();
                    Flextion = json.getFlexion();

                    TextView_Extension = (TextView) findViewById(R.id.textView_Extension_Value);
                    TextView_Flexion = (TextView) findViewById(R.id.textView_Flextion_Value);
                    TextView_Extension.setText(""+Extension);
                    TextView_Flexion.setText(""+Flextion);

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
                    Rotation.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter1);
//Setting graph UI of Graph 1 (Right)
                    GraphView LateralFlextion= (GraphView) findViewById(R.id.graph_LateralFlextion);
                    StaticLabelsFormatter staticLabelsFormatter2 = new StaticLabelsFormatter(LateralFlextion);
                    staticLabelsFormatter2.setHorizontalLabels(strTestSites);
                    LateralFlextion.setTitle(strValueItems[1]); //Lateral Flextion
                    LateralFlextion.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter2);


                   // valueItem = 0; //valueItem = 0 : Rotation, valueItem 1 : Lateral Flextion
                        BarGraphSeries<DataPoint> RotationSeries = new BarGraphSeries<>(new DataPoint[]{});
                        for (int testSites = 0; testSites < FX_TEST_SITES; testSites++) {
                            RotationSeries.appendData(new DataPoint(testSites, intFlex[0][testSites]), true, 10000);
                        }

                        BarGraphSeries<DataPoint> LateralFlextionSeries = new BarGraphSeries<>(new DataPoint[]{});
                        for (int testSites = 0; testSites < FX_TEST_SITES; testSites++) {
                            LateralFlextionSeries.appendData(new DataPoint(testSites, intFlex[1][testSites]), true, 10000);
                        }




                    LateralFlextionSeries.setSpacing(50);
                    LateralFlextionSeries.setAnimated(true);
                    LateralFlextion.addSeries(LateralFlextionSeries);
                    LateralFlextion.getViewport().setMinY(0);
                    LateralFlextion.getViewport().setYAxisBoundsManual(true);



                    RotationSeries.setSpacing(50);
                    RotationSeries.setAnimated(true);
                    Rotation.addSeries(RotationSeries);
                    Rotation.getViewport().setMinY(0);
                    Rotation.getViewport().setYAxisBoundsManual(true);




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
                Intent intent = new Intent(Detail_Flexibility_Graph.this, Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
