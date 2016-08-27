package br.com.nadod.designpatterns.abstractfactory;

import android.util.Log;

public class PizzaTQS implements Pizza {
    @Override
    public String get() {
        return "Hoje é dia de pizza de presunto! Aproveite o queijo, o presunto e o tomate!";
    }
}
