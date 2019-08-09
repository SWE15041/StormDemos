package com.lyn.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalenderTest {
    public static void main(String[] args) throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date current = dateFormat.parse("2019-07-30 11:20:00");

//        Date current = new Date();
        System.out.println(current);
        Date formatTime = getFormatTime(current, 122);
        System.out.println(formatTime);
    }

    public static Date getFormatTime(Date time, int interval) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        calendar.set(Calendar.MILLISECOND, 0);
        if (interval < 60) {
            minute = minute / interval * interval;
        } else {
            int hour_interval = interval / 60;
            int minute_interval = interval - hour_interval * 60;
            hour = hour / hour_interval * hour_interval;
            if (minute_interval == 0)
                minute = 0;
            else
                minute = minute / minute_interval * minute_interval;
        }
        calendar.set(year, month, day, hour, minute, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}
