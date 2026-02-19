package br.ufpb.MH;

import java.io.Serializable;
import java.util.Objects;

public class Funcionario implements Serializable {
    private String nome;
    private String id;
    private int vendasGasNoDia;

    public Funcionario (String nome, String id, int vendasGasNoDia){
        this.nome=nome;
        this.id=id;
        this.vendasGasNoDia=vendasGasNoDia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getVendasGasNoDia() {
        return vendasGasNoDia;
    }

    public void setVendasGasNoDia(int vendasGasNoDia) {
        this.vendasGasNoDia = vendasGasNoDia;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Funcionario that = (Funcionario) o;
        return vendasGasNoDia == that.vendasGasNoDia && Objects.equals(nome, that.nome) && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, id, vendasGasNoDia);
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "nome='" + nome + '\'' +
                ", id='" + id + '\'' +
                ", vendasGasNoDia=" + vendasGasNoDia +
                '}';
    }

    public void adicionarVendas(int quantidade) {
        this.vendasGasNoDia += quantidade;
    }

}
