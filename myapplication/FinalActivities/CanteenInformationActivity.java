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
import com.nedexplorer.myapplication.Classes.CanteenInformation;
import com.nedexplorer.myapplication.R;

public class CanteenInformationActivity extends BaseActivity {

    public static final String EXTRAS_KEY_MARKER_TITLE = "title";
    protected FirebaseFirestore firebaseFirestoreDb;
    protected CollectionReference collectionReference;
    protected CanteenInformation Canteen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Log.d("Reached Canteen","Canteen Information Activity");
        getSupportActionBar().setTitle("CANTEEN");

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.fragment_container); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.activity_canteen_information, contentFrameLayout);

        final Bundle extras = getIntent().getExtras();
        firebaseFirestoreDb = FirebaseFirestore.getInstance();
        collectionReference = firebaseFirestoreDb.collection("CanteensInformation");

        RetrieveCanteenInformation(extras);

    }

    public void RetrieveCanteenInformation(Bundle extras){
        final Query CanteenInformation = collectionReference
                .whereEqualTo("name",extras.getString(EXTRAS_KEY_MARKER_TITLE)).limit(1);
                CanteenInformation.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        Log.i("Query","Query Successful");
                        if(!queryDocumentSnapshots.isEmpty()){
                            for(DocumentSnapshot document: queryDocumentSnapshots){
                                Canteen = document.toObject(CanteenInformation.class);
                                getSupportActionBar().setTitle(Canteen.getName().toUpperCase());
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
