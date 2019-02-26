package com.example.lightmonitor;

import java.time.LocalTime;

public class DefaultSettings
{
    private static LocalTime minHour;
    private static LocalTime maxHour;
    // Limitamos também localização??

    public DefaultSettings()
    {
        this.minHour = LocalTime.of(20, 00);
        this.maxHour = LocalTime.of(04,0);
    }

    public DefaultSettings(LocalTime minHour, LocalTime maxHour) {
        this.minHour = minHour;
        this.maxHour = maxHour;
    }

    public static boolean validate(Sample a)
    {
        boolean ret = false;

        if(a.getTimestamp().toLocalTime().isAfter(minHour) && a.getTimestamp().toLocalTime().isBefore(maxHour))
        {
            ret = true;
        }

        return ret;
    }
}
