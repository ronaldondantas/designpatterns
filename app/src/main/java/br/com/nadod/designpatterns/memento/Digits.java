package br.com.nadod.designpatterns.memento;

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
    }

    public void undo() {
        digits = digitCaretaker.lastDigitsSaved().getDigits();
    }
}
