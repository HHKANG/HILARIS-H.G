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
import com.jjoe64.graphview.helper.StaticLabelsFormatter;

import org.json.JSONException;
import org.json.JSONObject;

public class Detail_Flexibility_Graph extends Graph {
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

    JSONObject profile;
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


        TextView_Extension = (TextView) findViewById(R.id.textView_Extension_Value);
        TextView_Flexion = (TextView) findViewById(R.id.textView_Flextion_Value);



        try {
            profile = new JSONObject(intent.getStringExtra("SelectedProfile"));
            json = new JSON(profile);
            int Extension, Flextion;
            Extension = json.getExtension();
            Flextion = json.getFlexion();
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
        } catch (JSONException e) {
            e.printStackTrace();
        }
//Getting Data and Saving into TextView and Array of Data


//adding Items in Array



                    // valueItem = 0; //valueItem = 0 : Rotation, valueItem 1 : Lateral Flextion
                           //Setting graph UI of Graph 1 (Left)
                    GraphView Rotation = (GraphView) findViewById(R.id.graph_Rotation);
                    StaticLabelsFormatter staticLabelsFormatter1 = new StaticLabelsFormatter(Rotation);
                    staticLabelsFormatter1.setHorizontalLabels(strTestSites);
                    Rotation.setTitle(strValueItems[0]);//Rotation
                    Rotation.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter1);
                    Rotation.addSeries(addBarSeriesData(intFlex[1], "BLUE"));
                    Rotation.getViewport().setMinY(0);
                    Rotation.getViewport().setYAxisBoundsManual(true);
//Setting graph UI of Graph 1 (Right)
                    GraphView LateralFlextion= (GraphView) findViewById(R.id.graph_LateralFlextion);
                    StaticLabelsFormatter staticLabelsFormatter2 = new StaticLabelsFormatter(LateralFlextion);
                    staticLabelsFormatter2.setHorizontalLabels(strTestSites);
                    LateralFlextion.setTitle(strValueItems[1]); //Lateral Flextion
                    LateralFlextion.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter2);
                    LateralFlextion.addSeries(addBarSeriesData(intFlex[0], "BLUE"));
                    LateralFlextion.getViewport().setMinY(0);
                    LateralFlextion.getViewport().setYAxisBoundsManual(true);

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
                Intent intent = new Intent(getApplicationContext(), Detail_HeartRateGraph.class);
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
                Intent intent = new Intent(Detail_Flexibility_Graph.this, Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
