package br.com.nadod.designpatterns.memento;

public class DigitMemento {
    String digit;

    public DigitMemento(String newDigit) {
        digit = newDigit;
    }

    public String getDigits() {
        return digit;
    }
}
