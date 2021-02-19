package com.example.kelompok.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kelompok.R;

public class DetailData extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Detail Data");
        setContentView(R.layout.detail_data);
    }
}
