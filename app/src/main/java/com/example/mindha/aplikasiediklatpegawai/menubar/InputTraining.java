package com.example.mindha.aplikasiediklatpegawai.menubar;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class InputTraining extends Fragment implements View.OnClickListener {

    EditText mType,mCategory,mName,mSDate,mEDate,mTime,mPlace,mOrganizer,mParticipants,mDesc;
    Button mSubmit, mGetLoc;
    RequestQueue requestQueue;
    String insertURL = "http://ediklat.pe.hu/insertDiklat.php";
    Calendar calendar = Calendar.getInstance();
    int year, month, day;

    View myView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.activity_input_training,container,false);
        inputTraining(myView);
        locationMaps(myView);
        startDate(myView);
        endDate(myView);
        return myView;
    }

    private void inputTraining(View view){

        mType = (EditText) view.findViewById(R.id.type);
        mCategory = (EditText) view.findViewById(R.id.category);
        mName = (EditText) view.findViewById(R.id.name);
        mSDate = (EditText) view.findViewById(R.id.sdate);
        mEDate = (EditText) view.findViewById(R.id.edate);
        mTime = (EditText) view.findViewById(R.id.time);
        mPlace = (EditText) view.findViewById(R.id.place);
        mOrganizer = (EditText) view.findViewById(R.id.organizer);
        mParticipants = (EditText) view.findViewById(R.id.participant);
        mDesc = (EditText) view.findViewById(R.id.description);
        mSubmit = (Button) view.findViewById(R.id.button_submit);
        mGetLoc = (Button) view.findViewById(R.id.loc);

        requestQueue = Volley.newRequestQueue(getActivity());

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Updating data.");
        progressDialog.setMessage("Please wait. Saving data...");

        mSubmit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){

                mPlace.setEnabled(false);
                requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

                final ProgressDialog progressDialog = new ProgressDialog(getActivity());
                progressDialog.setCancelable(false);
                progressDialog.setTitle("Updating data.");
                progressDialog.setMessage("Please wait. Saving data...");

                mSubmit.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View view){

                        progressDialog.show();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                progressDialog.dismiss();
                                Toast toast = Toast.makeText(getActivity(), "Data Diklat berhasil diupdate.!", Toast.LENGTH_SHORT);
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
                                parameters.put("tipe",mType.getText().toString());
                                parameters.put("kategori",mCategory.getText().toString());
                                parameters.put("nama",mName.getText().toString());
                                parameters.put("start_date",mSDate.getText().toString());
                                parameters.put("end_date",mEDate.getText().toString());
                                parameters.put("time",mTime.getText().toString());
                                parameters.put("place",mPlace.getText().toString());
                                parameters.put("organizer",mOrganizer.getText().toString());
                                parameters.put("jumlah_peserta",mParticipants.getText().toString());
                                parameters.put("deskripsi",mDesc.getText().toString());

                                return parameters;
                            }
                        };
                        requestQueue.add(request);
                    }

                });


        }

        });
    }

    private void locationMaps(View view){
        mGetLoc = (Button) view.findViewById(R.id.loc);
        mGetLoc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent a = new Intent(getActivity(),LocationPicker.class);
                startActivity(a);
            }
        });

    }

    private void startDate(View view){
        mSDate = (EditText) view.findViewById(R.id.sdate);

        mSDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                showCalendar();
            }
        });}

    public void showCalendar(){
        new DatePickerDialog(getActivity(), date, calendar
                .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show();
    }
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };


    private void updateLabel() {

        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        mSDate.setText(sdf.format(calendar.getTime()));
    }


    private void endDate(View view){
        mEDate = (EditText) view.findViewById(R.id.edate);
        mEDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCalendarEdate();
            }
        });

    }
    public void showCalendarEdate(){
        new DatePickerDialog(getActivity(), edate, calendar
                .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show();
    }
    DatePickerDialog.OnDateSetListener edate = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabelEdate();
        }
    };

    private void updateLabelEdate() {

        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        mEDate.setText(sdf.format(calendar.getTime()));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.button_submit:
                inputTraining(v);
                break;
            case R.id.loc:
                locationMaps(v);
                break;
            case R.id.sdate:
                startDate(v);
                break;
            case R.id.edate:
                endDate(v);
                break;
        }

    }

}
