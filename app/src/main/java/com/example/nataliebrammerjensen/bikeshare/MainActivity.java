package com.example.nataliebrammerjensen.bikeshare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nataliebrammerjensen on 08/02/2018.
 */

//Here is a skeleton for the BikeShareActivity.java:
public class MainActivity extends Activity { // GUI variables
    private static final String TAG = "MainActivity";

    private Button addRide;
    private TextView lastAdded;
    private TextView newWhat, newWhere;

    public static RidesDB rdb;

    private Ride last= new Ride("", "", "");

    //Go to other activity
    public Button GoToStart;
    public Button GoToEnd;
    public Button showListOfRides;

    public ArrayAdapter buckysAdapter;
    ArrayList<String> rideListStrings= new ArrayList<>();

    //Current ride
    public static Ride current = new Ride("", "", "");
    String currentString = current.toStringStart();
    TextView currentRideView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout. activity_main);

        Log.d(TAG, "onCreate(Bundle) called");

        currentRideView=(TextView) findViewById(R.id.current);;
        buckysAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, rideListStrings);
//
//        lastAdded = (TextView) findViewById(R.id.last_added);
//        updateUI();
//
//        // Button
//        addRide = (Button) findViewById(R.id.add_button);
//
//        // Texts
//        newWhat = (TextView) findViewById(R.id.what_text);
//        newWhere =(TextView) findViewById(R.id.where_edit);
//
//        // view products click event
//        addRide.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if ((newWhat.getText().length()>0) && (newWhere.getText().length()>0 )){
//                    last.setMbikeName(newWhat.getText().toString().trim());
//                    last.setMstartRide(newWhere.getText().toString().trim());
//
//                    // reset text fields
//                    newWhat.setText(""); newWhere.setText(""); updateUI();
//                }
//            }
//        });

        showListOfRides = (Button) findViewById(R.id.list_rides_button);
        showListOfRides.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                populateLIstView();
            }
        });

        initStart();
        initEnd();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) // Intent kommer fra StartRideActivity
    {
        // executed when start ride activity finishes
        // gets the added ride and adds it to the list and notifies the adapter to display it
        if(requestCode == 1000 && resultCode==RESULT_OK) // 1000 is an ID number to differ them from each other.
        {

            //rideListStrings.add(data.getData().toString());
            //buckysAdapter.notifyDataSetChanged();
            rdb=RidesDB.get(getApplicationContext());

            current = rdb.getLast();
            //currentString = null;
            currentString=current.toString();
            updateUI(currentString);
        }

        //Newstuff
        // end ride
        if(requestCode == 2000 && resultCode==RESULT_OK) // 1000 is an ID number to differ them from each other.
        {
            //rdb=RidesDB.get(getApplicationContext());
//            current = rdb.getLast();
            currentString = null;
            ArrayList<Ride> rides=RidesDB.get(getApplicationContext()).getRidesDB();
            rideListStrings.clear(); // clear so that we donøt have both the old and the new in this list.
            for(Ride r : rides)
                rideListStrings.add(r.toString());
            buckysAdapter.notifyDataSetChanged(); updateUI(currentString);
        }
    }

    private void updateUI(String newAdd){
        currentRideView.setText(newAdd);
    }

    public void initStart() {
        GoToStart = (Button) findViewById(R.id.go_to_start_button);
        GoToStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(MainActivity.this, StartRideActivity.class);
//                startActivity(toy);
                //HFM
                toy.putExtra("rides", rideListStrings);
                startActivityForResult(toy, 1000);
            }
        });
    }

    public void initEnd() {
        GoToEnd = (Button) findViewById(R.id.go_to_end_button);
        GoToEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(MainActivity.this, EndRideActivity.class);
//                startActivity(toy);
                //HFM
                toy.putStringArrayListExtra("rides", rideListStrings);
                startActivityForResult(toy,2000);
            }
        });
    }

    public void populateLIstView(){
        rdb = RidesDB.get(getApplicationContext());
        List<Ride> rides = rdb.getRidesDB();
//        List<String> rideListStrings = new ArrayList<>();
        for (Ride r: rides) {
            if(!r.getMstopRide().equals("")){
                rideListStrings.add(r.toString());
                System.out.println(r.toString());
            }

        }


        ListView buckysListView = findViewById(R.id.listView);
        buckysListView.setAdapter(buckysAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }
    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }
    @Override
    public void onResume() {
        super.onResume();

        Log.d(TAG, "onResume() called");
    }
    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }
}
