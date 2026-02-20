package br.ufpb.MH;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JLgas implements SistemaJL {

    private AgendaDeContatos agenda;
    private Caixa caixa;
    private SalvaArquivos salva;

    public JLgas(double saldoInicial, String caminhoArquivo) {
        this.agenda = new AgendaDeContatos();
        this.caixa = new Caixa(saldoInicial);
        this.salva = new SalvaArquivos(caminhoArquivo);
    }

    // FINANCEIRO
    @Override
    public void registrarGasto(String descricao, double valor) throws SaldoInsuficienteException {
        if (valor > caixa.getSaldoAtual()) {
            throw new SaldoInsuficienteException("Saldo insuficiente para registrar gasto.");
        }
        caixa.registrarCompra(descricao, valor, 1);
        try {
            salva.salvarLinha("Gasto registrado: " + descricao + " | Valor: R$" + valor);
        } catch (IOException e) {
            System.out.println("Erro ao salvar gasto: " + e.getMessage());
        }
    }

    @Override
    public void exibirRelatorioFinanceiro() {
        caixa.exibirResumoCaixa();
    }

    // AGENDA DE CONTATOS
    @Override
    public void cadastrarContato(Contato c) throws ContatoJaExisteException {
        agenda.adicionarContatoComValidacao(c);
        try {
            salva.salvarLinha("Contato cadastrado: " + c.toString());
        } catch (IOException e) {
            System.out.println("Erro ao salvar contato: " + e.getMessage());
        }
    }

    @Override
    public Contato localizarContato(String nome) throws ContatoInexistenteExeption {
        return agenda.buscarContato(nome);
    }

    @Override
    public boolean excluirContato(String nome) throws ContatoInexistenteExeption {
        boolean removido = agenda.removerContato(nome);
        if (removido) {
            try {
                salva.salvarLinha("Contato removido: " + nome);
            } catch (IOException e) {
                System.out.println("Erro ao salvar exclusão de contato: " + e.getMessage());
            }
        }
        return removido;
    }

    @Override
    public void exibirAgendaDeContatos() {
        agenda.exibirAgenda();
    }

    // ESTOQUE
    @Override
    public void registrarVenda(Estoque produto, int quantidade) throws EstoqueInsuficienteException {
        produto.registrarVenda(quantidade);
        caixa.registrarVenda(produto.getProduto(), produto.getPrecoVendaUnitario(), quantidade);
        try {
            salva.salvarLinha("Venda registrada: " + produto.getProduto() + " | Quantidade: " + quantidade);
        } catch (IOException e) {
            System.out.println("Erro ao salvar venda: " + e.getMessage());
        }
    }

    @Override
    public void registrarCompra(Estoque produto, int quantidade) throws QuantidadeInvalidaException {
        if (quantidade <= 0) {
            throw new QuantidadeInvalidaException("Quantidade comprada deve ser maior que zero.");
        }
        produto.registrarCompra(quantidade);
        caixa.registrarCompra(produto.getProduto(), produto.getPrecoCustoUnitario(), quantidade);
        try {
            salva.salvarLinha("Compra registrada: " + produto.getProduto() + " | Quantidade: " + quantidade);
        } catch (IOException e) {
            System.out.println("Erro ao salvar compra: " + e.getMessage());
        }
    }

    @Override
    public void exibirEstoque(Estoque produto) {
        System.out.println(produto.toString());
    }

    // SALVAR DADOS
    @Override
    public void salvarEstadoDoSistema() throws IOException {
        List<String> dados = new ArrayList<>();
        dados.add("--- Estado do Sistema ---");
        dados.add("Saldo atual: R$" + caixa.getSaldoAtual());
        dados.add("Receitas: R$" + caixa.getTotalReceitas());
        dados.add("Despesas: R$" + caixa.getTotalDespesas());
        salva.salvarLista(dados);
    }

    @Override
    public void carregarEstadoDoSistema() throws IOException {
        List<String> registros = salva.carregar();
        System.out.println("--- Registros carregados ---");
        for (String linha : registros) {
            System.out.println(linha);
        }
    }
}