package br.ufpb.MH;

import java.io.IOException;
import java.util.Collection;

public interface SistemaJL {

    public void registrarVenda(DiaMes dia, String produto, double valor, int idFuncionario)
            throws FuncionarioInexistenteException;

    public void registrarGasto(DiaMes dia, String descricao, double valor)
            throws SaldoInsuficienteException;

    public void exibirRelatorioFinanceiro();

    public void cadastrarContato(Contato c)
            throws ContatoJaExisteException;

    public Contato localizarContato(String nome)
            throws ContatoInexistenteExeption;

    public boolean excluirContato(String nome)
            throws ContatoInexistenteExeption;

    public void admitirFuncionario(Funcionario f)
            throws IdDuplicadoException, FuncionarioJaExisteException;

    public Funcionario buscarFuncionario(int id)
            throws FuncionarioInexistenteException;

    public void salvarEstadoDoSistema() throws IOException;

    public void carregarEstadoDoSistema() throws IOException;
}
