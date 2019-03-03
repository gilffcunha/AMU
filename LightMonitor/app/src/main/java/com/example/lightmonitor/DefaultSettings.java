package com.example.lightmonitor;

import java.util.Date;
import java.sql.Timestamp;
import java.util.Calendar;

public class DefaultSettings
{
    private static Date minHour;
    private static Date maxHour;
    // Limitamos também localização??

    public DefaultSettings()
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY,20);
        cal.set(Calendar.MINUTE,0);
        // min hour
        this.minHour = cal.getTime();

        cal.set(Calendar.HOUR_OF_DAY,20);
        cal.set(Calendar.MINUTE,0);
        // max hour
        this.maxHour = cal.getTime();
    }

    public DefaultSettings(Timestamp minHour, Timestamp maxHour) {
        this.minHour = minHour;
        this.maxHour = maxHour;
    }

    public static boolean validate(Sample a)
    {
        boolean ret = false;

        if(a.getTimestamp().after(minHour) && a.getTimestamp().before(maxHour))
        {
            ret = true;
        }

        return ret;
    }
}
