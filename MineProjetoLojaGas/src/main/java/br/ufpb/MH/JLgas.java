package br.ufpb.MH;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JLgas {
    private AgendaDeContatos agenda;
    private Map<String, Funcionario> mapaFuncionarios;
    private Map<String, Estoque> mapaEstoque;
    private Caixa caixa;
    private GerenciadorDeArquivos gerenciador;

    public JLgas() {
        this.agenda = new AgendaDeContatos();
        this.mapaFuncionarios = new HashMap<>();
        this.mapaEstoque = new HashMap<>();
        this.caixa = new Caixa(0.0, DiaMes.DIA_01);
        this.gerenciador = new GerenciadorDeArquivos();
    }

    // --- MÉTODOS FINANCEIROS ---

    public void registrarVenda(DiaMes dia, String produto, double valor, int idFuncionario)
            throws FuncionarioInexistenteException {

        String idStr = String.valueOf(idFuncionario);
        if (!mapaFuncionarios.containsKey(idStr)) {
            throw new FuncionarioInexistenteException("Funcionário ID " + idFuncionario + " não encontrado.");
        }

        Funcionario f = mapaFuncionarios.get(idStr);
        f.adicionarVendas(1);
        caixa.registrarEntrada(dia, "VENDA: " + produto + " | VENDEDOR: " + f.getNome(), valor);
    }

    public void registrarGasto(DiaMes dia, String descricao, double valor)
            throws SaldoInsuficienteException {
        caixa.registrarSaida(dia, descricao, valor);
    }

    public void exibirRelatorioFinanceiro() {
        for (String registro : caixa.getHistoricoLancamentos()) {
            System.out.println(registro);
        }
    }

    // --- MÉTODOS DE CONTATOS ---

    public void cadastrarContato(Contato c) throws ContatoJaExisteException {
        agenda.adicionar(c);
    }

    public Contato localizarContato(String nome) throws ContatoInexistenteExeption {
        return agenda.buscar(nome);
    }

    public boolean excluirContato(String nome) throws ContatoInexistenteExeption {
        if (!agenda.getMapaContatos().containsKey(nome)) {
            throw new ContatoInexistenteExeption("Contato não existe.");
        }
        agenda.getMapaContatos().remove(nome);
        return true;
    }

    // --- MÉTODOS DE FUNCIONÁRIOS ---

    public void admitirFuncionario(Funcionario f) throws IdDuplicadoException, FuncionarioJaExisteException {
        if (mapaFuncionarios.containsKey(f.getId())) {
            throw new IdDuplicadoException("ID já cadastrado.");
        }
        mapaFuncionarios.put(f.getId(), f);
    }

    public Funcionario buscarFuncionario(int id) throws FuncionarioInexistenteException {
        String idStr = String.valueOf(id);
        if (!mapaFuncionarios.containsKey(idStr)) {
            throw new FuncionarioInexistenteException("ID inválido.");
        }
        return mapaFuncionarios.get(idStr);
    }

    // --- MÉTODOS DE PERSISTÊNCIA ---

    public void salvarEstadoDoSistema() throws IOException {
        gerenciador.salvarTudo(
                agenda.getMapaContatos(),
                mapaFuncionarios,
                mapaEstoque,
                caixa.getHistoricoLancamentos()
        );
    }
}