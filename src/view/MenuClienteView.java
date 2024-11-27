package view;

import javax.swing.*;
import model.Cliente;
import model.Conta;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuClienteView extends JFrame {

    private Cliente cliente; // Cliente que irá interagir com o sistema

    public MenuClienteView(Cliente cliente) {
        super("Menu Cliente");
        this.cliente = cliente; // Atribuindo o cliente que foi autenticado no login

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
                solicitarSenha("Saque"); // Solicita a senha antes de abrir a tela de saque
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

    // Método para verificar a senha do cliente
    private boolean verificarSenha(String senha) {
        // Aqui você verificaria a senha com a base de dados ou lógica de autenticação
        return senha.equals("1234");  // Apenas para testes, substitua por lógica real
    }

    // Método para solicitar a senha antes de abrir a tela de saldo
    private void solicitarSenha(String operacao) {
        JPasswordField passwordField = new JPasswordField(10);
        int option = JOptionPane.showConfirmDialog(this, passwordField, "Digite sua senha para " + operacao, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            String senha = new String(passwordField.getPassword());
            if (verificarSenha(senha)) {
                if (operacao.equals("Saldo")) {
                    abrirTelaSaldo();  // Método que mostra o saldo
                } else if (operacao.equals("Saque")) {
                    abrirTelaSaque();  // Método que realiza o saque
                }
            } else {
                JOptionPane.showMessageDialog(this, "Senha incorreta!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Método para abrir a tela de saldo com o controle de saldo
    private void abrirTelaSaldo() {
        // Aqui, você pode acessar a conta do cliente e consultar o saldo
        double saldo = cliente.getConta().consultarSaldo();
        JOptionPane.showMessageDialog(this, "Seu saldo é: R$ " + saldo);
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
                    cliente.getConta().depositar(valor); // Realiza o depósito na conta do cliente
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
        // Aqui você pode mostrar um extrato real da conta do cliente
        String extrato = cliente.getConta().consultarExtrato();
        JOptionPane.showMessageDialog(this, "Extrato: \n" + extrato);
    }

    // Método para abrir a tela de saque
    private void abrirTelaSaque() {
        String valorString = JOptionPane.showInputDialog(this, "Digite o valor que deseja sacar:");

        if (valorString != null) {
            try {
                double valor = Double.parseDouble(valorString);

                if (valor > 0) {
                    boolean saqueRealizado = cliente.getConta().sacar(valor); // Tenta realizar o saque
                    if (saqueRealizado) {
                        JOptionPane.showMessageDialog(this, "Valor do saque: R$ " + valor);
                    } else {
                        JOptionPane.showMessageDialog(this, "Saldo insuficiente!", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "O valor deve ser maior que 0!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Valor inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        // Criando um cliente fictício para exemplo
        Cliente cliente = new Cliente("João", "123456789", new Conta(1000.00)); // Crie seu cliente e conta

        // Usando o Event Dispatch Thread para garantir que a interface gráfica seja criada corretamente
        SwingUtilities.invokeLater(() -> {
            new MenuClienteView(cliente).setVisible(true); // Passando o cliente autenticado
        });
    }
}
