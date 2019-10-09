package com.kevin.util;

import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public final class DateUtils {
    public static final String YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYYMMDD = "yyyy-MM-dd";
    public static final String YYYYMMDDHHMM = "yyyy-MM-dd HH:mm";
    public static final String YYYYMM = "yyyy-MM";

    public DateUtils() {
    }

    public static String getCurrentDate(String var1) {
        return (new SimpleDateFormat(var1)).format(new Date());
    }

    public static Date parse(String date) {
        if (StringUtils.isEmpty(date)) {
            return null;
        } else {
            try {
                return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(date);
            } catch (ParseException var2) {
                return null;
            }
        }
    }

    public static Date parse(String date, String format) {
        if (StringUtils.isEmpty(date)) {
            return null;
        } else {
            try {
                return (new SimpleDateFormat(format)).parse(date);
            } catch (ParseException var3) {
                return null;
            }
        }
    }

    public static Date parseToYMD(String date) {
        try {
            return (new SimpleDateFormat("yyyy-MM-dd")).parse(date);
        } catch (ParseException var2) {
            return null;
        }
    }

    public static String format(Date date) {
        return date != null ? (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(date) : "/";
    }

    public static String format(Date date, String format) {
        return (new SimpleDateFormat(format)).format(date);
    }

    public static Date addYear(Date date, int year) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(1, year);
        date = calendar.getTime();
        return date;
    }

    public static Date addMonth(Date date, int month) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(2, month);
        date = calendar.getTime();
        return date;
    }

    public static Date addDay(Date date, int day) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(5, day);
        date = calendar.getTime();
        return date;
    }

    public static Date addHour(Date date, int day) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(10, day);
        date = calendar.getTime();
        return date;
    }

    public static Date addMinute(Date date, int day) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(12, day);
        date = calendar.getTime();
        return date;
    }

    public static Date addWeek(Date date, int day) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(5, day);
        date = calendar.getTime();
        return date;
    }

    public static Long getTimestamp() {
        return System.currentTimeMillis();
    }

    public static Map<String, String> getCurrentWeek(boolean addEndDay) {
        Map<String, String> map = new HashMap(2);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(2);
        int dayWeek = cal.get(7);
        if (dayWeek == 1) {
            dayWeek = 8;
        }

        cal.add(5, cal.getFirstDayOfWeek() - dayWeek);
        Date mondayDate = cal.getTime();
        String weekBegin = sdf.format(mondayDate);
        map.put("beginWeek", weekBegin);
        cal.add(5, 4 + cal.getFirstDayOfWeek());
        if (addEndDay) {
            cal.add(5, 1);
        }

        Date sundayDate = cal.getTime();
        String weekEnd = sdf.format(addDay(sundayDate, -1)) + " 23:59:59";
        map.put("endWeek", weekEnd);
        return map;
    }

    public static Map<String, String> getCurrentMonth(boolean addEndDay) {
        Map<String, String> map = new HashMap(2);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        map.put("beginMonth", format(new Date(), "yyyy-MM") + "-01");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(5, calendar.getActualMaximum(5));
        if (addEndDay) {
            calendar.add(5, 1);
        }

        map.put("endMonth", sdf.format(calendar.getTime()));
        return map;
    }

    public static Map<String, String> getAppointMonth(Date date, boolean addEndDay) {
        Map<String, String> map = new HashMap(2);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        map.put("beginMonth", format(date, "yyyy-MM") + "-01");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(5, calendar.getActualMaximum(5));
        if (addEndDay) {
            calendar.add(5, 1);
        }

        map.put("endMonth", sdf.format(calendar.getTime()));
        return map;
    }

    public static String secondToTime(long second) {
        long days = second / 86400L;
        second %= 86400L;
        long hours = second / 3600L;
        second %= 3600L;
        long minutes = second / 60L;
        second %= 60L;
        return 0L < days ? days + "天" + hours + "小时" + minutes + "分" + second + "秒" : hours + "小时" + minutes + "分" + second + "秒";
    }

    public static int getYear(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY");
        return Integer.valueOf(sdf.format(date));
    }

    public static Date getYearFirst(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(1, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }

    public static Date getYearLast(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(1, year);
        calendar.roll(6, -1);
        Date currYearLast = calendar.getTime();
        return currYearLast;
    }
}
