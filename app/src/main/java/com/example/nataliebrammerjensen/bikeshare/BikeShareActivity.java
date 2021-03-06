package com.example.nataliebrammerjensen.bikeshare;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by nataliebrammerjensen on 08/02/2018.
 */

//Here is a skeleton for the BikeShareActivity.java:
public class BikeShareActivity extends Activity { // GUI variables
    private Button addRide;
    private TextView lastAdded;
    private TextView newWhat, newWhere;

    private Ride last= new Ride("", "", "");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout. activity_bike_share);

        lastAdded = (TextView) findViewById(R.id.last_added);
        updateUI();

        // Button
        addRide = (Button) findViewById(R.id.add_button);

        // Texts
        newWhat = (TextView) findViewById(R.id.what_text);
        newWhere =(TextView) findViewById(R.id.where_edit);

        // view products click event
        addRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((newWhat.getText().length()>0) && (newWhere.getText().length()>0 )){
                    last.setMbikeName(newWhat.getText().toString().trim());
                    last.setMstartRide(newWhere.getText().toString().trim());

                    // reset text fields
                    newWhat.setText(""); newWhere.setText(""); updateUI();
                }
            }
        });
    }
    private void updateUI(){
        lastAdded.setText(last.toString());
    }
}