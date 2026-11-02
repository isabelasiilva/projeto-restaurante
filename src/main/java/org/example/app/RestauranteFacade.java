package org.example.app;

import org.example.cardapio.ItemCardapio;
import org.example.cardapio.Lanche;
import org.example.cardapio.LancheFactory;
import org.example.pedido.GerenciadorPedidos;
import org.example.pedido.Pedido;

import java.util.Collection;
import java.util.Set;

public class RestauranteFacade {
    //coordena as operações, recebe os pedidos de MenuConsole
    // chama GerenciadorPedidos ou LancheFactory e devolve os resultados

    private GerenciadorPedidos gerenciadorPedidos = new GerenciadorPedidos();

    public Pedido criarPedido(String nomeCliente) {
        return gerenciadorPedidos.criarPedido(nomeCliente);
    }

    public void adicionarItemAoPedido(int numeroPedido, ItemCardapio item){
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

    public Pedido buscarPedido(int numero) {
        return gerenciadorPedidos.buscarPedido(numero);
    }

    public Set<String> getCombinacoesDoDia() {
        return gerenciadorPedidos.getCombinacoesDoDia();
    }

    public void removerItemDoPedido(int numeroPedido, int indiceItem) {
        Pedido pedido = gerenciadorPedidos.buscarPedido(numeroPedido);
        if (pedido != null) {
            pedido.removerItem(indiceItem);
        }
    }

    public void cancelarPedido(int numeroPedido) {
        gerenciadorPedidos.cancelarPedido(numeroPedido);
    }

    public Lanche criarXBurger() {
        return LancheFactory.xBurger();
    }

    public Lanche criarXSalada() {
        return LancheFactory.xSalada();
    }

    public Lanche criarXBacon() {
        return LancheFactory.xBacon();
    }
}
