package org.example.app;

import org.example.cardapio.ComBaconExtra;
import org.example.cardapio.ComCheddar;
import org.example.cardapio.ItemCardapio;
import org.example.cardapio.Lanche;
import org.example.pedido.Pedido;

import java.util.Collection;
import java.util.List;
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
                case "4":
                    System.out.println("Modificar pedido");
                    modificarPedido();
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
        System.out.println("4. Modificar pedido");
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

        return lerInteiro(); // tratar erro se inserir string
    }

    private int lerInteiro() {
        while (true) {
            String linha = sc.nextLine();

            if (linha.trim().equals("1") || linha.trim().equals("2") || linha.trim().equals("3")) {
                return Integer.parseInt(linha);
            } else {
                System.out.println("Entrada inválida! Digite um número de 1 a 3.");
            }
        }
    }


    public void adicionarItemNoPedido(Pedido pedido) {
        boolean adicionarItem = true;

        while (adicionarItem) {
            int opcao = mostrarCardapio();
            Lanche item = criarLanche(opcao);
            retirarIngrediente(item);

//            ItemCardapio novoItem = item;
            ItemCardapio novoItem = perguntarAdicionais(item);

            restauranteFacade.adicionarItemAoPedido(pedido.getNumero(), novoItem);

            adicionarItem = perguntarAdicionarMais();
        }
    }

    private void retirarIngrediente(Lanche lancheEscolhidoNoMostrarCardapio) {
        System.out.println("Deseja retirar queijo? 1-SIM 2-NÃO");
        String opcaoQueijo = sc.nextLine();
        switch (opcaoQueijo) {
            case "1":
                lancheEscolhidoNoMostrarCardapio.setQueijo(false);
                break;
            case "2":
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
        System.out.println("Deseja retirar alface? 1-SIM 2-NÃO");
        String opcaoAlface = sc.nextLine();
        switch (opcaoAlface) {
            case "1":
                lancheEscolhidoNoMostrarCardapio.setAlface(false);
                break;
            case "2":
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }

        System.out.println("Deseja retirar tomate? 1-SIM 2-NÃO");
        String opcaoTomate = sc.nextLine();
        switch (opcaoTomate) {
            case "1":
                lancheEscolhidoNoMostrarCardapio.setTomate(false);
                break;
            case "2":
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }

        System.out.println("Deseja retirar cebola? 1-SIM 2-NÃO");
        String opcaoCebola = sc.nextLine();
        switch (opcaoCebola) {
            case "1":
                lancheEscolhidoNoMostrarCardapio.setCebola(false);
                break;
            case "2":
                break;
            default:
                System.out.println("Opção inválida!");
                break;
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

    private Lanche criarLanche(int opcao) {
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

    private void modificarPedido() {
        System.out.println("Digite qual é o número do seu pedido");
        int pedidoParaAlterar = sc.nextInt();
        sc.nextLine();


        Pedido pedidoAlterar = restauranteFacade.buscarPedido(pedidoParaAlterar);

        if (pedidoAlterar == null) {
            System.out.println("Pedido não encontrado.");
        } else {
            System.out.println("Listando pedido:");
            exibirPedido(pedidoAlterar);
            System.out.println();

            System.out.println("Digite a opção desejada para modificar o pedido");
            System.out.println("1- Adicionar item");
            System.out.println("2- Remover item");
            System.out.println("3- Cancelar pedido");
            System.out.println("0- Voltar");

            int opcaoAlterarItem = sc.nextInt();
            sc.nextLine();

            switch (opcaoAlterarItem) {
                case 0:
                    System.out.println("Voltando...");
                    break;
                case 1:
                    System.out.println("Adicionando item");
                    adicionarItemNoPedido(pedidoAlterar);
                    break;
                case 2:
                    System.out.println("Removendo item");
                    removerItemDoPedido(pedidoAlterar);
                    break;
                case 3:
                    System.out.println("Cancelando pedido");
                    restauranteFacade.cancelarPedido(pedidoParaAlterar);
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }


    private void removerItemDoPedido(Pedido pedido) {
        List<ItemCardapio> itens = pedido.getItens();

        if (itens.isEmpty()) {
            System.out.println("O pedido não tem itens.");
            return;
        }

        System.out.println("Itens do pedido:");
        for (int i = 0; i < itens.size(); i++) {
            System.out.println((i + 1) + ". " + itens.get(i).getDescricao());
        }

        System.out.println("Digite o número do item que deseja remover: ");
        int indice = sc.nextInt();
        sc.nextLine();
        indice = indice - 1; // correçao de ordem no List

        if (indice >= 0 && indice < itens.size()) {
            restauranteFacade.removerItemDoPedido(pedido.getNumero(), indice);
            System.out.println("Item removido.");
        } else {
            System.out.println("Item inválido.");
        }
    }


}

