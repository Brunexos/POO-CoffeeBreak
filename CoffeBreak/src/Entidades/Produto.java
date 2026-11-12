package Entidades;

public abstract class Produto {
    private String nome;
    private Double preco;
    private Integer QuantidadeEstoque;
    private Integer codigo;

    public Produto(String nome, Double preco, Integer quantidadeEstoque, Integer codigo) {
        this.nome = nome;
        this.preco = preco;
        QuantidadeEstoque = quantidadeEstoque;
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getQuantidadeEstoque() {
        return QuantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        QuantidadeEstoque = quantidadeEstoque;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public void adicionarEstoque(int quantidade){
        if(quantidade > 0){
            QuantidadeEstoque += quantidade;
        }
    }

    public boolean removerEstoque(int quantidade){
        if(quantidade > 0 && quantidade <= QuantidadeEstoque){
            quantidade = quantidade - QuantidadeEstoque;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return  "Nome: " + nome + '\n' +
                "Preco: " + preco + '\n' +
                "Estoque: " + QuantidadeEstoque + '\n' +
                "Codigo: " + codigo;
    }
}
