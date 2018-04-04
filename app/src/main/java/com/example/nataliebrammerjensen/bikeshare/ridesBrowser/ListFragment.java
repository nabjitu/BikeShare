package com.example.nataliebrammerjensen.bikeshare.ridesBrowser;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nataliebrammerjensen.bikeshare.R;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ListFragment extends Fragment implements Observer {
  private static RidesDB sharedRides;

  //View to list all rides
  private RecyclerView mRidesList;
  private RidesAdapter mAdapter;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    sharedRides = RidesDB.get(getActivity());
    sharedRides.addObserver(this);
  }

  @Override
  public void update(Observable observable, Object data) {
    //updateListOfThings();
    mAdapter.notifyDataSetChanged();
  }


  //Det her er ligsom en CrimeHolder
  public class RideHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView mWhatTextView, mStartTextView, mEndTextView;
    private TextView mFromTextView;
    private TextView mToTextView;
    private TextView mDateTextView;
    private TextView mStartTimeTextView;
    private TextView mEndTimeTextView;

    public Ride mRide;

    public RideHolder(LayoutInflater inflater, ViewGroup parent){
      super(inflater.inflate(R.layout.list_item, parent, false));

      // TODO set views
      View iv = inflater.inflate(R.layout.list_item, parent, false); //iv = itemView
      //Side 182
      //mWhatTextView = (TextView) iv;

      //Stackoverflow
      /*LinearLayout ll = (LinearLayout) view; // get the parent layout view
      TextView tv = (TextView) ll.findViewById(R.id.contents); // get the child text view
      final String text = tv.getText().toString();*/

      //N
      mWhatTextView = (TextView) iv.findViewById(R.id.what_bike_ride);
      mFromTextView = (TextView) iv.findViewById(R.id.where_from_ride);
      mToTextView = (TextView) iv.findViewById(R.id.where_to_ride);
      mDateTextView = (TextView) iv.findViewById(R.id.date_of_ride);
      mStartTimeTextView = (TextView) iv.findViewById(R.id.time_of_start);
      mEndTimeTextView = (TextView) iv.findViewById(R.id.time_of_end);

      iv.setOnClickListener(this);
    }

    public void bind(Ride ride){
      mRide= ride;
      // TODO set contents of a row
      //Side 189 When given a Crime, RideHolder will now update the title TextView, date TextView, and solved CheckBox to reflect the state of the Crime.
      mFromTextView.setText(mRide.getMstartRide());
      mDateTextView.setText(mRide.getmStartddmmyyyy());
      mStartTimeTextView.setText(mRide.getmStarthhmmss());
      //mEndTimeTextView.setText(mRide.getHhmmss());
    }

    @Override
    public void onClick(View v) {
        sharedRides.delete(mRide, getActivity());
    }
  }

  private class RidesAdapter extends RecyclerView.Adapter<RideHolder> {
    private List<Ride> mRides;

    public RidesAdapter(List<Ride> rides){
      mRides= rides;
    }

    //Side 188
    @Override
    public RideHolder onCreateViewHolder(ViewGroup parent, int viewType){
      LayoutInflater layoutInflater= LayoutInflater.from(getActivity());
      return new RideHolder(layoutInflater, parent);
     }

    @Override
    public void onBindViewHolder(RideHolder holder, int position) {
      Ride ride= mRides.get(position);
      holder.bind(ride);
    }

    @Override
    public int getItemCount() {
      return mRides.size();
    }
  }


  private RecyclerView mCrimeRecyclerView;
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View v= inflater. inflate(R.layout.fragment_list, container, false);
    mRidesList= v.findViewById(R.id.list_recycler_view);
    mRidesList.setLayoutManager(new LinearLayoutManager(getActivity()));


    // TODO set up adapter
    //Muligvis sådan her
    //ListRow row = new ListRow(inflater.inflate(R.layout.list_row, parent, false));
    //View view = row.itemView;
    //ImageView thumbnailView = row.mThumbnail;

    mCrimeRecyclerView = (RecyclerView) v.findViewById(R.id.list_recycler_view);
    mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    updateUI();

    return v;
  }

  //Bogen siger den her er vigtig
  private void updateUI() {
    RidesDB crimeLab = RidesDB.get(getActivity());
    List<Ride> crimes = crimeLab.getRidesDB();
    mAdapter = new RidesAdapter(crimes);
    mCrimeRecyclerView.setAdapter(mAdapter);
  }

}
