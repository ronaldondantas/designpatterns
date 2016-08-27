package br.com.nadod.designpatterns.abstractfactory;

public class PizzaioloSQS extends AbstractPizzaiolo {
    @Override
    public Pizza cook() {
        return new PizzaSQS();
    }
}
