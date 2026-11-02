package org.example.cardapio;

public class ComCheddar implements ItemCardapio {
    private ItemCardapio item;

    public ComCheddar(ItemCardapio item) {
        this.item = item;
    }

    @Override
    public String getDescricao() {
        return item.getDescricao() + " + cheddar";
    }

    @Override
    public double getPreco() {
        return item.getPreco() + 4.0;
    }
}
