package br.ufpb.MH.GUI;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class GUI extends JFrame {

    public GUI(){
        setTitle("Sistema Deposito de Gás");
        setSize(400,200);
        setLocation(150,150);
        setResizable(false);
        getContentPane().setBackground(Color.gray);
    }

    public static void main(String [] args){
        JFrame janela= new GUI();
        janela.setVisible(true);
        WindowListener fechadorDeJanelaPrincipal= new WindowAdapter() {
       public void windowClosing(WindowEvent e){
           System.exit(0);
       }
        };
        janela.addWindowListener(fechadorDeJanelaPrincipal);
    }

}
