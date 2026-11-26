package Entidades;

/** Produto medido por volume em mililitros. */
public class Bebida extends Produto {
    private Integer volumeML; // volume de uma unidade, em mililitros

    /** Cria uma bebida usando os atributos herdados de Produto. */
    public Bebida(String nome, Double preco, Integer quantidadeEstoque, Integer codigo, Integer volumeML) {
        super(nome, preco, quantidadeEstoque, codigo);
        setVolumeML(volumeML);
    }

    /** Retorna o volume da bebida. */
    public Integer getVolumeML() {
        return volumeML;
    }

    /** Altera o volume da bebida. */
    public void setVolumeML(Integer volumeML) {
        if (volumeML == null || volumeML <= 0) {
            throw new IllegalArgumentException("O volume deve ser positivo.");
        }
        this.volumeML = volumeML;
    }

    /** Identifica este produto como bebida na tabela do cardapio. */
    @Override
    public String getTipo() {
        return "Bebida";
    }

    /** Bebidas sao medidas em mililitros. */
    @Override
    public String getDetalhe() {
        return volumeML + " mL";
    }

    /** Copia os dados comuns (via super) e o volume; o Cardapio garante que novosDados e uma Bebida. */
    @Override
    public void atualizarDados(Produto novosDados) {
        super.atualizarDados(novosDados);
        setVolumeML(((Bebida) novosDados).getVolumeML());
    }

    /** Acrescenta o volume a descricao herdada de Produto. */
    @Override
    public String toString() {
        return super.toString() + "\n" + "volumeML: " + volumeML;
    }
}
