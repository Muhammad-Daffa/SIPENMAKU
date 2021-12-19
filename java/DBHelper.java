package com.example.sipenmaku;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

import static android.content.ContentValues.TAG;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "sipenmaku.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table pengguna(nik text primary key, nama text, telp text, alamat text, password text)");
        db.execSQL("create table status_pengguna(nik text primary key, status text)");
        db.execSQL("create table check_in(id integer primary key autoincrement, nik text, nopol text, tanggal text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oledVersion, int newVersion) {
        db.execSQL("drop table if exists pengguna");
        db.execSQL("drop table if exists status_pengguna");
        db.execSQL("drop table if exists check_in");
    }

    public boolean insertData(String nik, String nama, String telp, String alamat, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("nik", nik);
        values.put("nama", nama);
        values.put("telp", telp);
        values.put("alamat", alamat);
        values.put("password", password);

        long result = db.insert("pengguna", null, values);
        if(result==-1){
            return false;
        } else{
            return true;
        }
    }

    public boolean insertCheckIn(String nik, String nopol, String tanggal){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("nik", nik);
        values.put("nopol", nopol);
        values.put("tanggal", tanggal);

        long result = db.insert("check_in", null, values);
        if(result==-1){
            return false;
        } else{
            return true;
        }
    }

    public boolean insertStatus(String nik, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values2 = new ContentValues();

        values2.put("nik", nik);
        values2.put("status", status);

        long result = db.insert("status_pengguna", null, values2);
        if(result==-1){
            return false;
        } else{
            return true;
        }
    }

    public boolean checkNIK(String nik){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from pengguna where nik=?", new String[] {nik});
        if(cursor.getCount()>0){
            return true;
        } else{
            return false;
        }
    }

    public boolean checkNIKPassword(String nik, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from pengguna where nik=? and password=?", new String[] {nik, password});
        if(cursor.getCount()>0){
            return true;
        } else{
            return false;
        }
    }

    public void readStatus(String nik){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from status_pengguna where nik=?", new String[] {nik});
        while (cursor.moveToNext()){
            String status = cursor.getString(1);
            Log.e(TAG, "Statusnya: "+status);
        }
        cursor.close();
    }

    public List<ModalPerjalanan> readRiwayat(String nik){
        List<ModalPerjalanan> judulModelList = new ArrayList<>();
        String selectQuery = "select * from check_in where nik=?";
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[] {nik});

        if (cursor.moveToFirst()){
            do {
                ModalPerjalanan mdPerjalanan = new ModalPerjalanan();
                mdPerjalanan.setTanggal(cursor.getString(3));
                mdPerjalanan.setNopol(cursor.getString(2));
                mdPerjalanan.setNik(cursor.getString(1));
                judulModelList.add(mdPerjalanan);
            } while (cursor.moveToNext());
        }
        db.close();
        return judulModelList;
    }

    public int updateProfile(ModalRiwayat mdPengguna){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nik", mdPengguna.getNik());
        values.put("nama", mdPengguna.getNama());
        values.put("telp", mdPengguna.getTelp());
        values.put("alamat", mdPengguna.getAlamat());
        values.put("password", mdPengguna.getPassword());

        return db.update("pengguna", values, "nik = ?", new String[] {String.valueOf(mdPengguna.getNik())});
    }

    public int updateStatus(ModalStatus mdStatus){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nik", mdStatus.getNik());
        values.put("status", mdStatus.getStatus());

        return db.update("status_pengguna", values, "nik=?", new String[] {String.valueOf(mdStatus.getNik())});
    }
}
