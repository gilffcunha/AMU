package com.example.lightmonitor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {
    EditText edreguser, edregpass, edregmail;
    Button btnreg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edreguser = (EditText) findViewById(R.id.edreguser);
        edregpass = (EditText) findViewById(R.id.edregpass);
        edregmail = (EditText) findViewById(R.id.edregmail);
        btnreg = (Button) findViewById(R.id.btnreg);

        onReg();
    }

    public void onReg(){
        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edreguser.getText().toString().equals("admin")||edregpass.getText().toString().equals("123")||edregmail.getText().toString().equals("admin@mail.com")){
                    Intent loginIntent = new Intent(RegisterActivity.this, Menu.class);
                    RegisterActivity.this.startActivity(loginIntent);
                }
            }
        });

    }
}
