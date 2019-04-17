package com.example.luxapp.Activities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.luxapp.R;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_begin)
    public void reg(){
        // Register Activity transition
        try {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent,
                    ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
