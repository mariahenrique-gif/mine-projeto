package controller;

import br.ufpb.MH.SistemaLoja;
import br.ufpb.MH.Estoque;
import br.ufpb.MH.QuantidadeInvalidaException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VendaGasController implements ActionListener {
    private SistemaLoja sistema;
    private JFrame janelaPrincipal;

    public VendaGasController(SistemaLoja sistema, JFrame janela) {
        this.sistema = sistema;
        this.janelaPrincipal = janela;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Pergunta nome do produto
            String nome = JOptionPane.showInputDialog(janelaPrincipal, "Nome do produto:");
            if (nome == null || nome.trim().isEmpty()) return;

            // Pergunta quantidade a vender
            int quantidade = Integer.parseInt(
                    JOptionPane.showInputDialog(janelaPrincipal, "Quantidade a vender:"));

            // Pergunta preço de venda por unidade
            double precoVendaUnidade = Double.parseDouble(
                    JOptionPane.showInputDialog(janelaPrincipal, "Preço de venda por unidade:"));

            // Verifica se o produto existe
            Estoque produto = sistema.getEstoques().get(nome);
            if (produto == null) {
                JOptionPane.showMessageDialog(janelaPrincipal, "Produto não encontrado!");
                return;
            }

            // Verifica se há estoque suficiente
            if (quantidade > produto.getQuantidade()) {
                JOptionPane.showMessageDialog(janelaPrincipal,
                        "Quantidade insuficiente em estoque!\n" +
                                "Disponível: " + produto.getQuantidade());
                return;
            }

            // Atualiza preço de venda do produto
            produto.setPrecoVendaUnidade(precoVendaUnidade);

            // Registra venda (diminui quantidade)
            sistema.registrarVenda(produto, quantidade);
            produto.setQuantidade(produto.getQuantidade() - quantidade);

            // Calcula valor total da venda
            double valorTotal = quantidade * precoVendaUnidade;

            JOptionPane.showMessageDialog(janelaPrincipal,
                    "Venda registrada com sucesso!\n" +
                            "Produto: " + produto.getProduto() +
                            " | Quantidade vendida: " + quantidade +
                            " | Preço de venda por unidade: R$ " + precoVendaUnidade +
                            " | Valor total: R$ " + valorTotal +
                            "\nQuantidade restante em estoque: " + produto.getQuantidade());

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(janelaPrincipal,
                    "Erro: quantidade ou preço inválido!");
        } catch (QuantidadeInvalidaException ex) {
            JOptionPane.showMessageDialog(janelaPrincipal,
                    "Erro: " + ex.getMessage());
        }
    }
}