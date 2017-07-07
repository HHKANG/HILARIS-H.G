package com.example.samsung.hilaris;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.json.JSONException;
import org.json.JSONObject;

public class Detail_HeartRateGraph extends AppCompatActivity {
    int Before = 0;
    int During =1;
    int After =2;
    JSON json;
    String get_mb_id;
    String get_GUID;



    double HRRest;
    double HRStim;
    double HRRecv;

    TextView Before_HeartRate;
    TextView During_HeartRate;
    TextView After_HeartRate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_detail__heart_rate_graph);

        Intent intent = getIntent();
        get_mb_id = intent.getExtras().getString("mb_id");
        get_GUID = intent.getExtras().getString("GUID");

        Before_HeartRate = (TextView) findViewById(R.id.textView_Before_HeartRate);
        During_HeartRate = (TextView) findViewById(R.id.textView_During_HeartRate);
        After_HeartRate = (TextView) findViewById(R.id.textView_After_HeartRate);


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
                    HRRest = json.getHRRest();
                    Before_HeartRate.setText(""+HRRest);

                    HRStim = json.getHRStim();
                    During_HeartRate.setText(""+HRStim);

                    HRRecv = json.getHRRecv();
                    After_HeartRate.setText(""+HRRecv);


                    GraphView graph_HeartRate = (GraphView) findViewById(R.id.graph_HeartRate);
                    StaticLabelsFormatter staticLabelsFormatter1 = new StaticLabelsFormatter(graph_HeartRate);
                    staticLabelsFormatter1.setHorizontalLabels(new String[] {"Before", "During", "After"});
                    graph_HeartRate.setTitle("Heart Rate");
                    graph_HeartRate.getLegendRenderer().setVisible(true);
                    graph_HeartRate.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);


                    graph_HeartRate.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter1);
                    LineGraphSeries<DataPoint> HeartRate = new LineGraphSeries<>(new DataPoint[] {
                            new DataPoint(Before,  HRRest),
                            new DataPoint(During, HRStim),
                            new DataPoint(After, HRRecv),
                    });
                    HeartRate.setColor(Color.BLUE);
                    HeartRate.setTitle("Heart Rate");
                    HeartRate.setAnimated(true);
                    graph_HeartRate.addSeries(HeartRate);
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
                Intent intent = new Intent(Detail_HeartRateGraph.this, Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
