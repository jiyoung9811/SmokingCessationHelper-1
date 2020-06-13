package com.example.smokingcessationhelper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.prolificinteractive.materialcalendarview.CalendarDay;

public class DateChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        CalendarDay today = CalendarDay.today();
        CalendarDay yesterday = CalendarDay.from(today.getYear(), today.getMonth(), today.getDay() - 1);
        CalendarFragment.updateDB(yesterday);
        CalendarFragment.updateCalendarData();
        CalendarFragment.updateView();
        CalendarFragment.initSuccessed();
    }
}
