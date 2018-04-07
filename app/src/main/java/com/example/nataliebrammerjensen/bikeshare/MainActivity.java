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

import com.amitshekhar.DebugDB;
import com.example.nataliebrammerjensen.bikeshare.ridesBrowser.BikeShareActivityRidesBrowser;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by nataliebrammerjensen on 08/02/2018.
 */

//Here is a skeleton for the BikeShareActivity.java:
public class MainActivity extends Activity { // GUI variables
    private static final String TAG = "MainActivity";

    private Button addRide;
    private TextView lastAdded;
    private TextView newWhat, newWhere;

    public static MyRidesDB rSqlDb;

    private RideMine last= new RideMine("", "", "");

    //Go to other activity
    public Button GoToStart;
    public Button GoToEnd;
    public Button showListOfRides;

    public ArrayAdapter buckysAdapter;
    public ArrayAdapter buckysAdapter2;
    public ArrayAdapter buckysAdapter3;
    ArrayList<String> rideListStrings= new ArrayList<>();
    ArrayList<String> rideDates= new ArrayList<>();
    ArrayList<String> rideTimes= new ArrayList<>();
    UUID uuidNew;

    //NDB
    List<RideMine> mRides;
    String uuidString = "";

    //Current ride
    public static RideMine current = new RideMine("", "", "");
    String currentString = current.toStringStart();
    TextView currentRideView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout. activity_main);

        Log.d(TAG, "onCreate(Bundle) called");

        //Her sørger den for at sql databasen bliver lavet.
        rSqlDb = MyRidesDB.get(getApplicationContext());

        currentRideView=(TextView) findViewById(R.id.current);;
        buckysAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, rideListStrings);
        buckysAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, rideDates);
        buckysAdapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, rideTimes);

        showListOfRides = (Button) findViewById(R.id.list_rides_button);
        showListOfRides.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //populateLIstView();

                Intent toy = new Intent(MainActivity.this, BikeShareActivityRidesBrowser.class);
                startActivity(toy);
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

            rSqlDb=MyRidesDB.get(getApplicationContext());
            //fp uuid fra startactivitywith result. Og brug det id til at fp ride.
            uuidString = data.getData().toString();
            System.out.println(uuidString);
            current = MyRidesDB.get(getApplicationContext()).getRIde(UUID.fromString(uuidString));
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
            ArrayList<RideMine> rides=MyRidesDB.get(getApplicationContext()).getRidesDB();
            rideListStrings.clear(); // clear so that we donøt have both the old and the new in this list.
            for(RideMine r : rides) {
                rideListStrings.add(r.toString());
                //W5
                String day = String.valueOf(r.getDay());
                rideDates.add(day);
                System.out.println("sdf");
            }
            buckysAdapter.notifyDataSetChanged();
            updateUI(currentString);
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
                //startActivity(toy);
                //HFM
                //%NDB
                //toy.putExtra("rides", rideListStrings);
                toy.putExtra("UUIDNUMBER", uuidNew);
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
                //startActivity(toy);
                //HFM
                //%NDB
                toy.putExtra("UUIDNUMBER", uuidString);
                System.out.println("qq"+uuidNew);
                startActivityForResult(toy,2000);
            }
        });
    }

    public void populateLIstView(){
        //NDB
        DebugDB.getAddressLog();

        /*rSqlDb = RidesDB.get(getApplicationContext());
        List<Ride> rides = rSqlDb.getRidesDB();
//        List<String> rideListStrings = new ArrayList<>();
        for (Ride r: rides) {
            if(!r.getMstopRide().equals("")){
                rideListStrings.add(r.toString());
                System.out.println(r.toString());
            }

        }


        ListView buckysListView = findViewById(R.id.listView);
        buckysListView.setAdapter(buckysAdapter);*/

        //NDB
        rSqlDb = MyRidesDB.get(getApplicationContext());
        ArrayList<RideMine> sqlRides = rSqlDb.getAllRides();
//        List<String> rideListStrings = new ArrayList<>();
        for (RideMine r: sqlRides) {
            if(!r.getMstopRide().equals("")){
                rideListStrings.add(r.toString());
                //listView2
                String day = String.valueOf(r.getMsdf());
                System.out.println("time = " + r.getMsdf());
                System.out.println("day = " + day);
                rideDates.add(day);
                //listView3
                String time = String.valueOf(r.getHhmmss());
                System.out.println("time = " + r.hhmmss);
                System.out.println("time = " + time);
                rideTimes.add(time);
            }

        }

        /*
        ArrayList<Ride> rides=RidesDB.get(getApplicationContext()).getRidesDB();
        rideListStrings.clear(); // clear so that we donøt have both the old and the new in this list.
        for(Ride r : rides) {
            rideListStrings.add(r.toString());
            //W5
            String day = String.valueOf(r.getDay());
            rideDates.add(day);
            System.out.println("sdf");
        }
        buckysAdapter.notifyDataSetChanged();
        updateUI(currentString);
        */

        ListView buckysListView = findViewById(R.id.listView1);
        buckysListView.setAdapter(buckysAdapter);

        ListView buckysListView2 = findViewById(R.id.listView2);
        buckysListView2.setAdapter(buckysAdapter2);

        ListView buckysListView3 = findViewById(R.id.listView3);
        buckysListView3.setAdapter(buckysAdapter3);

    }

    //NDB
    public void setCrimes(List<RideMine> rides) {
        mRides = rides;
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
