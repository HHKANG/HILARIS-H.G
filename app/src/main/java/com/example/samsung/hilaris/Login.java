package com.example.samsung.hilaris;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {
    Button login_button;
    Button cancel_button;
    EditText PhoneNumber;
    EditText password;
    String pn;
    String pw;
    String MB_ID;
    String Name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        PhoneNumber = (EditText) findViewById(R.id.phoneNumber);
        password = (EditText) findViewById(R.id.loginpassword);

        cancel_button = (Button) findViewById(R.id.cancelbutton);
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        login_button = (Button) findViewById(R.id.loginbutton);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                pn = PhoneNumber.getText().toString();
                pw = password.getText().toString();

                SendRequest();

                if (PhoneNumber.getText().toString().length() == 0 && password.getText().toString().length() != 0) {
                    Toast.makeText(getApplicationContext(), "please write your name", Toast.LENGTH_SHORT).show();
                } else if (password.getText().toString().length() == 0 && PhoneNumber.getText().toString().length() != 0) {
                    Toast.makeText(getApplicationContext(), "please write your password", Toast.LENGTH_SHORT).show();
                } else if (password.getText().toString().length() == 0 && PhoneNumber.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "please write your name and password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void SendRequest() {

// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "http://221.153.186.186/cooperadvisormobilews/WSCooperAdvisor.svc/userlogin/" + pn + "/" + pw;
// Request a string response from the provided URL.
        JsonObjectRequest objRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    MB_ID = response.getString("MB_ID");
                    Name = response.getString("Name");
                    if(!MB_ID.equals("null") && !Name.equals("null")) {
                        Intent intent = new Intent(getApplicationContext(), Simpleinfo.class);
                        intent.putExtra("mb_id", MB_ID);
                        intent.putExtra("name", Name);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Enter Correct Information", Toast.LENGTH_LONG).show();
                    }
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
        queue.add(objRequest);
// Add the request to the RequestQueue.
    }
}