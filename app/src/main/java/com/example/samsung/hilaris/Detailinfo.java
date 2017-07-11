package com.example.samsung.hilaris;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Detailinfo extends AppCompatActivity {
    Button exercise;
    TextView set_name;
    TextView set_Birthdate;
    String get_mb_id;
    String get_GUID;
    String get_name;
    ListView listView;
    DetailInfoListViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_detailinfo);
        set_name = (TextView)findViewById(R.id.name);
        set_Birthdate = (TextView)findViewById(R.id.BirthDate);

        Intent intent = getIntent();
        get_mb_id = intent.getExtras().getString("mb_id");
        get_GUID = intent.getExtras().getString("GUID");
        get_name = intent.getExtras().getString("name");

        exercise = (Button)findViewById(R.id.exercise_list);
        set_name.setText(get_name);

        adapter = new DetailInfoListViewAdapter();

        String url ="http://221.153.186.186/cooperadvisormobilews/WSCooperAdvisor.svc/GetMedifitTestByUser/"+get_mb_id+"/"+get_GUID;

        RequestQueue queue = Volley.newRequestQueue(this);

        // Request a string response from the provided URL.
        JsonObjectRequest objRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                //Iterator<?> keys =  response.keys();
                try {
                    listView = (ListView)findViewById(R.id.textview_information_listview);
                    listView.setAdapter(adapter);
                    JSON json = new JSON(response);
                    //모든 data 불러오기 from Database
                    //나중에 JSON CLASS 이용
                    //String objectString = keys.next().toString();
                    //adapter.addItem(objectString);
                    adapter.addItem("Blood Pressure");
                    adapter.addItem("Heart Rate");
                    adapter.addItem("Flexibility");
                    adapter.addItem("Upper Strength");
                    adapter.addItem("Lower Strength");
                    adapter.addItem("Upper Body Agility");
                    adapter.addItem("Upper Limb Agility");
                    set_Birthdate.setText(json.getBirthdate());

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                            if(position==0) {
                                Intent intent = new Intent(getApplicationContext(), Detail_BloodPressureGraph.class);
                                DetailInfoItem Item = (DetailInfoItem) listView.getItemAtPosition(position);
                                intent.putExtra("mb_id", get_mb_id);
                                intent.putExtra("GUID", get_GUID);
                                intent.putExtra("name", Item.getinfo1());
                                startActivity(intent);
                            }
                            else if(position==1) {
                                Intent intent = new Intent(getApplicationContext(), Detail_HeartRateGraph.class);
                                DetailInfoItem Item = (DetailInfoItem) listView.getItemAtPosition(position);
                                intent.putExtra("mb_id", get_mb_id);
                                intent.putExtra("GUID", get_GUID);
                                intent.putExtra("name", Item.getinfo1());
                                startActivity(intent);
                            }
                            else if(position == 2)
                            {
                                Intent intent = new Intent(getApplicationContext(), Detail_Flexibility_Graph.class);
                                DetailInfoItem Item = (DetailInfoItem) listView.getItemAtPosition(position);
                                intent.putExtra("mb_id", get_mb_id);
                                intent.putExtra("GUID", get_GUID);
                                intent.putExtra("name", Item.getinfo1());
                                startActivity(intent);
                            }
                            else{
                                //BAR GRAPH CLASS
                                Intent intent = new Intent(getApplicationContext(), Detail_bar_graph.class);
                                DetailInfoItem Item = (DetailInfoItem) listView.getItemAtPosition(position);
                                intent.putExtra("mb_id", get_mb_id);
                                intent.putExtra("GUID", get_GUID);
                                intent.putExtra("name", Item.getinfo1());
                                intent.putExtra("position", position-3);
                                startActivity(intent);
                            }
                        }
                    });
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


        exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Exerciselist.class);
                startActivity(intent);
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
                Intent intent = new Intent(Detailinfo.this, Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}