package com.example.nataliebrammerjensen.bikeshare.ridesBrowser;

import android.content.Context;
import android.widget.Toast;

import com.example.nataliebrammerjensen.bikeshare.RideMine;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class RidesDB extends Observable {   // Singleton
  private static RidesDB sRidesDB;

  public static RidesDB get(Context context) {
    if (sRidesDB == null) { sRidesDB= new RidesDB(context); }
    return sRidesDB;
  }

  private RidesDB(Context context) {
    mallRides= new ArrayList<>();

    // Add some rides for testing purposes
    mallRides.add(new RideMine("Peters bike", "ITU", "Fields"));
    mallRides.add(new RideMine("Peters bike", "Fields", "Kongens Nytorv"));
    mallRides.add(new RideMine("Jørgens bike", "Home", "ITU"));
  }

  private List<RideMine> mallRides;

  public List<RideMine> getRidesDB() { return mallRides; }

  public void delete(RideMine r, Context c) {
    mallRides.remove(r);
    Toast.makeText(c, "Deleted ride " + r.getMbikeName(), Toast.LENGTH_LONG).show();
    this.setChanged();
    notifyObservers();
  }
}