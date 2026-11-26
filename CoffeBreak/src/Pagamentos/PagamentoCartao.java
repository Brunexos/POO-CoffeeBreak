package Pagamentos;

import Interfaces.FormaPagamento;

/** Registro manual de um pagamento com cartao de credito ou debito. */
public class PagamentoCartao implements FormaPagamento {
    /** Modalidades de cartao aceitas pelo Coffee Break. */
    public enum Tipo {
        CREDITO,
        DEBITO
    }

    private final Tipo tipo; // modalidade fixa deste objeto: credito ou debito

    /** Define a modalidade escolhida antes de associar o pagamento ao pedido. */
    public PagamentoCartao(Tipo tipo) {
        if (tipo == null) {
            throw new IllegalArgumentException("Escolha credito ou debito.");
        }
        this.tipo = tipo;
    }

    /** Valida o valor que o atendente esta registrando. */
    @Override
    public boolean pagar(double valor) {
        return Double.isFinite(valor) && valor > 0;
    }

    /** Informa no pedido se o cartao e de credito ou debito. */
    @Override
    public String getNome() {
        return tipo == Tipo.CREDITO ? "Cartao de credito" : "Cartao de debito";
    }
}
