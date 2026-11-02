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
//        System.out.print("Escolha o lanche: ");

        while (true) {
            int opcaoLanche = lerInteiroTratandoErro("Escolha o lanche: ");
            if (opcaoLanche >= 1 && opcaoLanche <= 3) {
                return opcaoLanche;
            }
            System.out.println("Opção inválida! Escolha entre 1 e 3.");
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
        if (perguntarSimNao("Deseja retirar queijo?")) {
            System.out.println("Retirando o queijo");
            lancheEscolhidoNoMostrarCardapio.setQueijo(false);
        }

        if (perguntarSimNao("Deseja retirar alface?")) {
            System.out.println("Retirando a alface");
            lancheEscolhidoNoMostrarCardapio.setAlface(false);
        }

        if (perguntarSimNao("Deseja retirar tomate?")) {
            System.out.println("Retirando o tomate");
            lancheEscolhidoNoMostrarCardapio.setTomate(false);
        }

        if (perguntarSimNao("Deseja retirar cebola?")) {
            System.out.println("Retirando a cebola");
            lancheEscolhidoNoMostrarCardapio.setCebola(false);
        }
    }

    private boolean perguntarAdicionarMais() {
        while (true) {
            System.out.print("Deseja adicionar mais um item?\n1- SIM \n2- NÃO: ");
            String resposta = sc.nextLine().trim();

            if (resposta.equals("1")) {
                return true;
            } else if (resposta.equals("2")) {
                return false;
            } else {
                System.out.println("Resposta Invalida! Digite 1 ou 2");
            }
        }
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
        if (perguntarSimNao("Deseja adicionar cheddar?")) {
            item = new ComCheddar(item);
        }

        if (perguntarSimNao("Deseja adicionar bacon extra?")) {
            item = new ComBaconExtra(item);
        }

        return item;
    }

    private void modificarPedido() {
        int pedidoParaAlterar = lerInteiroTratandoErro("Digite qual é o número do seu pedido: ");

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

            int opcaoAlterarItem = lerInteiroTratandoErro("Escolha uma opção: ");

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

        int indice = lerInteiroTratandoErro("Digite o número do item que deseja remover: ");
        indice = indice - 1; // correçao de ordem no List

        if (indice >= 0 && indice < itens.size()) {
            restauranteFacade.removerItemDoPedido(pedido.getNumero(), indice);
            System.out.println("Item removido.");
        } else {
            System.out.println("Item inválido.");
        }
    }

    private int lerInteiroTratandoErro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String linha = sc.nextLine().trim();

            try {
                return Integer.parseInt(linha);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Digite um número.");
            }
        }


    }

    private boolean perguntarSimNao(String pergunta) {
        System.out.print(pergunta + " 1-SIM 2-NÃO: ");
        String resposta = sc.nextLine().trim();

        while (true) {
            if (resposta.equals("1")) {
                return true;
            }
            if (resposta.equals("2")) {
                return false;
            }
            System.out.print("Opção inválida! Digite 1 ou 2: ");
            resposta = sc.nextLine().trim();
        }
    }
}

