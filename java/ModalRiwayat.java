package com.example.sipenmaku;

public class ModalRiwayat {

    private String nik, nama, telp, alamat, password;

    public ModalRiwayat (String nik, String nama, String telp, String alamat, String password){
        this.nik = nik;
        this.nama = nama;
        this.telp = telp;
        this.alamat = alamat;
        this.password = password;
    }

    public ModalRiwayat(){

    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
