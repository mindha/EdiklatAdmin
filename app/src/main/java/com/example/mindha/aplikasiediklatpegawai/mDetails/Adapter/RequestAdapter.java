package com.example.mindha.aplikasiediklatpegawai.mDetails.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mindha.aplikasiediklatpegawai.R;
import com.example.mindha.aplikasiediklatpegawai.mDetails.Object.Diklat;

import java.util.ArrayList;

/**
 * Created by ASUS on 3/30/2017.
 */

public class RequestAdapter extends BaseAdapter {

    Context c;
    ArrayList<Diklat> diklats;
    LayoutInflater inflater;

    public RequestAdapter(Context c, ArrayList<Diklat> diklats) {
        this.c = c;
        this.diklats = diklats;
        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }



    @Override
    public int getCount() {
        return diklats.size();
    }

    @Override
    public Object getItem(int position) {
        return diklats.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(diklats.get(position).getId());
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView=inflater.inflate(R.layout.request_model,parent,false);
        }
        TextView nama_lengkap =(TextView) convertView.findViewById(R.id.nama_lengkap);

        nama_lengkap.setText(diklats.get(position).getNamaLengkap());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(c,diklats.get(position).getNamaLengkap(), Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }
}
