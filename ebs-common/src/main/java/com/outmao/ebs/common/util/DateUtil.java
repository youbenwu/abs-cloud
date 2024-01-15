package com.outmao.ebs.common.util;

import com.outmao.ebs.common.configuration.web.DateFormatPlugin;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static java.util.Calendar.*;

public class DateUtil {

    public static DateFormat yyyy_MM=new SimpleDateFormat("yyyy-MM");
    public static DateFormat yyyy_MM_dd=new SimpleDateFormat("yyyy-MM-dd");
    public static DateFormat yyyy_MM_dd_HH_mm=new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public static DateFormat yyyy_MM_dd_HH_mm_ss=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static DateFormatPlugin dateFormatPlugin=new DateFormatPlugin();

    public static Date parse(String s){
        try {
            return dateFormatPlugin.parse(s);
        }catch (Exception e){
            return null;
        }
    }


    public static Date format_yyyy_MM(Date date){
        String s=yyyy_MM.format(date);
        try {
            return yyyy_MM.parse(s);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static Date format_yyyy_MM_dd(Date date){
        String s=yyyy_MM_dd.format(date);
        try {
            return yyyy_MM_dd.parse(s);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }


    public static Date maxTime(Date date){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    public static Date addMonths(Date date,int months){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(MONTH, months);
        return calendar.getTime();
    }

    public static Date addYears(Date date,int years){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(YEAR, years);
        return calendar.getTime();
    }

    public static Date addDays(Date date,int days){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }

    public static Date addHours(Date date,int hours){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(HOUR, hours);
        return calendar.getTime();
    }

    public static Date addMinutes(Date date,int minutes){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(MINUTE, minutes);
        return calendar.getTime();
    }

    public static Date beforeDays(int days){
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.DATE, -days);
        return calendar.getTime();
    }

    public static Date beforeMonths(int months){
        Calendar calendar=Calendar.getInstance();
        calendar.add(MONTH, -months);
        return calendar.getTime();
    }

    /*
    * 获取当天零点时间
    * */
    public static Date zeroTime(Date date){
        Calendar calendar = Calendar.getInstance();
        if(date!=null)
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date time = calendar.getTime();
        return time;
    }


    public static String getDateMMdd(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        return sdf.format(date);
    }


    public static String getDateString(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return sdf.format(date);
    }

    public static String getDateStringCN(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd hh:mm:ss");
        return sdf.format(date);
    }

    public static String getDateShortString(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }


    public static boolean isSameDay(Date date1, Date date2) {
        if(date1 != null && date2 != null) {
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(date1);
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(date2);
            return isSameDay(cal1, cal2);
        } else {
            throw new IllegalArgumentException("The date must not be null");
        }
    }

    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if(cal1 != null && cal2 != null) {
            return cal1.get(ERA) == cal2.get(ERA) && cal1.get(YEAR) == cal2.get(YEAR) && cal1.get(DAY_OF_YEAR) == cal2.get(DAY_OF_YEAR);
        } else {
            throw new IllegalArgumentException("The date must not be null");
        }
    }


    /**
     * 计算两个日期之间相差的天数
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     *
     */
    public static int daysBetween(Date smdate,Date bdate)
    {
        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            smdate = sdf.parse(sdf.format(smdate));
            bdate = sdf.parse(sdf.format(bdate));
            Calendar cal = Calendar.getInstance();
            cal.setTime(smdate);
            long time1 = cal.getTimeInMillis();
            cal.setTime(bdate);
            long time2 = cal.getTimeInMillis();
            long between_days = (time2 - time1) / (1000 * 3600 * 24);

            return Integer.parseInt(String.valueOf(between_days));

        }catch (Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }




}
