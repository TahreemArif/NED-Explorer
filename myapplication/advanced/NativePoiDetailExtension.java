package com.nedexplorer.myapplication.advanced;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.nedexplorer.myapplication.Classes.LectureHall;
import com.nedexplorer.myapplication.FinalActivities.BankInformationActivity;
import com.nedexplorer.myapplication.FinalActivities.CanteenInformationActivity;
import com.nedexplorer.myapplication.FinalActivities.ClassroomInformationActivity;
import com.nedexplorer.myapplication.FinalActivities.LabInformationActivity;
import com.nedexplorer.myapplication.FinalActivities.LectureHallActivity;
import com.nedexplorer.myapplication.FinalActivities.LibraryInformationActivity;
import com.nedexplorer.myapplication.FinalActivities.ShopsInformationActivity;
import com.nedexplorer.myapplication.R;
import com.wikitude.architect.ArchitectJavaScriptInterfaceListener;
import com.wikitude.architect.ArchitectView;

import org.json.JSONException;
import org.json.JSONObject;

public class NativePoiDetailExtension extends ArchitectViewExtension implements ArchitectJavaScriptInterfaceListener {

    public NativePoiDetailExtension(Activity activity, ArchitectView architectView) {
        super(activity, architectView);
    }

    @Override
    public void onCreate() {
        /*
         * The ArchitectJavaScriptInterfaceListener has to be added to the Architect view after ArchitectView.onCreate.
         * There may be more than one ArchitectJavaScriptInterfaceListener.
         */
        architectView.addArchitectJavaScriptInterfaceListener(this);
    }

    @Override
    public void onDestroy() {
        // The ArchitectJavaScriptInterfaceListener has to be removed from the Architect view before ArchitectView.onDestroy.
        architectView.removeArchitectJavaScriptInterfaceListener(this);
    }

    /**
     * ArchitectJavaScriptInterfaceListener.onJSONObjectReceived is called whenever
     * AR.platform.sendJSONObject is called in the JavaScript code.
     *
     * In this case the jsonObject includes an id, a title and a description which will be
     * parsed and sent to the SamplePoiDetailActivity as intent extras.
     *
     * @param jsonObject jsonObject passed in AR.platform.sendJSONObject
     */
    @Override
    public void onJSONObjectReceived(JSONObject jsonObject) {
        final Intent poiDetailIntent = new Intent(activity, SamplePoiDetailActivity.class);
        try {
            switch (jsonObject.getString("action")) {
                case "present_poi_details":
                    poiDetailIntent.putExtra(SamplePoiDetailActivity.EXTRAS_KEY_POI_ID, jsonObject.getString("id"));
                    poiDetailIntent.putExtra(SamplePoiDetailActivity.EXTRAS_KEY_POI_TITILE, jsonObject.getString("title"));
                    poiDetailIntent.putExtra(SamplePoiDetailActivity.EXTRAS_KEY_POI_DESCR, jsonObject.getString("description"));
                    activity.startActivity(poiDetailIntent);
                    break;
            }

        } catch (JSONException e) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(activity, R.string.error_parsing_json, Toast.LENGTH_LONG).show();
                }
            });
            e.printStackTrace();
        }
    /*  final Intent MarkerDetailIntent;
      try {
          switch(jsonObject.getString("type")){
              case "Library":
                  MarkerDetailIntent = new Intent(activity,LibraryInformationActivity.class);
                  MarkerDetailIntent.putExtra("title",jsonObject.getString("title"));
                  activity.startActivity(MarkerDetailIntent);
                  break;
              case "Canteen":
                  MarkerDetailIntent = new Intent(activity,CanteenInformationActivity.class);
                  MarkerDetailIntent.putExtra("title",jsonObject.getString("title"));
                  activity.startActivity(MarkerDetailIntent);
                  break;
              case "Shop":
                  MarkerDetailIntent = new Intent(activity,ShopsInformationActivity.class);
                  MarkerDetailIntent.putExtra("title",jsonObject.getString("title"));
                  activity.startActivity(MarkerDetailIntent);
                  break;
              case "Bank":
                  MarkerDetailIntent = new Intent(activity,BankInformationActivity.class);
                  MarkerDetailIntent.putExtra("title",jsonObject.getString("title"));
                  activity.startActivity(MarkerDetailIntent);
                  break;
              case "Lab":
                  MarkerDetailIntent = new Intent(activity,LabInformationActivity.class);
                  MarkerDetailIntent.putExtra("title",jsonObject.getString("title"));
                  activity.startActivity(MarkerDetailIntent);
                  break;
              case "Classroom":
                  MarkerDetailIntent = new Intent(activity,ClassroomInformationActivity.class);
                  MarkerDetailIntent.putExtra("title",jsonObject.getString("title"));
                  activity.startActivity(MarkerDetailIntent);
                  break;
              case "LectureHall":
                  MarkerDetailIntent = new Intent(activity,LectureHallActivity.class);
                  MarkerDetailIntent.putExtra("title",jsonObject.getString("title"));
                  activity.startActivity(MarkerDetailIntent);
                  break;

          }
      }
      catch (JSONException e) {
          e.printStackTrace();
      }*/
    }
}
