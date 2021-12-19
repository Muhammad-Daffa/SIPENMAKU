package com.example.sipenmaku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditProfileActivity extends AppCompatActivity {

    EditText etEditNIK, etEditNama, etEditTelp, etEditAlamat, etEditPassword;
    Button btnBatal, btnSimpan;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        DB = new DBHelper(this);

        etEditNIK = findViewById(R.id.etEditNIK);
        etEditNama = findViewById(R.id.etEditNama);
        etEditTelp = findViewById(R.id.etEditTelp);
        etEditAlamat = findViewById(R.id.etEditAlamat);
        etEditPassword = findViewById(R.id.etEditPassword);
        btnBatal = findViewById(R.id.btnBatal);
        btnSimpan = findViewById(R.id.btnSimpan);

        String nik = getIntent().getStringExtra("keynik");
        etEditNIK.setText(nik);

        etEditNIK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(EditProfileActivity.this, "NIK Tidak Bisa Diubah", Toast.LENGTH_SHORT).show();
            }
        });

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nik = etEditNIK.getText().toString();
                String nama = etEditNama.getText().toString();
                String telp = etEditTelp.getText().toString();
                String alamat = etEditAlamat.getText().toString();
                String password = etEditPassword.getText().toString();

                if(TextUtils.isEmpty(nik) || TextUtils.isEmpty(nama) || TextUtils.isEmpty(telp) || TextUtils.isEmpty(alamat) || TextUtils.isEmpty(password)){
                    Toast.makeText(EditProfileActivity.this, "Pastikan Semua Kolom Terisi", Toast.LENGTH_SHORT).show();
                } else {
                    DB.updateProfile(new ModalRiwayat(nik, nama, telp, alamat, password));
                    Toast.makeText(EditProfileActivity.this, "Data Telah Diupdate", Toast.LENGTH_SHORT).show();
                    finish();
//                    Intent intent = new Intent(EditProfileActivity.this, HomeActivity.class);
//                    startActivity(intent);
                }
            }
        });

        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void backToMainPage(){
        Intent intent = new Intent(EditProfileActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}