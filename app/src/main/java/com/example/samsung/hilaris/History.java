package com.example.samsung.hilaris;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;


import com.jjoe64.graphview.GraphView;

import com.jjoe64.graphview.LegendRenderer;

import org.json.JSONException;
import org.json.JSONObject;

public class History extends Graph {

    private final int HISTORY_DATA = 1; // 임시로1로 해놓음,, (받아올 DATA들의 수)
    private final int HISTORY_HEARTRATE_STATES=3; //REST, STIM, RECV
    private final int History_States = 2; // Left = 0 & Right = 1
    private final int ATTRIBUTE = 2;

    public static final int BP_TEST_SITES = 2;      // Left Hand = 0 & right Hand = 1
    public static final int BP_SUBJECT_STATES = 3; // Rest State, Stimulus State, Recovery State
    public static final int BP_VALUE_PAIR = 2;
    // Systolic Pressure, Diastolic Pressure

    //Flexibiliity Rotation, Bending
    public static final int FX_TEST_SITES = 2;      // Left Hand = 0 & right Hand = 1
    public static final int FX_VALUE_ITEMS= 2;      // Rotation, Bending     || Extension, Flexion
    //
    public static final int NumOfGraph = 4;
    public static final int numOfCategory = 5;  // Num Of category In History List (BloodPressure, HeartRate, Flexibility, Strength, Agility
    int Category = 2;

    protected JSONObject[] History_JSONOBJECT = new JSONObject[HISTORY_DATA];
    protected JSON[] HISTORY_JSON = new JSON[HISTORY_DATA];

    protected double[][] History_Data = new double[HISTORY_HEARTRATE_STATES][HISTORY_DATA];
    protected double[][][] History_Agility = new double[ATTRIBUTE][History_States][HISTORY_DATA];
    protected double[][][] History_Strength_Data = new double[Category][History_States][HISTORY_DATA];
    public int[][][][] intBloodPress = new int[BP_TEST_SITES][BP_VALUE_PAIR][BP_SUBJECT_STATES][HISTORY_DATA];
    int[][][] intFlex_Rotation_LateralFlexion = new int[FX_VALUE_ITEMS][FX_TEST_SITES][HISTORY_DATA];
    int[][] intFlex_Extension_Flexion = new int[FX_VALUE_ITEMS][HISTORY_DATA];

    public String[] JSONObjectDate = new String[HISTORY_DATA];


//Flexibility
    String[] strFlexValueItems = {"Rotation", "LateralFlexion"};
    String[] strRotFlexValueItems = {"Extension", "Flexion"};
    //BloodPressure

    String[] strTestSites = {"Left", "Right"}; //BP_TEST_SITES
    String[] strSubjectStates = {"Rest", "Stim", "Recv"}; //HR_SUBJECT_STATES  Rest, Stimulus, Recovery
    String[] strBpValuePair = {"Systolic", "Diastolic"}; //BP_VALUE_PAIR

    String[] strStrengthName = {"HandStrength", "LegStrength"};
    String[] strAgilityName = {"UB", "UL"};
    String[] color= {"BLUE", "GREEN", "CYAN"}; //add Color
    String Position = "position";

    GraphView[] graph_history = new GraphView[NumOfGraph];

    LinearLayout linearLayout;

    //Get Position
    private final int BloodPressure = 0;
    private final int HeartRate = 1;
    private final int Flexibility = 2;
    private final int Strength= 3;
    private final int Agility = 4;

    Button next_button ;
    Button prev_button ;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);



        Intent intent = getIntent();
        position = intent.getExtras().getInt(Position);

        position = (position + numOfCategory) % numOfCategory; //for Circular rotation

        switch(position)
        {
            case  BloodPressure:
                setContentView(R.layout.activity_history_bloodpressure);
                linearLayout = (LinearLayout) findViewById(R.id.graph_linear);
                next_button = (Button)findViewById(R.id.button_next);
                prev_button = (Button)findViewById(R.id.button_prev);
                next_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onNextClick(v);
                    }
                });
                prev_button.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        onPrevClick(v);
                    }
                });
                for(int graphNum =0 ; graphNum< NumOfGraph; graphNum++) {
                    graph_history[graphNum] = (GraphView) linearLayout.findViewWithTag(""+graphNum);
                }
                MakeBloodPressureHistory(graph_history);
                break;

            case HeartRate :
                setContentView(R.layout.activity_history_heartrate);
              next_button = (Button)findViewById(R.id.button_next);
              prev_button = (Button)findViewById(R.id.button_prev);
                next_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onNextClick(v);
                    }
                });
                prev_button.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        onPrevClick(v);
                    }
                });
                linearLayout = (LinearLayout) findViewById(R.id.graph_linear);
                graph_history[0] = (GraphView) linearLayout.findViewWithTag(""+0);
                MakeHeartRateHistory(graph_history[0]);
                break;
            case Flexibility :
                setContentView(R.layout.activity_history_flexibility);
                linearLayout = (LinearLayout) findViewById(R.id.graph_linear);
                next_button = (Button)findViewById(R.id.button_next);
                prev_button = (Button)findViewById(R.id.button_prev);
                next_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onNextClick(v);
                    }
                });
                prev_button.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        onPrevClick(v);
                    }
                });
                for(int graphNum =0 ; graphNum< NumOfGraph; graphNum++) {
                    graph_history[graphNum] = (GraphView) linearLayout.findViewWithTag(""+graphNum);
                }
                MakeFlexibilityHistory(graph_history);
                break;

            case Strength :
                setContentView(R.layout.activity_history_strength);
                linearLayout = (LinearLayout)findViewById(R.id.graph_linear);
                next_button = (Button)findViewById(R.id.button_next);
                prev_button = (Button)findViewById(R.id.button_prev);
                next_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onNextClick(v);
                    }
                });
                prev_button.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        onPrevClick(v);
                    }
                });
                for(int num = 0; num < 2; num++){
                    graph_history[num] = (GraphView)linearLayout.findViewWithTag(""+num);
                }
                MakeStrengthHistory(graph_history);
                break;

            case Agility :
                setContentView(R.layout.activity_history_agility);
                linearLayout = (LinearLayout)findViewById(R.id.graph_linear);
                next_button = (Button)findViewById(R.id.button_next);
                prev_button = (Button)findViewById(R.id.button_prev);
                next_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onNextClick(v);
                    }
                });
                prev_button.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        onPrevClick(v);
                    }
                });
                for(int num = 0; num < 2; num++){
                    graph_history[num] = (GraphView)linearLayout.findViewWithTag(""+num);
                }
                try {
                    AgilityHistory(graph_history);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
    public void MakeHeartRateHistory(GraphView graph_history1) {
        try {
            for (int states = 0; states < HISTORY_HEARTRATE_STATES; states++) {
                for (int i = 0; i < HISTORY_DATA; i++) //
                {
                    History_JSONOBJECT[i] = new JSONObject(getIntent().getStringExtra("JSONObject" + i));
                    HISTORY_JSON[i] = new JSON(History_JSONOBJECT[i]);
                    History_Data[states][i] = HISTORY_JSON[i].getHeartRate(strSubjectStates[states]);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            for (int i = 0; i < HISTORY_DATA; i++) //
            {
                JSONObjectDate[i] = HISTORY_JSON[i].getTestDate();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

       //works only when HISTORY DATA is at least 2
        /*
            StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph_history1);
            staticLabelsFormatter.setHorizontalLabels(JSONObjectDate);
            graph_history1.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
        */


        graph_history1.setTitle("HeartRate");

        graph_history1.getLegendRenderer().setVisible(true);
        graph_history1.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        //graph_history.getGridLabelRenderer().setLabelFormatter(labelsFormatter);
        for (int i = 0; i < HISTORY_HEARTRATE_STATES; i++)
            graph_history1.addSeries(addLineSeriesData(History_Data[i], color[i], "HeartRate" + strSubjectStates[i]));
        graph_history1.getViewport().setMinY(0);
        graph_history1.getViewport().setYAxisBoundsManual(true);
    } // Heart Rate History (i most recent Data --> Change HISTORY_DATA -->i)
    public void MakeBloodPressureHistory(GraphView[] graph_history) // Need 4 graph 1. Left Systolic;; 2. Left Diastolic;; 3. Right Systolic;; 4. Right Diastolic;;
    {
        try {
            for(int testSites=0; testSites<BP_TEST_SITES; testSites++) {
                for (int valuePair = 0; valuePair < BP_VALUE_PAIR; valuePair++)
                    for (int subjectStates = 0; subjectStates < BP_SUBJECT_STATES; subjectStates++)
                        for (int dataIndex = 0; dataIndex < HISTORY_DATA; dataIndex++) //
                        {
                            History_JSONOBJECT[dataIndex] = new JSONObject(getIntent().getStringExtra("JSONObject" + dataIndex));
                            HISTORY_JSON[dataIndex] = new JSON(History_JSONOBJECT[dataIndex]);
                            intBloodPress[testSites][valuePair][subjectStates][dataIndex] = HISTORY_JSON[dataIndex].getBloodPressure(strTestSites[testSites], strSubjectStates[subjectStates], strBpValuePair[valuePair]);
                    }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            for (int i = 0; i < HISTORY_DATA; i++) //
            {
                JSONObjectDate[i] = HISTORY_JSON[i].getTestDate();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //STATICLABEL -- > GRAPH 의 X 축을 JSONOBJECTDATE를 이용하여 설정하는 법....
        //graph_history.getGridLabelRenderer().setLabelFormatter(labelsFormatter);
        int index =0 ;
        for(int testSites=0; testSites<BP_TEST_SITES; testSites++) {

            for (int valuePair = 0; valuePair < BP_VALUE_PAIR; valuePair++) {
                for (int subjectStates = 0; subjectStates < BP_SUBJECT_STATES; subjectStates++) {
                    graph_history[index].addSeries(addLineSeriesData(intBloodPress[testSites][valuePair][subjectStates], color[subjectStates], strTestSites[testSites]+strBpValuePair[valuePair]));
                }
/*
                StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph_history[index]);
                staticLabelsFormatter.setHorizontalLabels(JSONObjectDate);
                graph_history[index].getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
*/
                graph_history[index].setTitle(strTestSites[testSites]+strBpValuePair[valuePair]);
                graph_history[index].getLegendRenderer().setVisible(true);
                graph_history[index].getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
                graph_history[index].getViewport().setMinY(0);
                graph_history[index].getViewport().setYAxisBoundsManual(true);
                index ++;
            }
        }
    } // BloodPressure History (i most recent Data --> Change HISTORY_DATA -->i)
    public void MakeFlexibilityHistory(GraphView[] graph_history)
    {
        //Rotation, Bending Together in array
        int index =0 ; // Graph Index 0~2

        for (int valueItem = 0; valueItem < FX_VALUE_ITEMS; valueItem++) {
            for (int testSites = 0; testSites < FX_TEST_SITES; testSites++) {
                for (int dataIndex = 0; dataIndex < HISTORY_DATA; dataIndex++) //
                {
                    try {
                        History_JSONOBJECT[dataIndex] = new JSONObject(getIntent().getStringExtra("JSONObject" + dataIndex));
                        HISTORY_JSON[dataIndex] = new JSON(History_JSONOBJECT[dataIndex]);
                        intFlex_Rotation_LateralFlexion[valueItem][testSites][dataIndex] = HISTORY_JSON[dataIndex].getFlexLR(strFlexValueItems[valueItem], strTestSites[testSites]);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }//try, catch
                }
            }
        }
        //for
        try {
            for (int i = 0; i < HISTORY_DATA; i++) //
            {
                JSONObjectDate[i] = HISTORY_JSON[i].getTestDate();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //STATICLABEL -- > GRAPH 의 X 축을 JSONOBJECTDATE를 이용하여 설정하는 법....
        //graph_history.getGridLabelRenderer().setLabelFormatter(labelsFormatter);
            for (int valuePair = 0; valuePair < FX_VALUE_ITEMS; valuePair++) {
                for(int testSites=0; testSites<FX_TEST_SITES; testSites++) {
                graph_history[index].addSeries(addLineSeriesData(intFlex_Rotation_LateralFlexion[valuePair][testSites], color[testSites], strFlexValueItems[valuePair]+strTestSites[testSites] ));
            }
/*
                StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph_history[index]);
                staticLabelsFormatter.setHorizontalLabels(JSONObjectDate);
                graph_history[index].getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
*/
                graph_history[index].setTitle(strFlexValueItems[valuePair]);
                graph_history[index].getLegendRenderer().setVisible(true);
                graph_history[index].getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
                graph_history[index].getViewport().setMinY(0);
                graph_history[index].getViewport().setYAxisBoundsManual(true);

                index ++;
            }

            //Extension, Flexion

        for (int valueItem = 0; valueItem < FX_VALUE_ITEMS; valueItem++) {
            for (int dataIndex = 0; dataIndex < HISTORY_DATA; dataIndex++) //
            {
                try {
                    History_JSONOBJECT[dataIndex] = new JSONObject(getIntent().getStringExtra("JSONObject" + dataIndex));
                    HISTORY_JSON[dataIndex] = new JSON(History_JSONOBJECT[dataIndex]);
                    intFlex_Extension_Flexion[valueItem][dataIndex] = HISTORY_JSON[dataIndex].getFlexLR(strRotFlexValueItems[valueItem]);
                } catch (JSONException e) {
                    e.printStackTrace();
                }//try, catch
            }
        }

        for(int valueItem=0; valueItem<FX_VALUE_ITEMS; valueItem++) {
            graph_history[index].addSeries(addLineSeriesData(intFlex_Extension_Flexion[valueItem], color[valueItem], strRotFlexValueItems[valueItem]));
        }
/*
        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph_history[index]);
        staticLabelsFormatter.setHorizontalLabels(JSONObjectDate);
        graph_history[index].getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
*/
        graph_history[index].setTitle(strRotFlexValueItems[0]+"," + strRotFlexValueItems[1]);
        graph_history[index].getLegendRenderer().setVisible(true);
        graph_history[index].getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        graph_history[index].getViewport().setMinY(0);
        graph_history[index].getViewport().setYAxisBoundsManual(true);
        index ++;
        }
    public void MakeStrengthHistory(GraphView[] graph_history){
        try{
            for(int j = 0; j < Category; j++) {
                for (int states = 0; states < History_States; states++) {
                    for (int i = 0; i < HISTORY_DATA; i++) {
                        History_JSONOBJECT[i] = new JSONObject(getIntent().getStringExtra("JSONObject" + i));
                        HISTORY_JSON[i] = new JSON(History_JSONOBJECT[i]);
                        History_Strength_Data[j][states][i] = HISTORY_JSON[i].getStrength(strTestSites[states], strStrengthName[j]);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            for (int i = 0; i < HISTORY_DATA; i++) //
            {
                JSONObjectDate[i] = HISTORY_JSON[i].getTestDate();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //STATICLABEL -- > GRAPH 의 X 축을 JSONOBJECTDATE를 이용하여 설정하는 법....
        //graph_history.getGridLabelRenderer().setLabelFormatter(labelsFormatter);
        int index =0 ;
        for (int name = 0; name < strStrengthName.length; name++){
            for(int testSites=0; testSites<History_States; testSites++) {
                graph_history[index].addSeries(addLineSeriesData(History_Strength_Data[name][testSites], color[testSites], strStrengthName[name]+strTestSites[testSites]));
            }
            /*
            StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph_history[index]);
            staticLabelsFormatter.setHorizontalLabels(JSONObjectDate);
            graph_history[index].getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
            */
            graph_history[index].setTitle(strStrengthName[name]);
            graph_history[index].getLegendRenderer().setVisible(true);
            graph_history[index].getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
            graph_history[index].getViewport().setMinY(0);
            graph_history[index].getViewport().setYAxisBoundsManual(true);
            index ++;
        }
    }
    public void AgilityHistory(GraphView[] graph_history) throws JSONException {
        try{
            for(int j = 0; j < ATTRIBUTE; j++) {
                for (int states = 0; states < History_States; states++) {
                    for (int i = 0; i < HISTORY_DATA; i++) {
                        History_JSONOBJECT[i] = new JSONObject(getIntent().getStringExtra("JSONObject" + i));
                        HISTORY_JSON[i] = new JSON(History_JSONOBJECT[i]);
                        History_Agility[j][states][i] = HISTORY_JSON[i].getAgility(strTestSites[states], strAgilityName[j]);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            for (int i = 0; i < HISTORY_DATA; i++) //
            {
                JSONObjectDate[i] = HISTORY_JSON[i].getTestDate();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        int index =0 ;
        for (int name = 0; name < strAgilityName.length; name++){
            for(int testSites=0; testSites<History_States; testSites++) {
                graph_history[index].addSeries(addLineSeriesData(History_Agility[name][testSites], color[testSites], strAgilityName[name]+strTestSites[testSites]));
            }
            /*
            StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph_history[index]);
            staticLabelsFormatter.setHorizontalLabels(JSONObjectDate);
            graph_history[index].getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
            */
            graph_history[index].setTitle(strAgilityName[name]);
            graph_history[index].getLegendRenderer().setVisible(true);
            graph_history[index].getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
            graph_history[index].getViewport().setMinY(0);
            graph_history[index].getViewport().setYAxisBoundsManual(true);
            index ++;
        }
    }
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
                Intent intent = new Intent(History.this, Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void onNextClick(View view) {
            Intent intent = new Intent(getApplicationContext(), History.class);
            intent.putExtra(Position, position + 1);
        for(int i =0; i < HISTORY_DATA; i++) // change length to HISTORY LENGTH  WHEN THERE ARE MORE THAN 3 DATAS
        {
            intent.putExtra("JSONObject"+i, History_JSONOBJECT[i].toString());
        }
            startActivity(intent);
            finish();
        }
    public void onPrevClick(View view) {
            Intent intent = new Intent(getApplicationContext(), History.class);
            intent.putExtra(Position, position- 1);
        for(int i =0; i < HISTORY_DATA; i++) // change length to HISTORY LENGTH  WHEN THERE ARE MORE THAN 3 DATAS
        {
            intent.putExtra("JSONObject"+i, History_JSONOBJECT[i].toString());
        }
            startActivity(intent);
            finish();
        }
}



