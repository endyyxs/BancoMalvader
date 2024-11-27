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
import javax.swing.JOptionPane;

import controller.FuncionarioController;
import controller.LoginController;
import dao.FuncionarioDAO; 

public class LoginFuncionarioView extends JFrame {
    private JTextField usuario;
    private JPasswordField senha;

    public LoginFuncionarioView() {
        super("Login Funcionario");

        
        getContentPane().setBackground(new Color(252, 214, 247));
        this.setSize(500, 430);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        this.setLocationRelativeTo(null);

        
        JLabel telaprincipal = new JLabel("LOGIN");
        telaprincipal.setForeground(new Color(63, 63, 63));
        telaprincipal.setFont(new Font("Tahoma", Font.BOLD, 30));
        telaprincipal.setBounds(167, 92, 307, 34);
        getContentPane().add(telaprincipal);

        
        JLabel entradausuario = new JLabel("Usuário");
        entradausuario.setFont(new Font("Tahoma", Font.PLAIN, 13));
        entradausuario.setBounds(76, 162, 46, 14);
        getContentPane().add(entradausuario);
        usuario = new JTextField();
        usuario.setBounds(76, 181, 307, 20);
        getContentPane().add(usuario);
        usuario.setColumns(10);

        
        JLabel entradasenha = new JLabel("Senha");
        entradasenha.setFont(new Font("Tahoma", Font.PLAIN, 13));
        entradasenha.setBounds(76, 224, 46, 14);
        getContentPane().add(entradasenha);
        senha = new JPasswordField();
        senha.setBounds(76, 241, 307, 20);
        getContentPane().add(senha);

        
        JButton btnLogin = new JButton("Entrar");
        btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnLogin.setBounds(76, 300, 307, 30);
        getContentPane().add(btnLogin);

        
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String usuarioInput = usuario.getText();
                String senhaInput = new String(senha.getPassword());

                FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
                FuncionarioController funcionarioController = new FuncionarioController(funcionarioDAO);

                if (funcionarioController.validarLogin(usuarioInput, senhaInput)) {
                    MenuFuncionarioView menuFuncionarioView = new MenuFuncionarioView();
                    menuFuncionarioView.setVisible(true);
                    dispose(); 
                } else {
                    JOptionPane.showMessageDialog(null, 
                        "Usuário ou senha inválidos!", 
                        "Erro de Login", 
                        JOptionPane.ERROR_MESSAGE); 
                }
            }
        });

    }
}