package com.example.sipenmaku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText etNIK, etNama, etTelp, etAlamat, etPassword;
    Button btnRegister;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etNIK = findViewById(R.id.etNIK);
        etNama = findViewById(R.id.etNama);
        etTelp = findViewById(R.id.etTelp);
        etAlamat = findViewById(R.id.etAlamat);
        etPassword = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);
        DB = new DBHelper(this);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nik = etNIK.getText().toString();
                String nama = etNama.getText().toString();
                String telp = etTelp.getText().toString();
                String alamat = etAlamat.getText().toString();
                String password = etPassword.getText().toString();

                if(TextUtils.isEmpty(nik) || TextUtils.isEmpty(nama) || TextUtils.isEmpty(telp) || TextUtils.isEmpty(alamat) || TextUtils.isEmpty(password)){
                    Toast.makeText(RegisterActivity.this, "Pastikan Semua Kolom Terisi", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkNIK = DB.checkNIK(nik);
                    String status = "CHECK-OUT";
                    if(checkNIK==false){
                        Boolean insertStatus = DB.insertStatus(nik, status);
                        if(insertStatus==true){
                            DB.insertData(nik, nama, telp, alamat, password);
                            Toast.makeText(RegisterActivity.this, "Berhasil Daftar", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        } else{
                            Toast.makeText(RegisterActivity.this, "Gagal Daftar", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "NIK Telah Didaftarkan", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}