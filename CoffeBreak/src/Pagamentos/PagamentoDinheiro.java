package Pagamentos;

import Interfaces.FormaPagamento;

/** Registro manual de um pagamento em dinheiro. */
public class PagamentoDinheiro implements FormaPagamento {
    /** Aprova o registro quando o valor recebido em dinheiro e positivo. */
    @Override
    public boolean pagar(double valor) {
        return Double.isFinite(valor) && valor > 0;
    }

    /** Nome desta forma de pagamento nos menus e tabelas. */
    @Override
    public String getNome() {
        return "Dinheiro";
    }
}
