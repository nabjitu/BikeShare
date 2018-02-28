package com.example.nataliebrammerjensen.bikeshare;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * Created by nataliebrammerjensen on 08/02/2018.
 */

//Here is a skeleton for the BikeShareActivity.java:
public class StartRideActivity extends AppCompatActivity { // GUI variables
    private Button addRide;
    private TextView lastAdded;
    private TextView newWhat, newWhere;

    private Ride last= new Ride("", "", "");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout. activity_start);

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
//                    //HFM
//                    Intent data=new Intent();
//                    data.setData(Uri.parse(last.toString()));
//                    setResult(RESULT_OK, data);
//
//                    // reset text fields
//                    newWhat.setText(""); newWhere.setText("");
//                    updateUI();
//
//                    //HFM
//                    finish();
//                }
//            }
//        });

        FragmentManager fm = getSupportFragmentManager();

        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = new StartFragment();
            fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }


    }
    private void updateUI(){
        lastAdded.setText(last.toString());
    }
}
