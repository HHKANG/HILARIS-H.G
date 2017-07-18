package com.example.samsung.hilaris;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;

import org.json.JSONException;
import org.json.JSONObject;

public class History extends Graph {

    private final int HISTORY_DATA = 1; // 임시로1로 해놓음,,
    protected JSONObject[] History_JSONOBJECT = new JSONObject[HISTORY_DATA];
    protected JSON[] HISTORY_JSON = new JSON[HISTORY_DATA];
    protected double[] JSONObjectData = new double[HISTORY_DATA];
    protected String[] JSONObjectDate = new String[HISTORY_DATA];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        GraphView graph_history = (GraphView) findViewById(R.id.graph_history);
        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph_history);

        //Getting most recent 3 datas

            try {
                for(int i = 0 ; i<HISTORY_DATA; i++) //
                {
                    History_JSONOBJECT[i] = new JSONObject(getIntent().getStringExtra("JSONObject" + i));
                    HISTORY_JSON[i] = new JSON(History_JSONOBJECT[i]);
                    JSONObjectDate[i] = HISTORY_JSON[i].getTestDate();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                for(int i =0; i< HISTORY_DATA; i++) {
                    JSONObjectData[i] = HISTORY_JSON[i].getBMI();
                    Toast.makeText(this, "" + JSONObjectData[i], Toast.LENGTH_SHORT).show();
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
       // staticLabelsFormatter.setHorizontalLabels(JSONObjectDate);
        graph_history.setTitle("Waist");
        graph_history.getLegendRenderer().setVisible(true);
        graph_history.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        graph_history.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
        graph_history.addSeries(addBarSeriesData(JSONObjectData, "BLUE"));
    /*
        graph_history.getViewport().setMinY(0);
        graph_history.getViewport().setYAxisBoundsManual(true);
       */
    }
}
