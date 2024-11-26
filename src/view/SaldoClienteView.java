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
	private ClienteController saldocontroller;

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
        
        
        JButton botaoconsultarsaldo = new JButton("Confirmar");
        botaoconsultarsaldo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        botaoconsultarsaldo.setBounds(145, 163, 100, 30);
        getContentPane().add(botaoconsultarsaldo);
        
        saldocontroller = new ClienteController();
        
        botaoconsultarsaldo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String senhaCliente = new String(senha.getPassword());
                
                try {
                    ClienteController cliente = new ClienteController();
                    
                    if (saldocontroller.autenticarSenha(senha)) {
                        double saldo = saldocontroller.consultarSaldo(null);
                        JOptionPane.showMessageDialog(null, "Saldo disponível: R$ " + saldo);
                    } else {
                        JOptionPane.showMessageDialog(null, "Senha incorreta.");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage());
                }
            }
        });
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
