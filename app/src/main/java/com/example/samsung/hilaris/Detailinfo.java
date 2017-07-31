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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

public class Detailinfo extends AppCompatActivity implements Serializable{
    Button exercise;
    TextView set_name;
    TextView set_Birthdate;
    String get_name;
    ListView listView;
    DetailInfoListViewAdapter adapter;
    String SelectedProfile = "SelectedProfile";
    JSONObject profile;
    JSON json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_detailinfo);
        set_name = (TextView)findViewById(R.id.name);
        set_Birthdate = (TextView)findViewById(R.id.BirthDate);

        Intent intent = getIntent();

        get_name = intent.getExtras().getString("name");

        exercise = (Button)findViewById(R.id.exercise_list);
        set_name.setText(get_name);

        adapter = new DetailInfoListViewAdapter();

        listView = (ListView)findViewById(R.id.textview_information_listview);
        listView.setAdapter(adapter);

        try {
            profile = new JSONObject(intent.getStringExtra(SelectedProfile));
            json = new JSON(profile);
            set_Birthdate.setText(json.getBirthdate());
        } catch (JSONException e) {
            e.printStackTrace();
        }
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

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                            if(position==0) {
                                Intent intent = new Intent(getApplicationContext(), Detail_BloodPressureGraph.class);
                                ListViewItem Item = (ListViewItem) listView.getItemAtPosition(position);
                                intent.putExtra(SelectedProfile, profile.toString());
                                startActivity(intent);
                            }
                            else if(position==1) {
                                Intent intent = new Intent(getApplicationContext(), Detail_HeartRateGraph.class);
                                ListViewItem Item = (ListViewItem) listView.getItemAtPosition(position);
                                intent.putExtra(SelectedProfile, profile.toString());
                                startActivity(intent);
                            }
                            else if(position == 2)
                            {
                                Intent intent = new Intent(getApplicationContext(), Detail_Flexibility_Graph.class);
                                ListViewItem Item = (ListViewItem) listView.getItemAtPosition(position);
                                intent.putExtra(SelectedProfile, profile.toString());
                                startActivity(intent);
                            }
                            else {
                                //BAR GRAPH CLASS
                                Intent intent = new Intent(getApplicationContext(), Detail_bar_graph.class);
                                ListViewItem Item = (ListViewItem) listView.getItemAtPosition(position);
                                intent.putExtra("position", position-3);
                                intent.putExtra(SelectedProfile, profile.toString());
                                startActivity(intent);
                            }
                        }
                    });

        exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Exerciselist.class);
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
                Intent intent = new Intent(Detailinfo.this, Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}