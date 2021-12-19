package com.example.sipenmaku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnLogin, btnToRegister;
    EditText etNIKMsk, etPasswordMsk;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.btnLogin);
        btnToRegister = findViewById(R.id.btnToRegister);
        etNIKMsk = findViewById(R.id.etNIKMsk);
        etPasswordMsk = findViewById(R.id.etPasswordMsk);
        DB = new DBHelper(this);

        btnToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegisterActivity();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nik = etNIKMsk.getText().toString();
                String password = etPasswordMsk.getText().toString();

                if(TextUtils.isEmpty(nik) || TextUtils.isEmpty(password)){
                    Toast.makeText(MainActivity.this, "Pastikan Semua Kolom Terisi", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkNIKPassword = DB.checkNIKPassword(nik, password);
                    if(checkNIKPassword==true){
                        Toast.makeText(MainActivity.this, "Berhasil Login", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        intent.putExtra("keynik", nik);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "NIK/Password Salah", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    public void openRegisterActivity (){
        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
}