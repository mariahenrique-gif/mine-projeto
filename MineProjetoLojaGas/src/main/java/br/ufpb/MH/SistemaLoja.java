package br.ufpb.MH;

import java.io.IOException;
import java.util.Map;

public interface SistemaLoja {

    // FINANCEIRO
    void registrarGasto(String descricao, double valor) throws SaldoInsuficienteException;
    void exibirRelatorioFinanceiro();

    // ESTOQUE
    void adicionarProduto(String nome, int quantidade, double precoUnidade);
    void registrarVenda(Estoque produto, int quantidade) throws EstoqueInsuficienteException;
    void registrarCompra(Estoque produto, int quantidade) throws QuantidadeInvalidaException;
    void pesquisarProdutoPorNome(String nome);
    void exibirEstoque();

    // SALVAR/CARREGAR DADOS
    void salvarEstadoDoSistema() throws IOException;
    void carregarEstadoDoSistema() throws IOException;
    Map<String, Estoque> getEstoques();
}