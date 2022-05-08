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
import com.nedexplorer.myapplication.Classes.ClassroomInformation;
import com.nedexplorer.myapplication.Classes.LectureHall;
import com.nedexplorer.myapplication.R;

public class ClassroomInformationActivity extends BaseActivity {

    protected FirebaseFirestore firebaseFirestoreDb;
    protected CollectionReference collectionReference;
    protected ClassroomInformation Classroom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.fragment_container); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.activity_classroom_information, contentFrameLayout);

        final Bundle extras = getIntent().getExtras();
        firebaseFirestoreDb = FirebaseFirestore.getInstance();
        collectionReference = firebaseFirestoreDb.collection("ClassroomInformation");
        RetrieveClassroomInformation(extras);

    }

    public void RetrieveClassroomInformation(Bundle extras){
        final Query ClassroomInformation;
        if(extras.containsKey("title"))
            ClassroomInformation = collectionReference
                    .whereEqualTo("title",extras.getString("title"))
                    .limit(1);
        else
            ClassroomInformation = collectionReference
                    .whereEqualTo("name",extras.getString("name"))
                    .whereEqualTo("department",extras.getString("department"))
                    .limit(1);

        ClassroomInformation.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                Log.i("Query","Query Successful");
                if(!queryDocumentSnapshots.isEmpty()){
                    for(DocumentSnapshot document: queryDocumentSnapshots){
                        Classroom = document.toObject(ClassroomInformation.class);
                        getSupportActionBar().setTitle(Classroom.getName().toUpperCase());
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
