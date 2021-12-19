package com.example.sipenmaku;

public class ModalStatus {

    private String nik, status;

    public ModalStatus (String nik, String status){
        this.nik = nik;
        this.status = status;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
