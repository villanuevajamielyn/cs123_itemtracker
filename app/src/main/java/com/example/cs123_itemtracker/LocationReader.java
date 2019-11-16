package com.example.cs123_itemtracker;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.listener.multi.BaseMultiplePermissionsListener;

import org.androidannotations.annotations.EBean;

@EBean
public class LocationReader
{
    // SET APP PERMISSION IN THE APPS menu, turn on location
    // or request permission


    Context context;
    FusedLocationProviderClient fusedLocationProviderClient;
    LocationCallback locationCallback;
    Location loc;


    public LocationReader(final Context context)
    {
        this.context = context;

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);

        locationCallback = new LocationCallback()
        {
            public void onLocationResult(LocationResult result)
            {
                if (result==null)
                {
                    return;
                }

                for (Location location : result.getLocations())
                {
                    processLocation(location);
                }
            }
        };



    }

    public void locateMe()
    {
        locateMeImpl();
    }

    private void locateMeImpl()
    {
        try {
            LocationRequest locationRequest = new LocationRequest();
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

            fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);
        }
        catch(SecurityException se)
        {
            se.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void stop()
    {
        System.out.println("STOP LOCATION");
        fusedLocationProviderClient.removeLocationUpdates(locationCallback);  // field
    }

    public void processLocation(Location loc)
    {
        // do stuff with location here
        System.out.println(loc.getLatitude()+" , "+loc.getLongitude());

        if (myCallback!=null)
        {
            myCallback.newLocation(loc);
        }
    }


    public interface LocationReaderCallback
    {
        public void newLocation(Location loc);
    }

    private LocationReaderCallback myCallback;

    public void setLocationReaderCallback(LocationReaderCallback lrc)
    {
        myCallback = lrc;
    }

}

