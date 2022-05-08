package com.nedexplorer.myapplication.advanced;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;
import com.nedexplorer.myapplication.Classes.Marker;
import com.wikitude.architect.ArchitectView;

import org.imperiumlabs.geofirestore.GeoFirestore;
import org.imperiumlabs.geofirestore.GeoQuery;
import org.imperiumlabs.geofirestore.GeoQueryEventListener;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class PoiDataFromApplicationModel extends ArchitectViewExtension implements LocationListener {

    /** If the POIs were already generated and sent to JavaScript. */

    private boolean injectedPois;
    private FirebaseFirestore databaseReference =  FirebaseFirestore.getInstance();
    private CollectionReference collectionLocation = databaseReference.collection("MarkersLocation");
    private CollectionReference collectionMarkers =  databaseReference.collection("Markers") ;
    private GeoFirestore geoFirestore =  new GeoFirestore(collectionLocation);

    protected JSONArray Markers;
    public PoiDataFromApplicationModel(Activity activity, ArchitectView architectView) {
        super(activity, architectView);

       /* geoFirestore.setLocation("abcd", new GeoPo int(28.1234, 76.90789), new GeoFirestore.CompletionListener() {
            @Override
            public void onComplete(Exception e) {
                Log.i("Added","Marker added");
            }
        });

      /*  this.injectedPois = false;
        this.databaseReference =  FirebaseFirestore.getInstance();
        this.collectionLocation = databaseReference.collection("MarkersLocation");
        this.collectionMarkers = databaseReference.collection("Markers");
        this.geoFirestore = new GeoFirestore(this.collectionLocation);*/
    }


    /**
     * When the first location was received the POIs are generated and sent to the JavaScript code,
     * by using architectView.callJavascript.
     */
    @Override
    public void onLocationChanged(Location location) {
        if (!injectedPois) {
            Log.i("On location Changed"," called");
            //final JSONArray jsonArray = generatePoiInformation(location);
            final JSONArray jsonArray = generatePoiInformation(location);
            architectView.callJavascript("World.loadPoisFromJsonData(" + jsonArray.toString() + ")"); // Triggers the loadPoisFromJsonData function
            injectedPois = true; // don't load pois again
            if(injectedPois){
                Log.i("Injected Pois",String.valueOf(injectedPois));
            }
        }
    }

    private JSONArray generateMarkersInformation(final Location userLocation){

      this.Markers  = new JSONArray();


      //  geoFirestore.setLocation("abcd",new GeoPoint(28.1234,76.90789));
        // creates a new query around user location with a radius of 40 kilometers
       // GeoQuery geoQuery = geoFirestore.queryAtLocation(new GeoPoint(userLocation.getLatitude(),userLocation.getLongitude()), 40);
        GeoQuery geoQuery = geoFirestore.queryAtLocation(new GeoPoint(      24.926000,67.10100), 40);
        geoQuery.addGeoQueryEventListener(new GeoQueryEventListener() {
            @Override
            public void onKeyEntered(String s, GeoPoint geoPoint) {

                Log.d("Geopoint Key",s);
                DocumentReference marker = collectionMarkers.document(s);
                marker.get().
                        addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            Log.i("Key",documentSnapshot.getId().toString());
                            Marker marker1 = documentSnapshot.toObject(Marker.class);
                            Markers.put(marker1.toJSONObject());
                            Log.i("KeyDATA",String.valueOf(Markers.length()));
                            Log.i("KeyDATA",marker1.toJSONObject().toString());
                            architectView.callJavascript("World.loadPoisFromJsonData(" + Markers.toString() + ")");
                        }
                        }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.i("Error",e.getMessage());
                        }
                       });

            }

            @Override
            public void onKeyExited(String s) {

            }

            @Override
            public void onKeyMoved(String s, GeoPoint geoPoint) {

            }

            @Override
            public void onGeoQueryReady() {

            }

            @Override
            public void onGeoQueryError(Exception e) {
                Log.i("Message Error", e.getMessage());

            }
        });
        Log.i("KeyData2",Markers.toString());
        return Markers;
    }
    /**
     * The very basic LocationProvider setup of this sample app does not handle the following callbacks
     * to keep the sample app as small as possible. They should be used to handle changes in a production app.
     */
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}

    @Override
    public void onProviderEnabled(String provider) {}

    @Override
    public void onProviderDisabled(String provider) {}

    private static JSONArray generatePoiInformation(final Location userLocation) {

        final JSONArray pois = new JSONArray();

        // ensure these attributes are also used in JavaScript when extracting POI data
        final String ATTR_ID = "id";
        final String ATTR_NAME = "name";
        final String ATTR_DESCRIPTION = "description";
        final String ATTR_LATITUDE = "latitude";
        final String ATTR_LONGITUDE = "longitude";
        final String ATTR_ALTITUDE = "altitude";

        // generates 20 POIs
        for (int i = 1; i <= 20; i++) {
            final HashMap<String, String> poiInformation = new HashMap<String, String>();
            poiInformation.put(ATTR_ID, String.valueOf(i));
            poiInformation.put(ATTR_NAME, "POI#" + i);
            poiInformation.put(ATTR_DESCRIPTION, "This is the description of POI#" + i);
            double[] poiLocationLatLon = getRandomLatLonNearby(userLocation.getLatitude(), userLocation.getLongitude());
            poiInformation.put(ATTR_LATITUDE, String.valueOf(poiLocationLatLon[0]));
            poiInformation.put(ATTR_LONGITUDE, String.valueOf(poiLocationLatLon[1]));
            final float UNKNOWN_ALTITUDE = -32768f;  // equals "AR.CONST.UNKNOWN_ALTITUDE" in JavaScript (compare AR.GeoLocation specification)
            // Use "AR.CONST.UNKNOWN_ALTITUDE" to tell ARchitect that altitude of places should be on user level. Be aware to handle altitude properly in locationManager in case you use valid POI altitude value (e.g. pass altitude only if GPS accuracy is <7m).
            poiInformation.put(ATTR_ALTITUDE, String.valueOf(UNKNOWN_ALTITUDE));
            pois.put(new JSONObject(poiInformation));
        }

        return pois;
    }

    private static double[] getRandomLatLonNearby(final double lat, final double lon) {
        return new double[]{lat + Math.random() / 5 - 0.1, lon + Math.random() / 5 - 0.1};
    }


}
