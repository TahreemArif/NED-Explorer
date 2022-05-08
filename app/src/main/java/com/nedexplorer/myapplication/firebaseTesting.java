package com.nedexplorer.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import org.imperiumlabs.geofirestore.GeoFirestore;
import org.imperiumlabs.geofirestore.GeoQuery;
import org.imperiumlabs.geofirestore.GeoQueryEventListener;

import java.util.List;

public class firebaseTesting extends AppCompatActivity {

    private String TAG = "fireBaseTesting";
      protected FirebaseFirestore db = FirebaseFirestore.getInstance();
  //  db = FirebaseFirestore.getInstance();
    protected FirebaseFirestore geo = FirebaseFirestore.getInstance();
    CollectionReference geolocation = geo.collection("MarkersLocation");
    GeoFirestore geol = new GeoFirestore(geolocation);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_testing);

    }


    public void onClick(View view){

     DocumentReference  docref = db.collection("Markers").document("lG8TrBV3HrgQ8YzYXdtd");
        docref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.i(TAG, "DocumentSnapshot data: " + document.getId());
                    } else {
                        Log.i(TAG, "No such document");
                    }
                } else {
                    Log.i(TAG, "get failed with ", task.getException());
                }
            }
        });


       /* GeoQuery query = geol.queryAtLocation(new GeoPoint(24.000012,67.000012),0.5);
        query.addGeoQueryEventListener(new GeoQueryEventListener() {
            @Override
            public void onKeyEntered(String s, GeoPoint geoPoint) {
                Log.d("Key",s);
             DocumentReference ref = db.collection("Markers").document(s);
             ref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                 @Override
                 public void onSuccess(DocumentSnapshot documentSnapshot) {

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

            }
        });
                /*.addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if(!queryDocumentSnapshots.isEmpty()){
                            QuerySnapshot data = queryDocumentSnapshots.getDocuments();
                            Log.i(TAG, "onSuccess: "+ data.getData());

                           // List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();

                        }
                    }
                });*/
    }
}
