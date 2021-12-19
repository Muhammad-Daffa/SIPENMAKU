package com.example.sipenmaku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static android.content.ContentValues.TAG;

public class HomeActivity extends AppCompatActivity {

    Button btnInputNopol, btnEditProfile, btnRiwayat, btnLogout;
    TextView tvCheckInOut;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnRiwayat = findViewById(R.id.btnRiwayat);
        btnEditProfile = findViewById(R.id.btnEditProfil);
        btnLogout = findViewById(R.id.btnLogout);
        btnInputNopol = findViewById(R.id.btnInputNopol);
        DB = new DBHelper(this);
        tvCheckInOut = findViewById(R.id.tvCheckInOut);

        String nik = getIntent().getStringExtra("keynik");

        DB.readStatus(nik);
        SQLiteDatabase db = DB.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from status_pengguna where nik=?", new String[] {nik});
        while (cursor.moveToNext()){
            String status = cursor.getString(1);
            tvCheckInOut.setText(status);
        }
        cursor.close();

        if (tvCheckInOut.getText().equals("CHECK-OUT")){
            btnInputNopol.setText("CHECK-IN");
        } else {
            btnInputNopol.setText("CHECK-OUT");
        }

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat CalTanggal = new SimpleDateFormat("dd-MMM-yyyy");
        SimpleDateFormat CalJam = new SimpleDateFormat("H:mm");
        String tanggal = CalTanggal.format(calendar.getTime());
        String jam = CalJam.format(calendar.getTime());


        btnRiwayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, RiwayatActivity.class);
                intent.putExtra("keynik", nik);
                startActivity(intent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backLogout();
            }
        });

        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, EditProfileActivity.class);
                intent.putExtra("keynik", nik);
                startActivity(intent);
            }
        });

        btnInputNopol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnInputNopol.getText().equals("CHECK-IN")){
                    Intent intent = new Intent(HomeActivity.this, InputNopolActivity.class);
                    intent.putExtra("keynik", nik);
                    startActivity(intent);
                } else {
                    String status = "CHECK-OUT";
                    DB.updateStatus(new ModalStatus(nik, status));
                    Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                    intent.putExtra("keynik", nik);
                    startActivity(intent);
                    Toast.makeText(HomeActivity.this, "Berhasil CHECK-OUT", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void backLogout(){
        Intent intent2 = new Intent(HomeActivity.this, MainActivity.class);
        startActivity(intent2);
    }
}