package Entidades;

import java.util.ArrayList;
import Enums.StatusPedido;
import Interfaces.FormaPagamento;

/** Pedido composto por itens e associado a uma forma de pagamento. */
public class Pedido {
    private Integer codigo;
    private ArrayList<ItemPedido> itens;
    private FormaPagamento formaPagamento;
    private StatusPedido status;

    /** Recebe os itens e a forma de pagamento; nao cria essas dependencias. */
    public Pedido(Integer codigo, ArrayList<ItemPedido> itens, FormaPagamento formaPagamento) {
        setCodigo(codigo);
        validarItens(itens);
        setFormaPagamento(formaPagamento);
        this.itens = new ArrayList<>(itens);
        this.status = StatusPedido.PENDENTE;
    }

    /** Soma os subtotais de todos os itens do pedido. */
    public double calcularTotal() {
        double total = 0;
        for (ItemPedido item : itens) {
            total += item.calcularSubtotal();
        }
        return total;
    }

    /** Solicita o pagamento; so avanca se a forma de pagamento aprovar. */
    public boolean confirmarPagamento() {
        if (status != StatusPedido.PENDENTE || !formaPagamento.pagar(calcularTotal())) {
            return false;
        }
        status = StatusPedido.PROCESSANDO;
        return true;
    }

    /** Conclui apenas um pedido cujo pagamento foi confirmado. */
    public boolean confirmarPedido() {
        if (status != StatusPedido.PROCESSANDO) {
            return false;
        }
        status = StatusPedido.CONCLUIDO;
        return true;
    }

    /** Cancela somente pedidos ainda pendentes. */
    public boolean cancelarPedido() {
        if (status != StatusPedido.PENDENTE) {
            return false;
        }
        status = StatusPedido.CANCELADO;
        return true;
    }

    /** Retorna o identificador do pedido. */
    public Integer getCodigo() {
        return codigo;
    }

    /** Define um identificador positivo. */
    public void setCodigo(Integer codigo) {
        if (codigo == null || codigo <= 0) {
            throw new IllegalArgumentException("O codigo deve ser positivo.");
        }
        this.codigo = codigo;
    }

    /** Devolve uma copia para impedir mudancas diretas na lista interna. */
    public ArrayList<ItemPedido> getItens() {
        return new ArrayList<>(itens);
    }

    /** Permite alterar os itens somente antes de confirmar o pagamento. */
    public void setItens(ArrayList<ItemPedido> itens) {
        if (status != StatusPedido.PENDENTE) {
            throw new IllegalStateException("Os itens de um pedido processado nao podem mudar.");
        }
        validarItens(itens);
        this.itens = new ArrayList<>(itens);
    }

    /** Retorna a forma de pagamento recebida pelo pedido. */
    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    /** Permite mudar a forma de pagamento somente enquanto o pedido esta pendente. */
    public void setFormaPagamento(FormaPagamento formaPagamento) {
        if (formaPagamento == null) {
            throw new IllegalArgumentException("A forma de pagamento e obrigatoria.");
        }
        if (status != null && status != StatusPedido.PENDENTE) {
            throw new IllegalStateException("O pagamento de um pedido processado nao pode mudar.");
        }
        this.formaPagamento = formaPagamento;
    }

    /** Retorna a etapa atual do pedido. */
    public StatusPedido getStatus() {
        return status;
    }

    /** Impede que o pedido seja criado ou alterado com uma lista vazia. */
    private void validarItens(ArrayList<ItemPedido> itens) {
        if (itens == null || itens.isEmpty() || itens.contains(null)) {
            throw new IllegalArgumentException("O pedido precisa de pelo menos um item valido.");
        }
    }
}
