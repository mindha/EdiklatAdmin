package com.example.mindha.aplikasiediklatpegawai.menubar;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mindha.aplikasiediklatpegawai.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mindha on 30/04/2017.
 */

public class CostTraining extends Fragment implements View.OnClickListener{

    private EditText mBAccomodation, mBRegis, mBTiket, mBWorkshop, mOthers, mBLodging;
    private Button mSave, mCancel;
    RequestQueue requestQueue;
    String insertURL = "http://ediklat.pe.hu/insertCost.php";

    View myView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_cost_training,container,false);
        InputCost(myView);
        return myView;
    }

    public void InputCost(View view){


        mBAccomodation = (EditText) view.findViewById(R.id.BAkomodasi);
        mBRegis = (EditText) view.findViewById(R.id.BRegis);
        mBTiket = (EditText) view.findViewById(R.id.BTiket);
        mBWorkshop = (EditText) view.findViewById(R.id.BWorkshop);
        mOthers = (EditText) view.findViewById(R.id.BOthers);
        mBLodging = (EditText) view.findViewById(R.id.BLodging);
        mSave = (Button) view.findViewById(R.id.btn_save);

        requestQueue = Volley.newRequestQueue(getActivity());

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Updating data.");
        progressDialog.setMessage("Please wait. Saving data...");

        mSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                progressDialog.show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        progressDialog.dismiss();
                        Toast toast = Toast.makeText(getActivity(), "Cost Training berhasil diupdate.!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }, 3000);

                StringRequest request = new StringRequest(Request.Method.POST, insertURL, new Response.Listener<String>() {
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
                        parameters.put("accomodation_cost",mBAccomodation.getText().toString());
                        parameters.put("registration_cost",mBRegis.getText().toString());
                        parameters.put("ticket_cost",mBTiket.getText().toString());
                        parameters.put("workshop_cost",mBWorkshop.getText().toString());
                        parameters.put("other",mOthers.getText().toString());
                        parameters.put("lodging_cost",mBLodging.getText().toString());

                        return parameters;
                    }
                };
                requestQueue.add(request);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btn_save:
                InputCost(v);
                break;
        }
    }
}
