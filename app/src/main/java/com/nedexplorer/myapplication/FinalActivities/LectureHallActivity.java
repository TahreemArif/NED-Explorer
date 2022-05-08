package com.nedexplorer.myapplication.FinalActivities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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
import com.nedexplorer.myapplication.Classes.LectureHall;
import com.nedexplorer.myapplication.Classes.LibraryInformation;
import com.nedexplorer.myapplication.R;

public class LectureHallActivity extends BaseActivity {

    protected FirebaseFirestore firebaseFirestoreDb;
    protected CollectionReference collectionReference;
    protected LectureHall lectureHall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.fragment_container); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.activity_lecture_hall, contentFrameLayout);

        final Bundle extras = getIntent().getExtras();
        firebaseFirestoreDb = FirebaseFirestore.getInstance();
        collectionReference = firebaseFirestoreDb.collection("LectureHalls");
        RetrieveLectureHallInformation(extras);

    }

    public void RetrieveLectureHallInformation(Bundle extras){
        String QueryParameter;
        if(extras.containsKey("department"))
            QueryParameter = "department";
        else
            QueryParameter = "title";

        final Query LectureHallInformation = collectionReference
                .whereEqualTo(QueryParameter,extras.getString(QueryParameter))
                .limit(1);
        LectureHallInformation.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                Log.i("Query","Query Successful");
                if(!queryDocumentSnapshots.isEmpty()){
                    for(DocumentSnapshot document: queryDocumentSnapshots){
                        lectureHall = document.toObject(LectureHall.class);
                        getSupportActionBar().setTitle(lectureHall.getDepartment());
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
