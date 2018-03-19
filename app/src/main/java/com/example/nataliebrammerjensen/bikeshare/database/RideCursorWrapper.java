package com.example.nataliebrammerjensen.bikeshare.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.nataliebrammerjensen.bikeshare.Ride;

import java.util.UUID;

/**
 * Created by nataliebrammerjensen on 14/03/2018.
 */

public class RideCursorWrapper extends CursorWrapper {

    public RideCursorWrapper(Cursor cursor) {
    super(cursor);
    }

    public Ride getCrime() {
        String uuidString = getString(getColumnIndex(RideDBSchema.RideTable.Cols.UUID));
        String bikeName = getString(getColumnIndex(RideDBSchema.RideTable.Cols.BIKENAME));
        String start = getString(getColumnIndex(RideDBSchema.RideTable.Cols.START));
        String stop = getString(getColumnIndex(RideDBSchema.RideTable.Cols.STOP));

        Ride ride = new Ride(UUID.fromString(uuidString));
        ride.setMbikeName(bikeName);
        ride.setMstartRide(start);
        ride.setMendRide(stop);
        return ride;
    }
}