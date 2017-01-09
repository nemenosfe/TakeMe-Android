package com.pes.takemelegends.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.pes.takemelegends.Fragment.EventsViewPagerFragment;
import com.pes.takemelegends.Fragment.MarketFragment;
import com.pes.takemelegends.Fragment.MyEventsFragment;
import com.pes.takemelegends.Fragment.ProfileFragment;
import com.pes.takemelegends.R;
import com.pes.takemelegends.Utils.SharedPreferencesManager;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Fragment feed, profile, market, myEvents;
    private SharedPreferencesManager sharedPreferences;
    private static final int RECORD_REQUEST_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.main_ambar));

        sharedPreferences = new SharedPreferencesManager(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);
        header.setBackgroundColor(getResources().getColor(R.color.white));

        checkPermissions();

        //Mostrem el feed al conectar
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (feed == null) feed = new EventsViewPagerFragment();
        transaction.replace(R.id.fragment_container,feed);
        transaction.commit();
    }

    public void checkPermissions() {

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
            } else {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        RECORD_REQUEST_CODE);
            }
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



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (id == R.id.nav_lupe) {
            if (feed == null) feed = new EventsViewPagerFragment();
            transaction.replace(R.id.fragment_container,feed);
            transaction.addToBackStack("eventsFeed");
        } else if (id == R.id.nav_profile) {
            if (profile == null) profile = new ProfileFragment();
            transaction.replace(R.id.fragment_container,profile);
            transaction.addToBackStack("profile");
        } else if (id == R.id.nav_market) {
            if (market == null) market = new MarketFragment();
            transaction.replace(R.id.fragment_container,market);
            transaction.addToBackStack("market");
        } else if (id == R.id.nav_calendar) {
            if (myEvents == null) myEvents = new MyEventsFragment();
            transaction.replace(R.id.fragment_container,myEvents);
            transaction.addToBackStack("myEvents");
        } else if (id == R.id.settings) {
            Intent intent = new Intent(MainActivity.this, PreferencesActivity.class);
            intent.putExtra("skip", false);
            startActivity(intent);
        }
        transaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void exchangeTakes() {
        //getFragmentManager().popBackStack();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (market == null) market = new MarketFragment();
        transaction.replace(R.id.fragment_container,market);
        transaction.addToBackStack("market");
        transaction.commit();
    }
}
