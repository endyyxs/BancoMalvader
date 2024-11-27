package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {

    public LoginView() {
        super("Banco Malvader");

        // Configurações da janela
        getContentPane().setBackground(new Color(252, 214, 247));
        this.setSize(500, 430);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Usando GridBagLayout para responsividade
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Título
        JLabel telaprincipal = new JLabel("MENU PRINCIPAL");
        telaprincipal.setForeground(new Color(63, 63, 63));
        telaprincipal.setFont(new Font("Tahoma", Font.BOLD, 30));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(telaprincipal, gbc);

        // Botão Funcionário
        JButton botaofuncionario = new JButton("Funcionário");
        botaofuncionario.setBackground(new Color(255, 255, 255));
        botaofuncionario.setFont(new Font("Tahoma", Font.BOLD, 12));
        botaofuncionario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LoginFuncionarioView loginfuncionario = new LoginFuncionarioView();
                loginfuncionario.setVisible(true);
                dispose();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(botaofuncionario, gbc);

        // Botão Cliente
        JButton botaocliente = new JButton("Cliente");
        botaocliente.setBackground(new Color(255, 255, 255));
        botaocliente.setFont(new Font("Tahoma", Font.BOLD, 12));
        botaocliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LoginClienteView logincliente = new LoginClienteView();
                logincliente.setVisible(true);
                dispose();
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(botaocliente, gbc);

        // Botão Sair
        JButton botaosair = new JButton("Sair");
        botaosair.setFont(new Font("Tahoma", Font.BOLD, 12));
        botaosair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(botaosair, gbc);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginView frame = new LoginView();
            frame.setVisible(true);
        });
    }
}
