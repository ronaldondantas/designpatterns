package br.com.nadod.designpatterns.builder;

import java.util.List;

public class AtendenteOrderBuilder implements OrderBuilder {
    @Override
    public Order mount(List<OrderItem> orderItemList) {
        Order newOrder = new Order();
        newOrder.setAllItems(orderItemList);
        return newOrder;
    }
}
