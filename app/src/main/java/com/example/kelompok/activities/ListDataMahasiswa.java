package com.example.kelompok.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kelompok.R;

public class ListDataMahasiswa extends AppCompatActivity {
    private Button btn_input;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Input Data");
        setContentView(R.layout.list_data_mahasiswa);

        btn_input=(Button)findViewById(R.id.btnInputData);

        btn_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optionmenu, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.oplihatData){
            startActivity(new Intent(this, DetailData.class));
        } else if (item.getItemId() == R.id.opUpdateData) {
            startActivity(new Intent(this, UpdateData.class));
        } else if (item.getItemId() == R.id.opHapusData) {
            Toast.makeText(getApplicationContext(),"langsung pake SQL aja", Toast.LENGTH_LONG).show();
        }

        return true;
    }
}
