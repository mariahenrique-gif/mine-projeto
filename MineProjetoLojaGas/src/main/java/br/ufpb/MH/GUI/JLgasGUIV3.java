package br.ufpb.MH.GUI;
import br.ufpb.MH.JLgas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

public class JLgasGUIV3 extends JFrame {
    JLabel linha1, linha2;
    ImageIcon logo= new ImageIcon("./imgs/jlgaslogo.jpg");
    ImageIcon compraImg= new ImageIcon("./imgs/compra.jng");
    ImageIcon vendaImg= new ImageIcon("./imgs/venda.jpg");
    ImageIcon estoqueImg= new ImageIcon("./imgs/estoque.jpg");
JButton botaoCompra, botaoVenda, botaoEstoque;
    JLgas sistema = new JLgas(1000, "SalvaArquivos.txt");

public JLgasGUIV3(){
    setTitle("Sistema Empresarial JL Gás");
    setSize(600, 400);
    setLocation(150, 150);
    setResizable(false);
    getContentPane().setBackground(Color.WHITE);

    linha1 = new JLabel("Minha Agenda JL Gás", JLabel.CENTER);
    linha1.setForeground(Color.BLUE);
    linha1.setFont(new Font("Serif", Font.BOLD, 24));
    linha2 = new JLabel(new ImageIcon(getClass().getResource("/jlgaslogo.jpg")), JLabel.CENTER);
    JLgas sistema = new JLgas(1000.0, "registroJLgas.txt");



    //REVER ESTE TRECO E MODIFICAR CORRETAMENTE

    botaoCompra = new JButton("Registrar Compra");
    botaoCompra.addActionListener(e -> {
        System.out.println("Compra registrada!");
        // aqui você chama métodos do sistema JLgas
    });

    botaoVenda = new JButton("Registrar Venda");
    botaoVenda.addActionListener(e -> {
        System.out.println("Venda registrada!");
    });

    botaoEstoque = new JButton("Exibir Estoque");
    botaoEstoque.addActionListener(e -> {
        System.out.println("Estoque exibido!");
    });
}
}
