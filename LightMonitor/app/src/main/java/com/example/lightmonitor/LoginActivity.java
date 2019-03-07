package com.example.lightmonitor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.OnClick;


public class LoginActivity extends AppCompatActivity {
    EditText eduser, edpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        eduser = findViewById(R.id.eduser);
        edpass = findViewById(R.id.editT);
    }

    @OnClick(R.id.btnlogin)
    public void onLogin(){
        if(eduser.getText().equals("admin")||edpass.getText().equals("123")){
            Intent loginIntent = new Intent(LoginActivity.this, Menu.class);
            LoginActivity.this.startActivity(loginIntent);
        }
        else {Toast.makeText(getApplicationContext(), "Dados inválidos", Toast.LENGTH_SHORT).show();}
    }

    @OnClick(R.id.btnreg)
    public void onRegister(){
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);

        startActivity(intent);
    }
}
