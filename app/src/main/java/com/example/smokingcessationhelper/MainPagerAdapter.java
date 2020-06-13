package com.example.smokingcessationhelper;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class MainPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> mData;
    private CalendarFragment calendarFragment = new CalendarFragment();
    public MainPagerAdapter(FragmentManager fm) {
        super(fm);

        mData = new ArrayList<>();
        mData.add(new MainFragment());
        mData.add(calendarFragment);
        mData.add(new ContactFragment());
    }

    public boolean isCalendarFragmentPosition(int position) { return mData.get(position) == calendarFragment;}
    @Override
    public Fragment getItem(int position) {
        return mData.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }
}
