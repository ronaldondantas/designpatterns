package br.com.nadod.designpatterns.abstractfactory;

public class PizzaioloTQS extends AbstractPizzaiolo {
    @Override
    public Pizza cook() {
        return new PizzaTQS();
    }
}
