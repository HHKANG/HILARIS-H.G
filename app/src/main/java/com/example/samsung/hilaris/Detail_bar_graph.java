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
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import org.json.JSONException;
import org.json.JSONObject;

public class Detail_bar_graph extends AppCompatActivity {
    int Left = 0;
    int Right = 1;
    JSON json;
    String get_name;
    String get_mb_id;
    String get_GUID;

    double UB_left;
    double UB_right;
    double UL_left;
    double UL_right;

    int get_postion;

    TextView data_name;
    TextView left_data;
    TextView right_data;

    double[][] Bar_array = new double[7][2];
    String[] name = {"Bending" , "HandStrength", "LegStrength", "AgilityMovementUB", "AgilityReactionUB", "AgilityMovementUL", "AgilityReactionUL"};
    String[] LRdata = {"Left", "Right"};

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
        get_postion = intent.getExtras().getInt("position");

        data_name = (TextView)findViewById(R.id.name_text);
        left_data = (TextView)findViewById(R.id.left_text);
        right_data = (TextView)findViewById(R.id.right_text);

        data_name.setText(""+get_name);

        final double[][] graph_array = new double[5][];
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

                    for(int i=0; i < 7; i++)
                        for(int j =0; j<2; j++)
                        {
                           Bar_array[i][j] = json.insert(name[i], LRdata[j]);
                        }
                    UB_left = Bar_array[3][0] + Bar_array[4][0];
                    UB_right = Bar_array[3][1]+ Bar_array[4][1];
                    UL_left = Bar_array[5][0] + Bar_array[6][0];
                    UL_right = Bar_array[5][1] + Bar_array[6][1];

                    graph_array[0] = new double[]{Bar_array[0][0], Bar_array[0][1]};
                    graph_array[1] = new double[]{Bar_array[1][0], Bar_array[1][1]};
                    graph_array[2] = new double[]{Bar_array[2][0], Bar_array[2][1]};
                    graph_array[3] = new double[]{UB_left, UB_right};
                    graph_array[4] = new double[]{UL_left, UL_right};

                    GraphView graph = (GraphView) findViewById(R.id.graph);
                    StaticLabelsFormatter staticLabelsFormatter1 = new StaticLabelsFormatter(graph);
                    staticLabelsFormatter1.setHorizontalLabels(new String[] {"Left", "Right"});
                    graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter1);

                    BarGraphSeries<DataPoint> Bar_graph = new BarGraphSeries<>(new DataPoint[] {
                            new DataPoint(Left,  graph_array[get_postion][0]),
                            new DataPoint(Right, graph_array[get_postion][1]),
                    });
                    Bar_graph.setSpacing(50);
                    Bar_graph.setColor(Color.BLUE);
                    Bar_graph.setTitle("Bar_graph");

                    graph.addSeries(Bar_graph);

                    left_data.setText(""+graph_array[get_postion][0]);
                    right_data.setText(""+graph_array[get_postion][1]);

                    Bar_graph.setAnimated(true);

                    graph.getViewport().setYAxisBoundsManual(true);
                    graph.getViewport().setMinY(0);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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