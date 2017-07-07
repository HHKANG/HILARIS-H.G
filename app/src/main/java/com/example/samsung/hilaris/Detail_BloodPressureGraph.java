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
    int Before = 0;
    int During =1;
    int After =2;
    JSON json;
    String get_mb_id;
    String get_GUID;



    double BloodPressure_Rest_Left_sys;
    double BloodPressure_Stim_Left_sys;
    double BloodPressure_Recv_Left_sys;

    double BloodPressure_Rest_Left_dia;
    double BloodPressure_Stim_Left_dia;
    double BloodPressure_Recv_Left_dia;

    double BloodPressure_Rest_Right_sys;
    double BloodPressure_Stim_Right_sys;
    double BloodPressure_Recv_Right_sys;

    double BloodPressure_Rest_Right_dia;
    double BloodPressure_Stim_Right_dia;
    double BloodPressure_Recv_Right_dia;

    TextView BPL_BEFORE_SYS;
    TextView BPL_DURING_SYS;
    TextView BPL_AFTER_SYS;

    TextView BPL_BEFORE_DIA;
    TextView BPL_DURING_DIA;
    TextView BPL_AFTER_DIA;

    TextView BPR_BEFORE_SYS;
    TextView BPR_DURING_SYS;
    TextView BPR_AFTER_SYS;

    TextView BPR_BEFORE_DIA;
    TextView BPR_DURING_DIA;
    TextView BPR_AFTER_DIA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_detail__blood_pressure_graph);
        Intent intent = getIntent();
        get_mb_id = intent.getExtras().getString("mb_id");
        get_GUID = intent.getExtras().getString("GUID");

        BPL_BEFORE_SYS = (TextView) findViewById(R.id.textView_BPL_BEFORE_SYS);
        BPL_DURING_SYS = (TextView) findViewById(R.id.textView_BPL_DURING_SYS);
        BPL_AFTER_SYS = (TextView) findViewById(R.id.textView_BPL_AFTER_SYS);

        BPL_BEFORE_DIA = (TextView) findViewById(R.id.textView_BPL_BEFORE_DIA);
        BPL_DURING_DIA = (TextView) findViewById(R.id.textView_BPL_DURING_DIA);
        BPL_AFTER_DIA = (TextView) findViewById(R.id.textView_BPL_AFTER_DIA);

        BPR_BEFORE_SYS = (TextView) findViewById(R.id.textView_BPR_BEFORE_SYS);
        BPR_DURING_SYS = (TextView) findViewById(R.id.textView_BPR_DURING_SYS);
        BPR_AFTER_SYS = (TextView) findViewById(R.id.textView_BPR_AFTER_SYS);

        BPR_BEFORE_DIA = (TextView) findViewById(R.id.textView_BPR_BEFORE_DIA);
        BPR_DURING_DIA = (TextView) findViewById(R.id.textView_BPR_DURING_DIA);
        BPR_AFTER_DIA = (TextView) findViewById(R.id.textView_BPR_AFTER_DIA);


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
                    BloodPressure_Rest_Left_sys = json.getBPRestSystolicLeft();
                    BPL_BEFORE_SYS.setText(""+BloodPressure_Rest_Left_sys);

                    BloodPressure_Stim_Left_sys = json.getBPStimSystolicLeft();
                    BPL_DURING_SYS.setText(""+BloodPressure_Stim_Left_sys);

                    BloodPressure_Recv_Left_sys = json.getBPRecvSystolicLeft();
                    BPL_AFTER_SYS.setText(""+BloodPressure_Recv_Left_sys);

                    BloodPressure_Rest_Left_dia = json.getBPRestDiastolicLeft();
                    BPL_BEFORE_DIA.setText(""+BloodPressure_Rest_Left_dia);

                    BloodPressure_Stim_Left_dia = json.getBPStimDiastolicLeft();
                    BPL_DURING_DIA.setText(""+BloodPressure_Stim_Left_dia);

                    BloodPressure_Recv_Left_dia = json.getBPRecvDiastolicLeft();
                    BPL_AFTER_DIA.setText(""+BloodPressure_Recv_Left_dia);

                    BloodPressure_Rest_Right_sys = json.getBPRestSystolicRight();
                    BPR_BEFORE_SYS.setText(""+BloodPressure_Rest_Right_sys);

                    BloodPressure_Stim_Right_sys = json.getBPStimSystolicRight();
                    BPR_DURING_SYS.setText(""+BloodPressure_Stim_Right_sys);

                    BloodPressure_Recv_Right_sys = json.getBPRecvSystolicRight();
                    BPR_AFTER_SYS.setText(""+BloodPressure_Recv_Right_sys);

                    BloodPressure_Rest_Right_dia = json.getBPRestDiastolicRight();
                    BPR_BEFORE_DIA.setText(""+BloodPressure_Rest_Right_dia);

                    BloodPressure_Stim_Right_dia = json.getBPStimDiastolicRight();
                    BPR_DURING_DIA.setText(""+BloodPressure_Stim_Right_dia);

                    BloodPressure_Recv_Right_dia = json.getBPRecvDiastolicRight();
                    BPR_AFTER_DIA.setText(""+BloodPressure_Recv_Right_dia);


                    GraphView graph1 = (GraphView) findViewById(R.id.graph1);
                    StaticLabelsFormatter staticLabelsFormatter1 = new StaticLabelsFormatter(graph1);
                    staticLabelsFormatter1.setHorizontalLabels(new String[] {"Before", "During", "After"});
                    graph1.setTitle("Left Blood Pressure");
                    graph1.getLegendRenderer().setVisible(true);

                    graph1.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);

                    GraphView graph2= (GraphView) findViewById(R.id.graph2);


                    StaticLabelsFormatter staticLabelsFormatter2 = new StaticLabelsFormatter(graph2);
                    staticLabelsFormatter2.setHorizontalLabels(new String[] {"Before", "During", "After"});
                    graph2.setTitle("Right Blood Pressure");
                    graph2.getLegendRenderer().setVisible(true);
                    graph2.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);


                    graph1.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter1);
                    LineGraphSeries<DataPoint> BloodPressureLeftSys = new LineGraphSeries<>(new DataPoint[] {
                            new DataPoint(Before,  BloodPressure_Rest_Left_sys),
                            new DataPoint(During, BloodPressure_Stim_Left_sys),
                            new DataPoint(After, BloodPressure_Recv_Left_sys),
                    });
                    BloodPressureLeftSys.setColor(Color.BLUE);
                    BloodPressureLeftSys.setTitle("BloodPressureLeftSystolic");


                    graph1.addSeries(BloodPressureLeftSys);
                    LineGraphSeries<DataPoint> BloodPressureleftDia = new LineGraphSeries<>(new DataPoint[] {
                            new DataPoint(Before, BloodPressure_Rest_Left_dia),
                            new DataPoint(During, BloodPressure_Stim_Left_dia),
                            new DataPoint(After, BloodPressure_Recv_Left_dia),
                    });
                    BloodPressureleftDia.setColor(Color.GREEN);
                    BloodPressureleftDia.setTitle("BloodPressureLeftDiastolic");
                    graph1.addSeries(BloodPressureleftDia);
//Putting Data in Graph (dia) in Second Graph

                    graph2.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter2);
                    LineGraphSeries<DataPoint> BloodPressureRightSys = new LineGraphSeries<>(new DataPoint[] {
                            new DataPoint(Before, BloodPressure_Rest_Left_sys),
                            new DataPoint(During, BloodPressure_Stim_Left_sys),
                            new DataPoint(After, BloodPressure_Recv_Left_sys),
                    });
                    BloodPressureRightSys.setColor(Color.BLUE);
                    BloodPressureRightSys.setTitle("BloodPressureRightSystolic");
                    graph2.addSeries(BloodPressureRightSys);
                    LineGraphSeries<DataPoint> BloodPressureRightDia = new LineGraphSeries<>(new DataPoint[] {
                            new DataPoint(Before, BloodPressure_Rest_Left_dia),
                            new DataPoint(During, BloodPressure_Stim_Left_dia),
                            new DataPoint(After, BloodPressure_Recv_Left_dia),
                    });
                    BloodPressureRightDia.setColor(Color.GREEN);
                    BloodPressureRightDia.setTitle("BloodPressureRightDiastolic");
                    graph2.addSeries(BloodPressureRightDia);

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
