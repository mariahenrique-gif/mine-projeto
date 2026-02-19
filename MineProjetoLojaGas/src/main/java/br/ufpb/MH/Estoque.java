package br.ufpb.MH;

import java.io.Serializable;
import java.util.Objects;

public class Estoque implements Serializable {

    private String produto;
    private int quantidade;
    private double precoCusto;
    private double precoVenda;

    public Estoque(String produto, int quantidade, double precoCusto, double precoVenda) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
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

    public double getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(double precoCusto) {
        this.precoCusto = precoCusto;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Estoque estoque = (Estoque) o;
        return quantidade == estoque.quantidade && Double.compare(precoCusto, estoque.precoCusto) == 0 && Double.compare(precoVenda, estoque.precoVenda) == 0 && Objects.equals(produto, estoque.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(produto, quantidade, precoCusto, precoVenda);
    }

    @Override
    public String toString() {
        return "Estoque{" +
                "produto='" + produto + '\'' +
                ", quantidade=" + quantidade +
                ", precoCusto=" + precoCusto +
                ", precoVenda=" + precoVenda +
                '}';
    }
}