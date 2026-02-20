package br.ufpb.MH.GUI;

import javax.swing.*;
import java.awt.*;

public class JLgasGUIV2 extends JFrame {
    JLabel linha1, linha2;

    ImageIcon logo = new ImageIcon(getClass().getResource("/jlgaslogo.jpg"));

    public JLgasGUIV2(){
        setTitle("Sistema Empresarial JL Gás");
        setSize(400,400);
        setLocation(150,150);
        setResizable(false);
        getContentPane().setBackground(Color.WHITE);

        linha1= new JLabel("Bem-vindo ao JLgás!",JLabel.CENTER);
        linha1.setForeground(Color.BLUE);
        linha1.setFont(new Font("ser1f",Font.BOLD, 24));

        linha2 = new JLabel(logo, JLabel.CENTER);
        getContentPane().setLayout(new GridLayout(3,1));
        getContentPane().add(linha1);
        getContentPane().add(linha2);
    }
public static void main(String[]args){
        JFrame janela= new JLgasGUIV2();
        janela.setVisible(true);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
}
