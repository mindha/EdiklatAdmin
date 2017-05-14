package com.example.mindha.aplikasiediklatpegawai.mDetails.RequestMySQL;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mindha.aplikasiediklatpegawai.mDetails.Object.Diklat;
import com.example.mindha.aplikasiediklatpegawai.mDetails.Adapter.RequestAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

//import static com.example.asus.lv.R.id.tipe;

/**
 * Created by ASUS on 3/30/2017.
 */

public class DataParser extends AsyncTask<Void,Void,Integer> {
    Context c;
    ListView lv;
    String jsonData;

    ProgressDialog pd;
    ArrayList<Diklat> diklat = new ArrayList<>();


    public DataParser(Context c, ListView lv, String jsonData) {
        this.c = c;
        this.lv = lv;
        this.jsonData = jsonData;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd= new ProgressDialog(c);
        pd.setTitle("Memuat List Peserta");
        pd.setMessage("Sedang diproses. Mohon tunggu...");
        pd.show();
    }

    @Override
    protected Integer doInBackground(Void... params)
    {
        return this.parseData();
    }

    @Override
    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);

        pd.dismiss();
        if (result==0){
            Toast.makeText(c, "Tidak ada data di DIKLAT ini", Toast.LENGTH_SHORT).show();
        }else {

            RequestAdapter adapter = new RequestAdapter (c,diklat);
            lv.setAdapter(adapter);
        }
    }

    private int parseData(){
        try {
            JSONArray ja = new JSONArray(jsonData);
            JSONObject jo=null;

            diklat.clear();
            Diklat s=null;

            for (int i=0;i<ja.length();i++){

                jo=ja.getJSONObject(i);

                String nama = jo.getString("nama_lengkap");

                s=new Diklat();
                s.setNamaLengkap(nama);


                diklat.add(s);

            }
            return 1;

        }catch (JSONException e){
            e.printStackTrace();

        }return 0;
    }
}
