package com.example.nuwan.epicure.UI;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nuwan.epicure.FRAGMENT.disease_fragment;
import com.example.nuwan.epicure.FRAGMENT.location_fragment;
import com.example.nuwan.epicure.FRAGMENT.profile_fragment;
import com.example.nuwan.epicure.R;

public class home_activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private TextView tvNavHeaderEmail;
    private TextView tvNavHeaderName;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Diseases");
        setSupportActionBar(toolbar);
        Bundle bundle = getIntent().getExtras();

        disease_fragment disease_fragment = new disease_fragment();
        disease_fragment.setEmailDiseaseFrag(bundle.getCharSequence("key_email").toString());
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frmMain,disease_fragment).commit();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);
        tvNavHeaderEmail = (TextView) header.findViewById(R.id.tv_navHeaderEmail);
        tvNavHeaderName = (TextView) header.findViewById(R.id.tv_navHeaderName);
            if (bundle != null ){
                String fname =  bundle.getCharSequence("first_name").toString();
                String lname =  bundle.getCharSequence("last_name").toString();
                tvNavHeaderName.setText(fname+" "+lname);
                tvNavHeaderEmail.setText(bundle.getCharSequence("key_email").toString());

            }
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
        getMenuInflater().inflate(R.menu.activity_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentTransaction fragTrans = getSupportFragmentManager().beginTransaction();

        if (id == R.id.nav_disease) {
            toolbar.setTitle("Diseases");
            disease_fragment disease_fragment = new disease_fragment();
            fragTrans.replace(R.id.frmMain,disease_fragment);

        } else if (id == R.id.nav_location) {
            toolbar.setTitle("Location");
            location_fragment location = new location_fragment();
            fragTrans.replace(R.id.frmMain,location);

        } else if (id == R.id.nav_profile) {
            toolbar.setTitle("Profile");
            profile_fragment profile = new profile_fragment();
            fragTrans.replace(R.id.frmMain,profile);

        } else if (id == R.id.nav_logout){
            logoutProcess();
        }
        fragTrans.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logoutProcess() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_lock_power_off)
                .setTitle("Log Out")
                .setMessage("Are you sure you want to Exit EpiCure?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();


    }
}
