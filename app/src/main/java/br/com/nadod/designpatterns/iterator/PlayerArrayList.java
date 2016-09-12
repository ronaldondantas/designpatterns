package br.com.nadod.designpatterns.iterator;

import java.util.ArrayList;
import java.util.List;

public class PlayerArrayList implements CardsIterator {
    private List<Integer> currentCards = new ArrayList<>();

    public PlayerArrayList(List<Integer> currentCards) {
        this.currentCards = currentCards;
    }

    @Override
    public IteratorInterface createIterator() {
        return new ArrayListIterator(currentCards);
    }

    public List<Integer> getCurrentCards() {
        return currentCards;
    }

    public void setCurrentCards(List<Integer> currentCards) {
        this.currentCards = currentCards;
    }
}
