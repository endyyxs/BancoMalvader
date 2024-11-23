package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

public class MenuClienteView extends JFrame {
    public MenuClienteView() {
        super("Menu Cliente"); 
        getContentPane().setBackground(new Color(252, 214, 247)); 
        this.setSize(500, 430); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        getContentPane().setLayout(null);
        this.setLocationRelativeTo(null); 
        
        JLabel menucliente = new JLabel("MENU CLIENTE");
        menucliente.setForeground(new Color(63, 63, 63)); 
        menucliente.setFont(new Font("Tahoma", Font.BOLD, 30));
        menucliente.setBounds(120, 32, 307, 34);
        getContentPane().add(menucliente);
        
        JButton saldo = new JButton("Saldo");
        saldo.setFont(new Font("Tahoma", Font.BOLD, 12));
        saldo.setBounds(157, 117, 141, 23);
        getContentPane().add(saldo);
        
        JButton deposito = new JButton("Depósito");
        deposito.setFont(new Font("Tahoma", Font.BOLD, 12));
        deposito.setBounds(157, 151, 141, 23);
        getContentPane().add(deposito);
        
        JButton extrato = new JButton("Extrato");
        extrato.setFont(new Font("Tahoma", Font.BOLD, 12));
        extrato.setBounds(157, 219, 141, 23);
        getContentPane().add(extrato);
        
        JButton saque = new JButton("Saque");
        saque.setFont(new Font("Tahoma", Font.BOLD, 12));
        saque.setBounds(157, 185, 141, 23);
        getContentPane().add(saque);
        
        JButton consultarlimite = new JButton("Consultar Limite");
        consultarlimite.setFont(new Font("Tahoma", Font.BOLD, 12));
        consultarlimite.setBounds(157, 253, 141, 23);
        getContentPane().add(consultarlimite);
        
        JButton botaosair = new JButton("Sair");
        botaosair.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                LoginView telaprincipal = new LoginView();
                telaprincipal.setVisible(true);
                dispose();
            } 
        }); 
        botaosair.setFont(new Font("Tahoma", Font.BOLD, 12));
        botaosair.setBounds(360, 344, 114, 23);
        getContentPane().add(botaosair);
    }

    public static void main(String[] args) {
        // Usando o Event Dispatch Thread para garantir que a interface gráfica seja criada corretamente
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MenuClienteView().setVisible(true); // Tornando a janela visível
            }
        });
    }
}
