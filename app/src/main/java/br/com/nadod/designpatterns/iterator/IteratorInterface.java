package br.com.nadod.designpatterns.iterator;

import java.util.List;

public interface IteratorInterface {
    List<Integer> getValues();
    void first();
    void next();
    void remove();
    void add(int number);
    boolean isEmpty();
    int currentItem();
    int lower();
}
