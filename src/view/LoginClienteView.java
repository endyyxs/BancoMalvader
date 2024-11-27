package view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import controller.LoginController; // Certifique-se de ter esse controller importado

public class LoginClienteView extends JFrame {
    private JTextField usuario;
    private JPasswordField senha;

    public LoginClienteView() {
        super("Login Cliente");
        getContentPane().setBackground(new Color(252, 214, 247));
        this.setSize(500, 430);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        this.setLocationRelativeTo(null);

        // Título do formulário
        JLabel telaprincipal = new JLabel("LOGIN");
        telaprincipal.setForeground(new Color(63, 63, 63));
        telaprincipal.setFont(new Font("Tahoma", Font.BOLD, 30));
        telaprincipal.setBounds(167, 92, 307, 34);
        getContentPane().add(telaprincipal);

        // Campo de usuário
        JLabel entradausuario = new JLabel("Usuário");
        entradausuario.setFont(new Font("Tahoma", Font.PLAIN, 13));
        entradausuario.setBounds(76, 162, 46, 14);
        getContentPane().add(entradausuario);
        usuario = new JTextField();
        usuario.setBounds(76, 181, 307, 20);
        getContentPane().add(usuario);
        usuario.setColumns(10);

        // Campo de senha
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

        // Ação de Login
        btnLogin.addActionListener(e -> {
            // Obtendo os dados inseridos
            String usuarioInput = usuario.getText();
            String senhaInput = new String(senha.getPassword());

            // Validação simples
            if (usuarioInput.isEmpty() || senhaInput.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validar o login usando o LoginController
            LoginController loginController = new LoginController();
            if (loginController.validarLogin(usuarioInput, senhaInput)) {
                // Login bem-sucedido, abrir a próxima tela
                // Substitua com a tela que deseja abrir após o login, por exemplo, uma tela de cliente
                JOptionPane.showMessageDialog(this, "Login realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                // Fechar a janela de login
                dispose(); 
            } else {
                // Exibe mensagem de erro
                JOptionPane.showMessageDialog(this, "Usuário ou senha inválidos!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public static void main(String[] args) {
        // Criar e exibir a tela de login
        LoginClienteView telaLogin = new LoginClienteView();
        telaLogin.setVisible(true);
    }
}
