package org.example.pedido;

import java.util.HashMap;

//guarda todos os pedidos do restaurante
public class GerenciadorPedidos {
    private int contadorPedidos = 1;
    private HashMap<Integer, Pedido> pedidos = new HashMap<>();

    public Pedido criarPedido(String nomeCliente) {
        Pedido novo = new Pedido(contadorPedidos, nomeCliente);
        pedidos.put(novo.getNumero(), novo);
        contadorPedidos++;
        return novo;
    }

    public Pedido buscarPedido(int numero) {
        return pedidos.get(numero); // se nao existir retorna null
    }

    public HashMap<Integer, Pedido> getPedidos() {
        return this.pedidos;
    }

    public double calcularTotalDoDia() {
        double totalDoDia = 0;
        for (Pedido pedido : pedidos.values()) {
            totalDoDia += pedido.calcularTotal();
        }
        return totalDoDia;
    }
}
