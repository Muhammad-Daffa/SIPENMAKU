package com.example.sipenmaku;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<ModalPerjalanan> movieItems;

    public CustomListAdapter(Activity activity, List<ModalPerjalanan> movieItems){
        this.activity = activity;
        this.movieItems = movieItems;
    }

    @Override
    public int getCount() {
        return movieItems.size();
    }

    @Override
    public Object getItem(int location) {
        return movieItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater == null){
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView == null){
            convertView = inflater.inflate(R.layout.riwayat_cell, null);
        }

        TextView etTitle = (TextView) convertView.findViewById(R.id.etTitle);
        TextView etSubtitle = (TextView) convertView.findViewById(R.id.etSubtitle);

        ModalPerjalanan m = movieItems.get(position);

        etTitle.setText("Tanggal: " + m.getTanggal());
        etSubtitle.setText("Nomor Kendaraan: " + m.getNopol() + ", NIK: " + m.getNik());

        return convertView;
    }
}
