package org.example.app;

public class Main {
    public static void main(String[] args) {
//        GerenciadorPedidos gerenciadorPedidos = new GerenciadorPedidos();
//        double total = gerenciadorPedidos.calcularTotalDoDia();

        RestauranteFacade sistema = new RestauranteFacade();
        MenuConsole menu = new MenuConsole(sistema);
        menu.exibirMenu();

//        menu.exibirFechamentoDoDia(total);
    }
}
