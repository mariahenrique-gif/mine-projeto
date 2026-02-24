package br.ufpb.MH.GUI;

import br.ufpb.MH.LojaGas;
import controller.CompraGasController;
import controller.EstoqueGasController;
import controller.VendaGasController;

import javax.swing.*;
import java.awt.*;

public class GUIV3 extends JFrame {
    JLabel titulo, logo;
    JButton botaoCompra, botaoVenda, botaoEstoque;
    LojaGas sistema = new LojaGas(1000, "SalvaArquivos.txt");

    public GUIV3() {
        setTitle("Sistema Empresarial Estoque de Gás");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(Color.WHITE);

        // Título centralizado
        titulo = new JLabel("Gerenciador de Estoque", JLabel.CENTER);
        titulo.setForeground(Color.BLUE);
        titulo.setFont(new Font("Serif", Font.BOLD, 28));

        // Logo centralizada e redimensionada
        ImageIcon logoOriginal = new ImageIcon(getClass().getResource("/LogoDistribuidora.png"));
        Image imagemRedimensionada = logoOriginal.getImage().getScaledInstance(360, 200, Image.SCALE_SMOOTH);
        ImageIcon logoAjustada = new ImageIcon(imagemRedimensionada);
        logo = new JLabel(logoAjustada, JLabel.CENTER);

        // Botões com texto
        botaoCompra = new JButton("Comprar Gás");
        botaoVenda = new JButton("Vender Gás");
        botaoEstoque = new JButton("Verificar Estoque");

        botaoCompra.addActionListener(new CompraGasController(sistema, this));
        botaoVenda.addActionListener(new VendaGasController(sistema, this));
        botaoEstoque.addActionListener(new EstoqueGasController(sistema, this));

        // Estilo dos botões
        Dimension tamanhoBotao = new Dimension(160, 45);
        Font fonteBotao = new Font("Arial", Font.BOLD, 15);

        botaoCompra.setPreferredSize(tamanhoBotao);
        botaoCompra.setFont(fonteBotao);

        botaoVenda.setPreferredSize(tamanhoBotao);
        botaoVenda.setFont(fonteBotao);

        botaoEstoque.setPreferredSize(tamanhoBotao);
        botaoEstoque.setFont(fonteBotao);

        // Painel dos botões em linha
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 10));
        painelBotoes.setBackground(Color.WHITE);
        painelBotoes.add(botaoCompra);
        painelBotoes.add(botaoVenda);
        painelBotoes.add(botaoEstoque);

        // Painel central com logo e botões empilhados
        JPanel painelCentro = new JPanel();
        painelCentro.setLayout(new BoxLayout(painelCentro, BoxLayout.Y_AXIS));
        painelCentro.setBackground(Color.WHITE);
        painelCentro.add(logo);
        painelCentro.add(Box.createVerticalStrut(20));
        painelCentro.add(painelBotoes);

        // Layout principal
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(titulo, BorderLayout.NORTH);
        getContentPane().add(painelCentro, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new GUIV3();
    }
}