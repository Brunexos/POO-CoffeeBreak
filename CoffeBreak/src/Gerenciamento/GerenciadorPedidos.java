package Gerenciamento;

import Entidades.ItemPedido;
import Entidades.Pedido;
import Enums.StatusPedido;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/** Guarda os pedidos e centraliza suas operacoes de cadastro e consulta. */
public class GerenciadorPedidos {
    private final List<Pedido> pedidos;

    /** Recebe a estrutura de dados usada para guardar pedidos. */
    public GerenciadorPedidos(List<Pedido> pedidos) {
        if (pedidos == null) {
            throw new IllegalArgumentException("A lista de pedidos nao pode ser nula.");
        }
        this.pedidos = pedidos;
    }

    /** Cadastra somente pedidos com codigo ainda nao utilizado. */
    public boolean adicionarPedido(Pedido pedido) {
        return pedido != null && buscarPorCodigo(pedido.getCodigo()) == null
                && pedidos.add(pedido);
    }

    /** Busca um pedido pelo codigo. */
    public Pedido buscarPorCodigo(int codigo) {
        for (Pedido pedido : pedidos) {
            if (pedido.getCodigo() == codigo) return pedido;
        }
        return null;
    }

    /** Altera os itens de um pedido que ainda esta pendente. */
    public boolean alterarItens(int codigo, ArrayList<ItemPedido> novosItens) {
        Pedido pedido = buscarPorCodigo(codigo);
        if (pedido == null || pedido.getStatus() != StatusPedido.PENDENTE
                || novosItens == null || novosItens.isEmpty() || novosItens.contains(null)) {
            return false;
        }
        pedido.setItens(novosItens);
        return true;
    }

    /** Exclui um registro pelo codigo. */
    public boolean excluirPedido(int codigo) {
        Pedido pedido = buscarPorCodigo(codigo);
        return pedido != null && pedidos.remove(pedido);
    }

    /** Devolve copia da lista para impedir inclusoes externas. */
    public List<Pedido> listarPedidos() {
        return new ArrayList<>(pedidos);
    }

    /** Retorna uma copia ordenada por codigo. */
    public List<Pedido> listarPorCodigo() {
        List<Pedido> ordenados = listarPedidos();
        ordenados.sort(Comparator.comparing(Pedido::getCodigo));
        return ordenados;
    }

    /** Retorna uma copia ordenada por valor total. */
    public List<Pedido> listarPorTotal() {
        List<Pedido> ordenados = listarPedidos();
        ordenados.sort(Comparator.comparingDouble(Pedido::calcularTotal));
        return ordenados;
    }
}
