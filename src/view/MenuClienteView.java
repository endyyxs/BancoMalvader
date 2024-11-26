package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuClienteView extends JFrame {

    public MenuClienteView() {
        super("Menu Cliente");
        getContentPane().setBackground(new Color(252, 214, 247)); 
        this.setSize(500, 430); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        getContentPane().setLayout(null);
        this.setLocationRelativeTo(null);

        // Título
        JLabel menucliente = new JLabel("MENU CLIENTE");
        menucliente.setForeground(new Color(63, 63, 63)); 
        menucliente.setFont(new Font("Tahoma", Font.BOLD, 30));
        menucliente.setBounds(120, 32, 307, 34);
        getContentPane().add(menucliente);

        // Botões do menu
        JButton saldo = new JButton("Saldo");
        saldo.setFont(new Font("Tahoma", Font.BOLD, 12));
        saldo.setBounds(157, 117, 141, 23);
        getContentPane().add(saldo);
        saldo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                solicitarSenha("Saldo"); // Solicita a senha antes de abrir a tela de saldo
            }
        });

        JButton deposito = new JButton("Depósito");
        deposito.setFont(new Font("Tahoma", Font.BOLD, 12));
        deposito.setBounds(157, 151, 141, 23);
        getContentPane().add(deposito);
        deposito.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirTelaDeposito(); // Abre a tela de depósito
            }
        });

        JButton extrato = new JButton("Extrato");
        extrato.setFont(new Font("Tahoma", Font.BOLD, 12));
        extrato.setBounds(157, 219, 141, 23);
        getContentPane().add(extrato);
        extrato.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirTelaExtrato(); // Abre a tela de extrato
            }
        });

        JButton saque = new JButton("Saque");
        saque.setFont(new Font("Tahoma", Font.BOLD, 12));
        saque.setBounds(157, 185, 141, 23);
        getContentPane().add(saque);
        saque.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirTelaSaque(); // Abre a tela de saque
            }
        });

        // Botão "Sair"
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

    // Método para solicitar a senha antes de abrir a tela de saldo
    private void solicitarSenha(String operacao) {
        JPasswordField passwordField = new JPasswordField(10);
        JOptionPane.showConfirmDialog(this, passwordField, "Digite sua senha para " + operacao, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        // Aqui, você pode adicionar lógica para verificar a senha, mas como você pediu apenas a navegação, estou mantendo simples.
        String senha = new String(passwordField.getPassword());

        // Se a senha estiver correta, abre a tela correspondente
        if (senha.equals("1234")) { // Exemplo de senha, substitua pela sua lógica
            if (operacao.equals("Saldo")) {
                abrirTelaSaldo(); // Abre a tela de saldo
            }
        } else {
            JOptionPane.showMessageDialog(this, "Senha incorreta!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para abrir a tela de saldo
    private void abrirTelaSaldo() {
        // Apenas cria uma nova janela de saldo
        JFrame telaSaldo = new JFrame("Saldo");
        telaSaldo.setSize(400, 300);
        telaSaldo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        telaSaldo.setLocationRelativeTo(null);
        JLabel label = new JLabel("Tela de Saldo", SwingConstants.CENTER);
        telaSaldo.add(label);
        telaSaldo.setVisible(true);
    }

    // Método para abrir a tela de depósito
    private void abrirTelaDeposito() {
        // Solicita o valor do depósito
        String valorString = JOptionPane.showInputDialog(this, "Digite o valor do depósito:");
        
        if (valorString != null) {
            try {
                double valor = Double.parseDouble(valorString);

                if (valor > 0) {
                    // Aqui você pode adicionar lógica para registrar o valor no banco de dados
                    JOptionPane.showMessageDialog(this, "Valor registrado no banco de dados: R$ " + valor);
                } else {
                    JOptionPane.showMessageDialog(this, "O valor deve ser maior que 0!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Valor inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Método para abrir a tela de extrato
    private void abrirTelaExtrato() {
        // Apenas cria uma nova janela de extrato
        JFrame telaExtrato = new JFrame("Extrato");
        telaExtrato.setSize(400, 300);
        telaExtrato.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        telaExtrato.setLocationRelativeTo(null);
        JLabel label = new JLabel("Tela de Extrato", SwingConstants.CENTER);
        telaExtrato.add(label);
        telaExtrato.setVisible(true);
    }

    // Método para abrir a tela de saque
    private void abrirTelaSaque() {
        // Apenas cria uma nova janela de saque
        JFrame telaSaque = new JFrame("Saque");
        telaSaque.setSize(400, 300);
        telaSaque.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        telaSaque.setLocationRelativeTo(null);
        JLabel label = new JLabel("Tela de Saque", SwingConstants.CENTER);
        telaSaque.add(label);
        telaSaque.setVisible(true);
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
