package com.example.kelompok.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.kelompok.models.Mahasiswa;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "InfoMahasiswa";
    private static final String TABLE_NAME = "mahasiswa";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE Table " + TABLE_NAME + "(" +
                "nim INTEGER PRIMARY KEY," +
                "nama STRING," +
                "tanggal_lahir STRING," +
                "jenis_kelamin STRING," +
                "alamat STRING)";
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = ("DROP TABLE if EXISTS " + TABLE_NAME);
        db.execSQL(query);
        onCreate(db);
    }

    public void insert(Mahasiswa mahasiswa) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nim", mahasiswa.getNim());
        contentValues.put("nama", mahasiswa.getNama());
        contentValues.put("tanggal_lahir", mahasiswa.getTanggalLahir());
        contentValues.put("jenis_kelamin", mahasiswa.getJenisKelamin());
        contentValues.put("alamat", mahasiswa.getAlamat());

        db.insert(TABLE_NAME, null, contentValues);
    }

    public List<Mahasiswa> select() {
        ArrayList<Mahasiswa> lists = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {
                "nim",
                "nama",
                "tanggal_lahir",
                "jenis_kelamin",
                "alamat"
        };

        Cursor cursor = db.query(TABLE_NAME, columns, null, null, null, null, null);

        while (cursor.moveToNext()) {
            int nim = cursor.getInt(0);
            String nama = cursor.getString(1);
            String tanggalLahir = cursor.getString(2);
            String jenisKelamin = cursor.getString(3);
            String alamat = cursor.getString(4);

            Mahasiswa mahasiswa = new Mahasiswa();
            mahasiswa.setNim(nim);
            mahasiswa.setNama(nama);
            mahasiswa.setTanggalLahir(tanggalLahir);
            mahasiswa.setJenisKelamin(jenisKelamin);
            mahasiswa.setAlamat(alamat);

            lists.add(mahasiswa);
        }

        return lists;
    }

    public void update(Mahasiswa mahasiswa) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nim", mahasiswa.getNim());
        contentValues.put("nama", mahasiswa.getNama());
        contentValues.put("tanggal_lahir", mahasiswa.getTanggalLahir());
        contentValues.put("jenis_kelamin", mahasiswa.getJenisKelamin());
        contentValues.put("alamat", mahasiswa.getAlamat());

        String whereClause = "nim ='" + mahasiswa.getNim() + "'";
        db.update(TABLE_NAME, contentValues, whereClause, null);
    }

    public void delete(int nim) {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = "nim ='" + nim + "'";
        db.delete(TABLE_NAME, whereClause, null);
    }

}
