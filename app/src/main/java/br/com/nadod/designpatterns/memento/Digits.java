package br.com.nadod.designpatterns.memento;

import android.util.Log;

public class Digits {
    String digits;
    DigitCaretaker digitCaretaker;

    public Digits() {
        digits = "";
        digitCaretaker = new DigitCaretaker();
    }

    public String getDigits() {
        return digits;
    }

    public void writeDigit(String newDigit) {
        digitCaretaker.addDigit(new DigitMemento(digits));
        digits += newDigit;
        Log.d("ASD","ASD");
    }

    public void undo() {
        digits = digitCaretaker.lastDigitsSaved().getDigits();
    }
}
