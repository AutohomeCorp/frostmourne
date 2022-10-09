package com.autohome.frostmourne.common.time;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * description
 *
 * @author kechangqing
 * @since 2022/9/30 16:46
 */
public class TimeSpec {
    static final int TYPE_ABSOLUTE = 0;
    static final int TYPE_START = 1;
    static final int TYPE_END = 2;

    int type = TYPE_ABSOLUTE;
    int year, month, day, hour, min, sec;
    int wday;
    int dyear, dmonth, dday, dhour, dmin, dsec;

    String dateString;

    TimeSpec context;

    TimeSpec(String dateString) {
        this.dateString = dateString;
    }

    void localtime(long timestamp) {
        GregorianCalendar date = new GregorianCalendar();
        date.setTime(new Date(timestamp * 1000L));
        year = date.get(Calendar.YEAR) - 1900;
        month = date.get(Calendar.MONTH);
        day = date.get(Calendar.DAY_OF_MONTH);
        hour = date.get(Calendar.HOUR_OF_DAY);
        min = date.get(Calendar.MINUTE);
        sec = date.get(Calendar.SECOND);
        wday = date.get(Calendar.DAY_OF_WEEK) - Calendar.SUNDAY;
    }

    GregorianCalendar getTime() {
        GregorianCalendar gc;
        // absoulte time, this is easy
        if (type == TYPE_ABSOLUTE) {
            gc = new GregorianCalendar(year + 1900, month, day, hour, min, sec);
        }
        // relative time, we need a context to evaluate it
        else if (context != null && context.type == TYPE_ABSOLUTE) {
            gc = context.getTime();
        }
        // how would I guess what time it was?
        else {
            throw new RuntimeException("Relative times like '" + dateString + "' require proper absolute context to be evaluated");
        }
        gc.add(Calendar.YEAR, dyear);
        gc.add(Calendar.MONTH, dmonth);
        gc.add(Calendar.DAY_OF_MONTH, dday);
        gc.add(Calendar.HOUR_OF_DAY, dhour);
        gc.add(Calendar.MINUTE, dmin);
        gc.add(Calendar.SECOND, dsec);
        return gc;
    }

    public long getTimestamp() {
        Date date = getTime().getTime();
        return (date.getTime() + 499L) / 1000L;
    }

    String dump() {
        return (type == TYPE_ABSOLUTE ? "ABSTIME" : type == TYPE_START ? "START" : "END") + ": " + year + "/" + month + "/" + day + "/" + hour + "/" + min + "/"
            + sec + " (" + dyear + "/" + dmonth + "/" + dday + "/" + dhour + "/" + dmin + "/" + dsec + ")";
    }

    public static Calendar[] getTimes(TimeSpec spec1, TimeSpec spec2) {
        if (spec1.type == TYPE_START || spec2.type == TYPE_END) {
            throw new RuntimeException("Recursive time specifications not allowed");
        }
        spec1.context = spec2;
        spec2.context = spec1;
        return new Calendar[] {spec1.getTime(), spec2.getTime()};
    }
}
