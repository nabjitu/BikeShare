package com.example.nataliebrammerjensen.bikeshare;

import java.io.Serializable;

/**
 * Created by nataliebrammerjensen on 08/02/2018.
 */



public class Ride implements Serializable{
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
    public String getMstopRide() {
        return mstopRide;
    }

    public void setMendRide(String end) {
         mstopRide=end;
    }

    public void setMstartRide(String mstartRide) {
        this.mstartRide = mstartRide;
    }

    public String toString() {
        return mbikeName+" went from: " + mstartRide + " to " + mstopRide;
    }

    public String toStringStart() {
        return mbikeName+" went from: " + mstartRide;
    }
}
