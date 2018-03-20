package com.example.nataliebrammerjensen.bikeshare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.nataliebrammerjensen.bikeshare.database.RideBaseHelper;
import com.example.nataliebrammerjensen.bikeshare.database.RideCursorWrapper;
import com.example.nataliebrammerjensen.bikeshare.database.RideDBSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by nataliebrammerjensen on 14/02/2018.
 */

public class RidesDB { // Singleton
    private static RidesDB sRidesDB;

    private static SQLiteDatabase mDatabase;
    private Context mContext;

    public static RidesDB get(Context context) {
        if (sRidesDB == null) {
            sRidesDB= new RidesDB(context);
        }

        return sRidesDB;
    }

    private static ContentValues getContentValues(Ride ride) {
        ContentValues values = new ContentValues();
        values.put(RideDBSchema.RideTable.Cols.UUID, ride.getId().toString());
        values.put(RideDBSchema.RideTable.Cols.BIKENAME, ride.getMbikeName());
        values.put(RideDBSchema.RideTable.Cols.START, ride.getMstartRide());
        values.put(RideDBSchema.RideTable.Cols.STOP, ride.getMstopRide());
        return values;
    }

    private ArrayList<Ride> mallRides;
    private Ride mlastRide = new Ride("", "", "");

    public ArrayList<Ride> getRidesDB() {
        return mallRides;
    }

    public void addRide(String what, String from, String to) {
        mallRides.add(new Ride(what, from, to));
    }

    //NDB
    public void addRide(Ride r) {
        ContentValues values = getContentValues(r);
        mDatabase.insert(RideDBSchema.RideTable.NAME, null, values);
    }

    public void updateRide(Ride ride) {
        String uuidString = ride.getId().toString();
        ContentValues values = getContentValues(ride);
        mDatabase.update(RideDBSchema.RideTable.NAME, values, RideDBSchema.RideTable.Cols.UUID + " = ?", new String[] { uuidString });
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
//        mallRides.add(new Ride("Peters bike", "ITU", "Fields"));
//        mallRides.add(new Ride("Peters bike", "Fields", "Kongens Nytorv"));
//        mallRides.add(new Ride("Jørgens bike", "Home", "ITU"));

        //n
        mContext = context.getApplicationContext();
        mDatabase = new RideBaseHelper(mContext).getWritableDatabase();
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

    /*public Ride getLast(){
        queryRides()
        if(mallRides.size() > 0) {
            return mallRides.get(mallRides.size() - 1);
        } else {
            System.out.println("table is empty");
            return null;
        }
    }*/

    public void replaceLast(Ride substitute, UUID old){
        //NDB
        /*for(Ride r : getAllRides()){
            if (r.getId().equals(old)){
                //Modify ebtry and add a end attribute ti the ride.
                mDatabase.update(RideDBSchema.RideTable.NAME, getContentValues(r), "UUID ="+old, null ); //Her er jeg nået til. JEg mangler at finde ud af hvad der skal stå på null's plads.
            }
        }


        mallRides.remove(mallRides.size() - 1);
        mallRides.add(substitute);
        Log.d("ici",mallRides.get(mallRides.size()-1).toString());*/

        //HFM19.30.1
        //Prepared statement to avid sql injection
        mDatabase.update(RideDBSchema.RideTable.NAME, getContentValues(substitute), "uuid =?", new String[]{old.toString()} );
    }

    private RideCursorWrapper queryRides(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(RideDBSchema.RideTable.NAME,
            null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null  // orderBy
        );
        return new RideCursorWrapper(cursor);
    }

    public ArrayList<Ride> getAllRides() {
        ArrayList<Ride> rides = new ArrayList<>();
        RideCursorWrapper cursor = queryRides(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                rides.add(cursor.getCrime());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return rides;
    }

    public Ride getRIde(UUID id) {
        RideCursorWrapper cursor = queryRides(

        RideDBSchema.RideTable.Cols.UUID + " = ?",
                new String[] { id.toString() }
        );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getCrime();
        } finally {
            cursor.close();
        }
    }
}
