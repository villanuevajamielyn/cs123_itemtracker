package com.example.cs123_itemtracker;

import android.app.Activity;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import org.androidannotations.annotations.AfterViews;

public class DrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Activity context;

    public DrawerActivity (Activity context){
        this.context=context;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_categories) {
            CategoryActivity_.intent(context)
                    .start();
        } else if (id == R.id.nav_list) {
            MainActivity_.intent(context)
                    .start();
        } else if (id == R.id.nav_GPS) {
            ItemMapsActivity_.intent(context)
                    .start();
        } else if (id == R.id.nav_loc) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
