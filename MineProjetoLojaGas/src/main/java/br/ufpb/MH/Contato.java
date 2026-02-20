package br.ufpb.MH;

import java.io.Serializable;
import java.util.Objects;

public class Contato implements Serializable {
    private String nome;
    private String telefone;
    private String email;
    private String tipo; // "Funcionario", "Fornecedor" ou "Cliente"

    public Contato(String nome, String telefone, String email, String tipo) {
        this.nome = nome;
        this.telefone = telefone;
        this.email= email;
        this.tipo = tipo;
    }

    public String getNome() {

        return nome;
    }

    public void setNome(String nome) {

        this.nome = nome;
    }

    public String getTelefone() {

        return telefone;
    }

    public void setTelefone(String telefone) {

        this.telefone = telefone;
    }

    public String getTipo() {

        return tipo;
    }

    public void setTipo(String tipo) {

        this.tipo = tipo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Contato contato = (Contato) o;
        return Objects.equals(nome, contato.nome) && Objects.equals(telefone, contato.telefone) && Objects.equals(email, contato.email) && Objects.equals(tipo, contato.tipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, telefone, email, tipo);
    }

    @Override
    public String toString() {
        return "Contato{" +
                "nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
