package br.ufpb.MH;

import java.util.ArrayList;
import java.util.List;

public class Caixa {

    private double saldoAtual;
    private double totalReceitas;
    private double totalDespesas;
    private List<String> movimentacoes;

    public Caixa(double saldoInicial) {
        this.saldoAtual = saldoInicial;
        this.movimentacoes = new ArrayList<>();
    }

    public double getSaldoAtual() { return saldoAtual; }
    public double getTotalReceitas() { return totalReceitas; }
    public double getTotalDespesas() { return totalDespesas; }

    public void registrarCompra(Estoque produto, int quantidade) {
        double valorTotal = produto.getPrecoCompraUnidade() * quantidade;
        saldoAtual -= valorTotal;
        totalDespesas += valorTotal;
        produto.setQuantidade(produto.getQuantidade() + quantidade);
        movimentacoes.add("Compra - " + produto.getProduto() + " (" + quantidade + " unidades) | Valor: R$" + valorTotal);
    }

    public void registrarVenda(Estoque produto, int quantidade) {
        double valorTotal = produto.getPrecoVendaUnidade() * quantidade;
        saldoAtual += valorTotal;
        totalReceitas += valorTotal;
        produto.setQuantidade(produto.getQuantidade() - quantidade);
        movimentacoes.add("Venda - " + produto.getProduto() + " (" + quantidade + " unidades) | Valor: R$" + valorTotal);
    }

    public void exibirResumoCaixa() {
        System.out.println("--- Resumo do Caixa ---");
        System.out.println("Receitas: R$" + totalReceitas);
        System.out.println("Despesas: R$" + totalDespesas);
        System.out.println("Saldo Atual: R$" + saldoAtual);
        System.out.println("--- Movimentações ---");
        for (String mov : movimentacoes) {
            System.out.println(mov);
        }
    }
}