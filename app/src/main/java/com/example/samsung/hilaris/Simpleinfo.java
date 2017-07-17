package com.example.samsung.hilaris;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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

public class Simpleinfo extends AppCompatActivity {
    TextView set_name;
    TextView set_BirthDate;
    String get_name;
    String get_mb_id;
    ListView listView;
    ListView recentlistView;
    ListViewAdapter recentadapter;
    ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_simpleinfo);

        Intent intent = getIntent();
        get_name = intent.getExtras().getString("name");
        get_mb_id = intent.getExtras().getString("mb_id");


        set_name = (TextView) findViewById(R.id.name);
        set_BirthDate = (TextView) findViewById(R.id.BirthDate);

        set_name.setText(get_name);
        recentadapter = new ListViewAdapter();
        adapter = new ListViewAdapter();
// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://221.153.186.186/cooperadvisormobilews/WSCooperAdvisor.svc/GetMedifitTestByUserJSON/" + get_mb_id;


// Request a string response from the provided URL.
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                // Do something with response

                // Process the JSON
                try {

                    JSONObject abc = response.getJSONObject(0);
                    JSON Test = new JSON(abc);
                    set_BirthDate.setText(Test.Birthdate());
                    recentlistView = (ListView) findViewById(R.id.recent_simple_list);
                    recentlistView.setAdapter(recentadapter);
                    recentadapter.addItem(Test);

                    recentlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                            ListViewItem GUID = (ListViewItem) recentlistView.getItemAtPosition(position);

                            Intent intent = new Intent(getApplicationContext(), Detailinfo.class);

                            intent.putExtra("mb_id", get_mb_id);
                            intent.putExtra("GUID", GUID.getinfo6());
                            intent.putExtra("name", get_name);
                            startActivity(intent);
                        }
                    });

                    listView = (ListView) findViewById(R.id.simple_list);
                    listView.setAdapter(adapter);
                    // Loop through the array elements
                    for (int i = 1; i < response.length(); i++) {
                        // Get current json object
                        JSONObject Tests = response.getJSONObject(i);
                        JSON object = new JSON(Tests);
                        adapter.addItem(object);

                    }
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                            ListViewItem GUID = (ListViewItem) listView.getItemAtPosition(position);

                            Intent intent = new Intent(getApplicationContext(), Detailinfo.class);

                            intent.putExtra("mb_id", get_mb_id);
                            intent.putExtra("GUID", GUID.getinfo6());
                            intent.putExtra("name", get_name);

                            startActivity(intent);
                        }
                    });
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
                Intent intent = new Intent(Simpleinfo.this, Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}