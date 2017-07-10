package com.example.samsung.hilaris;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
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

import org.json.JSONException;
import org.json.JSONObject;

public class Detail_bar_graph extends AppCompatActivity {
    int Left = 0;
    int Right =1;
    JSON json;
    String get_name;
    String get_mb_id;
    String get_GUID;

    double FlexLeft;
    double FlexRight;

    double HandStrengthLeft;
    double HandStrengthRight;

    double LegStrengthLeft;
    double LegStrengthRight;

    double AgilityMovementUBLeft;
    double AgilityReactionUBLeft;
    double UB_left;

    double AgilityMovementUBRight;
    double AgilityReactionUBRight;
    double UB_right;

    double AgilityMovementULLeft;
    double AgilityReactionULLeft;
    double UL_left;

    double AgilityMovementULRight;
    double AgilityReactionULRight;
    double UL_right;

    TextView data_name;
    TextView left_data;
    TextView right_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_detail_bar_graph);

        Intent intent = getIntent();

        get_mb_id = intent.getExtras().getString("mb_id");
        get_GUID = intent.getExtras().getString("GUID");
        get_name = intent.getExtras().getString("name");

        data_name = (TextView)findViewById(R.id.name_text);
        left_data = (TextView)findViewById(R.id.left_text);
        right_data = (TextView)findViewById(R.id.right_text);

        data_name.setText(""+get_name);
//Setting Graph's X axis

        String url ="http://221.153.186.186/cooperadvisormobilews/WSCooperAdvisor.svc/GetMedifitTestByUser/"+get_mb_id+"/"+get_GUID;
        RequestQueue queue = Volley.newRequestQueue(this);
        // Request a string response from the provided URL.
        JsonObjectRequest objRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    json = new JSON(response);
//Getting Data
                    FlexLeft = json.getFlexLeft();
                    FlexRight = json.getFlexRight();

                    HandStrengthLeft = json.getHandStrengthLeft();
                    HandStrengthRight = json.getHandStrengthRight();

                    LegStrengthLeft = json.getLegStrengthLeft();
                    LegStrengthRight = json.getLegStrengthRight();

                    AgilityMovementUBLeft = json.getAgilityMovementUBLeft();
                    AgilityReactionUBLeft = json.getAgilityReactionUBLeft();
                    UB_left = AgilityMovementUBLeft + AgilityReactionUBLeft;

                    AgilityMovementUBRight = json.getAgilityMovementUBRight();
                    AgilityReactionUBRight = json.getAgilityReactionUBRight();
                    UB_right = AgilityMovementUBRight + AgilityReactionUBRight;

                    AgilityMovementULLeft = json.getAgilityMovementULLeft();
                    AgilityReactionULLeft = json.getAgilityReactionULLeft();
                    UL_left = AgilityMovementULLeft + AgilityReactionULLeft;

                    AgilityMovementULRight = json.getAgilityMovementULRight();
                    AgilityReactionULRight = json.getAgilityReactionULRight();
                    UL_right = AgilityMovementULRight + AgilityReactionULRight;

                    GraphView graph1 = (GraphView) findViewById(R.id.graph1);
                    StaticLabelsFormatter staticLabelsFormatter1 = new StaticLabelsFormatter(graph1);
                    staticLabelsFormatter1.setHorizontalLabels(new String[] {"Left", "Right"});
                    graph1.getLegendRenderer().setVisible(true);


                    graph1.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
                    graph1.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter1);

                    if(get_name.equals("Flexibility")){
                        BarGraphSeries<DataPoint> BloodPressureLeftSys = new BarGraphSeries<>(new DataPoint[] {
                                new DataPoint(Left,  FlexLeft),
                                new DataPoint(Right, FlexRight),
                        });
                        BloodPressureLeftSys.setSpacing(50);
                        BloodPressureLeftSys.setColor(Color.BLUE);
                        BloodPressureLeftSys.setTitle("Flexibility");


                        graph1.addSeries(BloodPressureLeftSys);

                        left_data.setText(""+FlexLeft);
                        right_data.setText(""+FlexRight);


                                           }

                    else if(get_name.equals("Upper Strength")){
                        BarGraphSeries<DataPoint> BloodPressureLeftSys = new BarGraphSeries<>(new DataPoint[] {
                                new DataPoint(Left,  HandStrengthLeft),
                                new DataPoint(Right, HandStrengthRight),
                        });
                        BloodPressureLeftSys.setSpacing(50);
                        BloodPressureLeftSys.setColor(Color.BLUE);
                        BloodPressureLeftSys.setTitle("Hand Strength");

                        graph1.addSeries(BloodPressureLeftSys);

                        left_data.setText(""+HandStrengthLeft);
                        right_data.setText(""+HandStrengthRight);
                    }

                    else if(get_name.equals("Lower Strength")){
                        BarGraphSeries<DataPoint> BloodPressureLeftSys = new BarGraphSeries<>(new DataPoint[] {
                                new DataPoint(Left,  LegStrengthLeft),
                                new DataPoint(Right, LegStrengthRight),
                        });
                        BloodPressureLeftSys.setSpacing(50);
                        BloodPressureLeftSys.setColor(Color.BLUE);
                        BloodPressureLeftSys.setTitle("Leg Strength");

                        graph1.addSeries(BloodPressureLeftSys);

                        left_data.setText(""+LegStrengthLeft);
                        right_data.setText(""+LegStrengthRight);
                    }

                    else if(get_name.equals("Upper Body Agility")){
                        BarGraphSeries<DataPoint> BloodPressureLeftSys = new BarGraphSeries<>(new DataPoint[] {
                                new DataPoint(Left,  UB_left),
                                new DataPoint(Right, UB_right),
                        });
                        BloodPressureLeftSys.setSpacing(50);
                        BloodPressureLeftSys.setColor(Color.BLUE);
                        BloodPressureLeftSys.setTitle("Upper Body Agility");

                        graph1.addSeries(BloodPressureLeftSys);

                        left_data.setText(""+UB_left);
                        right_data.setText(""+UB_right);
                    }

                    else if(get_name.equals("Upper Limb Agility")){
                        BarGraphSeries<DataPoint> BloodPressureLeftSys = new BarGraphSeries<>(new DataPoint[] {
                                new DataPoint(Left,  UL_left),
                                new DataPoint(Right, UL_right),
                        });
                        BloodPressureLeftSys.setSpacing(50);
                        BloodPressureLeftSys.setColor(Color.BLUE);
                        BloodPressureLeftSys.setTitle("Upper Limb Agility");

                        graph1.addSeries(BloodPressureLeftSys);

                        left_data.setText(""+UL_left);
                        right_data.setText(""+UL_right);
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
                Intent intent = new Intent(Detail_bar_graph.this, Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}