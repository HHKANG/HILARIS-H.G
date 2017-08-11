package com.example.samsung.hilaris;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//import com.example.expandable3.R;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

public class GuideLineList extends AppCompatActivity {

    List<NLevelItem> list;
    ListView listView;

    private RequestQueue queue;
    private String url;
    public Prescription_Guideline[] prescription_guidelines;


    private String date;
    public Guidelines guideline;
    public Prescription prescription;
    private JSON response_JSON;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guideline_list);
        listView = (ListView) findViewById(R.id.listView1);
        list = new ArrayList<NLevelItem>();
        final LayoutInflater inflater = LayoutInflater.from(this);



        url ="http://221.153.186.186/cooperadvisormobilews/WSCooperAdvisor.svc/GetPrescription/MF000004_00012054_20170627131529";



        queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) { // Date 가 하나 일때 오류발생?
                int responseLength = response.length();
                for(int i = 0 ; i < responseLength; i++)
                {
                    try {
                        prescription_guidelines = new Prescription_Guideline[responseLength];
                        prescription_guidelines[i] = new Prescription_Guideline(response.getJSONObject(i));
                        Toast.makeText(GuideLineList.this, "numOfresponseIndex"+responseLength+"-"+i, Toast.LENGTH_SHORT).show();
                        try
                        {
                                NLevelItem grandParent = new NLevelItem(new SomeObject(prescription_guidelines[i].date),null, new NLevelView() {
                                    @Override
                                    public View getView(NLevelItem item) {
                                        View view = inflater.inflate(R.layout.list_item, null);
                                        TextView tv = (TextView) view.findViewById(R.id.textView);
                                        String name = (String) ((SomeObject) item.getWrappedObject()).getName();
                                        tv.setText(name);
                                        return view;
                                    }
                                });
                                Toast.makeText(GuideLineList.this, "i="+i+":"+prescription_guidelines[i].date, Toast.LENGTH_SHORT).show();
                                list.add(grandParent);
                                for (int j = 0; j < prescription_guidelines[i].guideline.numOfguidelines; j++) {
                                    NLevelItem parent = new NLevelItem(new SomeObject(prescription_guidelines[i].guideline.Objects[j].title),grandParent, new NLevelView() {
                                        @Override
                                        public View getView(NLevelItem item) {
                                            View view = inflater.inflate(R.layout.list_item, null);
                                            TextView tv = (TextView) view.findViewById(R.id.textView);
                                            tv.setPadding(50,0,0,0);
                                            String name = (String) ((SomeObject) item.getWrappedObject()).getName();
                                            tv.setText(name);
                                            return view;
                                        }
                                    });
                                    Toast.makeText(GuideLineList.this, "numOfguidelines"+prescription_guidelines[i].guideline.numOfguidelines+"-"+j, Toast.LENGTH_SHORT).show();

                                    list.add(parent);
                                    for( int k = 0; k < prescription_guidelines[i].guideline.Objects[j].numOfroutines; k++) {
                                        NLevelItem child = new NLevelItem(new SomeObject(prescription_guidelines[i].guideline.Objects[j].routine[k]),parent, new NLevelView() {

                                            @Override
                                            public View getView(NLevelItem item) {
                                                View view = inflater.inflate(R.layout.list_item, null);
                                                TextView tv = (TextView) view.findViewById(R.id.textView);
                                                tv.setPadding(100,0,0,0);
                                                String name = (String) ((SomeObject) item.getWrappedObject()).getName();
                                                tv.setText(name);
                                                return view;
                                            }
                                        });
                                        Toast.makeText(GuideLineList.this, "numOfRoutines"+prescription_guidelines[i].guideline.Objects[j].numOfroutines+"-"+k, Toast.LENGTH_SHORT).show();
                                        list.add(child);
                                    }
                                }


                            NLevelAdapter adapter = new NLevelAdapter(list);
                            listView.setAdapter(adapter);
                            listView.setOnItemClickListener(new OnItemClickListener() {

                                @Override
                                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                                        long arg3) {
                                    ((NLevelAdapter)listView.getAdapter()).toggle(arg2);
                                    ((NLevelAdapter)listView.getAdapter()).getFilter().filter();

                                }
                            });

                        }catch (Exception e)
                        {
                            Toast.makeText(GuideLineList.this, "Please", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        Toast.makeText(GuideLineList.this, "GuidelineListError", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();
            }
        });
        // Add JsonArrayRequest to the RequestQueue
        queue.add(jsonArrayRequest);
    }


    class SomeObject {
        public String name;

        public SomeObject(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }
    }

    public void setGuidelines(JSONObject response) throws JSONException {
        JSON Guideline = new JSON(response); // Parse it into JsonObject --> GuideLine
        JSON xmlToJson = new JSON(XML.toJSONObject(Guideline.get_Guideline())); // make XML type to Json Type
        JSON guidelines_JSON = new JSON(xmlToJson.get_guidelines()); // Make it into JsonObject
        JSONArray GuidelineArray = guidelines_JSON.get_guidelineConvert();
        guideline = new Guidelines(GuidelineArray);
    }
    public void setPrescriptions(JSONObject response) throws JSONException {
        JSON Prescription = new JSON(response); // Parse it into JsonObject --> GuideLine
        JSON xmlToJson = new JSON(XML.toJSONObject(Prescription.get_Prescription())); // make XML type to Json Type
        JSON prescriptions_JSON = new JSON(xmlToJson.get_prescriptions());
        JSONArray PrescriptionArray = prescriptions_JSON.get_ExerciseRoutineConvert();
        prescription = new Prescription(PrescriptionArray);
    }

}
