package br.com.nadod.designpatterns.builder;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<OrderItem> allItems = new ArrayList<>();
    private List<OrderItem> dentroDaCaixa = new ArrayList<>();

    private List<OrderItem> foraDaCaixa = new ArrayList<>();

    public void adicionarDentroDaCaixa(OrderItem item) {
        dentroDaCaixa.add(item);
    }
    public void adicionarForaDaCaixa(OrderItem item) {
        foraDaCaixa.add(item);
    }

    public List<OrderItem> getForaDaCaixa() {
        return foraDaCaixa;
    }

    public void setForaDaCaixa(List<OrderItem> foraDaCaixa) {
        this.foraDaCaixa = foraDaCaixa;
    }

    public List<OrderItem> getDentroDaCaixa() {
        return dentroDaCaixa;
    }

    public void setDentroDaCaixa(List<OrderItem> dentroDaCaixa) {
        this.dentroDaCaixa = dentroDaCaixa;
    }

    public List<OrderItem> getAllItems() {
        return allItems;
    }

    public void setAllItems(List<OrderItem> allItems) {
        this.allItems = allItems;
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("Seu pedido:\n");
        buffer.append("Dentro da caixa:\n");
        for (OrderItem item : dentroDaCaixa) {
            if (item.getType().compareTo("potato") == 0) {
                buffer.append("\t").append("Batata ").append(item.getName()).append("\n");
            } else {
                buffer.append("\t").append(item.getName()).append("\n");
            }
        }

        buffer.append("Fora da caixa:\n");
        for (OrderItem item : foraDaCaixa) buffer.append("\t").append(item.getName()).append("\n");
        buffer.append("\nTenha um bom dia!\n\n");

        return buffer.toString();
    }
}
