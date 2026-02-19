package br.ufpb.MH;

import java.util.HashMap;
import java.util.Map;

public class AgendaDeContatos extends Contato {

    private Map<String, Contato> mapaContatos;

    public AgendaDeContatos() {
        super("Agenda Geral", "0000-0000", "Sistema");
        this.mapaContatos = new HashMap<>();
    }

    public void adicionarContato(Contato novoContato) {
        mapaContatos.put(novoContato.getNome(), novoContato);
    }

    public Contato buscarPorNome(String nome) {
        return mapaContatos.get(nome);
    }

    public Map<String, Contato> getMapaContatos() {
        return mapaContatos;
    }

    public void adicionar(Contato c) throws ContatoJaExisteException {
        if (mapaContatos.containsKey(c.getNome())) {
            throw new ContatoJaExisteException("Erro: O contato '" + c.getNome() + "' já está cadastrado.");
        }
        mapaContatos.put(c.getNome(), c);
    }

    public Contato buscar(String nome) throws ContatoInexistenteExeption {
        Contato c = mapaContatos.get(nome);
        if (c == null) {
            throw new ContatoInexistenteExeption("Erro: Contato '" + nome + "' não encontrado na agenda.");
        }
        return c;
    }

    public void exibirAgenda() {
        System.out.println("--- Contatos Cadastrados ---");
        for (Contato c : mapaContatos.values()) {
            System.out.println("Nome: " + c.getNome() + " | Tel: " + c.getTelefone() + " [" + c.getTipo() + "]");
        }
    }
    public boolean remover(String nome) throws ContatoInexistenteExeption {
        if (!mapaContatos.containsKey(nome)) {
            throw new ContatoInexistenteExeption("Não é possível remover: contato não existe.");
        }
        mapaContatos.remove(nome);
        return true;
    }
}