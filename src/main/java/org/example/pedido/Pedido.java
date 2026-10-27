package org.example.pedido;

import lombok.Getter;
import org.example.cardapio.Lanche;

import java.util.ArrayList;

public class Pedido {
    private int numero;
    @Getter private String cliente;

    ArrayList<Lanche> itens = new ArrayList<>();

    public Pedido(int numero, String cliente) {
        this.numero = numero;
        setCliente(cliente);
    }

    public void adicionarItem(Lanche lanche) {
        itens.add(lanche);
    }

    public void getItens() {
        for (int i = 0; i < itens.size(); i++) {
            System.out.println("Pedido " + (i + 1) + itens.get(i));
        }
    }

    public double calcularTotal() {
        double total = 0;
        for (int i = 0; i < itens.size(); i++) {
            total += itens.get(i).getPreco();
        }
        return total;
    }

    public void setCliente(String cliente) {
        if (cliente == null || cliente.trim().isEmpty()){
            throw new IllegalArgumentException("Nome inválido: não pode ser nulo ou vazio");
        }
        this.cliente = cliente;
    }
}
