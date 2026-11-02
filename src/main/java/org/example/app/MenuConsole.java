package org.example.app;

import org.example.pedido.Pedido;
import java.util.Scanner;

public class MenuConsole {
    Scanner sc = new Scanner(System.in);
    RestauranteFacade restauranteFacade = new RestauranteFacade();

    public MenuConsole(RestauranteFacade facade) {
        this.restauranteFacade = facade;
    }

    public void exibirMenu(){
        boolean sair = false;

        while (!sair) {
            mostrarOpcoesMenu();
            String opcao = entradaUsuario();

            switch(opcao){
                case "0":
                    System.out.println("Até logo!");
                    sair = true;
                    break;
                case "1":
                    System.out.println("Registrar novo pedido");
                    cadastrarNovoPedido();
                    break;
                case "2":
                    System.out.println("Listar pedidos do dia");
                    listarPedidosDoDia();
                    break;
                case "3":
                    System.out.println("Fechar restaurante");
                    fecharRestaurante();
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    void mostrarOpcoesMenu(){
        System.out.println("=== RESTAURANTE ===");
        System.out.println("1. Registrar novo pedido");
        System.out.println("2. Listar pedidos do dia");
        System.out.println("3. Fechar restaurante");
        System.out.println("0. Sair");
    }

    String entradaUsuario(){
        System.out.print("Escolha uma opção: ");
        return sc.nextLine();
    }

    public void cadastrarNovoPedido(){
        System.out.println("Digite o nome do cliente: ");
        String nomeCliente = sc.nextLine();

        Pedido pedido = restauranteFacade.criarPedido(nomeCliente);
        System.out.println("Pedido N°" + pedido.getNumero() + " criado.");

        System.out.println("Quer adicionar mais um item?");
        boolean adicionarMais = true;
        while (adicionarMais) {
            int opcaoLanche = sc.nextInt();
//            ItemCardapio item = criarLanchePelaOpcao(opcaoLanche);
//            restauranteFacade.adicionarItemAoPedido(pedido.getNumero(), item);

//            String resposta = lerQuerAdicionarMais();
//            adicionarMais = resposta.equalsIgnoreCase();
        }
        //String adicionarItem = sc.nextLine();

    }

    public int mostrarCardapio() {
        System.out.println("Cardápio:");
        System.out.println("1. X-Burger — R$ 16,00");
        System.out.println("2. X-Salada — R$ 18,00");
        System.out.println("3. X-Bacon  — R$ 22,00");
        System.out.print("Escolha o lanche: ");
        return Integer.parseInt(sc.nextLine());
    }

    void listarPedidosDoDia(){}

    void fecharRestaurante(){}

    public void exibirFechamentoDoDia(double total){
        System.out.println("Fechamento do dia: ");
    }
}
