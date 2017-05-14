package com.example.mindha.aplikasiediklatpegawai.menubar;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
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

public class InputUser extends Fragment implements View.OnClickListener{

    private EditText snamalengkap, semail, spassword;
    private Button mSave;
    RequestQueue requestQueue;
    String insertURL = "http://ediklat.pe.hu/inputDataPegawai.php";

    View myView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_input_user,container,false);
        InputUser(myView);
        return myView;
    }

    public void InputUser(View view){


        snamalengkap = (EditText) view.findViewById(R.id.mnamalengkap);
        semail = (EditText) view.findViewById(R.id.memail);
        spassword = (EditText) view.findViewById(R.id.mpassword);
        mSave = (Button) view.findViewById(R.id.btn_input);

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
                        Toast toast = Toast.makeText(getActivity(), "User berhasil diupdate.!", Toast.LENGTH_SHORT);
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
                        parameters.put("nama_lengkap",snamalengkap.getText().toString());
                        parameters.put("email",semail.getText().toString());
                        parameters.put("password",spassword.getText().toString());

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

            case R.id.btn_input:
                InputUser(v);
                break;
        }
    }
}
