package org.example.app;

import org.example.cardapio.ItemCardapio;
import org.example.cardapio.factory.LancheFactory;
import org.example.pedido.GerenciadorPedidos;
import org.example.pedido.Pedido;

import java.util.Collection;

public class RestauranteFacade {
    //coordena as operações, recebe os pedidos de MenuConsole
    // chama GerenciadorPedidos ou LancheFactory e devolve os resultados

    GerenciadorPedidos gerenciadorPedidos = new GerenciadorPedidos();

    public Pedido criarPedido(String nomeCliente) {
        return gerenciadorPedidos.criarPedido(nomeCliente);
    }

    void adicionarItemAoPedido(int numeroPedido, ItemCardapio item){
        Pedido pedido = gerenciadorPedidos.buscarPedido(numeroPedido);
        if (pedido != null) {
            pedido.adicionarItem(item);
        }
    }

    public Collection<Pedido> listarPedidos() {
        return gerenciadorPedidos.getPedidos().values();
    }

    public double calcularTotalDoDia() {
        return gerenciadorPedidos.calcularTotalDoDia();
    }

    public ItemCardapio criarXBurger() {
        return LancheFactory.xBurger();
    }

    public ItemCardapio criarXSalada() {
        return LancheFactory.xSalada();
    }

    public ItemCardapio criarXBacon() {
        return LancheFactory.xBacon();
    }
}
