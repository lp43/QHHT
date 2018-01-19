package com.qhhtofficial.qhht.module;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by ehs_app_1 on 2018/1/19.
 */

public class TimeConsumeManager {
    private static final String TAG = TimeConsumeManager.class.getSimpleName();

    private Context mContext;
    private static TimeConsumeManager mTimeComsumeManager;
    public static int timeTotalMins = 0;

    ArrayList<Integer> timeMarks = new ArrayList<>();

    public static TimeConsumeManager getInstance(){
        if(mTimeComsumeManager==null){
            mTimeComsumeManager = new TimeConsumeManager();
        }
        return mTimeComsumeManager;
    }

    public void addMinute(int min){
        timeTotalMins ++;
    }

    public void addTimeMark(){
        timeMarks.add(getTimesTotal());
    }
    public ArrayList<Integer> getTimeMarks(){
        return timeMarks;
    }

    public int getTimesTotal(){
        return timeTotalMins;
    }

    public String convertToHHMM(int totalMins){
        if(totalMins<0)return "";

        int hours = totalMins / 60; //since both are ints, you get an int
        int minutes = totalMins % 60;
        return String.format("%d:%02d", hours, minutes);
    }

    public void reset(){
        timeTotalMins = 0;
        timeMarks.clear();
    }
}
