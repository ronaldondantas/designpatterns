package br.com.nadod.designpatterns.builder;

import java.util.List;

public interface OrderBuilder {
    Order mount(List<OrderItem> orderItemList);
}
