package com.example.mindha.aplikasiediklatpegawai.mDetails;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mindha.aplikasiediklatpegawai.R;
import com.example.mindha.aplikasiediklatpegawai.mDetails.List.RequestLists;

public class DiklatDetail extends AppCompatActivity {

    TextView mNama,mTipe,mKategori,mStartDate,mTime,mPlace,mJumlah,mDeskripsi;
    Button mRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diklat_detail);

//        ActionBar actionBar = getActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        final String id = intent.getStringExtra("ID");
        String tipe = intent.getStringExtra("TIPE");
        String kategori = intent.getStringExtra("KATEGORI");
        String nama = intent.getStringExtra("NAMA");
        String start_date = intent.getStringExtra("START_DATE");
        String end_date = intent.getStringExtra("END_DATE");
        String time = intent.getStringExtra("TIME");
        String place = intent.getStringExtra("PLACE");
        String organizer = intent.getStringExtra("ORGANIZER");
        String jumlah_peserta = intent.getStringExtra("JUMLAH_PESERTA");
        String deskripsi = intent.getStringExtra("DESKRIPSI");

        mTipe = (TextView) findViewById(R.id.tipe);
        mKategori = (TextView) findViewById(R.id.kategori);
        mNama = (TextView) findViewById(R.id.nama);
        mStartDate = (TextView) findViewById(R.id.tanggal);
        mPlace = (TextView) findViewById(R.id.location);
        mJumlah = (TextView) findViewById(R.id.jumlah);
        mDeskripsi = (TextView) findViewById(R.id.deskripsi);
        mRequest = (Button) findViewById(R.id.request);

        mTipe.setText(tipe);
        mKategori.setText(kategori);
        mNama.setText(nama);
        mStartDate.setText(start_date);
        mPlace.setText(place);
        mJumlah.setText(jumlah_peserta);
        mDeskripsi.setText(deskripsi);

        mRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(DiklatDetail.this, id, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DiklatDetail.this, RequestLists.class);
                intent.putExtra("ID",id);
                startActivity(intent);
            }
        });
    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
//        startActivityForResult(myIntent, 0);
//        return true;
//    }
//    public void MemberList(){
//        Intent intent = new Intent(DiklatDetail.this,MemberList.class);
//        startActivity(intent);
//    }
//
//    public void RequestList(){
//        Intent intent = new Intent(DiklatDetail.this, RequestList.class);
//        startActivity(intent);
//    }

}
