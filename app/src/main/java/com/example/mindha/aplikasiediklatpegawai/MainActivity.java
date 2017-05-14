package com.example.mindha.aplikasiediklatpegawai;

import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.mindha.aplikasiediklatpegawai.menubar.CostTraining;
import com.example.mindha.aplikasiediklatpegawai.menubar.InputUser;
import com.example.mindha.aplikasiediklatpegawai.menubar.InputTraining;
import com.example.mindha.aplikasiediklatpegawai.menubar.ListTraining;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private SharedPreferences pref;
    private TextView ynama, yemail;
    private NavigationView nav_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nav_view = (NavigationView) findViewById(R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(this);
        View header=nav_view.getHeaderView(0);

        Intent intent = getIntent();
        ynama = (TextView) header.findViewById(R.id.nama_akun);
        yemail = (TextView) header.findViewById(R.id.email_akun);
        String acc = intent.getStringExtra("account");
        yemail.setText(acc);

        //Toast.makeText(MainActivity.this, "halo = "+acc, Toast.LENGTH_LONG).show();


        ListTraining input = new ListTraining();
        FragmentTransaction fragment = getFragmentManager().beginTransaction();
        fragment.replace(R.id.fragment_container,input);
        fragment.commit();



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        pref = MainActivity.this.getPreferences(0);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(DataLoginRegister.IS_LOGGED_IN,false);
        editor.putString(DataLoginRegister.KEY_EMAIL,"");
        editor.putString(DataLoginRegister.KEY_PASSWORD,"");
        editor.apply();

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Keluar?");
        builder.setCancelable(true);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public  void onClick(DialogInterface dialog, int id){
                finish();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();


    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


          if (id == R.id.nav_list_training) {
            ListTraining input = new ListTraining();
            FragmentTransaction fragment = getFragmentManager().beginTransaction();
            fragment.replace(R.id.fragment_container,input);
            fragment.commit();
        } else if (id == R.id.nav_input_training) {
            InputTraining input = new InputTraining();
            FragmentTransaction fragment = getFragmentManager().beginTransaction();
            fragment.replace(R.id.fragment_container,input);
            fragment.commit();
        } else if (id == R.id.nav_cost_training) {
            CostTraining input = new CostTraining();
            FragmentTransaction fragment = getFragmentManager().beginTransaction();
            fragment.replace(R.id.fragment_container, input);
            fragment.commit();
        } else if (id == R.id.nav_input_user) {
            InputUser input = new InputUser();
            FragmentTransaction fragment = getFragmentManager().beginTransaction();
            fragment.replace(R.id.fragment_container, input);
            fragment.commit();

        }
        else if (id == R.id.nav_logout) {
            logout();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
