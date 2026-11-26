package Entidades;

/** Produto medido por peso em gramas. */
public class Comida extends Produto{
    private Double pesoGramas; // peso de uma porcao, em gramas

    /** Cria uma comida usando os atributos herdados de Produto. */
    public Comida(String nome, Double preco, Integer quantidadeEstoque, Integer codigo, Double pesoGramas) {
        super(nome, preco, quantidadeEstoque, codigo);
        setPesoGramas(pesoGramas);
    }

    /** Retorna o peso da porcao. */
    public Double getPesoGramas() {
        return pesoGramas;
    }

    /** Altera o peso da porcao. */
    public void setPesoGramas(Double pesoGramas) {
        if (pesoGramas == null || !Double.isFinite(pesoGramas) || pesoGramas <= 0) {
            throw new IllegalArgumentException("O peso deve ser positivo.");
        }
        this.pesoGramas = pesoGramas;
    }

    /** Identifica este produto como comida na tabela do cardapio. */
    @Override
    public String getTipo() {
        return "Comida";
    }

    /** Comidas sao medidas em gramas. */
    @Override
    public String getDetalhe() {
        return pesoGramas + " g";
    }

    /** Copia os dados comuns (via super) e o peso; o Cardapio garante que novosDados e uma Comida. */
    @Override
    public void atualizarDados(Produto novosDados) {
        super.atualizarDados(novosDados);
        setPesoGramas(((Comida) novosDados).getPesoGramas());
    }

    /** Acrescenta o peso a descricao herdada de Produto. */
    @Override
    public String toString() {
        return super.toString() + "\n" + "pesoGramas: " + pesoGramas;
    }
}
