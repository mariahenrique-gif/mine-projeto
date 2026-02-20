package br.ufpb.MH;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SalvaArquivos {

    private String caminhoArquivo;

    public SalvaArquivos(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    // Salvar uma única linha no arquivo
    public void salvarLinha(String linha) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo, true))) {
            writer.write(linha);
            writer.newLine();
        }
    }

    // Salvar várias linhas de uma vez
    public void salvarLista(List<String> linhas) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo, true))) {
            for (String linha : linhas) {
                writer.write(linha);
                writer.newLine();
            }
        }
    }

    // Carregar todas as linhas do arquivo
    public List<String> carregar() throws IOException {
        List<String> linhas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                linhas.add(linha);
            }
        }
        return linhas;
    }
}