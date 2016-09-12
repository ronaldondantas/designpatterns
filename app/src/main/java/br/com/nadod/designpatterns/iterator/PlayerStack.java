package br.com.nadod.designpatterns.iterator;

import java.util.Stack;

public class PlayerStack implements CardsIterator {
    private Stack<Integer> currentCards = new Stack<>();

    public PlayerStack(Stack<Integer> currentCards) {
        this.currentCards = currentCards;
    }

    @Override
    public IteratorInterface createIterator() {
        return new StackIterator(currentCards);
    }

    public Stack<Integer> getCurrentCards() {
        return currentCards;
    }

    public void setCurrentCards(Stack<Integer> currentCards) {
        this.currentCards = currentCards;
    }
}
