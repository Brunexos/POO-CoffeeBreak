package Entidades;

import java.util.ArrayList;

import Interfaces.FormaPagamento;

public class Pedido {
    private Integer codigo;
    private ArrayList<ItemPedido> itens;
    private FormaPagamento FormaPagamento; 
}
