package com.example.smokingcessationhelper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.MainActivity_viewPager);
        final MainPagerAdapter mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mainPagerAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }
            @Override
            public void onPageSelected(int position) {
                if (mainPagerAdapter.isCalendarFragmentPosition(position));
                    CalendarFragment.updateView();
            }
            @Override
            public void onPageScrollStateChanged(int state) { }
        });
        BroadcastReceiver broadcastReceiver = new DateChangeReceiver();
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_DATE_CHANGED);
        registerReceiver(broadcastReceiver, intentFilter);
    }

}
