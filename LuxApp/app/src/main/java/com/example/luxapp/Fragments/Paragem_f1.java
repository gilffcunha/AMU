package com.example.luxapp.Fragments;

import com.example.luxapp.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.OnClick;


public class Paragem_f1 extends Fragment{


    public Paragem_f1() {
    }

    @BindView(R.id.btn_bs_step2)
    Button btn_bs_step2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bs_step1, container, false);
    }



}
