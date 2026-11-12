package Entidades;

public class Comida extends Produto{
    private Double pesoGramas;

    public Comida(String nome, Double preco, Integer quantidadeEstoque, Integer codigo, Double pesoGramas) {
        super(nome, preco, quantidadeEstoque, codigo);
        this.pesoGramas = pesoGramas;
    }

    public Double getPesoGramas() {
        return pesoGramas;
    }

    public void setPesoGramas(Double pesoGramas) {
        this.pesoGramas = pesoGramas;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" + "pesoGramas: " + pesoGramas;
    }
}
