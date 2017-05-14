package com.example.mindha.aplikasiediklatpegawai.mDetails.List;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mindha.aplikasiediklatpegawai.R;
import com.example.mindha.aplikasiediklatpegawai.mDetails.RequestMySQL.Downloader;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RequestLists extends AppCompatActivity {

    RequestQueue requestQueue;
//    Intent intent = getIntent();
//    String mid = intent.getStringExtra("ID");
//    String mid = "4";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_lists);

        Intent intent = getIntent();
        final String mid = intent.getStringExtra("ID");

        String urlAddress="http://ediklat.pe.hu/listRequest.php?id="+mid+"";
//  TODO Ini min setingan POST ke API. Tulis kode nya dibawah

        requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST, urlAddress, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parameters = new HashMap<String, String>();
                parameters.put("id",mid);

                return parameters;
            }
        };
        requestQueue.add(request);

        final ListView lv = (ListView) findViewById(R.id.requestList);

        Downloader d = new Downloader (RequestLists.this,urlAddress,lv);
        d.execute();
    }
}
