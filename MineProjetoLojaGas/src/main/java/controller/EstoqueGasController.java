package controller;

import br.ufpb.MH.SistemaLoja;
import br.ufpb.MH.Estoque;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EstoqueGasController implements ActionListener {
    private SistemaLoja sistema;
    private JFrame janelaPrincipal;

    public EstoqueGasController(SistemaLoja sistema, JFrame janela) {
        this.sistema = sistema;
        this.janelaPrincipal = janela;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        StringBuilder sb = new StringBuilder("--- Estoque Atual ---\n\n");

        // Percorre todos os produtos cadastrados
        for (Estoque est : sistema.getEstoques().values()) {
            sb.append("Produto: ").append(est.getProduto())
                    .append(" | Quantidade: ").append(est.getQuantidade())
                    .append(" | Preço de compra: R$ ").append(est.getPrecoCompraUnidade())
                    .append(" | Preço de venda: R$ ").append(est.getPrecoVendaUnidade())
                    .append("\n");
        }

        // Mostra em uma janela de diálogo
        JOptionPane.showMessageDialog(janelaPrincipal, sb.toString(),
                "Estoque Atual", JOptionPane.INFORMATION_MESSAGE);
    }
}