package com.example.sipenmaku;

public class ModalPerjalanan {

    private String nik, nopol, tanggal;

    public ModalPerjalanan (String nik, String nopol, String tanggal){
        this.nik = nik;
        this.nopol = nopol;
        this.tanggal = tanggal;
    }

    public ModalPerjalanan(){

    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNopol() {
        return nopol;
    }

    public void setNopol(String nopol) {
        this.nopol = nopol;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
