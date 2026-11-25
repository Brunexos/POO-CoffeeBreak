package Progama;

import Entidades.Bebida;
import Entidades.Comida;
import Entidades.ItemPedido;
import Entidades.Pedido;
import Entidades.Produto;
import Enums.StatusPedido;
import Gerenciamento.Cardapio;
import Gerenciamento.GerenciadorPedidos;
import Interfaces.FormaPagamento;
import Pagamentos.PagamentoCartao;
import Pagamentos.PagamentoDinheiro;
import Pagamentos.PagamentoPix;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/** Ponto de entrada: cria as dependencias e conduz os menus pelo terminal. */
public class Programa {
    public static void main(String[] args) {
        // O main fornece listas e formas de pagamento aos objetos que dependem delas.
        Cardapio cardapio = new Cardapio(new ArrayList<>());
        GerenciadorPedidos gerenciadorPedidos = new GerenciadorPedidos(new ArrayList<>());
        List<FormaPagamento> pagamentos = new ArrayList<>();
        pagamentos.add(new PagamentoPix());
        pagamentos.add(new PagamentoDinheiro());
        pagamentos.add(new PagamentoCartao(PagamentoCartao.Tipo.CREDITO));
        pagamentos.add(new PagamentoCartao(PagamentoCartao.Tipo.DEBITO));

        // Objetos iniciais para exercitar as operacoes pedidas na AV3.
        cardapio.adicionarProduto(new Comida("Coxinha", 6.00, 20, 1, 120.0));
        cardapio.adicionarProduto(new Comida("Pao de queijo", 4.50, 25, 2, 70.0));
        cardapio.adicionarProduto(new Comida("Sanduiche", 12.00, 15, 3, 180.0));
        cardapio.adicionarProduto(new Comida("Brigadeiro", 3.50, 30, 4, 35.0));
        cardapio.adicionarProduto(new Comida("Bolo", 7.00, 12, 5, 100.0));
        cardapio.adicionarProduto(new Comida("Empada", 6.50, 18, 6, 110.0));
        cardapio.adicionarProduto(new Comida("Cookie", 5.00, 22, 7, 60.0));
        cardapio.adicionarProduto(new Bebida("Cafe expresso", 5.50, 30, 8, 50));
        cardapio.adicionarProduto(new Bebida("Cafe com leite", 7.00, 25, 9, 200));
        cardapio.adicionarProduto(new Bebida("Cha", 5.00, 20, 10, 200));
        cardapio.adicionarProduto(new Bebida("Suco de laranja", 8.00, 18, 11, 300));
        cardapio.adicionarProduto(new Bebida("Agua", 3.00, 40, 12, 500));
        cardapio.adicionarProduto(new Bebida("Refrigerante", 6.00, 24, 13, 350));
        cardapio.adicionarProduto(new Bebida("Chocolate quente", 9.00, 15, 14, 250));
        for (int codigo = 1; codigo <= 7; codigo++) {
            ArrayList<ItemPedido> itens = new ArrayList<>();
            itens.add(new ItemPedido(cardapio.buscarPorCodigo(codigo), codigo % 3 + 1));
            itens.add(new ItemPedido(cardapio.buscarPorCodigo(codigo + 7), 1));
            gerenciadorPedidos.adicionarPedido(
                    new Pedido(codigo, itens, pagamentos.get((codigo - 1) % pagamentos.size())));
        }

        try (Scanner scanner = new Scanner(System.in)) {
            boolean aberto = true;
            while (aberto) {
                System.out.println("\n========== COFFEE BREAK ==========");
                System.out.println("1 - Listar produtos");
                System.out.println("2 - Cadastrar produto");
                System.out.println("3 - Alterar produto");
                System.out.println("4 - Excluir produto");
                System.out.println("5 - Buscar produto por codigo");
                System.out.println("6 - Ordenar e listar produtos");
                System.out.println("7 - Gerenciar pedidos");
                System.out.println("0 - Sair");
                System.out.print("Opcao: ");
                if (!scanner.hasNextLine()) break;
                String opcao = scanner.nextLine().trim();

                try {
                    switch (opcao) {
                        case "1":
                            cardapio.exibirProdutos(cardapio.listarProdutos());
                            break;
                        case "2": {
                            System.out.print("1 - Comida | 2 - Bebida: ");
                            int tipo = Integer.parseInt(scanner.nextLine().trim());
                            if (tipo != 1 && tipo != 2) {
                                System.out.println("Tipo invalido.");
                                break;
                            }
                            System.out.print("Codigo: ");
                            int codigo = Integer.parseInt(scanner.nextLine().trim());
                            if (codigo <= 0 || cardapio.buscarPorCodigo(codigo) != null) {
                                System.out.println("O codigo deve ser positivo e ainda nao utilizado.");
                                break;
                            }
                            System.out.print("Nome: ");
                            String nome = scanner.nextLine().trim();
                            System.out.print("Preco: ");
                            double preco = Double.parseDouble(scanner.nextLine().trim().replace(',', '.'));
                            System.out.print("Quantidade em estoque: ");
                            int estoque = Integer.parseInt(scanner.nextLine().trim());
                            Produto novo;
                            if (tipo == 1) {
                                System.out.print("Peso em gramas: ");
                                double peso = Double.parseDouble(scanner.nextLine().trim().replace(',', '.'));
                                if (!Double.isFinite(peso) || peso <= 0) {
                                    System.out.println("O peso deve ser positivo.");
                                    break;
                                }
                                novo = new Comida(nome, preco, estoque, codigo, peso);
                            } else {
                                System.out.print("Volume em mL: ");
                                int volume = Integer.parseInt(scanner.nextLine().trim());
                                if (volume <= 0) {
                                    System.out.println("O volume deve ser positivo.");
                                    break;
                                }
                                novo = new Bebida(nome, preco, estoque, codigo, volume);
                            }
                            System.out.println(cardapio.adicionarProduto(novo)
                                    ? "Produto cadastrado." : "Dados do produto invalidos.");
                            break;
                        }
                        case "3": {
                            System.out.print("Codigo a alterar: ");
                            Produto atual = cardapio.buscarPorCodigo(
                                    Integer.parseInt(scanner.nextLine().trim()));
                            if (atual == null) {
                                System.out.println("Produto nao encontrado.");
                                break;
                            }
                            System.out.print("Novo nome: ");
                            String nome = scanner.nextLine().trim();
                            System.out.print("Novo preco: ");
                            double preco = Double.parseDouble(scanner.nextLine().trim().replace(',', '.'));
                            System.out.print("Novo estoque: ");
                            int estoque = Integer.parseInt(scanner.nextLine().trim());
                            Produto substituto;
                            if (atual instanceof Comida) {
                                System.out.print("Novo peso em gramas: ");
                                double peso = Double.parseDouble(scanner.nextLine().trim().replace(',', '.'));
                                if (!Double.isFinite(peso) || peso <= 0) {
                                    System.out.println("O peso deve ser positivo.");
                                    break;
                                }
                                substituto = new Comida(nome, preco, estoque, atual.getCodigo(), peso);
                            } else {
                                System.out.print("Novo volume em mL: ");
                                int volume = Integer.parseInt(scanner.nextLine().trim());
                                if (volume <= 0) {
                                    System.out.println("O volume deve ser positivo.");
                                    break;
                                }
                                substituto = new Bebida(nome, preco, estoque, atual.getCodigo(), volume);
                            }
                            System.out.println(cardapio.alterarProduto(atual.getCodigo(), substituto)
                                    ? "Produto alterado." : "Nao foi possivel alterar o produto.");
                            break;
                        }
                        case "4": {
                            System.out.print("Codigo a excluir: ");
                            int codigo = Integer.parseInt(scanner.nextLine().trim());
                            System.out.println(cardapio.excluirProduto(codigo)
                                    ? "Produto excluido." : "Produto nao encontrado.");
                            break;
                        }
                        case "5": {
                            System.out.print("Codigo a buscar: ");
                            Produto produto = cardapio.buscarPorCodigo(
                                    Integer.parseInt(scanner.nextLine().trim()));
                            // A implementacao de toString depende da subclasse concreta.
                            System.out.println(produto == null ? "Produto nao encontrado." : produto);
                            break;
                        }
                        case "6": {
                            System.out.print("1 - Nome | 2 - Preco: ");
                            String criterio = scanner.nextLine().trim();
                            if (criterio.equals("1")) cardapio.exibirProdutos(cardapio.listarPorNome());
                            else if (criterio.equals("2")) cardapio.exibirProdutos(cardapio.listarPorPreco());
                            else System.out.println("Criterio invalido.");
                            break;
                        }
                        case "7": {
                            boolean emPedidos = true;
                            while (emPedidos) {
                                System.out.println("\n------------ PEDIDOS ------------");
                                System.out.println("1 - Listar pedidos");
                                System.out.println("2 - Cadastrar pedido");
                                System.out.println("3 - Buscar pedido por codigo");
                                System.out.println("4 - Alterar pedido pendente");
                                System.out.println("5 - Excluir pedido");
                                System.out.println("6 - Ordenar e listar pedidos");
                                System.out.println("7 - Confirmar pagamento");
                                System.out.println("8 - Concluir pedido");
                                System.out.println("9 - Cancelar pedido");
                                System.out.println("0 - Voltar");
                                System.out.print("Opcao: ");
                                if (!scanner.hasNextLine()) {
                                    aberto = false;
                                    break;
                                }
                                String opcaoPedido = scanner.nextLine().trim();
                                try {
                                    switch (opcaoPedido) {
                                        case "1":
                                            gerenciadorPedidos.exibirPedidos(gerenciadorPedidos.listarPedidos());
                                            break;
                                        case "2":
                                        case "4": {
                                            boolean alterar = opcaoPedido.equals("4");
                                            System.out.print(alterar ? "Codigo a alterar: " : "Codigo do novo pedido: ");
                                            int codigo = Integer.parseInt(scanner.nextLine().trim());
                                            Pedido existente = gerenciadorPedidos.buscarPorCodigo(codigo);
                                            if (alterar && (existente == null
                                                    || existente.getStatus() != StatusPedido.PENDENTE)) {
                                                System.out.println("Pedido inexistente ou ja processado.");
                                                break;
                                            }
                                            if (!alterar && (codigo <= 0 || existente != null)) {
                                                System.out.println("O codigo deve ser positivo e ainda nao utilizado.");
                                                break;
                                            }
                                            ArrayList<ItemPedido> itens = new ArrayList<>();
                                            cardapio.exibirProdutos(cardapio.listarProdutos());
                                            while (true) {
                                                System.out.print("Codigo do produto (0 para terminar): ");
                                                int codigoProduto = Integer.parseInt(scanner.nextLine().trim());
                                                if (codigoProduto == 0) break;
                                                Produto produto = cardapio.buscarPorCodigo(codigoProduto);
                                                if (produto == null) {
                                                    System.out.println("Produto nao encontrado.");
                                                    continue;
                                                }
                                                System.out.print("Quantidade: ");
                                                int quantidade = Integer.parseInt(scanner.nextLine().trim());
                                                if (quantidade <= 0) {
                                                    System.out.println("A quantidade deve ser positiva.");
                                                    continue;
                                                }
                                                itens.add(new ItemPedido(produto, quantidade));
                                                System.out.println("Item incluido.");
                                            }
                                            if (itens.isEmpty()) {
                                                System.out.println(alterar
                                                        ? "Alteracao cancelada: o pedido precisa de itens."
                                                        : "Pedido nao criado: inclua pelo menos um item.");
                                                break;
                                            }
                                            FormaPagamento pagamento = null;
                                            while (pagamento == null) {
                                                System.out.print("Forma de pagamento: 1 - Pix | 2 - Dinheiro | 3 - Cartao: ");
                                                String escolha = scanner.nextLine().trim();
                                                if (escolha.equals("1")) pagamento = pagamentos.get(0);
                                                else if (escolha.equals("2")) pagamento = pagamentos.get(1);
                                                else if (escolha.equals("3")) {
                                                    System.out.print("Cartao: 1 - Credito | 2 - Debito: ");
                                                    String tipoCartao = scanner.nextLine().trim();
                                                    if (tipoCartao.equals("1")) pagamento = pagamentos.get(2);
                                                    else if (tipoCartao.equals("2")) pagamento = pagamentos.get(3);
                                                    else System.out.println("Escolha 1 ou 2.");
                                                } else System.out.println("Escolha 1, 2 ou 3.");
                                            }
                                            if (alterar) {
                                                if (gerenciadorPedidos.alterarItens(codigo, itens)) {
                                                    existente.setFormaPagamento(pagamento);
                                                    System.out.println("Pedido alterado.");
                                                } else {
                                                    System.out.println("Nao foi possivel alterar o pedido.");
                                                }
                                            } else {
                                                Pedido pedido = new Pedido(codigo, itens, pagamento);
                                                System.out.println(gerenciadorPedidos.adicionarPedido(pedido)
                                                        ? String.format("Pedido cadastrado. Total: R$ %.2f",
                                                                pedido.calcularTotal())
                                                        : "Nao foi possivel cadastrar o pedido.");
                                            }
                                            break;
                                        }
                                        case "3": {
                                            System.out.print("Codigo a buscar: ");
                                            Pedido pedido = gerenciadorPedidos.buscarPorCodigo(
                                                    Integer.parseInt(scanner.nextLine().trim()));
                                            if (pedido == null) System.out.println("Pedido nao encontrado.");
                                            else pedido.exibirDetalhes();
                                            break;
                                        }
                                        case "5": {
                                            System.out.print("Codigo a excluir: ");
                                            int codigo = Integer.parseInt(scanner.nextLine().trim());
                                            System.out.println(gerenciadorPedidos.excluirPedido(codigo)
                                                    ? "Pedido excluido." : "Pedido nao encontrado.");
                                            break;
                                        }
                                        case "6": {
                                            System.out.print("1 - Codigo | 2 - Total: ");
                                            String criterio = scanner.nextLine().trim();
                                            if (criterio.equals("1")) {
                                                gerenciadorPedidos.exibirPedidos(gerenciadorPedidos.listarPorCodigo());
                                            } else if (criterio.equals("2")) {
                                                gerenciadorPedidos.exibirPedidos(gerenciadorPedidos.listarPorTotal());
                                            } else System.out.println("Criterio invalido.");
                                            break;
                                        }
                                        case "7":
                                        case "8":
                                        case "9": {
                                            System.out.print("Codigo do pedido: ");
                                            Pedido pedido = gerenciadorPedidos.buscarPorCodigo(
                                                    Integer.parseInt(scanner.nextLine().trim()));
                                            if (pedido == null) {
                                                System.out.println("Pedido nao encontrado.");
                                            } else if (opcaoPedido.equals("7")) {
                                                System.out.println(pedido.confirmarPagamento()
                                                        ? "Pagamento registrado; pedido em processamento."
                                                        : "Pedido ja processado ou pagamento recusado.");
                                            } else if (opcaoPedido.equals("8")) {
                                                System.out.println(pedido.confirmarPedido()
                                                        ? "Pedido concluido."
                                                        : "O pedido precisa estar em processamento.");
                                            } else {
                                                System.out.println(pedido.cancelarPedido()
                                                        ? "Pedido cancelado."
                                                        : "Somente pedidos pendentes podem ser cancelados.");
                                            }
                                            break;
                                        }
                                        case "0":
                                            emPedidos = false;
                                            break;
                                        default:
                                            System.out.println("Opcao invalida.");
                                    }
                                } catch (IllegalArgumentException erro) {
                                    System.out.println("Entrada invalida: " + erro.getMessage());
                                }
                            }
                            break;
                        }
                        case "0":
                            System.out.println("Ate logo!");
                            aberto = false;
                            break;
                        default:
                            System.out.println("Opcao invalida.");
                    }
                } catch (IllegalArgumentException erro) {
                    System.out.println("Entrada invalida: " + erro.getMessage());
                } catch (NoSuchElementException fim) {
                    // Entrada fechada durante uma operacao: encerra o programa.
                    aberto = false;
                }
            }
        }
    }
}
