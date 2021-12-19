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

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class InputNopolActivity extends AppCompatActivity {

    EditText etNopol;
    Button btnInputCheckIn;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_nopol);

        etNopol = findViewById(R.id.etNopol);
        btnInputCheckIn = findViewById(R.id.btnInputCheckIn);
        DB = new DBHelper(this);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat CalTanggal = new SimpleDateFormat("dd-MMM-yyyy");
        String tanggal = CalTanggal.format(calendar.getTime());

        String nik = getIntent().getStringExtra("keynik");

        btnInputCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nopol = etNopol.getText().toString();
                if(TextUtils.isEmpty(nopol)){
                    Toast.makeText(InputNopolActivity.this, "Pastikan Kolom Terisi", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean insertCheckIn = DB.insertCheckIn(nik, nopol, tanggal);
                    if(insertCheckIn==true){
                        String status = "CHECK-IN";
                        DB.updateStatus(new ModalStatus(nik, status));
                        Toast.makeText(InputNopolActivity.this, "Berhasil Check-In", Toast.LENGTH_SHORT).show();
                        finish();
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        intent.putExtra("keynik", nik);
                        startActivity(intent);
                    } else{
                        Toast.makeText(InputNopolActivity.this, "Gagal Check-In", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}