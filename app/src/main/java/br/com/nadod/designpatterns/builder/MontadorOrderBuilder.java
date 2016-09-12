package br.com.nadod.designpatterns.builder;

import java.util.ArrayList;
import java.util.List;

public class MontadorOrderBuilder implements OrderBuilder {
    @Override
    public Order mount(List<OrderItem> orderItemList) {
        Order finalOrder = new Order();
        List<OrderItem> listIn = new ArrayList<>();
        List<OrderItem> listOut = new ArrayList<>();
        for (OrderItem item : orderItemList) {
            if (item.getType() == "soda") listOut.add(item);
            else listIn.add(item);
        }
        finalOrder.setDentroDaCaixa(listIn);
        finalOrder.setForaDaCaixa(listOut);
        return finalOrder;
    }
}
