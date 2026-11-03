# Sistema de Restaurante - ElasTech SoulCode

Primeiro projeto do bootcamp **ElasTech da SoulCode**: um sistema console para gerenciamento de um restaurante que serve lanches.

**Video de apresentação:** https://youtu.be/q7LnqNu1Y34

## Funções do sistema:

- **Cardápio:** exibir os lanches disponíveis (X-Burger, X-Salada e X-Bacon).
- **Pedidos:** criar, listar, modificar e cancelar pedidos.
- **Personalização dos lanches:** retirar ingredientes (queijo, alface, tomate, cebola) e adicionar extras (cheddar e bacon).
- **Fechamento do dia:** calcular o total arrecadado e mostrar as combinações de lanches pedidas.

## Tecnologias usadas

- **Java 26**
- **Maven** para gerenciamento de build e dependências

## Dependências / Pacotes importados

- **Lombok, versão 1.18.48:** usado para gerar getters e setters automaticamente 
- **JUnit Jupiter, versão 5.10.2:** usado para testes unitários 
- **Mockito, versão 5.23.0:** usado para mocks nos testes

## Estrutura de pacotes

```
org.example.app          → Ponto de entrada, menu console e fachada do sistema
org.example.cardapio     → Cardápio, fábrica de lanches e decoradores de extras
org.example.pedido       → Pedidos e gerenciador de pedidos
org.example.financeiro   → Estrutura para controle financeiro
```

## Funcionalidades do menu

1. Registrar novo pedido
2. Listar pedidos do dia
3. Fechar restaurante (fechamento do dia)
4. Modificar pedido
0. Sair

---

Projeto desenvolvido por Isabela Silva, guiado pela professora Flora Belas, para entrega no bootcamp ElasTech da SoulCode.
