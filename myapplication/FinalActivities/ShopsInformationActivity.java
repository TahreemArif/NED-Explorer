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
import com.nedexplorer.myapplication.Classes.ShopInformation;
import com.nedexplorer.myapplication.R;

public class ShopsInformationActivity extends BaseActivity {

    public static final String EXTRAS_KEY_MARKER_TITLE = "title";
    protected FirebaseFirestore firebaseFirestoreDb;
    protected CollectionReference collectionReference;
    protected ShopInformation Shop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.fragment_container); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.activity_bank_information, contentFrameLayout);

        final Bundle extras = getIntent().getExtras();
        firebaseFirestoreDb = FirebaseFirestore.getInstance();
        collectionReference = firebaseFirestoreDb.collection("ShopsInformation");
        RetrieveShopInformation(extras);

    }

    public void RetrieveShopInformation(Bundle extras){
        final Query ShopInformation = collectionReference
                .whereEqualTo("name",extras.getString(EXTRAS_KEY_MARKER_TITLE))
                .limit(1);
        ShopInformation.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                Log.i("Query","Query Successful");
                if(!queryDocumentSnapshots.isEmpty()){
                    for(DocumentSnapshot document: queryDocumentSnapshots){
                        Shop = document.toObject(ShopInformation.class);
                        getSupportActionBar().setTitle(Shop.getName().toUpperCase());
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
