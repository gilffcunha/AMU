package com.example.lightmonitor;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.time.LocalTime;

public class DefaultSettings
{
    private static LocalTime horaMinima;
    private static LocalTime horaMaxima;
    // Limitamos também localização??

    @RequiresApi(api = Build.VERSION_CODES.O)
    public DefaultSettings()
    {
        this.horaMinima = LocalTime.of(20, 00);
        this.horaMaxima = LocalTime.of(04,0);
    }

    public DefaultSettings(LocalTime horaMinima, LocalTime horaMaxima) {
        this.horaMinima = horaMinima;
        this.horaMaxima = horaMaxima;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static boolean valida(Amostra a)
    {
        boolean ret = false;

        if(a.getTimestamp().toLocalTime().isAfter(horaMinima) && a.getTimestamp().toLocalTime().isBefore(horaMaxima))
        {
            ret = true;
        }

        return ret;
    }
}
