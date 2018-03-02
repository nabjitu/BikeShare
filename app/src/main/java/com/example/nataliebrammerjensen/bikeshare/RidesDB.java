package com.example.nataliebrammerjensen.bikeshare;

import android.content.Context;
import android.util.Log;

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

    public ArrayList<Ride> getRidesDB() {
        return mallRides;
    }

    public void addRide(String what, String from, String to) {
        mallRides.add(new Ride(what, from, to));
    }

    public void addOneRide(Ride newbie) {
        mallRides.add(newbie);
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

    public Ride getRide(String uniqueName){
        for (Ride r : mallRides){
            if (r.getMbikeName().equals(uniqueName)) {
                return r;
            }
            else return null;
        }
        return null;
    }

    public Ride getLast(){
        return mallRides.get(mallRides.size() - 1);
    }

    public void replaceLast(Ride substitute){
        mallRides.remove(mallRides.size() - 1);
        mallRides.add(substitute);
        Log.d("ici",mallRides.get(mallRides.size()-1).toString());
    }
}
