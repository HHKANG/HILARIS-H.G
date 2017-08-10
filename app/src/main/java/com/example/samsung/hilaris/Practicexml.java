package com.example.samsung.hilaris;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class Practicexml extends AppCompatActivity {

    JSONObject[] Tests;
    TextView txtGuidelines;
    TextView txtguideline1;
    TextView txtguideline2;
    TextView txtguideline3;
    TextView txtroutine1;
    TextView txtroutine2;
    TextView txtroutine3;
    TextView txtroutines;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml);

        final check c = new check();

         txtGuidelines = (TextView)findViewById(R.id.txtGuidelines);
        txtguideline1 = (TextView)findViewById(R.id.txtguideline1);
        txtguideline2 = (TextView)findViewById(R.id.txtguideline2);
        txtguideline3 = (TextView)findViewById(R.id.txtguideline3);
         txtroutine1 = (TextView) findViewById(R.id.txtroutine1);
        txtroutine2 = (TextView)findViewById(R.id.txtroutine2);
        txtroutine3 = (TextView)findViewById(R.id.txtroutine3);

        txtroutines = (TextView)findViewById(R.id.txtroutines);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://221.153.186.186/cooperadvisormobilews/WSCooperAdvisor.svc/GetPrescription/MF000004_00012054_20170627131529";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                // Do something with response
                int numOflength = 0;
                try {
                    JSON xmlToJson = null;
                    JSON GuidelineArray = null;
                    JSON GuidelineObject = null;
                    JSONArray RoutineArray = null;

                    //Toast.makeText(Practicexml.this, "1", Toast.LENGTH_SHORT).show();
                    JSON GuidelineParent = new JSON(response.getJSONObject(0)); // Parse it into JsonObject --> GuideLine
                    //Toast.makeText(Practicexml.this, "2", Toast.LENGTH_SHORT).show();
                    xmlToJson = new JSON(XML.toJSONObject(GuidelineParent.get_H_Guideline())); // make XML type to Json Type
                    //Toast.makeText(Practicexml.this, "3", Toast.LENGTH_SHORT).show();
                    GuidelineArray = new JSON(xmlToJson.get_M_guidelines()); // Make it into JsonObject
                    //Toast.makeText(Practicexml.this, "4", Toast.LENGTH_SHORT).show();
                    GuidelineObject = new JSON(GuidelineArray.get_L_guideline());
                    //Toast.makeText(Practicexml.this, "5", Toast.LENGTH_SHORT).show();
                    RoutineArray = GuidelineObject.getRoutineArray();
                    //Toast.makeText(Practicexml.this, "6", Toast.LENGTH_SHORT).show();

                    String Title = GuidelineObject.getTitle();
                    String description = GuidelineObject.getDescription();
                    String routine = GuidelineObject.getRoutine();


                    JSON routine0 = new JSON(RoutineArray.getJSONObject(0));
                    JSON routine1 = new JSON( RoutineArray.getJSONObject(1));
                    JSON routine2 = new JSON(RoutineArray.getJSONObject(2));


                    txtguideline1.setText(Title);
                    txtguideline2.setText(description);
                    txtguideline3.setText(routine);

                    txtroutine1.setText(routine0.getTitle());
                    txtroutine2.setText(routine1.getTitle());
                    txtroutine3.setText(routine2.getTitle());

                } catch (JSONException e) {
                    Toast.makeText(Practicexml.this, "Error", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }


                // Process the JSON

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

    class check{
        public JSONArray checking(Object obj){
            if(obj instanceof JSONArray) {
                Toast.makeText(getApplicationContext(), "Array", Toast.LENGTH_SHORT).show();

                return (JSONArray)obj;
            }

            else if(obj instanceof JSONObject) {
                Toast.makeText(getApplicationContext(), "Object", Toast.LENGTH_SHORT).show();
                JSONArray jsonArray = new JSONArray();
                jsonArray.put(obj);

                return jsonArray;
            }
            return null;
        }
    }
}