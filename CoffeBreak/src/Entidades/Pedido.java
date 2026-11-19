package Entidades;

import java.util.ArrayList;

import Enums.StatusPedido;
import Interfaces.FormaPagamento;

public class Pedido {
    private Integer codigo;
    private ArrayList<ItemPedido> itens;
    private FormaPagamento FormaPagamento;
    private StatusPedido status;

    public Pedido(Integer codigo, ArrayList<ItemPedido> itens, FormaPagamento formaPagamento) {
        this.codigo = codigo;
        this.itens = new ArrayList<>();
        this.FormaPagamento = formaPagamento;
        this.status = StatusPedido.PENDENTE;
    }

    public void confirmarPedido(){
        if(status == StatusPedido.PROCESSANDO){
            status = StatusPedido.CONCLUIDO;
        }
    }

    public void confirmarPagamento(){
        if(status == StatusPedido.PENDENTE){
            status = StatusPedido.PROCESSANDO;
        }
    }

    public boolean cancelarPedido(){
        if(status == StatusPedido.PENDENTE){
            status = StatusPedido.CANCELADO;
            return true;
        } else
            return false;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public ArrayList<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(ArrayList<ItemPedido> itens) {
        this.itens = itens;
    }

    public FormaPagamento getFormaPagamento() {
        return FormaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        FormaPagamento = formaPagamento;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }
}
