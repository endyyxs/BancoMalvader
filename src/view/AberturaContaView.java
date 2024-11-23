package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AberturaContaView extends JFrame {

    private JTextField nomeClienteField;
    private JTextField numeroContaField;

    public AberturaContaView() {
        super("Abertura de Conta");
        
        getContentPane().setBackground(new Color(252, 214, 247));
        this.setSize(500, 430); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        getContentPane().setLayout(null); 
        this.setLocationRelativeTo(null); 
        
        JLabel label = new JLabel("Tela de Abertura de Conta");
        label.setFont(new Font("Tahoma", Font.BOLD, 18));
        label.setBounds(140, 50, 250, 30);
        getContentPane().add(label);

       
        JButton contaPoupancaButton = new JButton("Conta Poupança (CP)");
        contaPoupancaButton.setBounds(50, 180, 200, 30);
        contaPoupancaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CadastroContaPoupancaView cadastroContaPoupancaView = new CadastroContaPoupancaView();
                cadastroContaPoupancaView.setVisible(true);
                dispose(); 
            }
        });
        getContentPane().add(contaPoupancaButton);

        
        JButton contaCorrenteButton = new JButton("Conta Corrente (CC)");
        contaCorrenteButton.setBounds(250, 180, 200, 30);
        contaCorrenteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CadastroContaCorrenteView cadastroContaCorrenteView = new CadastroContaCorrenteView();
                cadastroContaCorrenteView.setVisible(true);
                dispose(); 
            }
        });
        getContentPane().add(contaCorrenteButton);

       
        JButton voltarButton = new JButton("Voltar");
        voltarButton.setBounds(200, 320, 100, 30);
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuFuncionarioView menuFuncionarioView = new MenuFuncionarioView();
                menuFuncionarioView.setVisible(true);
                dispose(); 
            }
        });
        getContentPane().add(voltarButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AberturaContaView().setVisible(true); // Tornar a janela visível
            }
        });
    }
}
