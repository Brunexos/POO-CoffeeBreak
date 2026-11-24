package Pagamentos;

import Interfaces.FormaPagamento;

/** Registro manual de um pagamento em Pix, sem conexao bancaria. */
public class PagamentoPix implements FormaPagamento {
    @Override
    public boolean pagar(double valor) {
        return Double.isFinite(valor) && valor > 0;
    }

    @Override
    public String getNome() {
        return "Pix";
    }
}
