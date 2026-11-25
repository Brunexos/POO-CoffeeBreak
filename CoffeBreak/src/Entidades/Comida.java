package Entidades;

/** Produto medido por peso em gramas. */
public class Comida extends Produto{
    private Double pesoGramas;

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

    /** Acrescenta o peso a descricao herdada de Produto. */
    @Override
    public String toString() {
        return super.toString() + "\n" + "pesoGramas: " + pesoGramas;
    }
}
