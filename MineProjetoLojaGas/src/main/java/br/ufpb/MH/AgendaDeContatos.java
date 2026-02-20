package br.ufpb.MH;

import java.util.HashMap;
import java.util.Map;

public class AgendaDeContatos {

    private Map<String, Contato> mapaContatos;

    public AgendaDeContatos() {
        this.mapaContatos = new HashMap<>();
    }

    // Adicionar contato
    public void adicionarContato(Contato novoContato) {
        mapaContatos.put(novoContato.getNome(), novoContato);
    }

    // Buscar contato por nome
    public Contato buscarContatoPorNome(String nome) {
        return mapaContatos.get(nome);
    }

    // Adicionar contato
    public void adicionarContatoComValidacao(Contato c) throws ContatoJaExisteException {
        if (mapaContatos.containsKey(c.getNome())) {
            throw new ContatoJaExisteException("Erro: O contato '" + c.getNome() + "' já está cadastrado.");
        }
        mapaContatos.put(c.getNome(), c);
    }

    // Buscar contato com exceção
    public Contato buscarContato(String nome) throws ContatoInexistenteExeption {
        Contato c = mapaContatos.get(nome);
        if (c == null) {
            throw new ContatoInexistenteExeption("Erro: Contato '" + nome + "' não encontrado na agenda.");
        }
        return c;
    }

    // Exibir todos os contatos
    public void exibirAgenda() {
        System.out.println("--- Contatos Cadastrados ---");
        for (Contato c : mapaContatos.values()) {
            System.out.println("Nome: " + c.getNome() + " | Tel: " + c.getTelefone() + " | Email: " + c.getEmail() + " [" + c.getTipo() + "]");
        }
    }

    // Remover contato com exceção
    public boolean removerContato(String nome) throws ContatoInexistenteExeption {
        if (!mapaContatos.containsKey(nome)) {
            throw new ContatoInexistenteExeption("Não é possível remover: contato não existe.");
        }
        mapaContatos.remove(nome);
        return true;
    }

    // Getter para o mapa
    public Map<String, Contato> getMapaContatos() {
        return mapaContatos;
    }
}

