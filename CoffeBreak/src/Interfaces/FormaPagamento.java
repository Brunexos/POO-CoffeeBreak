package Interfaces;

/**
 * Contrato das formas de pagamento registradas pelo atendente.
 * Pedido depende desta interface, nao de Pix, dinheiro ou cartao (inversao de dependencia).
 */
public interface FormaPagamento {
    /** Valida o valor informado para registrar o pagamento. */
    boolean pagar(double valor);

    /** Nome apresentado no menu e nos detalhes do pedido. */
    String getNome();
}
