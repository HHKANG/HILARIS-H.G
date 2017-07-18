package com.example.samsung.hilaris;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONException;
import org.json.JSONObject;

public class History extends AppCompatActivity {

    private final int HISTORY_DATA = 3;
    JSONObject[] History = new JSONObject[HISTORY_DATA];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        //Getting most recent 3 datas
        for(int i = 0 ; i<1; i++) //
        {
            try {
                History[i] = new JSONObject(getIntent().getStringExtra("JSONObject"+i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }



    }
}
