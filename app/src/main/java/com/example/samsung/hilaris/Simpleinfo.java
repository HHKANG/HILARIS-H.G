package com.example.samsung.hilaris;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Simpleinfo extends AppCompatActivity {
    private final int History_Data = 3; //Num of Data to compare in History
    Button history;
    Button goguideline;
    TextView set_name;
    TextView set_BirthDate;
    String get_name;
    String get_mb_id;
    ListView listView;
    ListView recentlistView;
    simpleInfo_ListViewAdapter recentadapter;
    simpleInfo_ListViewAdapter adapter;
    JSONObject[] Tests;
    String SelectedProfile = "SelectedProfile";
    String new_pw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_simpleinfo);

        Intent intent = getIntent();
        get_name = intent.getExtras().getString("name");
        get_mb_id = intent.getExtras().getString("mb_id");
        //pw = intent.getExtras().getString("password");

        set_name = (TextView) findViewById(R.id.name);
        set_BirthDate = (TextView) findViewById(R.id.BirthDate);

        set_name.setText(get_name);
        recentadapter = new simpleInfo_ListViewAdapter();
        adapter = new simpleInfo_ListViewAdapter();

// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://221.153.186.186/cooperadvisormobilews/WSCooperAdvisor.svc/GetMedifitTestByUserJSON/" + get_mb_id;

// Request a string response from the provided URL.
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                // Do something with response

                Tests = new JSONObject[response.length()];
                // Process the JSON
                try {
                    Tests[0] = response.getJSONObject(0);
                    JSON Test = new JSON(Tests[0]);
                    Intent intent = new Intent(getApplicationContext(), Detailinfo.class);

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
                            intent.putExtra(SelectedProfile, Tests[0].toString());
                    startActivity(intent);
                }
                    });

                    listView = (ListView) findViewById(R.id.simple_list);
                    listView.setAdapter(adapter);
                    // Loop through the array elements
                    for (int i = 1; i < response.length(); i++) {
                        // Get current json object
                        Tests[i] = response.getJSONObject(i);
                        JSON object = new JSON(Tests[i]);
                        adapter.addItem(object);
                    }
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                            //ListViewItem GUID = (ListViewItem) listView.getItemAtPosition(position);

                            Intent intent = new Intent(getApplicationContext(), Detailinfo.class);
                            intent.putExtra("name", get_name);
                            intent.putExtra(SelectedProfile, Tests[position].toString());
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

//Putting most recent 3 datas
        history  = (Button) findViewById(R.id.history);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Tests.length <2) {
                    Toast.makeText(Simpleinfo.this, "Exception Occured : Results must exist at least 2", Toast.LENGTH_SHORT).show();
                } // When labeling the x axis in the graph, Data needs to be at least 2

                else {
                    Intent intent = new Intent(getApplicationContext(), History_list.class);

                    for (int i = 0; i < History_Data; i++) // change length to HISTORY LENGTH  WHEN THERE ARE MORE THAN 3 DATAS
                    {
                        intent.putExtra("JSONObject" + i, Tests[i].toString());
                    }
                    startActivity(intent);
                }
                }
        });

        goguideline  = (Button) findViewById(R.id.go_guidlines);
        goguideline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), GuideLineList.class);
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
                Intent intent = new Intent(Simpleinfo.this, Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                return true;

            case R.id.change_password:
                Dialog();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void Dialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.change_password, null);
        dialog.setView(view);
        dialog.setTitle("Change Password");

        dialog.setPositiveButton("change", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                final EditText change_pass = (EditText)((AlertDialog)dialogInterface).findViewById(R.id.change_pass);
                final EditText check_pass = (EditText)((AlertDialog)dialogInterface).findViewById(R.id.check_pass);

                if(change_pass.getText().toString().equals(check_pass.getText().toString())){
                    new_pw = change_pass.getText().toString();

                    String url = "http://221.153.186.186/cooperadvisormobilews/WSCooperAdvisor.svc/changeuserpwd/" + get_mb_id + "/" + new_pw;
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Intent intent = new Intent(Simpleinfo.this, Login.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                        }
                    }, new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();
                        }
                    });

                    RequestQueue postrequestQueue = Volley.newRequestQueue(Simpleinfo.this);
                    postrequestQueue.add(stringRequest);
                }
                else
                    Toast.makeText(getApplicationContext(),"Password not match", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.setNegativeButton("cancel", null);
        dialog.show();
    }
}