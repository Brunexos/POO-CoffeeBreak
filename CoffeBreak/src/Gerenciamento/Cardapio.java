package Gerenciamento;

import Entidades.Produto;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/** Guarda e gerencia os produtos disponiveis no Coffee Break. */
public class Cardapio {
    private final List<Produto> produtos; // estrutura de dados que guarda comidas e bebidas

    /** Recebe a estrutura de dados de fora para facilitar a injecao de dependencia. */
    public Cardapio(List<Produto> produtos) {
        if (produtos == null) {
            throw new IllegalArgumentException("A lista de produtos nao pode ser nula.");
        }
        this.produtos = produtos;
    }

    /** Inclui um produto valido, desde que seu codigo ainda nao exista. */
    public boolean adicionarProduto(Produto produto) {
        if (!produtoValido(produto) || buscarPorCodigo(produto.getCodigo()) != null) {
            return false;
        }
        return produtos.add(produto);
    }

    /** Encontra um produto pelo codigo ou retorna null. */
    public Produto buscarPorCodigo(int codigo) {
        for (Produto produto : produtos) {
            if (produto.getCodigo() != null && produto.getCodigo() == codigo) {
                return produto;
            }
        }
        return null;
    }

    /** Altera os dados de um produto, mantendo seu codigo e seu tipo original. */
    public boolean alterarProduto(int codigo, Produto novosDados) {
        if (!produtoValido(novosDados) || novosDados.getCodigo() != codigo) {
            return false;
        }
        Produto atual = buscarPorCodigo(codigo);
        // Uma comida so pode receber dados de comida, e uma bebida so de bebida.
        if (atual == null || atual.getClass() != novosDados.getClass()) {
            return false;
        }
        // Altera o mesmo objeto, para que os pedidos que ja usam o produto vejam o estoque novo.
        atual.atualizarDados(novosDados);
        return true;
    }

    /** Exclui um produto pelo codigo, se ele existir. */
    public boolean excluirProduto(int codigo) {
        Produto produto = buscarPorCodigo(codigo);
        return produto != null && produtos.remove(produto);
    }

    /** Retorna uma copia para impedir alteracoes externas na lista interna. */
    public List<Produto> listarProdutos() {
        return new ArrayList<>(produtos);
    }

    /** Devolve uma lista ordenada por nome, sem mudar a ordem armazenada. */
    public List<Produto> listarPorNome() {
        List<Produto> ordenados = listarProdutos();
        ordenados.sort(Comparator.comparing(Produto::getNome, String.CASE_INSENSITIVE_ORDER));
        return ordenados;
    }

    /** Devolve uma lista ordenada por preco, sem mudar a ordem armazenada. */
    public List<Produto> listarPorPreco() {
        List<Produto> ordenados = listarProdutos();
        ordenados.sort(Comparator.comparing(Produto::getPreco));
        return ordenados;
    }

    /** Exibe os produtos recebidos em uma tabela, inclusive listas ordenadas. */
    public void exibirProdutos(List<Produto> produtosParaExibir) {
        if (produtosParaExibir == null || produtosParaExibir.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }
        // Larguras fixas no printf alinham as colunas da tabela.
        System.out.printf("%-6s %-8s %-22s %10s %8s %12s%n",
                "Codigo", "Tipo", "Nome", "Preco", "Estoque", "Peso/Volume");
        for (Produto produto : produtosParaExibir) {
            // Polimorfismo: getTipo() e getDetalhe() executam a versao de Comida ou de Bebida.
            System.out.printf("%-6d %-8s %-22.22s %10.2f %8d %12s%n",
                    produto.getCodigo(), produto.getTipo(), produto.getNome(), produto.getPreco(),
                    produto.getQuantidadeEstoque(), produto.getDetalhe());
        }
    }

    /** Confere os campos comuns antes de cadastrar ou alterar. */
    private boolean produtoValido(Produto produto) {
        return produto != null
                && produto.getCodigo() != null && produto.getCodigo() > 0
                && produto.getNome() != null && !produto.getNome().trim().isEmpty()
                && produto.getPreco() != null && Double.isFinite(produto.getPreco()) && produto.getPreco() > 0
                && produto.getQuantidadeEstoque() != null && produto.getQuantidadeEstoque() >= 0;
    }
}
