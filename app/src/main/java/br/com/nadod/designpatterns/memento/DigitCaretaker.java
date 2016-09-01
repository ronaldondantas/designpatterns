package br.com.nadod.designpatterns.memento;

import java.util.ArrayList;

public class DigitCaretaker {
    protected ArrayList<DigitMemento> digits;

    public DigitCaretaker() {
        this.digits = new ArrayList<>();
    }

    public void addDigit(DigitMemento digit) {
        digits.add(digit);
    }

    public DigitMemento lastDigitsSaved() {
        DigitMemento digitMemento = new DigitMemento("");
        if (digits.size() > 0) {
            int lastPosition = (digits.size() - 1);
            digitMemento = digits.get(lastPosition);
            digits.remove(lastPosition);
        }
        return digitMemento;
    }
}
