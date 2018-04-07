package com.example.nataliebrammerjensen.bikeshare.ridesBrowser;

import java.text.SimpleDateFormat;

public class Ride {
    private String mbikeName;
    private String mstartRide;
    private String mendRide;

    //N
    private String mStartddmmyyyy;
    String mStarthhmmss;


    public Ride(String name, String start) {
        mbikeName= name;
        mstartRide= start;

        //N
        mStartddmmyyyy = new SimpleDateFormat("dd.MM.yyyy").format(new java.util.Date());
        mStarthhmmss = new SimpleDateFormat("HH:mm:ss").format(new java.util.Date());
    }

    public Ride(String name, String startRide, String endRide) { // mainly for testing
        mbikeName= name;
        mstartRide= startRide;

        mendRide= endRide;

        //N
        mStartddmmyyyy = new SimpleDateFormat("dd.MM.yyyy").format(new java.util.Date());
        mStarthhmmss = new SimpleDateFormat("HH:mm:ss").format(new java.util.Date());
    }

    public String getMbikeName() {
        return mbikeName;
    }

    public void setMbikeName(String mbikeName) {
        this.mbikeName = mbikeName;
    }

    public String getMstartRide() {
        return mstartRide;
    }

    public void setMstartRide(String mstartRide) {
        this.mstartRide = mstartRide;
    }

    public String getMendRide() { return mendRide; }

    public void setMendRide(String mendRide) { this.mendRide = mendRide; }

    public String toString(String delim) { return mbikeName + delim + mstartRide ; }

    public String toString() { return mbikeName + " started: " + mstartRide + " ended: " + mendRide; }

    public String getmStartddmmyyyy() {
        return mStartddmmyyyy;
    }

    public String getmStarthhmmss() {
        return mStarthhmmss;
    }
}
