package com.nedexplorer.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;

import com.nedexplorer.myapplication.Classes.CanteenInformation;
import com.nedexplorer.myapplication.Classes.ClassroomInformation;
import com.nedexplorer.myapplication.FinalActivities.BankInformationActivity;
import com.nedexplorer.myapplication.FinalActivities.CanteenInformationActivity;
import com.nedexplorer.myapplication.FinalActivities.ClassroomInformationActivity;
import com.nedexplorer.myapplication.FinalActivities.LabInformationActivity;
import com.nedexplorer.myapplication.FinalActivities.LectureHallActivity;
import com.nedexplorer.myapplication.FinalActivities.LibraryInformationActivity;
import com.nedexplorer.myapplication.FinalActivities.ShopsInformationActivity;

public class NearByPlaces extends  BaseActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("In nearby places", "onCreate: ");

        getSupportActionBar().setTitle("NEARBY PLACES");

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.fragment_container); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.activity_near_by_places, contentFrameLayout);

        final Animation animAlpha= AnimationUtils.loadAnimation(this,R.anim.anime_alpha);

        Button But1= (Button) findViewById(R.id.button);
        Button But2= (Button) findViewById(R.id.button2);
        Button But3= (Button) findViewById(R.id.button3);
        Button But4= (Button) findViewById(R.id.button4);
        Button But5= (Button) findViewById(R.id.button5);
        Button But6= (Button) findViewById(R.id.button6);



        But1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animAlpha);
                Intent int1 = new Intent(NearByPlaces.this,Department.class);
                startActivity(int1);
            }
        });

        But2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animAlpha);
                Intent int2 = new Intent(NearByPlaces.this,Library.class);
                startActivity(int2);
            }
        });

        But3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animAlpha);
                Intent int3 = new Intent(NearByPlaces.this,AdminOffices.class);
                startActivity(int3);
            }
        });

        But4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animAlpha);
                Intent int4 = new Intent(NearByPlaces.this,Bank.class);
                startActivity(int4);
            }
        });

        But5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animAlpha);
                Intent int5 = new Intent(NearByPlaces.this,Canteens.class);
               // Intent int5 = new Intent(NearByPlaces.this, LabInformationActivity.class);
               // int5.putExtra("department","Computer & Information Systems Engineering");
               // int5.putExtra("name","Room 1");
                startActivity(int5);
            }
        });

        But6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animAlpha);
                Intent int6 = new Intent(NearByPlaces.this,Shops.class);
                startActivity(int6);
            }
        });
    }
}
