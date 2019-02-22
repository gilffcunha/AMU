package Server;
/*
    Classe para definir as condições de cada amostra
 */

import java.time.LocalTime;

public class DefaultSettings
{
    private static LocalTime horaMinima;
    private static LocalTime horaMaxima;
    // Limitamos também localização?? 


    public DefaultSettings(LocalTime horaMinima, LocalTime horaMaxima) {
        this.horaMinima = horaMinima;
        this.horaMaxima = horaMaxima;
    }

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
