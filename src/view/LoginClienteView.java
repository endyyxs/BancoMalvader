package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginClienteView  extends JFrame{
	private JTextField usuario; 
	private JPasswordField senha;
	
	public LoginClienteView() {
	super("Login Cliente"); 
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
	}

	public static void main(String[] args) {

	}

}
