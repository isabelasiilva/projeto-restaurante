package org.example.pedido;

import lombok.Getter;
import lombok.Setter;
import org.example.cardapio.ItemCardapio;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
//cuida de uma comanda só
public class Pedido {
    private int numero;
    private String cliente;

    ArrayList<ItemCardapio> itens = new ArrayList<>();

    public Pedido(int numero, String cliente) {
        this.numero = numero;
        setCliente(cliente);
    }

    public void adicionarItem(ItemCardapio item) {
        itens.add(item);
    }

    public List<ItemCardapio> getItens() {
        return itens;
    }

    public double calcularTotal() {
        double total = 0;
        for  (ItemCardapio lanche : itens) {
            total += lanche.getPreco();
        }
        return total;
    }

    public void removerItem(int indice) {
        if (indice >= 0 && indice < itens.size()) {
            itens.remove(indice);
        }
    }

    public void setCliente(String cliente) {
        if (cliente == null || cliente.trim().isEmpty()){
            throw new IllegalArgumentException("Nome inválido: não pode ser nulo ou vazio");
        }
        this.cliente = cliente;
    }
}
