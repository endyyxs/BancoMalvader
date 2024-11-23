package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.SwingUtilities;

import controller.ClienteController;
import controller.LoginController;
import model.Cliente;
import model.Conta;

public class SaldoClienteView extends JFrame {
	private JPasswordField senha;

	public SaldoClienteView() {
        super("Saldo"); 
        getContentPane().setBackground(new Color(252, 214, 247));
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        this.setLocationRelativeTo(null); 
        
        JLabel menucliente = new JLabel("SALDO");
        menucliente.setForeground(new Color(63, 63, 63)); 
        menucliente.setFont(new Font("Tahoma", Font.BOLD, 30));
        menucliente.setBounds(135, 49, 140, 34);
        getContentPane().add(menucliente);
        
        JLabel senhasaldo = new JLabel("Senha");
        senhasaldo.setFont(new Font("Tahoma", Font.PLAIN, 13));
        senhasaldo.setBounds(62, 114, 46, 14);
        getContentPane().add(senhasaldo);
        senha = new JPasswordField();
        senha.setBounds(62, 132, 262, 20);
        getContentPane().add(senha);
        
        JButton botaoconsultarsaldo = new JButton("Entrar");
        botaoconsultarsaldo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        botaoconsultarsaldo.setBounds(145, 163, 100, 30);
        getContentPane().add(botaoconsultarsaldo);

    }



	public static void main(String[] args) {
	    SwingUtilities.invokeLater(new Runnable() {
	        @Override
	        public void run() {
	            new SaldoClienteView().setVisible(true);
	        }
	    });
	}
	
	}
