package com.nedexplorer.myapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.wikitude.architect.ArchitectStartupConfiguration;
import com.wikitude.architect.ArchitectView;

import java.io.IOException;

public class ArActivity extends AppCompatActivity {

    protected ArchitectView architectView;

    private static final int WIKITUDE_PERMISSIONS_REQUEST_CAMERA = 1;
    private static final int WIKITUDE_PERMISSIONS_REQUEST_GPS = 2;

    private LocationManager locationManager;
    private LocationListener locationListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_architect_view);

        this.architectView = (ArchitectView)this.findViewById( R.id.architectView );
        final ArchitectStartupConfiguration config = new ArchitectStartupConfiguration();
        config.setLicenseKey("tQ3EovGGCtuLtgE1LiU8vBclpbXN5Vs8K7aE8f9XWS/icnHcGI340oblGyMDLTLsSZeUfFSxOIZ7OOQsnaehYFfOuW8FyvSbFrmnbcgDWdp0yy7znRCcx1Jc/yHNKy8+oV17H44ulr8XEAE/LKN+MjZ78x2GlgbTD8DSR2HzTUJTYWx0ZWRfX6t4GISUpkEH2ZWchk4OKaZ0nTND9mWxQQainRm4tdn43JSeZNLo56Y2jXOfh61IvG9PGBVWqzEbUI8E+82puCm5xiJr8GwNCvL9eQmD09ymmjyuLp7U2/JqworuH97nS90PBAi/7j2CKvPhpeRkMXTCUKWyawJsSpDU5XKLYeV1DJ/p/zBwx8sqqKZspXc+yX98KNzMcSsRWGQ4VC+nKYyMvE979unu08bLewXluquawQtP6j3aZoU6WXVNpEU0Kk/PZ4S944dNxFQxpe76qHzQjriE+mwymVQ3VIkg8kBkEALwguCWrcArLroCf2Bh43sA1Wn4A8CrsI9W4C9fTOQ8IpRhIkEyvZE4ciO/XOKBg5ybFYl9W3WVl3gOCm+fHwdCX94fcUG7Nc0XVnMr9oGN/Orw7Xs4HxvevF9EUAk2cV4fqTzvJb74nXW32WVvpsC6csjoHIFGd41mFkokGTS4qzJhN8p7sknGmnAbae3BvyT5rLHueyHQa42oO/pGN+jYDWTiBYrH8QpW4w5q5D5l0kuJOJvi0w==" );


        // camera settings
        if ( ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA}, WIKITUDE_PERMISSIONS_REQUEST_CAMERA);
        }

      locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
               Log.d("location", location.toString());
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    WIKITUDE_PERMISSIONS_REQUEST_GPS);

        } else {
            locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 0, 0, locationListener);
        }

        this.architectView.onCreate( config );
    }

   public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

            if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED ){

                locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 0, 0, locationListener);

            }
        }

    }
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        architectView.onPostCreate();


        try{
            this.architectView.load( "file:///android_asset/demo/index.html" );
        }catch (Exception e){

        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        architectView.onResume(); // Mandatory ArchitectView lifecycle call
    }

    @Override
    protected void onPause() {
        super.onPause();
        architectView.onPause(); // Mandatory ArchitectView lifecycle call
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /*
         * Deletes all cached files of this instance of the ArchitectView.
         * This guarantees that internal storage for this instance of the ArchitectView
         * is cleaned and app-memory does not grow each session.
         *
         * This should be called before architectView.onDestroy
         */
        architectView.clearCache();
        architectView.onDestroy(); // Mandatory ArchitectView lifecycle call
    }

}

