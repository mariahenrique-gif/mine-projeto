package br.ufpb.MH;

import java.io.IOException;

public interface SistemaJL {

    // FINANCEIRO
    void registrarGasto(String descricao, double valor)
            throws SaldoInsuficienteException;

    void exibirRelatorioFinanceiro();

    // AGENDA DE CONTATOS
    void cadastrarContato(Contato c)
            throws ContatoJaExisteException;

    Contato localizarContato(String nome)
            throws ContatoInexistenteExeption;

    boolean excluirContato(String nome)
            throws ContatoInexistenteExeption;

    void exibirAgendaDeContatos();

    // ESTOQUE
    void registrarVenda(Estoque produto, int quantidade)
            throws EstoqueInsuficienteException;

    void registrarCompra(Estoque produto, int quantidade)
            throws QuantidadeInvalidaException;

    void exibirEstoque(Estoque produto);

    // SALVAR DADOS
    void salvarEstadoDoSistema() throws IOException;

    void carregarEstadoDoSistema() throws IOException;
}