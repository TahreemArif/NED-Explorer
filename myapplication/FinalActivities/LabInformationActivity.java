package com.nedexplorer.myapplication.FinalActivities;

import android.support.annotation.NonNull;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.nedexplorer.myapplication.BaseActivity;
import com.nedexplorer.myapplication.Classes.LabInformation;
import com.nedexplorer.myapplication.R;

public class LabInformationActivity extends BaseActivity {
    protected FirebaseFirestore firebaseFirestoreDb;
    protected CollectionReference collectionReference;
    protected LabInformation Lab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.fragment_container); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.activity_lab_information, contentFrameLayout);

        final Bundle extras = getIntent().getExtras();
        firebaseFirestoreDb = FirebaseFirestore.getInstance();
        collectionReference = firebaseFirestoreDb.collection("LabInformation");
        RetrieveLabInformation(extras);

    }

    public void RetrieveLabInformation(Bundle extras){
        final Query LabInformation;
        if(extras.containsKey("title"))
            LabInformation = collectionReference
                    .whereEqualTo("title",extras.getString("title"))
                    .limit(1);
        else
            LabInformation = collectionReference
                    .whereEqualTo("name",extras.getString("name"))
                    .whereEqualTo("department",extras.getString("department"))
                    .limit(1);

        LabInformation.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                Log.i("Query","Query Successful");
                if(!queryDocumentSnapshots.isEmpty()){
                    for(DocumentSnapshot document: queryDocumentSnapshots){
                        Lab = document.toObject(LabInformation.class);
                        getSupportActionBar().setTitle(Lab.getName().toUpperCase());
                    }
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
