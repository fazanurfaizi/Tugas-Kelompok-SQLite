package com.example.kelompok.models;

public class Mahasiswa {

    /**
     * Nomor Induk Mahasiswa
     */
    private int nim;

    /**
     * Nama Mahasiswa
     */
    private String nama;

    /**
     * Tanngal Lahir
     */
    private String tanggalLahir;

    /**
     * Jenis Kelamin
     */
    private String jenisKelamin;

    /**
     * Alamat
     */
    private String alamat;

    public int getNim() {
        return nim;
    }

    public void setNim(int nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
