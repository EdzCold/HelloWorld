package com.volley.profuturo.en501863.learningproyectv07;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by EN501863 on 31/10/2017.
 */

public class Util {

    //                            Date d = new Date();
//                            CharSequence fechaInicio = DateFormat.format("MMMM d, yyyy ", d.getTime());
//                            CharSequence fechaInicio = "";

    public static String getDate(){
        //Format
        //DD/MM/YYYY - HH:MM:SS

        Calendar c = new GregorianCalendar();
        Date date = new Date();
        c.setTime(date);

        String fechaInicio = c.get(Calendar.DATE) +"/"+ c.get(Calendar.MONTH) +"/"+ c.get(Calendar.YEAR)
                +" - "+c.get(Calendar.HOUR) +":"+ c.get(Calendar.MINUTE) +
                ":"+ c.get(Calendar.SECOND);

        return fechaInicio;
    }
}
