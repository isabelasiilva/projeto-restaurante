package org.example.app;

import org.example.cardapio.ComBaconExtra;
import org.example.cardapio.ComCheddar;
import org.example.cardapio.ItemCardapio;
import org.example.pedido.Pedido;

import java.util.Collection;
import java.util.Scanner;

public class MenuConsole {
    Scanner sc = new Scanner(System.in);
    RestauranteFacade restauranteFacade;

    public MenuConsole(RestauranteFacade facade) {
        this.restauranteFacade = facade;
    }

    public void exibirMenu() {
        boolean sair = false;

        while (!sair) {
            mostrarOpcoesMenu();
            String opcao = sc.nextLine();

            switch (opcao) {
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

    void mostrarOpcoesMenu() {
        System.out.println("=== RESTAURANTE ===");
        System.out.println("1. Registrar novo pedido");
        System.out.println("2. Listar pedidos do dia");
        System.out.println("3. Fechar restaurante");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    public void cadastrarNovoPedido() {
        System.out.println("Digite o nome do cliente: ");
        String nomeCliente = sc.nextLine();

        Pedido pedido = restauranteFacade.criarPedido(nomeCliente);
        System.out.println("Pedido N°" + pedido.getNumero() + " criado.");

        adicionarItemNoPedido(pedido);
        exibirPedido(pedido);

    }

    public int mostrarCardapio() {
        System.out.println("Cardápio:");
        System.out.println("1. X-Burger — R$ 16,00");
        System.out.println("2. X-Salada — R$ 18,00");
        System.out.println("3. X-Bacon  — R$ 22,00");
        System.out.print("Escolha o lanche: ");
        return Integer.parseInt(sc.nextLine());
    }

    public void adicionarItemNoPedido(Pedido pedido) {
        boolean adicionarItem = true;

        while (adicionarItem) {
            int opcao = mostrarCardapio();
            ItemCardapio item = criarLanche(opcao);
            item = perguntarAdicionais(item);

            restauranteFacade.adicionarItemAoPedido(pedido.getNumero(), item);

            adicionarItem = perguntarAdicionarMais();
        }
    }

    private boolean perguntarAdicionarMais() {
//        while (true) {
            System.out.print("Deseja adicionar mais um item?\n1- SIM \n2- NÃO: ");
            String resposta = sc.nextLine().trim();

            if (resposta.equals("1")) {
                return true;
            } else if (resposta.equals("2")) {
                return false;
            } else {
                System.out.println("Resposta Invalida!");
                return false;
            }
//        }
    }

    void listarPedidosDoDia() {
        Collection<Pedido> pedidos = restauranteFacade.listarPedidos();
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido registrado.");
        } else {
            for (Pedido pedido : pedidos) {
                exibirPedido(pedido);
            }
        }
    }

    void fecharRestaurante() {
        double total = restauranteFacade.calcularTotalDoDia();
        exibirFechamentoDoDia(total);
    }

    public void exibirPedido(Pedido pedido) {
        System.out.println("\n--- Pedido n°" + pedido.getNumero() + " ---");
        System.out.println("Cliente: " + pedido.getCliente());
        for (ItemCardapio item : pedido.getItens()) {
            System.out.println("- " + item.getDescricao() + " | R$ " + item.getPreco());
        }
        System.out.println("Total do pedido: R$ " + pedido.calcularTotal());
    }

    public void exibirFechamentoDoDia(double total) {
        System.out.println("\n=== FECHAMENTO DO DIA ===");
        System.out.println("Total arrecadado: R$ " + total);
    }

    private ItemCardapio criarLanche(int opcao) {
        return switch (opcao) {
            case 1 -> restauranteFacade.criarXBurger();
            case 2 -> restauranteFacade.criarXSalada();
            case 3 -> restauranteFacade.criarXBacon();
            default -> throw new IllegalArgumentException("Opção de lanche inválida");
        };
    }

    private ItemCardapio perguntarAdicionais(ItemCardapio item) {
        System.out.print("Deseja adicionar cheddar?\n1- SIM \n2- NÃO: ");
        String resposta = sc.nextLine().trim();

        switch (resposta) {
            case "1":
            item = new ComCheddar(item);
            break;
            case "2":
                break;
            default:
                System.out.println("Resposta Invalida!");
        }

        System.out.print("Deseja adicionar bacon extra?\n1- SIM \n2- NÃO: ");
        resposta = sc.nextLine().trim();
        switch (resposta) {
            case "1":
                item = new ComBaconExtra(item);
                break;
            case "2":
                break;
            default:
                System.out.println("Resposta Invalida!");
        }

        return item;
    }
}

