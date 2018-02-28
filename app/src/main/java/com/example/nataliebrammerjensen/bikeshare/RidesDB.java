package com.example.nataliebrammerjensen.bikeshare;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nataliebrammerjensen on 14/02/2018.
 */

public class RidesDB { // Singleton
    private static RidesDB sRidesDB;

    public static RidesDB get(Context context) {
        if (sRidesDB == null) {
            sRidesDB= new RidesDB(context);
        }
        return sRidesDB;
    }

    private ArrayList<Ride> mallRides;
    private Ride mlastRide = new Ride("", "", "");

    public List<Ride> getRidesDB() {
        return mallRides;
    }

    public void addRide(String what, String where) {
        mallRides.add(new Ride(what, where, where));
    }

    public void endRide(String what, String where) {
        mallRides.add(new Ride(what, where, where));
    }

    private RidesDB(Context context) {
        mallRides= new ArrayList<>();
        // Add some rides for testing purposes
        mallRides.add(new Ride("Peters bike", "ITU", "Fields"));
        mallRides.add(new Ride("Peters bike", "Fields", "Kongens Nytorv"));
        mallRides.add(new Ride("Jørgens bike", "Home", "ITU"));
    }
}
