package br.ufpb.MH;

import java.io.*;
import java.util.Map;
import java.util.List;

public class GerenciadorDeArquivos {
    private static final String ARQUIVO_SISTEMA = "dados_jlgas.dat";

    /**
     * Salva o estado completo do sistema em um único arquivo binário.
     * Ordem de salvamento: Contatos -> Funcionários -> Estoque -> Financeiro.
     */
    public void salvarTudo(Map<String, Contato> contatos,
                           Map<String, Funcionario> funcionarios,
                           Map<String, Estoque> estoque,
                           List<String> historicoFinanceiro) throws IOException {

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ARQUIVO_SISTEMA))) {
            // 1. Salva o Mapa de Contatos
            out.writeObject(contatos);

            // 2. Salva o Mapa de Funcionários
            out.writeObject(funcionarios);

            // 3. Salva o Mapa de Estoque
            out.writeObject(estoque);

            // 4. Salva a Lista de Histórico do Caixa
            out.writeObject(historicoFinanceiro);

            System.out.println(">>> Backup realizado: Contatos, Funcionários, Estoque e Financeiro salvos.");
        }
    }
}