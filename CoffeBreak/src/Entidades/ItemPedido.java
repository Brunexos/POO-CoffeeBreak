package Entidades;

/** Um produto e sua quantidade dentro de um pedido. */
public class ItemPedido {
    private final Produto produto;      // produto do cardapio que foi pedido
    private final int quantidade;       // unidades pedidas desse produto
    private final double precoUnitario; // preco copiado no momento do pedido

    /** Guarda o preco no momento da inclusao para preservar o valor do pedido. */
    public ItemPedido(Produto produto, int quantidade) {
        if (produto == null || produto.getPreco() == null
                || !Double.isFinite(produto.getPreco()) || produto.getPreco() <= 0) {
            throw new IllegalArgumentException("Produto e preco devem ser validos.");
        }
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser positiva.");
        }
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = produto.getPreco();
    }

    /** Retorna o produto associado ao item. */
    public Produto getProduto() {
        return produto;
    }

    /** Retorna o numero de unidades. */
    public int getQuantidade() {
        return quantidade;
    }

    /** Retorna o preco registrado quando o item foi criado. */
    public double getPrecoUnitario() {
        return precoUnitario;
    }

    /** Calcula o valor deste item sem depender de alteracoes futuras no cardapio. */
    public double calcularSubtotal() {
        return precoUnitario * quantidade;
    }
}
