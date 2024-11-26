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
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import controller.ClienteController;
import controller.LoginController;
import model.Cliente;
import model.Conta;
import java.awt.BorderLayout;

public class SaldoClienteView extends JFrame {
	public SaldoClienteView() {
		
	
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
