package Entidades;

/** Dados e comportamentos comuns a comidas e bebidas. */
public abstract class Produto {
    private String nome;
    private Double preco;
    private Integer quantidadeEstoque;
    private Integer codigo;

    /** Inicializa os atributos compartilhados pelas subclasses. */
    public Produto(String nome, Double preco, Integer quantidadeEstoque, Integer codigo) {
        this.nome = nome;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
        this.codigo = codigo;
    }

    /** Retorna o nome exibido no cardapio. */
    public String getNome() {
        return nome;
    }

    /** Altera o nome do produto. */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /** Retorna o preco atual do produto. */
    public Double getPreco() {
        return preco;
    }

    /** Altera o preco atual do produto. */
    public void setPreco(Double preco) {
        this.preco = preco;
    }

    /** Retorna a quantidade disponivel em estoque. */
    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    /** Define a quantidade em estoque. */
    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    /** Retorna o identificador do produto. */
    public Integer getCodigo() {
        return codigo;
    }

    /** Altera o identificador do produto. */
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    /** Acrescenta somente quantidades positivas ao estoque. */
    public void adicionarEstoque(int quantidade){
        if(quantidade > 0){
            quantidadeEstoque += quantidade;
        }
    }

    /** Retira unidades quando existe estoque suficiente. */
    public boolean removerEstoque(int quantidade){
        if(quantidade > 0 && quantidade <= quantidadeEstoque){
            quantidadeEstoque -= quantidade;
            return true;
        }
        return false;
    }

    /** Descreve os dados comuns; as subclasses acrescentam seus dados especificos. */
    @Override
    public String toString() {
        return  "Nome: " + nome + '\n' +
                "Preco: " + preco + '\n' +
                "Estoque: " + quantidadeEstoque + '\n' +
                "Codigo: " + codigo;
    }
}
