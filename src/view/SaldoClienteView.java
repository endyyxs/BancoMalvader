package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.ClienteController;
import model.Cliente;
import model.Conta;

public class SaldoClienteView extends JFrame {

    public SaldoClienteView() {
        super("Consulta de Saldo");
        
        // Definindo o layout e o tamanho da janela
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().setBackground(new Color(252, 214, 247));
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        // Título da tela
        JLabel saldoLabel = new JLabel("CONSULTA DE SALDO", SwingConstants.CENTER);
        saldoLabel.setForeground(new Color(63, 63, 63));
        saldoLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        saldoLabel.setBounds(70, 30, 250, 30);
        getContentPane().add(saldoLabel);
        
        // Campo de entrada para a senha do cliente
        JLabel senhaLabel = new JLabel("Digite sua senha:");
        senhaLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        senhaLabel.setBounds(90, 100, 120, 20);
        getContentPane().add(senhaLabel);
        
        JPasswordField senhaField = new JPasswordField();
        senhaField.setBounds(90, 130, 220, 30);
        getContentPane().add(senhaField);

        // Botão para consultar o saldo
        JButton consultarSaldoButton = new JButton("Consultar Saldo");
        consultarSaldoButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        consultarSaldoButton.setBounds(140, 180, 120, 30);
        getContentPane().add(consultarSaldoButton);
        
        consultarSaldoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String senha = new String(senhaField.getPassword());
                if (validarSenha(senha)) {
                    // Aqui, você deve obter o cliente e a conta do banco de dados ou do sistema
                    Cliente cliente = obterCliente();
                    Conta conta = cliente.getConta(); // Supondo que o cliente tenha uma conta associada
                    double saldo = conta.consultarSaldo();
                    
                    // Exibe o saldo em uma nova janela ou na própria tela
                    JOptionPane.showMessageDialog(SaldoClienteView.this, "Saldo: R$ " + saldo);
                } else {
                    JOptionPane.showMessageDialog(SaldoClienteView.this, "Senha incorreta!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // Método para validar a senha do cliente (aqui você pode integrar com um sistema de autenticação real)
    private boolean validarSenha(String senha) {
        // Implementação de senha (pode ser integrado com um sistema de autenticação real)
        return "1234".equals(senha); // Exemplo de senha fixa
    }
    
    // Método para obter o cliente (aqui você pode substituir por um método real de obtenção de cliente)
    private Cliente obterCliente() {
        // Para fins de exemplo, retornando um cliente fictício com uma conta fictícia
        Cliente cliente = new Cliente("João", "123.456.789-00");
        Conta conta = new Conta(cliente);
        cliente.setConta(conta);
        return cliente;
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
