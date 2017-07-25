package com.example.samsung.hilaris;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class History_list extends AppCompatActivity {

    ListView listView;
    DetailInfoListViewAdapter adapter;
    private final int HISTORY_DATA =3;
    protected JSONObject[] History_JSONOBJECT = new JSONObject[HISTORY_DATA];
    JSON json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_history_list);
        Intent intent = getIntent();
        try {
        for (int i = 0; i < HISTORY_DATA; i++) //
        {
                History_JSONOBJECT[i] = new JSONObject(getIntent().getStringExtra("JSONObject" + i));
        }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        adapter = new DetailInfoListViewAdapter();
        listView = (ListView)findViewById(R.id.textview_information_listview);
        listView.setAdapter(adapter);

        adapter.addItem("Blood Pressure");
        adapter.addItem("Heart Rate");
        adapter.addItem("Flexibility");
        adapter.addItem("Strength");
        adapter.addItem("Agility");


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                    Intent intent = new Intent(getApplicationContext(), History.class);
                     for(int i =0; i < HISTORY_DATA; i++) // change length to HISTORY LENGTH  WHEN THERE ARE MORE THAN 3 DATAS
                  {
                    intent.putExtra("JSONObject"+i, History_JSONOBJECT[i].toString());
                }
                    intent.putExtra("position", position);
                    startActivity(intent);
            }
        });
    }

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
                Intent intent = new Intent(History_list.this, Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
