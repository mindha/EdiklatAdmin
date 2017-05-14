package com.example.mindha.aplikasiediklatpegawai.menubar;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.mindha.aplikasiediklatpegawai.MainActivity;
import com.example.mindha.aplikasiediklatpegawai.R;
import com.example.mindha.aplikasiediklatpegawai.mMySQL.Downloader;


/**
 * Created by Mindha on 30/04/2017.
 */

public class ListTraining extends Fragment implements View.OnClickListener {

    String urlAddress="http://ediklat.pe.hu/listview.php";

    View myView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_list_training,container,false);
        listTraining(myView);
        return myView;
    }

    private void listTraining(View view) {
        final ListView lv = (ListView)view.findViewById(R.id.lv);

        Downloader d = new Downloader(getActivity(),urlAddress,lv);
        d.execute();

        FloatingActionButton fab = (FloatingActionButton)view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                Downloader d = new Downloader (getActivity(),urlAddress,lv);
                d.execute();
            }
        });


    }

    @Override
    public void onClick(View v) {

    }
}




