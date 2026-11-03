package org.example.app;

public class Main {
    public static void main(String[] args) {
        RestauranteFacade sistema = new RestauranteFacade();

        MenuConsole menu = new MenuConsole(sistema);
        menu.exibirMenu();

    }
}
