package Entidades;

public class Bebida extends Produto {
    private Integer volumeML;

    public Bebida(String nome, Double preco, Integer quantidadeEstoque, Integer codigo, Integer volumeML) {
        super(nome, preco, quantidadeEstoque, codigo);
        this.volumeML = volumeML;
    }

    public Integer getVolumeML() {
        return volumeML;
    }

    public void setVolumeML(Integer volumeML) {
        this.volumeML = volumeML;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" + "volumeML: " + volumeML;
    }
}
