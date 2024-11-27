package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaAlteracaoDeDadosView extends JFrame {

    public TelaAlteracaoDeDadosView() {
        super("Tela de Alteração de Dados");
        
        // Definindo o fundo e o layout
        getContentPane().setBackground(new Color(252, 214, 247));
        this.setSize(500, 430);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        this.setLocationRelativeTo(null);
        
        // Título da tela
        JLabel titulo = new JLabel("Alteração de Dados");
        titulo.setFont(new Font("Tahoma", Font.BOLD, 18));
        titulo.setBounds(150, 50, 200, 30);
        getContentPane().add(titulo);
        
        // Botão para Alterar Conta
        JButton alterarContaButton = new JButton("Alterar Conta");
        alterarContaButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        alterarContaButton.setBounds(150, 120, 200, 30);
        getContentPane().add(alterarContaButton);
        
        alterarContaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implementar a lógica para alterar a conta (abrir tela de alteração de conta)
                AlteracaoContaView alteracaoContaView = new AlteracaoContaView();
                alteracaoContaView.setVisible(true);
                dispose();
            }
        });

        // Botão para Alterar Funcionário
        JButton alterarFuncionarioButton = new JButton("Alterar Funcionário");
        alterarFuncionarioButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        alterarFuncionarioButton.setBounds(150, 170, 200, 30);
        getContentPane().add(alterarFuncionarioButton);

        alterarFuncionarioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implementar a lógica para alterar o funcionário (abrir tela de alteração de funcionário)
                AlteracaoFuncionarioView alteracaoFuncionarioView = new AlteracaoFuncionarioView();
                alteracaoFuncionarioView.setVisible(true);
                dispose();
            }
        });
        
        // Botão para Alterar Cliente
        JButton alterarClienteButton = new JButton("Alterar Cliente");
        alterarClienteButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        alterarClienteButton.setBounds(150, 220, 200, 30);
        getContentPane().add(alterarClienteButton);
        
        alterarClienteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implementar a lógica para alterar o cliente (abrir tela de alteração de cliente)
                AlteracaoClienteView alteracaoClienteView = new AlteracaoClienteView();
                alteracaoClienteView.setVisible(true);
                dispose();
            }
        });
        
        // Botão para Voltar ao Menu
        JButton voltarButton = new JButton("Voltar ao Menu");
        voltarButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        voltarButton.setBounds(200, 270, 100, 30);
        getContentPane().add(voltarButton);

        voltarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Voltar para o menu do funcionário
                MenuFuncionarioView menuFuncionarioView = new MenuFuncionarioView();
                menuFuncionarioView.setVisible(true);
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaAlteracaoDeDadosView().setVisible(true);
            }
        });
    }
}
