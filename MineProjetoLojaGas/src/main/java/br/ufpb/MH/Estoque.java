package br.ufpb.MH;

import java.io.Serializable;
import java.util.Objects;

public class Estoque implements Serializable {

    private String produto;
    private int quantidade;
    private double precoCompraUnidade;
    private double precoVendaUnidade; // NOVO campo

    public Estoque(String produto, int quantidade, double precoCompraUnidade){
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoCompraUnidade = precoCompraUnidade;
        this.precoVendaUnidade = precoCompraUnidade; // inicializa igual, pode ser alterado depois
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

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecoCompraUnidade() {
        return precoCompraUnidade;
    }

    public void setPrecoCompraUnidade(double precoCompraUnidade) {
        this.precoCompraUnidade = precoCompraUnidade;
    }

    public double getPrecoVendaUnidade() {
        return precoVendaUnidade;
    }

    public void setPrecoVendaUnidade(double precoVendaUnidade) {
        this.precoVendaUnidade = precoVendaUnidade;
    }

    public String totalEstoque() {
        return "Produto: " + produto +
                " | Quantidade em estoque: " + quantidade +
                " | Preço compra: R$ " + precoCompraUnidade +
                " | Preço venda: R$ " + precoVendaUnidade;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Estoque estoque = (Estoque) o;
        return quantidade == estoque.quantidade &&
                Double.compare(precoCompraUnidade, estoque.precoCompraUnidade) == 0 &&
                Double.compare(precoVendaUnidade, estoque.precoVendaUnidade) == 0 &&
                Objects.equals(produto, estoque.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(produto, quantidade, precoCompraUnidade, precoVendaUnidade);
    }

    @Override
    public String toString() {
        return "Estoque{" +
                "produto='" + produto + '\'' +
                ", quantidade=" + quantidade +
                ", precoCompraUnidade=" + precoCompraUnidade +
                ", precoVendaUnidade=" + precoVendaUnidade +
                '}';
    }
}