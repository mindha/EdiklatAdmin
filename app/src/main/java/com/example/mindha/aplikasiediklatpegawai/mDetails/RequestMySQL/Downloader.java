package com.example.mindha.aplikasiediklatpegawai.mDetails.RequestMySQL;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ASUS on 3/30/2017.
 */

public class Downloader extends AsyncTask<Void,Void,String> {

    Context c;
    String urlAddress;
    ListView lv;
    ProgressDialog pd;
    RequestQueue requestQueue;

    public Downloader(Context c, String urlAddress, ListView lv) {
        this.c = c;
        this.urlAddress = urlAddress;
        this.lv = lv;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd=new ProgressDialog(c);
        pd.setTitle("Memuat List Peserta");
        pd.setMessage("Sedang diproses. Mohon tunggu...");
        pd.show();
    }

    @Override
    protected String doInBackground(Void... params) {

        return this.downloadData();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        pd.dismiss();

//        if (s==null){
//            Toast.makeText(c,"Unsuccessfull,Null eeturned",Toast.LENGTH_SHORT).show();
//        }else
//        {
            DataParser parser= new DataParser(c,lv,s);
            parser.execute();

//        }

    }

    private  String downloadData(){
        HttpURLConnection con= Connector.connect(urlAddress);
        if(con==null){
            return  null;
        }
        InputStream is=null;

        try{
            is=new BufferedInputStream(con.getInputStream());
            BufferedReader br=new BufferedReader(new InputStreamReader(is));

            String line=null;
            StringBuffer response=new StringBuffer();

            if(br!=null){
                while ((line=br.readLine()) !=null)
                {
                    response.append(line+"\n");
                }
                br.close();

            }else {
                return null;
            }
            return response.toString();

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (is != null){
                try {
                    is.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
