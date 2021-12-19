package com.example.sipenmaku;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RiwayatActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView lvRiwayat;
    private CustomListAdapter adapter_off;
    private DBHelper DB;
    private List<ModalPerjalanan> ListRiwayatPerjalanan = new ArrayList<ModalPerjalanan>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat);

        DB = new DBHelper(this);

        adapter_off = new CustomListAdapter(this, ListRiwayatPerjalanan);
        lvRiwayat = (ListView) findViewById(R.id.lvRiwayat);
        lvRiwayat.setAdapter(adapter_off);
        lvRiwayat.setOnItemClickListener(this);
        lvRiwayat.setClickable(true);
        ListRiwayatPerjalanan.clear();

        String nik = getIntent().getStringExtra("keynik");

        List<ModalPerjalanan> contacts = DB.readRiwayat(nik);
        for (ModalPerjalanan cn : contacts){
            ModalPerjalanan judulModel = new ModalPerjalanan();
            judulModel.setNik(cn.getNik());
            judulModel.setNopol(cn.getNopol());
            judulModel.setTanggal(cn.getTanggal());
            ListRiwayatPerjalanan.add(judulModel);

            if (ListRiwayatPerjalanan.isEmpty()){
                Toast.makeText(RiwayatActivity.this, "Tidak ada data", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}