package com.example.nataliebrammerjensen.bikeshare;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by nataliebrammerjensen on 08/02/2018.
 */

public class Ride implements Serializable{
    private String mbikeName;
    private String mstartRide;
    private String mstopRide;
    private String msdf;
    //NDB
    private UUID mId;

    public Ride(String name, String start) {
        mbikeName = name;
        mstartRide = start;
    }

    public UUID getId() {
        return mId;
    }

    public Ride() {
        mId = UUID.randomUUID();

        //W5
        Date currentDate = new Date();
        long time = currentDate.getTime();
        msdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new java.util.Date());
    }

    public Ride(UUID id) {
        mId = id;

        //W5
        Date currentDate = new Date();
        long time = currentDate.getTime();
        msdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new java.util.Date());
    }

    public String getMsdf() {
        return msdf;
    }

    public Ride(String name, String start, String stop) {
        mbikeName= name;
        mstartRide= start;
        mstopRide = stop;


        //W5
        Date currentDate = new Date();
        long time = currentDate.getTime();
        msdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new java.util.Date());
    }
    //NDB
    public Ride(UUID id, String name, String start, String stop) {
        mId = id;
        mbikeName= name;
        mstartRide= start;
        mstopRide = stop;

        //W5
        Date currentDate = new Date();
        long time = currentDate.getTime();
        msdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new java.util.Date());
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
