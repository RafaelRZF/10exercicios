package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int id;
    private Cliente cliente;
    private LocalDate dataPedido;
    private String status;

    // Lista de itens para representar o relacionamento de muitos para muitos
    private List<ItemPedido> itens;

    public Pedido() {
        this.itens = new ArrayList<>();
    }

    public Pedido(int id, Cliente cliente, LocalDate dataPedido, String status) {
        this.id = id;
        this.cliente = cliente;
        this.dataPedido = dataPedido;
        this.status = status;
        this.itens = new ArrayList<>();
    }


    public void adicionarItem(ItemPedido item) {
        this.itens.add(item);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }
}