package com.example.kelompok.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kelompok.R;

public class Dialog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Input Data");
        setContentView(R.layout.input_data);
    }
}
