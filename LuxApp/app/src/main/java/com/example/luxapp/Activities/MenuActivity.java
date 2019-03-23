package com.example.luxapp.Activities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.luxapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MenuActivity extends AppCompatActivity {

    int userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);
        userID = (Integer) getIntent().getExtras().get("userID");
    }

    @OnClick(R.id.proto_street)
    public void street_protocol(){
        try {
            Intent intent = new Intent(MenuActivity.this, RuaActivity.class);
            intent.putExtra("userID", userID);
            startActivity(intent,
                    ActivityOptions.makeSceneTransitionAnimation(MenuActivity.this).toBundle());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.proto_crossroad)
    public void crossroad_protocol(){
        try {
            Intent intent = new Intent(MenuActivity.this, PassadeiraActivity.class);
            intent.putExtra("userID", userID);
            startActivity(intent,
                    ActivityOptions.makeSceneTransitionAnimation(MenuActivity.this).toBundle());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.proto_park)
    public void park_protocol(){
        try {
            Intent intent = new Intent(MenuActivity.this, ParqueActivity.class);
            intent.putExtra("userID", userID);
            startActivity(intent,
                    ActivityOptions.makeSceneTransitionAnimation(MenuActivity.this).toBundle());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.proto_bus)
    public void bus_protocol(){
        try {
            Intent intent = new Intent(MenuActivity.this, ParagemActivity.class);
            intent.putExtra("userID", userID);
            startActivity(intent,
                    ActivityOptions.makeSceneTransitionAnimation(MenuActivity.this).toBundle());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


}
