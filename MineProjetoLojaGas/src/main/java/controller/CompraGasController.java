package controller;

import br.ufpb.MH.SistemaLoja;
import br.ufpb.MH.Estoque;
import br.ufpb.MH.QuantidadeInvalidaException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CompraGasController implements ActionListener {
    private SistemaLoja sistema;
    private JFrame janelaPrincipal;

    public CompraGasController(SistemaLoja sistema, JFrame janela) {
        this.sistema = sistema;
        this.janelaPrincipal = janela;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Pergunta todas as informações necessárias
            String nome = JOptionPane.showInputDialog(janelaPrincipal, "Nome do produto:");
            if (nome == null || nome.trim().isEmpty()) return;

            int quantidade = Integer.parseInt(
                    JOptionPane.showInputDialog(janelaPrincipal, "Quantidade:"));

            double precoUnidade = Double.parseDouble(
                    JOptionPane.showInputDialog(janelaPrincipal, "Preço por unidade:"));

            // Verifica se o produto já existe no estoque
            Estoque produto = sistema.getEstoques().get(nome);

            if (produto == null) {
                // Se não existe, cria um novo produto no estoque
                produto = new Estoque(nome, quantidade, precoUnidade);
                sistema.getEstoques().put(nome, produto);
            } else {
                // Se já existe, apenas registra a compra (aumenta quantidade)
                sistema.registrarCompra(produto, quantidade);
                // Atualiza o preço se necessário
                produto.setProduto(nome);
                produto.setQuantidade(produto.getQuantidade() + quantidade);
            }

            JOptionPane.showMessageDialog(janelaPrincipal,
                    "Compra registrada com sucesso!\n" +
                            "Produto: " + produto.getProduto() +
                            " | Quantidade: " + produto.getQuantidade() +
                            " | Preço: R$ " + produto.getPrecoCompraUnidade());

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(janelaPrincipal,
                    "Erro: quantidade ou preço inválido!");
        } catch (QuantidadeInvalidaException ex) {
            JOptionPane.showMessageDialog(janelaPrincipal,
                    "Erro: " + ex.getMessage());
        }
    }
}