package Gerenciamento;

import Entidades.Produto;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/** Guarda e gerencia os produtos disponiveis no Coffee Break. */
public class Cardapio {
    private final List<Produto> produtos;

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

    /** Substitui um produto, mantendo seu codigo e seu tipo original. */
    public boolean alterarProduto(int codigo, Produto substituto) {
        if (!produtoValido(substituto) || substituto.getCodigo() != codigo) {
            return false;
        }
        for (int indice = 0; indice < produtos.size(); indice++) {
            Produto atual = produtos.get(indice);
            if (atual.getCodigo() == codigo && atual.getClass() == substituto.getClass()) {
                produtos.set(indice, substituto);
                return true;
            }
        }
        return false;
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

    /** Confere os campos comuns antes de cadastrar ou alterar. */
    private boolean produtoValido(Produto produto) {
        return produto != null
                && produto.getCodigo() != null && produto.getCodigo() > 0
                && produto.getNome() != null && !produto.getNome().trim().isEmpty()
                && produto.getPreco() != null && Double.isFinite(produto.getPreco()) && produto.getPreco() > 0
                && produto.getQuantidadeEstoque() != null && produto.getQuantidadeEstoque() >= 0;
    }
}
