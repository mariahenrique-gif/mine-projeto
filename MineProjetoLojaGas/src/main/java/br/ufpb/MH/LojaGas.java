package br.ufpb.MH;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LojaGas implements SistemaLoja {

    private Caixa caixa;
    private SalvaArquivos salva;
    private Map<String, Estoque> estoques;

    public LojaGas(double saldoInicial, String caminhoArquivo) {
        this.caixa = new Caixa(saldoInicial);
        this.salva = new SalvaArquivos(caminhoArquivo);
        this.estoques = new HashMap<>();
    }

    // FINANCEIRO
    @Override
    public void registrarGasto(String descricao, double valor) throws SaldoInsuficienteException {
        if (valor > caixa.getSaldoAtual()) {
            throw new SaldoInsuficienteException("Saldo insuficiente para registrar gasto.");
        }
        caixa.registrarCompra(new Estoque(descricao, 0, valor), 1);
        try {
            salva.salvarTudo(List.of("Gasto registrado: " + descricao + " | Valor: R$" + valor));
        } catch (IOException e) {
            System.out.println("Erro ao salvar gasto: " + e.getMessage());
        }
    }

    @Override
    public void exibirRelatorioFinanceiro() {
        caixa.exibirResumoCaixa();
    }

    // ESTOQUE
    @Override
    public void adicionarProduto(String nome, int quantidade, double precoUnidade) {
        Estoque novo = new Estoque(nome, quantidade, precoUnidade);
        estoques.put(nome, novo);
    }

    @Override
    public void registrarVenda(Estoque produto, int quantidade) throws EstoqueInsuficienteException {
        if (quantidade > produto.getQuantidade()) {
            throw new EstoqueInsuficienteException("Estoque insuficiente para venda.");
        }
        caixa.registrarVenda(produto, quantidade);
        try {
            salva.salvarTudo(List.of("Venda registrada: " + produto.getProduto() + " | Quantidade: " + quantidade));
        } catch (IOException e) {
            System.out.println("Erro ao salvar venda: " + e.getMessage());
        }
    }

    @Override
    public void registrarCompra(Estoque produto, int quantidade) throws QuantidadeInvalidaException {
        if (quantidade <= 0) {
            throw new QuantidadeInvalidaException("Quantidade comprada deve ser maior que zero.");
        }
        caixa.registrarCompra(produto, quantidade);
        try {
            salva.salvarTudo(List.of("Compra registrada: " + produto.getProduto() + " | Quantidade: " + quantidade));
        } catch (IOException e) {
            System.out.println("Erro ao salvar compra: " + e.getMessage());
        }
    }
    @Override
    public void pesquisarProdutoPorNome(String nome) {
        System.out.println("--- Resultado da pesquisa por: " + nome + " ---");

        estoques.values().stream()
                .filter(e -> e.getProduto().equalsIgnoreCase(nome)) // filtra pelo nome
                .forEach(e -> System.out.println(e.totalEstoque())); // imprime os resultados
    }


    @Override
    public void exibirEstoque() {
        System.out.println("--- Estoque da Loja ---");

        estoques.values().stream()
                .sorted(Comparator.comparing(Estoque::getProduto)) // ordena por nome
                .forEach(e -> System.out.println(e.totalEstoque())); // imprime cada item
    }

    @Override
    public Map<String, Estoque> getEstoques() {
        return estoques;
    }

    // SALVAR/CARREGAR
    @Override
    public void salvarEstadoDoSistema() throws IOException {
        List<String> dados = new java.util.ArrayList<>();
        dados.add("--- Estado do Sistema ---");
        dados.add("Saldo atual: R$" + caixa.getSaldoAtual());
        dados.add("Receitas: R$" + caixa.getTotalReceitas());
        dados.add("Despesas: R$" + caixa.getTotalDespesas());
        for (Estoque e : estoques.values()) {
            dados.add(e.totalEstoque());
        }
        salva.salvarTudo(dados);
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