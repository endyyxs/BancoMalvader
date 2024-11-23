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

import controller.FuncionarioController;
import javax.swing.JTextField;

public class DepositoClienteView extends JFrame {
	private JTextField textField;

	    public DepositoClienteView() {
	    	super("Depósito"); 
	        getContentPane().setBackground(new Color(252, 214, 247));
	        this.setSize(400, 300);
	        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        getContentPane().setLayout(null);
	        this.setLocationRelativeTo(null); 
	        
	        JLabel menucliente = new JLabel("DEPÓSITO");
	        menucliente.setForeground(new Color(63, 63, 63)); 
	        menucliente.setFont(new Font("Tahoma", Font.BOLD, 30));
	        menucliente.setBounds(109, 49, 215, 34);
	        getContentPane().add(menucliente);
	        
	        JLabel senhasaldo = new JLabel("Digite o valor a ser depositado:");
	        senhasaldo.setFont(new Font("Tahoma", Font.PLAIN, 13));
	        senhasaldo.setBounds(72, 108, 262, 20);
	        getContentPane().add(senhasaldo);
	        
	        JButton botaoconsultarsaldo = new JButton("Depositar");
	        botaoconsultarsaldo.setFont(new Font("Tahoma", Font.PLAIN, 14));
	        botaoconsultarsaldo.setBounds(145, 163, 100, 30);
	        getContentPane().add(botaoconsultarsaldo);
	        
	        textField = new JTextField();
	        textField.setBounds(72, 132, 246, 20);
	        getContentPane().add(textField);
	        textField.setColumns(10);

	    }

	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                new DepositoClienteView().setVisible(true);
	            }
	        });
	    }
	}
