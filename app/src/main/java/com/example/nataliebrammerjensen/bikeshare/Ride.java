package com.example.nataliebrammerjensen.bikeshare;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    //W5
    Date currentDate;
    int day;
    int hour;
    int minutes;
    int second;

    String hhmmss;

    public Ride(String name, String start) {
        mbikeName = name;
        mstartRide = start;

        mId = UUID.randomUUID();

        Calendar rightNow = Calendar.getInstance();
        int day = rightNow.get(Calendar.DAY_OF_MONTH);
        int hour = rightNow.get(Calendar.HOUR_OF_DAY);
        int minutes = rightNow.get(Calendar.MINUTE);
        int second = rightNow.get(Calendar.SECOND);

        currentDate = new Date();
        long time = currentDate.getTime();
        msdf = new SimpleDateFormat("dd.MM.yyyy").format(new java.util.Date());
        hhmmss = new SimpleDateFormat("HH:mm:ss").format(new java.util.Date());
    }

    public UUID getId() {
        return mId;
    }

    public String getHhmmss() {
        return hhmmss;
    }

    public Ride() {
        mId = UUID.randomUUID();

        //W5
        currentDate = new Date();
        long time = currentDate.getTime();
        Calendar rightNow = Calendar.getInstance();
        int day = rightNow.get(Calendar.DAY_OF_MONTH);
        int hour = rightNow.get(Calendar.HOUR_OF_DAY);
        int minutes = rightNow.get(Calendar.MINUTE);
        int second = rightNow.get(Calendar.SECOND);
        msdf = new SimpleDateFormat("dd.MM.yyyy").format(new java.util.Date());
        hhmmss = new SimpleDateFormat("HH:mm:ss").format(new java.util.Date());
    }

    public Ride(UUID id) {
        mId = id;

        //W5
        Calendar rightNow = Calendar.getInstance();
        int day = rightNow.get(Calendar.DAY_OF_MONTH);
        int hour = rightNow.get(Calendar.HOUR_OF_DAY);
        int minutes = rightNow.get(Calendar.MINUTE);
        int second = rightNow.get(Calendar.SECOND);

        currentDate = new Date();
        long time = currentDate.getTime();
        msdf = new SimpleDateFormat("dd.MM.yyyy").format(new java.util.Date());
        hhmmss = new SimpleDateFormat("HH:mm:ss").format(new java.util.Date());
    }

    public int getDay() {
        return day;
    }
    public int getHour() {
        return hour;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSecond() {
        return second;
    }

    public String getMsdf() {
        return msdf;
    }

    public Ride(String name, String start, String stop) {
        mbikeName= name;
        mstartRide= start;
        mstopRide = stop;


        //W5
        Calendar rightNow = Calendar.getInstance();
        int day = rightNow.get(Calendar.DAY_OF_MONTH);
        int hour = rightNow.get(Calendar.HOUR_OF_DAY);
        int minutes = rightNow.get(Calendar.MINUTE);
        int second = rightNow.get(Calendar.SECOND);


        Date currentDate = new Date();
        long time = currentDate.getTime();
        msdf = new SimpleDateFormat("dd.MM.yyyy").format(new java.util.Date());
        hhmmss = new SimpleDateFormat("HH:mm:ss").format(new java.util.Date());
    }
    //NDB
    public Ride(UUID id, String name, String start, String stop) {
        mId = id;
        mbikeName= name;
        mstartRide= start;
        mstopRide = stop;

        //W5
        Calendar rightNow = Calendar.getInstance();
        int day = rightNow.get(Calendar.DAY_OF_MONTH);
        int hour = rightNow.get(Calendar.HOUR_OF_DAY);
        int minutes = rightNow.get(Calendar.MINUTE);
        int second = rightNow.get(Calendar.SECOND);


        Date currentDate = new Date();
        long time = currentDate.getTime();
        msdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new java.util.Date());
        hhmmss = new SimpleDateFormat("HH:mm:ss").format(new java.util.Date());
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
