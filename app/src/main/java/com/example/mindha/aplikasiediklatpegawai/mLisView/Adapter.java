package com.example.mindha.aplikasiediklatpegawai.mLisView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mindha.aplikasiediklatpegawai.mDetails.DiklatDetail;
import com.example.mindha.aplikasiediklatpegawai.R;
import com.example.mindha.aplikasiediklatpegawai.mDataObject.Diklat;

import java.util.ArrayList;

/**
 * Created by ASUS on 3/30/2017.
 */

public class Adapter extends BaseAdapter {

    Context c;
    ArrayList<Diklat> diklats;
    LayoutInflater inflater;

    public Adapter(Context c, ArrayList<Diklat> diklats) {
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
            convertView=inflater.inflate(R.layout.model,parent,false);
        }
        TextView nama=(TextView) convertView.findViewById(R.id.nama);
        TextView place=(TextView) convertView.findViewById(R.id.place);
        TextView start_date=(TextView) convertView.findViewById(R.id.start_date);

        nama.setText(diklats.get(position).getNama());
        place.setText(diklats.get(position).getPlace());
        start_date.setText(diklats.get(position).getStart_date());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(c,diklats.get(position).getId(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(v.getContext(), DiklatDetail.class);
                i.putExtra("ID",diklats.get(position).getId());
                i.putExtra("TIPE",diklats.get(position).getTipe());
                i.putExtra("KATEGORI",diklats.get(position).getKategori());
                i.putExtra("NAMA",diklats.get(position).getNama());
                i.putExtra("START_DATE",diklats.get(position).getStart_date());
                i.putExtra("END_DATE",diklats.get(position).getEnd_date());
                i.putExtra("TIME",diklats.get(position).getTime());
                i.putExtra("PLACE",diklats.get(position).getPlace());
                i.putExtra("ORGANIZER",diklats.get(position).getOrganizer());
                i.putExtra("JUMLAH_PESERTA",diklats.get(position).getJumlah_peserta());
                i.putExtra("DESKRIPSI",diklats.get(position).getDeskripsi());
                c.startActivity(i);
            }
        });
        return convertView;
    }
}
