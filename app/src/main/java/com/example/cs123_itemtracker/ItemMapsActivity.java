package com.example.cs123_itemtracker;

import androidx.fragment.app.FragmentActivity;

import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.navigation.NavigationView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;

import java.util.ArrayList;

import io.realm.RealmResults;

@EActivity(R.layout.c_activity_item_maps)
public class ItemMapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationReader.LocationReaderCallback {

    //@Extra
    //String name;

    private GoogleMap mMap;

    @Bean
    LocationReader locationReader;

    @Bean
    ItemManagement im;

    @AfterViews
    public void init()
    {
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        locationReader.setLocationReaderCallback(this);
        NavigationView nav = findViewById(R.id.nav_view);
        nav.setNavigationItemSelectedListener(new DrawerActivity(this));
    }

    public void newLocation(Location loc)
    {
        // do stuff with the location
        LatLng newLoc = new LatLng(loc.getLatitude(), loc.getLongitude());
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(newLoc, 1.0f));
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        RealmResults<Item> items = im.findAll();
        ArrayList<LatLng> latLngs = new ArrayList<>();

        for (int i=0; i<items.size(); i++){
            latLngs.add(new LatLng(items.get(i).getGpsPingLatitude(),items.get(i).getGpsPingLongitude()));
        }

        for (int i=0; i<latLngs.size(); i++){
            mMap.addMarker(new MarkerOptions()
                    .position(latLngs.get(i))
                    .title(items.get(i).getName())
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));
        }

        if (latLngs.size()!=0) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngs.get(0), 16.99f));
        }
    }
}