package org.example.cardapio;

public class ComBaconExtra implements ItemCardapio {
    private ItemCardapio item;

    public ComBaconExtra(ItemCardapio item) {
        this.item = item;
    }

    @Override
    public String getDescricao() {
        return item.getDescricao() + " + bacon extra";
    }

    @Override
    public double getPreco() {
        return item.getPreco() + 6.0;
    }
}