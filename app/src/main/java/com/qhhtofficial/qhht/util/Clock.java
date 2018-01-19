package com.qhhtofficial.qhht.util;

import java.util.*;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.SystemClock;
import android.text.format.Time;

/**
 * Created by ehs_app_1 on 2017/11/28.
 */

public class Clock {

    private Context mContext;
    private Time mTime;
    private Handler mHandler;
    private List<OnClockTickListner> clockTickListeners = new ArrayList<OnClockTickListner>();

    private Runnable ticker;

    private BroadcastReceiver intentReceiver;
    private IntentFilter intentFilter;



    public Clock(Context context)
    {
        this(context, TICK_METHOD.minute);
    }
    public Clock(Context context,TICK_METHOD method)
    {
        this.mContext =context;
        this.mTime =new Time();
        this.mTime.setToNow();

        if(method == TICK_METHOD.day){
            startTickPerDay();
        }
        else if(method == TICK_METHOD.minute){
            startTickPerMinute();
        }
        else{
            startTickPerSecond();
        }
    }

    private void tick(long tickInMillis)
    {
        Clock.this.mTime.set(Clock.this.mTime.toMillis(true)+tickInMillis);
        this.notifyOnTickListners();
    }

    private void notifyOnTickListners()
    {
        for(OnClockTickListner listner: clockTickListeners)
        {
            listner.onClockTick(mTime);
        }
    }

    private void startTickPerSecond()
    {
        this.mHandler =new Handler();
        this.ticker = new Runnable()
        {
            public void run()
            {
                tick(1000);
                long now = SystemClock.uptimeMillis();
                long next = now + (1000 - now % 1000);
                mHandler.postAtTime(ticker, next);
            }
        };
        this.ticker.run();

    }

    private void startTickPerMinute()
    {
        this.intentReceiver = new BroadcastReceiver()
        {
            @Override
            public void onReceive(Context context, Intent intent)
            {
                tick(60_000);

            }
        };
        this.intentFilter = new IntentFilter();
        this.intentFilter.addAction(Intent.ACTION_TIME_TICK);
        this.mContext.registerReceiver(this.intentReceiver, this.intentFilter, null, this.mHandler);

    }

    private void startTickPerDay()
    {
        this.intentReceiver = new BroadcastReceiver()
        {
            @Override
            public void onReceive(Context context, Intent intent)
            {
                tick( 24/*時*/ * 60/*分*/ * 60_000/*秒*/);

            }
        };
        this.intentFilter = new IntentFilter();
        this.intentFilter.addAction(Intent.ACTION_DATE_CHANGED);
        this.mContext.registerReceiver(this.intentReceiver, this.intentFilter, null, this.mHandler);

    }

    public void stopTick()
    {
        if(this.intentReceiver !=null)
        {
            if(this.mContext!=null) this.mContext.unregisterReceiver(this.intentReceiver);
        }
        if(this.mHandler !=null)
        {
            this.mHandler.removeCallbacks(this.ticker);
        }
    }

    public Time getCurrentTime()
    {
        return this.mTime;
    }

    public void addOnClockTickListner(OnClockTickListner listner)
    {
        this.clockTickListeners.add(listner);

    }

    public enum TICK_METHOD{
        second, minute, day
    }
    public interface OnClockTickListner {
        void onClockTick(Time currentTime);
    }
}
