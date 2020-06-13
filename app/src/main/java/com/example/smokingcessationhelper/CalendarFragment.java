package com.example.smokingcessationhelper;

import android.content.Context;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.HashSet;

public class CalendarFragment extends Fragment {
    private static Context mContext = null;
    private static MaterialCalendarView mcvNoSmokingCalendar = null;
    private static Button btNoSmokingStart = null;
    private static HashSet<CalendarDay> successedDays = new HashSet<>();
    private static HashSet<CalendarDay> failedDays = new HashSet<>();

    private static boolean successed = true;

    public CalendarFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        mcvNoSmokingCalendar = view.findViewById(R.id.CalendarFragment_mcvNoSmokingCalendar);
        btNoSmokingStart = view.findViewById(R.id.CalendarFragment_btNoSmokingStart);
        mContext = getContext();

         /*
        * successedDates는 금연 성공 날짜, failedDates는 금연 실패 날짜이다.

        프로그램 동작
        * 켜질 시 firebase로부터 금연 시도 여부와 날마다의 금연 성공 여부 데이터를 얻어온다
        * 만약 금연 시도중이면 btNoSmokingStart를 볼 수 없게 만든다
        * successedDays와 failedDays에 아이템을 add한다  (updateCalendarData())
        * Decorator를 붙인다 (updateView())
        * 이벤트 발생(흡연)시 오늘을 failedDays에 추가하고 Decorator를 재적용한다. 이후 firebase에 금연 실패 데이터를 저장한다.  - 추후 구현
        * 날짜 변경이 감지되면 어제를 successedDays에 추가하고 Decorator를 재적용한다. 이후 firebase에 금연 성공 데이터를 저장한다.
         */

         // 금연 시도 여부 검사
        //  if (금연 중) btNoSmokingStart.setVisibility(View.INVISIBLE);
        updateCalendarData();
        updateView();

        btNoSmokingStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //DB에 금연시작 여부를 알 수 있는 데이터 저장
                btNoSmokingStart.setVisibility(View.INVISIBLE);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateCalendarData();
        updateView();
    }

    public static void initSuccessed() {
        successed = true;
    }

    public static void updateDB(CalendarDay day) {
        // DB에 어제에 대한 데이터가 등록되어있는지 검사
        // DB에 데이터 등록
        if (successed)
            successedDays.add(day); // <- 지워야함, 테스트용
        else
            failedDays.add(day);
    }

    public static void updateCalendarData()
    {
        /*
        successedDays.clear();
        failedDays.clear();

        DB에서 데이터 가져와서 successedDays와 failedDays에 저장
        successedDays.add(CalendarDay.from(2020,4,1));
        failedDays.add(CalendarDay.from(2020,4,2));
         */
    }

    public static void updateView() {
        if (mContext == null || mcvNoSmokingCalendar == null) {
            return;
        }

        try {
        mcvNoSmokingCalendar.removeDecorators();
        mcvNoSmokingCalendar.invalidateDecorators();
        mcvNoSmokingCalendar.addDecorator(new SuccessedDaysDecorator(mContext, successedDays));
        mcvNoSmokingCalendar.addDecorator(new FailedDaysDecorator(mContext, failedDays));
        } catch (Exception e) { return; }

        return;
    }

}
