package br.com.nadod.designpatterns.abstractfactory;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class AbstractPizzaiolo {
    public abstract Pizza cook();
    public static AbstractPizzaiolo createPizzaiolo(String day) throws ParseException {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date d = sdf.parse(day);
            sdf = new SimpleDateFormat("EEEE");
            String dayOfTheWeek = sdf.format(d);
            if (dayOfTheWeek.compareTo("Monday") == 0 || dayOfTheWeek.compareTo("Wednesday") == 0 ||
                    dayOfTheWeek.compareTo("Friday") == 0){
                return new PizzaioloSQS();
            } else if (dayOfTheWeek.compareTo("Tuesday") == 0 || dayOfTheWeek.compareTo("Thursday") == 0 ||
                    dayOfTheWeek.compareTo("Saturday") == 0){
                return new PizzaioloTQS();
            } else {
                Log.d("LOG", "Pizzaria fechada");
            }
        } catch (Exception e) {
            throw e;
        }
        return null;
    }
}
