package com.example.nataliebrammerjensen.bikeshare;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.UUID;

import static android.app.Activity.RESULT_OK;

/**
 * Created by nataliebrammerjensen on 02/03/2018.
 */


public class EndFragment extends Fragment {

    private Button addRide;
    private TextView lastAdded;
    private TextView newWhat, newWhere;

    private Ride last= new Ride("", "", "");

    //tags
    private static final String EXTRA_RIDES_DB = "com.bignerdranch.android.geoquiz.rides_DB";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_end, container, false);

        lastAdded = (TextView) v.findViewById(R.id.last_added_end);
        updateUI();

        // Button
        addRide = (Button) v.findViewById(R.id.add_button_end);

        // Texts
        newWhat = (TextView) v.findViewById(R.id.what_text_end);
        newWhere = (TextView) v.findViewById(R.id.where_edit_end);


        //get data from intent fra mainactivity. Jeg skal bruge UUID
        //NDB
        //Intent data = new Intent();
        //UUID uuidFromMain = UUID.fromString(data.getData().toString());

        //ANDB
        String uuidFromMain = getActivity().getIntent().getExtras().getString("UUIDNUMBER");
        System.out.println(uuidFromMain);
        UUID uuidUUID = UUID.fromString(uuidFromMain);
        final Ride newRide = RidesDB.get(getActivity().getApplicationContext()).getRIde(uuidUUID);

        if (newRide.getMstopRide().equals("")) {
            newWhat.setText(newRide.getMbikeName());
            newWhat.setEnabled(false);
        }
        // view products click event
        addRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((newWhat.getText().length() > 0) && (newWhere.getText().length() > 0)) {
                    last.setMbikeName(newWhat.getText().toString().trim());
                    last.setMstartRide(newWhere.getText().toString().trim());

                    //NewStuff
                    // Adding to Db instead of HFM
                    //Ride last = RidesDB.get(getActivity().getApplicationContext()).getLast();
                    newRide.setMendRide(newWhere.getText().toString().trim());
                    RidesDB rdb = RidesDB.get(getActivity().getApplicationContext());
                    rdb.replaceLast(newRide, newRide.getId());

                    //change
//                    RidesDB rdb = RidesDB.get(getApplicationContext());
//                    Riderdb.getLast();
//                    current = null;

                    // /HFM
                    /*Intent data = new Intent();
                    data.setData(Uri.parse(last.toString()));
                    getActivity().setResult(RESULT_OK, data);*/

                    //NDB
                    Intent data = new Intent();
                    data.setData(Uri.parse(newRide.getId().toString()));
                    getActivity().setResult(RESULT_OK, data);

                    // reset text fields
                    newWhat.setText("");
                    newWhere.setText("");
                    updateUI();

                    //HFM
                    getActivity().finish();
                }
            }
        });
        return v;
    }

    private void updateUI(){
        lastAdded.setText(last.toString());
    }
}
