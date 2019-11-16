package com.example.cs123_itemtracker;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.listener.multi.BaseMultiplePermissionsListener;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@EActivity(R.layout.b_add_item_popup)
public class AddItemActivity extends AppCompatActivity {

    @ViewById
    EditText itemName;

    @ViewById
    EditText itemDescription;

    @ViewById
    EditText itemLocation;

    @ViewById
    ImageView itemImage;

    @ViewById
    Button currentLoc;

    @Bean
    ItemManagement im;

    @Bean
    LocationReader locationReader;

    private double lat;
    private double lng;

    private String itemUUID;

    @AfterViews
    public void init(){
        itemUUID = UUID.randomUUID().toString();
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA,
                        Manifest.permission.ACCESS_FINE_LOCATION

                )

                .withListener(new BaseMultiplePermissionsListener() {
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                            current();
                            currentLoc.setText("Get Current Location");
                        } else {
                            Toast.makeText(getApplicationContext(), "You must provide permissions for app to run", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }
                })
                .check();
    }

    @Click(R.id.addItemImage)
    public void addItemImage(){
        ImageActivity_.intent(this)
                .startForResult(0);
    }

    @Click(R.id.addItem)
    public void addItem(){
        String name = itemName.getText().toString();
        String desc = itemDescription.getText().toString();
        String loc = itemLocation.getText().toString();

        im.saveCreds(itemUUID, name, desc, loc, lat, lng);

        MainActivity_.intent(this)
                .start();
    }

    public void onActivityResult(int requestCode, int responseCode, Intent data) {
        if (requestCode == 0) {
            if (responseCode == 100) {
                // save rawImage to file savedImage.jpeg
                // load file via picasso
                byte[] jpeg = data.getByteArrayExtra("rawJpeg");

                try {
                    File savedImage = saveFile(jpeg);
                    refreshImageView(savedImage);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

    private File saveFile(byte[] jpeg) throws IOException {
        File getImageDir = getExternalCacheDir();
        String filename = itemUUID + ".jpeg";
        File savedImage = new File(getImageDir, filename);

        FileOutputStream fos = new FileOutputStream(savedImage);
        fos.write(jpeg);
        fos.close();
        return savedImage;
    }

    private void refreshImageView(File savedImage) {
        Picasso.with(this)
                .load(savedImage)
                .networkPolicy(NetworkPolicy.NO_CACHE)
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .into(itemImage);
    }

    @Click (R.id.currentLoc)
    public void click(){
        current();
        //currentLoc.setClickable(false);
    }

    public void current(){
        locationReader.locateMe();
        LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        Looper looper = null;
        Criteria c = new Criteria();
        try {
            locationManager.requestSingleUpdate(c, locationListener, looper);
        } catch (SecurityException se){
            se.printStackTrace();
        }
        currentLoc.setText(lat+","+lng);
    }

    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            Log.d("Location Changes", location.toString());
            lat=location.getLatitude();
            lng=location.getLongitude();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.d("Status Changed", String.valueOf(status));
        }

        @Override
        public void onProviderEnabled(String provider) {
            Log.d("Provider Enabled", provider);
        }

        @Override
        public void onProviderDisabled(String provider) {
            Log.d("Provider Disabled", provider);
        }
    };
}


