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

//import com.example.expandable3.R;

public class GuideLineList extends AppCompatActivity {

    List<NLevelItem> list;
    ListView listView;



    private RequestQueue queue;
    private String url;
    public  Prescription_Guideline[] prescription_guidelines;
    public Prescription_Guideline prescription_guidelines_selected;
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


        url ="http://221.153.186.186/cooperadvisormobilews/WSCooperAdvisor.svc/GetPrescription/MF000004_00012054_20170627131529";
        queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) { // Date 가 하나 일때 오류발생?
                responseLength = response.length();
                for(int i = 0 ; i < responseLength; i++)
                {
                    try {
                        myindex= i;
                        prescription_guidelines = new Prescription_Guideline[responseLength];
                        prescription_guidelines[i] = new Prescription_Guideline(response.getJSONObject(i));
                        try
                        {
                            final NLevelItem grandParent = new NLevelItem(new SomeObject(prescription_guidelines[i].date),null, new NLevelView() {
                                @Override
                                public View getView(NLevelItem item) {
                                    View view = inflater.inflate(R.layout.list_item, null);
                                    TextView tv = (TextView) view.findViewById(R.id.textView01);
                                    String name = (String) ((SomeObject) item.getWrappedObject()).getName();
                                    tv.setText(name);
                                    return view;
                                }
                            });
                            //Toast.makeText(GuideLineList.this, "i="+i+":"+prescription_guidelines[i].date, Toast.LENGTH_SHORT).show();
                            list.add(grandParent);
                            for (int j = 0; j < prescription_guidelines[i].guideline.numOfguidelines; j++) {
                                NLevelItem parent = new NLevelItem(new SomeObject(prescription_guidelines[i].guideline.Objects[j].title,prescription_guidelines[i].guideline.Objects[j].description),grandParent, new NLevelView() {
                                    @Override
                                    public View getView(NLevelItem item) {
                                        View view = inflater.inflate(R.layout.list_item2, null);
                                        TextView tv01 = (TextView) view.findViewById(R.id.textView01);
                                        TextView tv02 = (TextView) view.findViewById(R.id.textView02);

                                        tv01.setPadding(50,0,0,0);
                                        tv02.setPadding(100,0,0,0);

                                        String name1 = (String) ((SomeObject) item.getWrappedObject()).getName();
                                        String name2 = (String) ((SomeObject) item.getWrappedObject()).getName2();

                                        tv01.setText(name1);
                                        tv02.setText(name2);

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
                                    });
                                    list.add(child);

                                }
                            }

                            final NLevelAdapter adapter = new NLevelAdapter(list);
                            listView.setAdapter(adapter);
                            listView.setOnItemClickListener(new OnItemClickListener() {

                                @Override
                                public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                                    ((NLevelAdapter) listView.getAdapter()).toggle(arg2);
                                    ((NLevelAdapter) listView.getAdapter()).getFilter().filter();
                                    NLevelItem item = (NLevelItem) adapter.getItem(arg2);
                                    String Routine =  ((SomeObject) item.getWrappedObject()).getName();


                                    if (Routine.startsWith("R")) {
                                        NLevelItem item2 = (NLevelItem) item.getParent().getParent();
                                        String grandparent = ((SomeObject)item2.getWrappedObject()).getName();
                                         Toast.makeText(GuideLineList.this,Routine, Toast.LENGTH_SHORT).show();
                                       // Toast.makeText(GuideLineList.this, prescription_guidelines[myindex].responseString, Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), Week_Day_Select.class);
                                        intent.putExtra("routine", Routine);
                                        intent.putExtra("prescription_guideline",prescription_guidelines[myindex].responseString);
                                        startActivity(intent);
                                    }
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
        public String name2;


        public SomeObject(String name) {
            this.name = name;
        }

        public SomeObject(String name, String name2) {
            this.name = name;
            this.name2 = name2;

        }
        public String getName() {
            return name;
        }
        public String getName2() {
            return name2;
        }
    }

}
