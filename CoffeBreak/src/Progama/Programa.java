package Progama;

import Entidades.Bebida;
import Entidades.Comida;
import Entidades.ItemPedido;
import Entidades.Pedido;
import Entidades.Produto;
import Gerenciamento.Cardapio;
import Gerenciamento.GerenciadorPedidos;
import Interfaces.FormaPagamento;
import Pagamentos.PagamentoCartao;
import Pagamentos.PagamentoDinheiro;
import Pagamentos.PagamentoPix;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Ponto de entrada e interface textual do Coffee Break.
 * Scanner, Cardapio, GerenciadorPedidos e FormaPagamento chegam pelo construtor.
 * Programa conversa com o usuario; os gerenciadores guardam os registros.
 */
public class Programa {
    private final Scanner entrada;
    private final Cardapio cardapio;
    private final GerenciadorPedidos gerenciadorPedidos;
    private final List<FormaPagamento> formasPagamento;

    public Programa(Scanner entrada, Cardapio cardapio,
            GerenciadorPedidos gerenciadorPedidos,
            List<FormaPagamento> formasPagamento) {
        this.entrada = entrada;
        this.cardapio = cardapio;
        this.gerenciadorPedidos = gerenciadorPedidos;
        if (formasPagamento == null || formasPagamento.size() != 4
                || formasPagamento.contains(null)) {
            throw new IllegalArgumentException("Informe Pix, dinheiro, credito e debito.");
        }
        this.formasPagamento = new ArrayList<>(formasPagamento);
    }

    /** Cria as dependencias e inicia o menu principal. */
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Cardapio cardapio = new Cardapio(new ArrayList<>());
        GerenciadorPedidos gerenciador = new GerenciadorPedidos(new ArrayList<>());
        List<FormaPagamento> pagamentos = new ArrayList<>();
        pagamentos.add(new PagamentoPix());
        pagamentos.add(new PagamentoDinheiro());
        pagamentos.add(new PagamentoCartao(PagamentoCartao.Tipo.CREDITO));
        pagamentos.add(new PagamentoCartao(PagamentoCartao.Tipo.DEBITO));
        Programa programa = new Programa(entrada, cardapio, gerenciador, pagamentos);
        programa.cadastrarDadosIniciais();
        programa.cadastrarPedidosIniciais();
        programa.executar();
        entrada.close();
    }

    /** Carrega sete comidas e sete bebidas para demonstrar as operacoes. */
    private void cadastrarDadosIniciais() {
        if (!cardapio.listarProdutos().isEmpty()) return;
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
    }

    /** Cria sete pedidos pendentes para demonstrar busca, ordenacao e CRUD. */
    private void cadastrarPedidosIniciais() {
        if (!gerenciadorPedidos.listarPedidos().isEmpty()) return;
        for (int codigo = 1; codigo <= 7; codigo++) {
            ArrayList<ItemPedido> itens = new ArrayList<>();
            itens.add(new ItemPedido(cardapio.buscarPorCodigo(codigo), codigo % 3 + 1));
            itens.add(new ItemPedido(cardapio.buscarPorCodigo(codigo + 7), 1));
            FormaPagamento pagamento = formasPagamento.get((codigo - 1) % formasPagamento.size());
            gerenciadorPedidos.adicionarPedido(new Pedido(codigo, itens, pagamento));
        }
    }

    /** Mantem o programa aberto ate o usuario escolher sair. */
    private void executar() {
        int opcao;
        do {
            System.out.println("\n========== COFFEE BREAK ==========");
            System.out.println("1 - Listar produtos");
            System.out.println("2 - Cadastrar produto");
            System.out.println("3 - Alterar produto");
            System.out.println("4 - Excluir produto");
            System.out.println("5 - Buscar produto por codigo");
            System.out.println("6 - Ordenar e listar produtos");
            System.out.println("7 - Gerenciar pedidos");
            System.out.println("0 - Sair");
            opcao = lerInteiro("Opcao: ");
            switch (opcao) {
                case 1: listarProdutos(); break;
                case 2: cadastrarProduto(); break;
                case 3: alterarProduto(); break;
                case 4: excluirProduto(); break;
                case 5: buscarProduto(); break;
                case 6: ordenarProdutos(); break;
                case 7: menuPedidos(); break;
                case 0: System.out.println("Ate logo!"); break;
                default: System.out.println("Opcao invalida.");
            }
        } while (opcao != 0);
    }

    /** Exibe os produtos em uma tabela; recebe Produto para aceitar ambas as subclasses. */
    private void listarProdutos() {
        mostrarProdutos(cardapio.listarProdutos());
    }

    /** Formata qualquer lista de produtos como tabela. */
    private void mostrarProdutos(List<Produto> produtos) {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }
        System.out.printf("%-6s %-8s %-22s %10s %8s %12s%n", "Codigo", "Tipo", "Nome", "Preco", "Estoque", "Peso/Volume");
        for (Produto produto : produtos) {
            String tipo = produto instanceof Comida ? "Comida" : "Bebida";
            String detalhe = produto instanceof Comida
                    ? ((Comida) produto).getPesoGramas() + " g"
                    : ((Bebida) produto).getVolumeML() + " mL";
            System.out.printf("%-6d %-8s %-22.22s %10.2f %8d %12s%n",
                    produto.getCodigo(), tipo, produto.getNome(), produto.getPreco(),
                    produto.getQuantidadeEstoque(), detalhe);
        }
    }

    /** Inclui uma comida ou bebida com codigo unico. */
    private void cadastrarProduto() {
        int tipo = lerInteiro("1 - Comida | 2 - Bebida: ");
        if (tipo != 1 && tipo != 2) {
            System.out.println("Tipo invalido.");
            return;
        }
        int codigo = lerInteiro("Codigo: ");
        if (codigo <= 0 || cardapio.buscarPorCodigo(codigo) != null) {
            System.out.println("O codigo deve ser positivo e ainda nao utilizado.");
            return;
        }
        String nome = lerTexto("Nome: ");
        double preco = lerDoublePositivo("Preco: ");
        int estoque = lerInteiroNaoNegativo("Quantidade em estoque: ");
        if (tipo == 1) {
            double peso = lerDoublePositivo("Peso em gramas: ");
            cardapio.adicionarProduto(new Comida(nome, preco, estoque, codigo, peso));
        } else {
            int volume = lerInteiroPositivo("Volume em mL: ");
            cardapio.adicionarProduto(new Bebida(nome, preco, estoque, codigo, volume));
        }
        System.out.println("Produto cadastrado.");
    }

    /** Atualiza os dados de um produto preservando seu codigo e seu tipo. */
    private void alterarProduto() {
        Produto produto = cardapio.buscarPorCodigo(lerInteiro("Codigo a alterar: "));
        if (produto == null) {
            System.out.println("Produto nao encontrado.");
            return;
        }
        String nome = lerTexto("Novo nome: ");
        double preco = lerDoublePositivo("Novo preco: ");
        int estoque = lerInteiroNaoNegativo("Novo estoque: ");
        Produto substituto;
        if (produto instanceof Comida) {
            double peso = lerDoublePositivo("Novo peso em gramas: ");
            substituto = new Comida(nome, preco, estoque, produto.getCodigo(), peso);
        } else {
            int volume = lerInteiroPositivo("Novo volume em mL: ");
            substituto = new Bebida(nome, preco, estoque, produto.getCodigo(), volume);
        }
        System.out.println(cardapio.alterarProduto(produto.getCodigo(), substituto)
                ? "Produto alterado." : "Nao foi possivel alterar o produto.");
    }

    /** Remove o produto identificado por seu codigo. */
    private void excluirProduto() {
        int codigo = lerInteiro("Codigo a excluir: ");
        System.out.println(cardapio.excluirProduto(codigo)
                ? "Produto excluido." : "Produto nao encontrado.");
    }

    /** Busca e mostra um produto pelo codigo. */
    private void buscarProduto() {
        Produto produto = cardapio.buscarPorCodigo(lerInteiro("Codigo a buscar: "));
        System.out.println(produto == null ? "Produto nao encontrado." : produto);
    }

    /** Ordena a lista por nome ou preco e a exibe. */
    private void ordenarProdutos() {
        int criterio = lerInteiro("1 - Nome | 2 - Preco: ");
        if (criterio == 1) {
            mostrarProdutos(cardapio.listarPorNome());
        } else if (criterio == 2) {
            mostrarProdutos(cardapio.listarPorPreco());
        } else {
            System.out.println("Criterio invalido.");
            return;
        }
    }

    /** Apresenta as operacoes de pedidos sem misturar entrada de dados ao gerenciador. */
    private void menuPedidos() {
        int opcao;
        do {
            System.out.println("\n------------ PEDIDOS ------------");
            System.out.println("1 - Listar pedidos");
            System.out.println("2 - Cadastrar pedido");
            System.out.println("3 - Buscar pedido por codigo");
            System.out.println("4 - Alterar itens de pedido pendente");
            System.out.println("5 - Excluir pedido");
            System.out.println("6 - Ordenar e listar pedidos");
            System.out.println("7 - Confirmar pagamento");
            System.out.println("8 - Concluir pedido");
            System.out.println("9 - Cancelar pedido");
            System.out.println("0 - Voltar");
            opcao = lerInteiro("Opcao: ");
            switch (opcao) {
                case 1: mostrarPedidos(gerenciadorPedidos.listarPedidos()); break;
                case 2: cadastrarPedido(); break;
                case 3: buscarPedido(); break;
                case 4: alterarPedido(); break;
                case 5: excluirPedido(); break;
                case 6: ordenarPedidos(); break;
                case 7: confirmarPagamento(); break;
                case 8: concluirPedido(); break;
                case 9: cancelarPedido(); break;
                case 0: break;
                default: System.out.println("Opcao invalida.");
            }
        } while (opcao != 0);
    }

    /** Monta um pedido a partir de produtos encontrados no Cardapio. */
    private void cadastrarPedido() {
        int codigo = lerInteiroPositivo("Codigo do novo pedido: ");
        if (gerenciadorPedidos.buscarPorCodigo(codigo) != null) {
            System.out.println("Esse codigo ja esta em uso.");
            return;
        }
        ArrayList<ItemPedido> itens = lerItensPedido();
        if (itens.isEmpty()) {
            System.out.println("Pedido nao criado: inclua pelo menos um item.");
            return;
        }
        FormaPagamento pagamento = escolherFormaPagamento();
        Pedido pedido = new Pedido(codigo, itens, pagamento);
        System.out.println(gerenciadorPedidos.adicionarPedido(pedido)
                ? "Pedido cadastrado. Total: R$ " + String.format("%.2f", pedido.calcularTotal())
                : "Nao foi possivel cadastrar o pedido.");
    }

    /** Le produtos e quantidades; codigo zero encerra a selecao. */
    private ArrayList<ItemPedido> lerItensPedido() {
        ArrayList<ItemPedido> itens = new ArrayList<>();
        mostrarProdutos(cardapio.listarProdutos());
        while (true) {
            int codigoProduto = lerInteiro("Codigo do produto (0 para terminar): ");
            if (codigoProduto == 0) return itens;
            Produto produto = cardapio.buscarPorCodigo(codigoProduto);
            if (produto == null) {
                System.out.println("Produto nao encontrado.");
                continue;
            }
            int quantidade = lerInteiroPositivo("Quantidade: ");
            itens.add(new ItemPedido(produto, quantidade));
            System.out.println("Item incluido.");
        }
    }

    /** Escolhe uma implementacao da interface e a modalidade do cartao. */
    private FormaPagamento escolherFormaPagamento() {
        while (true) {
            int opcao = lerInteiro("Forma de pagamento: 1 - Pix | 2 - Dinheiro | 3 - Cartao: ");
            if (opcao == 1 || opcao == 2) return formasPagamento.get(opcao - 1);
            if (opcao == 3) {
                while (true) {
                    int tipo = lerInteiro("Cartao: 1 - Credito | 2 - Debito: ");
                    if (tipo == 1) return formasPagamento.get(2);
                    if (tipo == 2) return formasPagamento.get(3);
                    System.out.println("Escolha 1 ou 2.");
                }
            }
            System.out.println("Escolha 1, 2 ou 3.");
        }
    }

    /** Mostra todos os pedidos em formato tabular. */
    private void mostrarPedidos(List<Pedido> pedidos) {
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido cadastrado.");
            return;
        }
        System.out.printf("%-8s %-8s %12s %-14s %-10s%n", "Codigo", "Itens", "Total", "Status", "Pagamento");
        for (Pedido pedido : pedidos) {
            System.out.printf("%-8d %-8d %12.2f %-14s %-10s%n", pedido.getCodigo(),
                    pedido.getItens().size(), pedido.calcularTotal(), pedido.getStatus(),
                    pedido.getFormaPagamento().getNome());
        }
    }

    /** Exibe cada item e o total de um pedido buscado por codigo. */
    private void buscarPedido() {
        Pedido pedido = gerenciadorPedidos.buscarPorCodigo(lerInteiro("Codigo a buscar: "));
        if (pedido == null) {
            System.out.println("Pedido nao encontrado.");
            return;
        }
        System.out.println("Pedido " + pedido.getCodigo() + " - " + pedido.getStatus()
                + " - " + pedido.getFormaPagamento().getNome());
        System.out.printf("%-22s %8s %12s %12s%n", "Produto", "Qtd.", "Preco", "Subtotal");
        for (ItemPedido item : pedido.getItens()) {
            System.out.printf("%-22.22s %8d %12.2f %12.2f%n", item.getProduto().getNome(),
                    item.getQuantidade(), item.getPrecoUnitario(), item.calcularSubtotal());
        }
        System.out.printf("Total: R$ %.2f%n", pedido.calcularTotal());
    }

    /** Substitui os itens somente enquanto o pedido estiver pendente. */
    private void alterarPedido() {
        int codigo = lerInteiro("Codigo a alterar: ");
        Pedido pedido = gerenciadorPedidos.buscarPorCodigo(codigo);
        if (pedido == null || pedido.getStatus() != Enums.StatusPedido.PENDENTE) {
            System.out.println("Pedido inexistente ou ja processado.");
            return;
        }
        ArrayList<ItemPedido> itens = lerItensPedido();
        if (itens.isEmpty()) {
            System.out.println("Alteracao cancelada: o pedido precisa de itens.");
            return;
        }
        if (gerenciadorPedidos.alterarItens(codigo, itens)) {
            pedido.setFormaPagamento(escolherFormaPagamento());
            System.out.println("Pedido alterado.");
        } else {
            System.out.println("Nao foi possivel alterar o pedido.");
        }
    }

    /** Exclui o registro informado pelo usuario. */
    private void excluirPedido() {
        int codigo = lerInteiro("Codigo a excluir: ");
        System.out.println(gerenciadorPedidos.excluirPedido(codigo)
                ? "Pedido excluido." : "Pedido nao encontrado.");
    }

    /** Lista pedidos ordenados por codigo ou valor total. */
    private void ordenarPedidos() {
        int criterio = lerInteiro("1 - Codigo | 2 - Total: ");
        if (criterio == 1) mostrarPedidos(gerenciadorPedidos.listarPorCodigo());
        else if (criterio == 2) mostrarPedidos(gerenciadorPedidos.listarPorTotal());
        else System.out.println("Criterio invalido.");
    }

    /** Registra o pagamento atraves da interface FormaPagamento. */
    private void confirmarPagamento() {
        Pedido pedido = gerenciadorPedidos.buscarPorCodigo(lerInteiro("Codigo do pedido: "));
        System.out.println(pedido != null && pedido.confirmarPagamento()
                ? "Pagamento registrado; pedido em processamento."
                : "Pedido inexistente, ja processado ou pagamento recusado.");
    }

    /** Conclui somente pedidos que ja tiveram pagamento registrado. */
    private void concluirPedido() {
        Pedido pedido = gerenciadorPedidos.buscarPorCodigo(lerInteiro("Codigo do pedido: "));
        System.out.println(pedido != null && pedido.confirmarPedido()
                ? "Pedido concluido." : "O pedido precisa estar em processamento.");
    }

    /** Cancela somente pedidos pendentes. */
    private void cancelarPedido() {
        Pedido pedido = gerenciadorPedidos.buscarPorCodigo(lerInteiro("Codigo do pedido: "));
        System.out.println(pedido != null && pedido.cancelarPedido()
                ? "Pedido cancelado." : "Somente pedidos pendentes podem ser cancelados.");
    }

    /** Exige texto nao vazio. */
    private String lerTexto(String mensagem) {
        String texto;
        do {
            System.out.print(mensagem);
            texto = entrada.nextLine().trim();
        } while (texto.isEmpty());
        return texto;
    }

    /** Repete a leitura ate receber um inteiro valido. */
    private int lerInteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Integer.parseInt(entrada.nextLine().trim());
            } catch (NumberFormatException erro) {
                System.out.println("Digite um numero inteiro valido.");
            }
        }
    }

    /** Le zero ou um inteiro positivo. */
    private int lerInteiroNaoNegativo(String mensagem) {
        int valor;
        do { valor = lerInteiro(mensagem); } while (valor < 0);
        return valor;
    }

    /** Le um inteiro estritamente positivo. */
    private int lerInteiroPositivo(String mensagem) {
        int valor;
        do { valor = lerInteiro(mensagem); } while (valor <= 0);
        return valor;
    }

    /** Aceita ponto ou virgula decimal e rejeita valores nao positivos. */
    private double lerDoublePositivo(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                double valor = Double.parseDouble(entrada.nextLine().trim().replace(',', '.'));
                if (Double.isFinite(valor) && valor > 0) return valor;
            } catch (NumberFormatException erro) {
                // A mensagem abaixo tambem cobre entrada nao numerica.
            }
            System.out.println("Digite um valor positivo valido.");
        }
    }
}
