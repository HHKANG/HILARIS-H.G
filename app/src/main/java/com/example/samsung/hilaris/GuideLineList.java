package com.example.samsung.hilaris;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
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

import java.util.ArrayList;
import java.util.List;

/**
 * GuideLineList class creates Presciption_Guideline object.
 * This object splits Date, Guidelines, Routines.
 * GuideLineList class uses three level expandable listview.
 * 1 level is Date. 2 level is GuideLines 3 level is routines.
 */


public class GuideLineList extends AppCompatActivity {

    List<NLevelItem> list;
    ListView listView;
    NLevelAdapter adapter;



    String responseString;

    private RequestQueue queue;
    private String url;
    public  Prescription_Guideline[] prescription_guidelines;
    int responseLength;
    int myindex = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guideline_list);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        listView = (ListView) findViewById(R.id.listView1);
        list = new ArrayList<NLevelItem>();
        final LayoutInflater inflater = LayoutInflater.from(this);
        Intent intent = getIntent();
        String uri = intent.getStringExtra("uri");
        url ="http://221.153.186.186/cooperadvisormobilews/WSCooperAdvisor.svc/GetPrescription/"+uri;
        //url ="http://221.153.186.186/cooperadvisormobilews/WSCooperAdvisor.svc/GetPrescription/MF000004_00012054_20170627131529";
        queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) { // Date 가 하나 일때 오류발생?
                responseString = response.toString();
                responseLength = response.length();

                for(int i = 0 ; i < responseLength; i++)
                {
                    try {
                        prescription_guidelines = new Prescription_Guideline[responseLength];
                        prescription_guidelines[i] = new Prescription_Guideline(response.getJSONObject(i));

                        try
                        {
                            final NLevelItem grandParent = new NLevelItem(new SomeObject(prescription_guidelines[i].date, i),null, new NLevelView() {
                                @Override
                                public View getView(NLevelItem item) {
                                    View view = inflater.inflate(R.layout.list_item, null);
                                    TextView tv = (TextView) view.findViewById(R.id.textView01);
                                    String name = (String) ((SomeObject) item.getWrappedObject()).getName();
                                    tv.setText(name);
                                    return view;
                                }
                            });
                            list.add(grandParent);
                            for (int j = 0; j < prescription_guidelines[i].guideline.numOfguidelines; j++) {
                                NLevelItem parent = new NLevelItem(new SomeObject(prescription_guidelines[i].guideline.Objects[j].title),grandParent, new NLevelView() {
                                    @Override
                                    public View getView(NLevelItem item) {
                                        View view = inflater.inflate(R.layout.list_item, null);
                                        TextView tv = (TextView) view.findViewById(R.id.textView01);

                                        tv.setPadding(50,0,0,0);


                                        String name = (String) ((SomeObject) item.getWrappedObject()).getName();

                                        tv.setText(name);

                                        return view;
                                    }
                                });
                                list.add(parent);
                                for( int k = 0; k < prescription_guidelines[i].guideline.Objects[j].numOfroutines; k++) {
                                    NLevelItem child = new NLevelItem(new SomeObject(prescription_guidelines[i].guideline.Objects[j].routine[k]),parent, new NLevelView() {

                                        @Override
                                        public View getView(NLevelItem item) {
                                            View view = inflater.inflate(R.layout.list_item, null);
                                            TextView tv = (TextView) view.findViewById(R.id.textView01);
                                            tv.setPadding(150,0,0,0);
                                            String name = (String) ((SomeObject) item.getWrappedObject()).getName();
                                            tv.setText(name);

                                            return view;
                                        }
                                    }, true);
                                    list.add(child);

                                }
                            }
                                NLevelItem parent_prescription = new NLevelItem(new SomeObject("Exercise-Routines", i),grandParent, new NLevelView() {
                                    @Override
                                    public View getView(NLevelItem item) {
                                        View view = inflater.inflate(R.layout.list_item, null);
                                        TextView tv = (TextView) view.findViewById(R.id.textView01);

                                        tv.setPadding(50,0,0,0);

                                        String name = (String) ((SomeObject) item.getWrappedObject()).getName();

                                        tv.setText(name);

                                        return view;
                                    }
                                });
                                list.add(parent_prescription);
                                for( int k = 0; k < prescription_guidelines[i].prescription.routine_length; k++) {
                                    NLevelItem child_prescription = new NLevelItem(new SomeObject(prescription_guidelines[i].prescription.routine[k].Title),parent_prescription, new NLevelView() {

                                        @Override
                                        public View getView(NLevelItem item) {
                                            View view = inflater.inflate(R.layout.list_item, null);
                                            TextView tv = (TextView) view.findViewById(R.id.textView01);
                                            tv.setPadding(150,0,0,0);
                                            String name = (String) ((SomeObject) item.getWrappedObject()).getName();
                                            tv.setText(name);

                                            return view;
                                        }
                                    }, true);
                                    list.add(child_prescription);

                                }



                        }catch (Exception e)
                        {
                            Toast.makeText(GuideLineList.this, "Error to get Guidelines", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        Toast.makeText(GuideLineList.this, "GuidelineListError", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
                adapter = new NLevelAdapter(list);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                        ((NLevelAdapter) listView.getAdapter()).toggle(arg2);
                        ((NLevelAdapter) listView.getAdapter()).getFilter().filter();
                        NLevelItem item = (NLevelItem) adapter.getItem(arg2);
                        String Routine =  ((SomeObject) item.getWrappedObject()).getName();
                        if (item.isRoutine()) {
                            NLevelItem item2 = (NLevelItem) item.getParent().getParent();//Date
                            Intent intent = new Intent(getApplicationContext(), Week_Day_Select.class);
                            try
                            {
                                myindex = ((SomeObject) item2.getWrappedObject()).getindex();
                                intent.putExtra("routine", Routine);
                                intent.putExtra("Index", myindex);
                                intent.putExtra("responseString", responseString);
                                startActivity(intent);
                            }
                            catch(Exception e)
                            {
                                Toast.makeText(GuideLineList.this, "Failed to pass Prescription_guideline", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                });
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error to get Guidelines", Toast.LENGTH_LONG).show();
            }
        });
        // Add JsonArrayRequest to the RequestQueue
        queue.add(jsonArrayRequest);
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
                Intent intent = new Intent(GuideLineList.this, Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }


    class SomeObject {
        public String name;
        public int index;

        public SomeObject(String name) {
            this.name = name;
        }
        public SomeObject(String name, int index) {
            this.name = name;
            this.index = index;
        }

        public String getName() {
            return name;
        }
        public int getindex(){return index;}
    }


}
