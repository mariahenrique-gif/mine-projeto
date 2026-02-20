package br.ufpb.MH;

import java.util.HashMap;
import java.util.Map;

public class Caixa {

    private double saldoAtual;
    private double totalReceitas;
    private double totalDespesas;
    private Map<String, Double> movimentacoes;

    public Caixa(double saldoInicial) {
        this.saldoAtual = saldoInicial;
        this.movimentacoes = new HashMap<>();
    }

    // Registrar compra (saída de dinheiro)

    public void registrarCompra(String descricao, double valorUnitario, int quantidade) {
        double valorTotal = valorUnitario * quantidade;
        saldoAtual -= valorTotal;
        totalDespesas += valorTotal;
        movimentacoes.put("Compra - " + descricao + " (" + quantidade + " unidades)", valorTotal);
    }

    // Registrar venda (entrada de dinheiro)
    public void registrarVenda(String descricao, double valorUnitario, int quantidade) {
        double valorTotal = valorUnitario * quantidade;
        saldoAtual += valorTotal;
        totalReceitas += valorTotal;
        movimentacoes.put("Venda - " + descricao + " (" + quantidade + " unidades)", valorTotal);
    }

    public double getSaldoAtual() {
        return saldoAtual;
    }

    public double getTotalReceitas() {
        return totalReceitas;
    }

    public double getTotalDespesas() {
        return totalDespesas;
    }

    public void exibirResumoCaixa() {
        System.out.println("--- Resumo do Caixa ---");
        System.out.println("Receitas: R$" + totalReceitas);
        System.out.println("Despesas: R$" + totalDespesas);
        System.out.println("Saldo Atual: R$" + saldoAtual);
        System.out.println("--- Movimentações ---");
        for (Map.Entry<String, Double> entry : movimentacoes.entrySet()) {
            System.out.println(entry.getKey() + " | Valor: R$" + entry.getValue());
        }
    }
}