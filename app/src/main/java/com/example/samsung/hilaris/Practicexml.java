package com.example.samsung.hilaris;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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




public class Practicexml extends AppCompatActivity {

    RequestQueue queue;
    String url;
    Guidelines Guidelines;


    int numOfObjects;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practicexml);


        queue = Volley.newRequestQueue(this);
        url ="http://221.153.186.186/cooperadvisormobilews/WSCooperAdvisor.svc/GetPrescription/MF000004_00012054_20170627131529";
        setGuidelines(0);
    }
    public void setGuidelines(final int index)
    {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                // Do something with response
                try {
                    JSON Guideline = new JSON(response.getJSONObject(index)); // Parse it into JsonObject --> GuideLine
                    JSON xmlToJson = new JSON(XML.toJSONObject(Guideline.get_Guideline())); // make XML type to Json Type
                    JSON guidelines_JSON = new JSON(xmlToJson.get_guidelines()); // Make it into JsonObject
                    JSONArray GuidelineArray = guidelines_JSON.get_guidelineConvert();
                    Guidelines = new Guidelines(GuidelineArray);
                } catch (JSONException e) {
                    Toast.makeText(Practicexml.this, "Error", Toast.LENGTH_SHORT).show();
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


}
