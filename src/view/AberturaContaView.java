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

        // Nome do Cliente
        JLabel nomeClienteLabel = new JLabel("Nome do Cliente:");
        nomeClienteLabel.setBounds(50, 100, 150, 20);
        getContentPane().add(nomeClienteLabel);
        
        nomeClienteField = new JTextField();
        nomeClienteField.setBounds(200, 100, 200, 20);
        getContentPane().add(nomeClienteField);

        // Número da Conta
        JLabel numeroContaLabel = new JLabel("Número da Conta:");
        numeroContaLabel.setBounds(50, 140, 150, 20);
        getContentPane().add(numeroContaLabel);
        
        numeroContaField = new JTextField();
        numeroContaField.setBounds(200, 140, 200, 20);
        getContentPane().add(numeroContaField);
        
        // Botões de Seleção de Conta
        JButton contaPoupancaButton = new JButton("Conta Poupança (CP)");
        contaPoupancaButton.setBounds(50, 180, 200, 30);
        contaPoupancaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateInputs()) {
                    CadastroContaPoupancaView cadastroContaPoupancaView = new CadastroContaPoupancaView();
                    cadastroContaPoupancaView.setVisible(true);
                    dispose(); 
                }
            }
        });
        getContentPane().add(contaPoupancaButton);

        JButton contaCorrenteButton = new JButton("Conta Corrente (CC)");
        contaCorrenteButton.setBounds(250, 180, 200, 30);
        contaCorrenteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateInputs()) {
                    CadastroContaCorrenteView cadastroContaCorrenteView = new CadastroContaCorrenteView();
                    cadastroContaCorrenteView.setVisible(true);
                    dispose(); 
                }
            }
        });
        getContentPane().add(contaCorrenteButton);

        // Botão Voltar
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

    // Método para validação dos campos de entrada
    private boolean validateInputs() {
        String nomeCliente = nomeClienteField.getText();
        String numeroConta = numeroContaField.getText();
        
        if (nomeCliente.isEmpty() || numeroConta.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Aqui você pode adicionar mais validações, como o formato do número da conta, etc.
        return true;
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
