package view; 
 
import javax.swing.JFrame; 
import javax.swing.SwingUtilities; 
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color; 
import javax.swing.JButton; 
import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants; 
 
public class LoginView extends JFrame {
	private JTextField usuario; 
	private JTextField textField;
	
	 public LoginView() { 
	  super("Banco Malvader"); 
	  getContentPane().setBackground(new Color(252, 214, 247)); 
	  this.setSize(500, 430); 
	  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	  getContentPane().setLayout(null); 
	  this.setLocationRelativeTo(null); 
	  
	 
	  JLabel telaprincipal = new JLabel("MENU PRINCIPAL"); 
	  telaprincipal.setForeground(new Color(63, 63, 63)); 
	  telaprincipal.setFont(new Font("Tahoma", Font.BOLD, 30)); 
	  telaprincipal.setBounds(100, 31, 307, 34); 
	  getContentPane().add(telaprincipal); 
	  
	   
	  JButton botaofuncionario = new JButton("Funcionário"); 
	  botaofuncionario.setBackground(new Color(255, 255, 255)); 
	  botaofuncionario.addActionListener(new ActionListener() { 
	   public void actionPerformed(ActionEvent e) { 
		MenuFuncionarioView menufuncionario = new MenuFuncionarioView();
		menufuncionario.setVisible(true);
		dispose();
	   } 
	  }); 
	  botaofuncionario.setFont(new Font("Tahoma", Font.BOLD, 12)); 
	  botaofuncionario.setBounds(229, 99, 154, 34); 
	  getContentPane().add(botaofuncionario); 
	   
	  
	  JButton botaocliente = new JButton("Cliente"); 
	  botaocliente.setBackground(new Color(255, 255, 255)); 
	  botaocliente.addActionListener(new ActionListener() { 
	   public void actionPerformed(ActionEvent e) { 
		MenuClienteView menucliente = new MenuClienteView();
		menucliente.setVisible(true);
		dispose();
	    } 
	   }); 
	  botaocliente.setFont(new Font("Tahoma", Font.BOLD, 12)); 
	  botaocliente.setBounds(76, 99, 154, 34); 
	  getContentPane().add(botaocliente); 
	   
	  
	  JButton botaosair = new JButton("Sair"); 
	  botaosair.addActionListener(new ActionListener(){ 
	   public void actionPerformed(ActionEvent e) { 
	    System.exit(0); 
	   } 
	  }); 
	  botaosair.setFont(new Font("Tahoma", Font.BOLD, 12)); 
	  botaosair.setBounds(367, 334, 89, 23); 
	  getContentPane().add(botaosair); 
	  
	  
	  usuario = new JTextField();
	  usuario.setBounds(76, 181, 307, 20);
	  getContentPane().add(usuario);
	  usuario.setColumns(10);
	  
	  JLabel lblNewLabel = new JLabel("Usuário");
	  lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
	  lblNewLabel.setBounds(76, 162, 46, 14);
	  getContentPane().add(lblNewLabel);
	  
	  textField = new JTextField();
	  textField.setColumns(10);
	  textField.setBounds(76, 241, 307, 20);
	  getContentPane().add(textField);
	  
	  
	  JLabel lblSenha = new JLabel("Senha");
	  lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 13));
	  lblSenha.setBounds(76, 224, 46, 14);
	  getContentPane().add(lblSenha);
	  
	 } 
	  
	 
	 public static void main(String[] args) { 
	  SwingUtilities.invokeLater(() -> { 
	   LoginView frame = new LoginView(); 
	   frame.setVisible(true); 
	  }); 
	 
	 } 
	}