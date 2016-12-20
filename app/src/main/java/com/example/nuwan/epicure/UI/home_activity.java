package com.example.nuwan.epicure.UI;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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

import com.example.nuwan.epicure.FRAGMENT.disease_fragment;
import com.example.nuwan.epicure.FRAGMENT.location_fragment;
import com.example.nuwan.epicure.FRAGMENT.profile_fragment;
import com.example.nuwan.epicure.R;

public class home_activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private TextView tvNavHeaderEmail;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Diseases");
        setSupportActionBar(toolbar);

        disease_fragment disease_fragment = new disease_fragment();
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
        Bundle bundle = getIntent().getExtras();
            if (bundle != null ){
                tvNavHeaderEmail.setText(bundle.getString("key_email"));
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
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (id == R.id.nav_disease) {
            toolbar.setTitle("Diseases");
            disease_fragment disease_fragment = new disease_fragment();
            fragmentManager.beginTransaction().replace(R.id.frmMain,disease_fragment).commit();

        } else if (id == R.id.nav_location) {
            toolbar.setTitle("Location");
            location_fragment location = new location_fragment();
            fragTrans.replace(R.id.frmMain,location);

        } else if (id == R.id.nav_profile) {
            toolbar.setTitle("Profile");
            profile_fragment profile = new profile_fragment();
            fragTrans.replace(R.id.frmMain,profile);

        } else if (id == R.id.nav_logout){

        }
        fragTrans.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
