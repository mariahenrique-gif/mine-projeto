package br.ufpb.MH;

import java.io.*;
import java.util.List;

public class SalvaArquivos {

    private String caminhoArquivo;

    public SalvaArquivos(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    // Salvar todo o estado do sistema de uma vez
    public void salvarTudo(List<String> linhas) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            for (String linha : linhas) {
                writer.write(linha);
                writer.newLine();
            }
        }
    }

    // Carregar todas as linhas do arquivo
    public List<String> carregar() throws IOException {
        List<String> linhas = new java.util.ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                linhas.add(linha);
            }
        }
        return linhas;
    }
}