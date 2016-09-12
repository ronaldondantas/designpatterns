package br.com.nadod.designpatterns.iterator;

import java.util.List;
import java.util.Stack;

public class StackIterator implements IteratorInterface{
    Stack<Integer> cardsNumber = new Stack<>();
    int position = 0;

    protected StackIterator(Stack<Integer> cards) {
        cardsNumber = cards;
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
        if (isEmpty()) return;
        else {
            position = (cardsNumber.size() - 1);
            Stack<Integer> tmpStack = new Stack<>();
            for (int i = 0; i < cardsNumber.size(); i++) {
                if (cardsNumber.get(i) != currentItem()) {
                    tmpStack.push(cardsNumber.get(i));
                }
            }
            cardsNumber.clear();
            while (!tmpStack.isEmpty()) cardsNumber.push(tmpStack.pop());
        }
    }

    @Override
    public void add(int number) {
        cardsNumber.push(number);
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

    @Override
    public List<Integer> getValues() {
        return cardsNumber;
    }
}
