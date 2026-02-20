package br.ufpb.MH;

import java.io.Serializable;
import java.util.Objects;

public class Estoque implements Serializable {

    private String produto;
    private int quantidade;
    private double precoCustoUnitario;
    private double precoVendaUnitario;

    public Estoque(String produto, int quantidade, double precoCustoUnitario, double precoVendaUnitario) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoCustoUnitario = precoCustoUnitario;
        this.precoVendaUnitario = precoVendaUnitario;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) throws QuantidadeInvalidaException {
        if (quantidade < 0) {
            throw new QuantidadeInvalidaException("Quantidade não pode ser negativa.");
        }
        this.quantidade = quantidade;
    }

    public double getPrecoCustoUnitario() {
        return precoCustoUnitario;
    }


    public double getPrecoVendaUnitario() {
        return precoVendaUnitario;
    }

    // Métodos

    public double calcularCustoTotal() {
        return precoCustoUnitario * quantidade;
    }

    public double calcularValorVendaTotal() {
        return precoVendaUnitario * quantidade;
    }

    public double calcularLucroUnitario() {
        return precoVendaUnitario - precoCustoUnitario;
    }

    public double calcularLucroTotal() {
        return calcularLucroUnitario() * quantidade;
    }

    // Atualizar estoque após venda
    public void registrarVenda(int quantidadeVendida) throws EstoqueInsuficienteException {
        if (quantidadeVendida > quantidade) {
            throw new EstoqueInsuficienteException("Estoque insuficiente para a venda de " + quantidadeVendida + " unidades.");
        }
        this.quantidade -= quantidadeVendida;
    }

    // Atualizar estoque após compra
    public void registrarCompra(int quantidadeComprada) {
        this.quantidade += quantidadeComprada;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Estoque estoque = (Estoque) o;
        return quantidade == estoque.quantidade &&
                Double.compare(precoCustoUnitario, estoque.precoCustoUnitario) == 0 &&
                Double.compare(precoVendaUnitario, estoque.precoVendaUnitario) == 0 &&
                Objects.equals(produto, estoque.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(produto, quantidade, precoCustoUnitario, precoVendaUnitario);
    }

    @Override
    public String toString() {
        return "Produto: " + produto +
                " | Quantidade: " + quantidade +
                " | Preço Custo Unitário: R$" + precoCustoUnitario +
                " | Preço Venda Unitário: R$" + precoVendaUnitario +
                " | Lucro Unitário: R$" + calcularLucroUnitario();
    }
}