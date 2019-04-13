package com.example.luxapp.Activities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.example.luxapp.Classes.*;
import com.example.luxapp.R;
import android.os.Bundle;
import android.util.Log;
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterActivity extends AppCompatActivity {


    // UI references.
    private EditText email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        // Set up the register form.
        email = findViewById(R.id.email);

    }

    @OnClick(R.id.btnreg)
    public void register() {
        String REGEX = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";
        String INPUT = email.getText().toString().trim();

        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(INPUT);

        // No email
        if(INPUT.isEmpty()) {
            try {
                Intent intent = new Intent(RegisterActivity.this, MenuActivity.class);
                intent.putExtra("userID", 1);
                startActivity(intent,
                        ActivityOptions.makeSceneTransitionAnimation(RegisterActivity.this).toBundle());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            if (!m.find() && !INPUT.isEmpty())
                Toast.makeText(RegisterActivity.this, "Email Inválido", Toast.LENGTH_SHORT).show();
            else {
                // REGISTER
                StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.REG_URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(RegisterActivity.this, response, Toast.LENGTH_SHORT).show();
                        if (!response.contains("Falha no registo...")) {
                            // Insert new user
                            try {
                                Intent intent = new Intent(RegisterActivity.this, MenuActivity.class);
                                // User already exists
                                int id = 1;
                                if (!response.contains("Registado com sucesso!"))
                                    id = Integer.parseInt(response);

                                intent.putExtra("userID", id);
                                startActivity(intent,
                                        ActivityOptions.makeSceneTransitionAnimation(RegisterActivity.this).toBundle());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else
                            Toast.makeText(RegisterActivity.this, "Falha no registo...", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error instanceof TimeoutError) {
                            Toast.makeText(RegisterActivity.this, "Timeout Error", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof NoConnectionError) {
                            Toast.makeText(RegisterActivity.this, "No Connection Error", Toast.LENGTH_SHORT).show();
                            Log.v("Error", error.toString());
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
                        params.put(Constants.KEY_EMAIL, email.getText().toString().trim());
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
        }
    }
}