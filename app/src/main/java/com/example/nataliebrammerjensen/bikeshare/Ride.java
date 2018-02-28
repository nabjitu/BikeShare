package com.example.nataliebrammerjensen.bikeshare;

/**
 * Created by nataliebrammerjensen on 08/02/2018.
 */



public class Ride {
    private String mbikeName;
    private String mstartRide;
    private String mstopRide;

//    public Ride(String name, String start) {
//        mbikeName = name;
//        mstartRide = start;
//    }

    public Ride(String name, String start, String stop) {
        mbikeName= name;
        mstartRide= start;
        mstopRide = stop;
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

    public String toString() {
        return mbikeName+" went from: " + mstartRide + " to " + mstopRide;
    }
}
