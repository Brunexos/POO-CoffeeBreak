package Enums;

/** Etapas permitidas para o ciclo de vida de um pedido. */
public enum StatusPedido {
    PENDENTE,    // criado; itens e pagamento ainda podem mudar
    PROCESSANDO, // pagamento registrado e estoque baixado
    CONCLUIDO,   // pedido entregue ao cliente
    CANCELADO    // cancelado antes do pagamento
}
