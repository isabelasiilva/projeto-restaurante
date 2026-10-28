package org.example.pedido;

import java.util.HashMap;

//guarda todos os pedidos do restaurante
public class GerenciadorPedidos {
    private int numeroPedido;
    private int contadorPedidos = 1;
    private HashMap<Integer, Pedido> pedidos = new HashMap<>();

    public Pedido criarPedido(String nomeCliente) {
        Pedido novo = new Pedido(contadorPedidos, nomeCliente);
        pedidos.put(novo.getNumero(), novo);
        contadorPedidos++;
        return novo;
    }

    public void buscarPedido(int numero) {
        if (pedidos.containsKey(numero)) {
            System.out.println(pedidos.get(numero));
        } else {
            System.out.println("Número não registrado!");
        }
    }

    public void getPedidos() {
        for (Pedido pedido : pedidos.values()) {
            System.out.println(pedido);
        }
    }

    public double calcularTotalDoDia() {
        double totalDoDia = 0;
        for (Pedido pedido : pedidos.values()) {
            totalDoDia += pedido.calcularTotal();
        }
        return totalDoDia;
    }
}
