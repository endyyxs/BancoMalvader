package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroContaView extends JFrame {

    private JTextField nomeClienteField;
    private JTextField cpfField;
    private JTextField tipoContaField;

    public CadastroContaView() {
        super("Cadastro de Conta");
        
        // Configuração inicial da janela
        getContentPane().setBackground(new Color(252, 214, 247));
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        this.setLocationRelativeTo(null);

        // Título da tela
        JLabel label = new JLabel("Cadastro de Conta");
        label.setFont(new Font("Tahoma", Font.BOLD, 18));
        label.setBounds(120, 30, 200, 30);
        getContentPane().add(label);

        // Nome do Cliente
        JLabel nomeClienteLabel = new JLabel("Nome do Cliente:");
        nomeClienteLabel.setBounds(30, 70, 150, 20);
        getContentPane().add(nomeClienteLabel);

        nomeClienteField = new JTextField();
        nomeClienteField.setBounds(150, 70, 200, 20);
        getContentPane().add(nomeClienteField);

        // CPF do Cliente
        JLabel cpfLabel = new JLabel("CPF:");
        cpfLabel.setBounds(30, 110, 150, 20);
        getContentPane().add(cpfLabel);

        cpfField = new JTextField();
        cpfField.setBounds(150, 110, 200, 20);
        getContentPane().add(cpfField);

        // Tipo de Conta
        JLabel tipoContaLabel = new JLabel("Tipo de Conta:");
        tipoContaLabel.setBounds(30, 150, 150, 20);
        getContentPane().add(tipoContaLabel);

        tipoContaField = new JTextField();
        tipoContaField.setBounds(150, 150, 200, 20);
        getContentPane().add(tipoContaField);

        // Botão Enviar
        JButton enviarButton = new JButton("Cadastrar");
        enviarButton.setBounds(150, 200, 100, 30);
        enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Captura os dados dos campos
                String nomeCliente = nomeClienteField.getText();
                String cpf = cpfField.getText();
                String tipoConta = tipoContaField.getText();

                // Validação simples
                if (nomeCliente.isEmpty() || cpf.isEmpty() || tipoConta.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");
                } else {
                    JOptionPane.showMessageDialog(null, "Conta cadastrada com sucesso!");
                    // Aqui, você pode adicionar o código para salvar as informações no banco de dados
                }
            }
        });
        getContentPane().add(enviarButton);

        // Botão Voltar
        JButton voltarButton = new JButton("Voltar");
        voltarButton.setBounds(260, 200, 100, 30);
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Voltar para a tela anterior (Menu do Funcionário, por exemplo)
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
                new CadastroContaView().setVisible(true); // Tornar a janela visível
            }
        });
    }
}

