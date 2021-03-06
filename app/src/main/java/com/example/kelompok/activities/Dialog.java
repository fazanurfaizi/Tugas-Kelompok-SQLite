package com.example.kelompok.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kelompok.R;
import com.example.kelompok.database.DatabaseHelper;
import com.example.kelompok.models.Mahasiswa;
import com.example.kelompok.views.GenderDropdown;

import java.util.ArrayList;
import java.util.Calendar;

public class Dialog extends AppCompatActivity implements View.OnClickListener {

    private EditText txtNim;
    private EditText txtNama;
    private EditText txtTanggalLahir;
    private EditText txtAlamat;
    private Button saveButton;
    private GenderDropdown genderDropdown;
    private String[] gender = {
            "Laki-Laki",
            "Perempuan"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Input Data");
        setContentView(R.layout.input_data);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.txtNim = (EditText)findViewById(R.id.txtDetailNomor);
        this.txtNama = (EditText)findViewById(R.id.txtInputNama);
        this.txtAlamat = (EditText)findViewById(R.id.txtInputAlamat);

        // Set Date Format in EditText
        this.txtTanggalLahir = (EditText)findViewById(R.id.tglInputLahir);
        txtTanggalLahir.addTextChangedListener(dateWatcher);

        this.saveButton = (Button)findViewById(R.id.btnSimpan);
        this.saveButton.setOnClickListener(this);

        // Gender Dropdown
        ArrayList<String> gender = new ArrayList<>();
        gender.add("Laki-Laki");
        gender.add("Perempuan");
        this.genderDropdown = (GenderDropdown) findViewById(R.id.txtInputJenkel);
        this.genderDropdown.setOptions(gender);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    TextWatcher dateWatcher = new TextWatcher() {

        private String current = "";
        private String ddmmyyyy = "DDMMYYYY";
        private Calendar calendar = Calendar.getInstance();

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (!s.toString().equals(current)) {
                String clean = s.toString().replaceAll("[^\\d.]|\\.", "");
                String cleanC = current.replaceAll("[^\\d.]|\\.", "");

                int cl = clean.length();
                int sel = cl;
                for (int i = 2; i <= cl && i < 6; i += 2) {
                    sel++;
                }

                if (clean.equals(cleanC)) sel--;

                if (clean.length() < 8) {
                    clean = clean + ddmmyyyy.substring(clean.length());
                } else {

                    int day  = Integer.parseInt(clean.substring(0,2));
                    int mon  = Integer.parseInt(clean.substring(2,4));
                    int year = Integer.parseInt(clean.substring(4,8));

                    mon = mon < 1 ? 1 : mon > 12 ? 12 : mon;
                    calendar.set(Calendar.MONTH, mon - 1);
                    year = (year < 1900) ? 1900:(year > 2100) ? 2100 : year;

                    day = (day > calendar.getActualMaximum(Calendar.DATE))? calendar.getActualMaximum(Calendar.DATE):day;
                    clean = String.format("%02d%02d%02d", day, mon, year);
                }

                clean = String.format("%s-%s-%s", clean.substring(0, 2),
                        clean.substring(2, 4),
                        clean.substring(4, 8));

                sel = sel < 0 ? 0 : sel;
                current = clean;
                txtTanggalLahir.setText(current);
                txtTanggalLahir.setSelection(sel < current.length() ? sel : current.length());
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    public void onClick(View v) {
        DatabaseHelper db = new DatabaseHelper(this);
        if(v.getId() == R.id.btnSimpan) {
            insertData(db);
        }
    }

    private void insertData(DatabaseHelper db) {
        Mahasiswa mahasiswa = new Mahasiswa();
        mahasiswa.setNim(Integer.parseInt(this.txtNim.getText().toString()));
        mahasiswa.setNama(this.txtNama.getText().toString());
        mahasiswa.setTanggalLahir(this.txtTanggalLahir.getText().toString());
        mahasiswa.setAlamat(this.txtAlamat.getText().toString());
        mahasiswa.setJenisKelamin(this.genderDropdown.getSelectedGender());
        db.insert(mahasiswa);
        Toast.makeText(this, "Data berhasil ditambahkan", Toast.LENGTH_SHORT)
                .show();

        Intent intent = new Intent(Dialog.this, ListDataMahasiswa.class);
        startActivity(intent);
    }
}
