package br.com.nadod.designpatterns.iterator;

import java.util.ArrayList;
import java.util.List;

public class ArrayListIterator implements IteratorInterface{
    List<Integer> cardsNumber = new ArrayList<>();
    int position = 0;

    protected ArrayListIterator(List<Integer> cards) {
        cardsNumber = cards;
    }

    @Override
    public List<Integer> getValues() {
        return cardsNumber;
    }

    @Override
    public void first() {
        position = 0;
    }

    @Override
    public void next() {
        position++;
    }

    @Override
    public void remove() {
        cardsNumber.remove(position);
        if (position > 0) position--;
    }

    @Override
    public void add(int number) {
        cardsNumber.add(number);
    }

    @Override
    public boolean isEmpty() {
        return cardsNumber.isEmpty();
    }

    @Override
    public int currentItem() {
        return cardsNumber.get(position);
    }

    @Override
    public int lower() {
        int lower = cardsNumber.get(0);
        for (int i=0; i<cardsNumber.size(); i++) {
            if (cardsNumber.get(i) < lower) {
                lower = cardsNumber.get(i);
                position = i;
            }
        }
        return lower;
    }
}
