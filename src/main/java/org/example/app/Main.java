package org.example.app;

import org.example.cardapio.Lanche;
import org.example.pedido.Pedido;

public class Main {
    public static void main(String[] args) {
        Lanche lanche = new Lanche( "aa", 30);
        Pedido pedido = new Pedido( 0 , "aa" );

        pedido.adicionarItem(lanche);
        pedido.getItens();
        System.out.println(pedido.calcularTotal());
        System.out.println("aaaaa");
    }
}
