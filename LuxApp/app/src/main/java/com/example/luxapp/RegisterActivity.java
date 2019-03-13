package com.example.luxapp;


import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.OnClick;



public class RegisterActivity extends AppCompatActivity {


    // UI references.
    private EditText user;
    private AutoCompleteTextView email;
    private EditText password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        // Set up the register form.

        user = findViewById(R.id.username);

        email = findViewById(R.id.email);

        password = findViewById(R.id.password);

    }

    @OnClick(R.id.btnreg)
    public void register() {

        // REGISTER
        Toast.makeText(RegisterActivity.this, "A registar...", Toast.LENGTH_SHORT).show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.REGISTER_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(RegisterActivity.this, response, Toast.LENGTH_SHORT).show();
                if(response.contains("Registado com sucesso!"))
                    log();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError) {
                    Toast.makeText(RegisterActivity.this, "Timeout Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof NoConnectionError) {
                    Toast.makeText(RegisterActivity.this, "No Connection Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof AuthFailureError) {
                    Toast.makeText(RegisterActivity.this, "Authentication Failure Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof NetworkError) {
                    Toast.makeText(RegisterActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ServerError) {
                    Toast.makeText(RegisterActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError) {
                    Toast.makeText(RegisterActivity.this, "JSON Parse Error", Toast.LENGTH_SHORT).show();
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(Constants.KEY_NAME, user.getText().toString().trim());
                params.put(Constants.KEY_EMAIL, email.getText().toString().trim());
                params.put(Constants.KEY_PASSWORD, password.getText().toString().trim());
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("User-Agent", "LuxApp");
                return headers;
            }
        };

        MySingleton.getInstance(RegisterActivity.this).addToRequestQueue(stringRequest);
    }

    @OnClick(R.id.btnlog)
    public void log(){
        // Login Activity transition
        try {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent,
                    ActivityOptions.makeSceneTransitionAnimation(RegisterActivity.this).toBundle());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}