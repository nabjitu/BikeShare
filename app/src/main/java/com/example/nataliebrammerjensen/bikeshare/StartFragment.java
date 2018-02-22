package com.example.nataliebrammerjensen.bikeshare;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static android.app.Activity.RESULT_OK;

/**
 * Created by nataliebrammerjensen on 21/02/2018.
 */

public class StartFragment extends Fragment {
    private Button addRide;
    private TextView lastAdded;
    private TextView newWhat, newWhere;

    private Ride last= new Ride("", "");


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_start, container, false);

        lastAdded = (TextView) v.findViewById(R.id.last_added);
        updateUI();

        // Button
        addRide = (Button) v.findViewById(R.id.add_button);

        // Texts
        newWhat = (TextView) v.findViewById(R.id.what_text);
        newWhere =(TextView) v.findViewById(R.id.where_edit);

        // view products click event
        addRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((newWhat.getText().length()>0) && (newWhere.getText().length()>0 )){
                    last.setMbikeName(newWhat.getText().toString().trim());
                    last.setMstartRide(newWhere.getText().toString().trim());

                    //HFM
                    Intent data=new Intent();
                    data.setData(Uri.parse(last.toString()));
                    getActivity().setResult(RESULT_OK, data);

                    // reset text fields
                    newWhat.setText(""); newWhere.setText("");
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
