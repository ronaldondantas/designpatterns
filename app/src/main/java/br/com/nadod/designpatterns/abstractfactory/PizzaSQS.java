package br.com.nadod.designpatterns.abstractfactory;

public class PizzaSQS implements Pizza {
    @Override
    public String get() {
        return "Hoje é dia de pizza de calabresa! Aproveite o queijo, o calabresa e o tomate!";
    }
}
