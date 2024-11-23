package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JOptionPane; // Importando o JOptionPane para mostrar mensagens

import controller.LoginController; // Certifique-se de que seu controller esteja importado

public class LoginFuncionarioView extends JFrame {
    private JTextField usuario;
    private JPasswordField senha;

    public LoginFuncionarioView() {
        super("Login Funcionario");

        // Configuração da janela
        getContentPane().setBackground(new Color(252, 214, 247));
        this.setSize(500, 430);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        this.setLocationRelativeTo(null);

        // Título da tela
        JLabel telaprincipal = new JLabel("LOGIN");
        telaprincipal.setForeground(new Color(63, 63, 63));
        telaprincipal.setFont(new Font("Tahoma", Font.BOLD, 30));
        telaprincipal.setBounds(167, 92, 307, 34);
        getContentPane().add(telaprincipal);

        // Label e campo de usuário
        JLabel entradausuario = new JLabel("Usuário");
        entradausuario.setFont(new Font("Tahoma", Font.PLAIN, 13));
        entradausuario.setBounds(76, 162, 46, 14);
        getContentPane().add(entradausuario);
        usuario = new JTextField();
        usuario.setBounds(76, 181, 307, 20);
        getContentPane().add(usuario);
        usuario.setColumns(10);

        // Label e campo de senha
        JLabel entradasenha = new JLabel("Senha");
        entradasenha.setFont(new Font("Tahoma", Font.PLAIN, 13));
        entradasenha.setBounds(76, 224, 46, 14);
        getContentPane().add(entradasenha);
        senha = new JPasswordField();
        senha.setBounds(76, 241, 307, 20);
        getContentPane().add(senha);

        // Botão de Login
        JButton btnLogin = new JButton("Entrar");
        btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnLogin.setBounds(76, 300, 307, 30);
        getContentPane().add(btnLogin);

        // Adicionando ActionListener para o botão de login
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtém as informações digitadas pelo usuário
                String usuarioInput = usuario.getText();
                String senhaInput = new String(senha.getPassword());

                // Verifica as credenciais
                LoginController loginController = new LoginController();
                if (loginController.validarLogin(usuarioInput, senhaInput)) {
                    // Se as credenciais forem válidas, abre o menu do funcionário
                    MenuFuncionarioView menuFuncionarioView = new MenuFuncionarioView();
                    menuFuncionarioView.setVisible(true);
                    dispose(); // Fecha a janela de login
                } else {
                    // Caso as credenciais sejam inválidas, exibe uma mensagem de erro
                    JOptionPane.showMessageDialog(null, 
                        "Usuário ou senha inválidos!", 
                        "Erro de Login", 
                        JOptionPane.ERROR_MESSAGE); // Exibe uma mensagem de erro
                }
            }
        });
    }

    public static void main(String[] args) {
        // Inicia a interface gráfica
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginFuncionarioView frame = new LoginFuncionarioView();
                frame.setVisible(true);  // Torna o JFrame visível
            }
        });
    }
}