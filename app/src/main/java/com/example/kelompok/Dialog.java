package com.example.kelompok;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Dialog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Input Data");
        setContentView(R.layout.input_data);
    }
}
