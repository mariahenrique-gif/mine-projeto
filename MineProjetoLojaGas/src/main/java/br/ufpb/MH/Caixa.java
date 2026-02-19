package br.ufpb.MH;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Caixa implements Serializable {
    private double saldoAtual;
    private List<String> historicoLancamentos = new ArrayList<>();

    public Caixa(double saldoInicial, DiaMes dia) {
        this.saldoAtual = saldoInicial;
        anotar(dia, "ABERTURA", saldoInicial, 0.0);
    }

    public double getSaldoAtual() {
        return saldoAtual;
    }

    public void setSaldoAtual(double saldoAtual) {
        this.saldoAtual = saldoAtual;
    }

    public List<String> getHistoricoLancamentos() {
        return historicoLancamentos;
    }

    public void setHistoricoLancamentos(List<String> historicoLancamentos) {
        this.historicoLancamentos = historicoLancamentos;
    }

    public void registrarEntrada(DiaMes dia, String descricao, double valor) {
        this.saldoAtual += valor;
        anotar(dia, descricao, valor, 0.0);
    }

    public void registrarSaida(DiaMes dia, String desc, double valor) throws SaldoInsuficienteException {
        if (valor > this.saldoAtual) {
            throw new SaldoInsuficienteException("Saldo insuficiente! Atual: R$ " + saldoAtual + " | Tentativa: R$ " + valor);
        }
        this.saldoAtual -= valor;
        anotar(dia, desc, 0.0, valor);
    }

    private void anotar(DiaMes dia, String desc, double entrada, double saida) {
        String registroFormatado = "DATA: " + dia + " | DESC: " + desc +
                " | ENTRADA: R$ " + entrada + " | SAÍDA: R$ " + saida +
                " | SALDO: R$ " + this.saldoAtual;
        this.historicoLancamentos.add(registroFormatado);
    }

}