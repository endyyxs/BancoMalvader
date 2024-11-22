package view; 
 
import javax.swing.JFrame; 
import javax.swing.SwingUtilities; 
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color; 
import javax.swing.JButton; 
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
 
public class LoginView extends JFrame {
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
	  telaprincipal.setBounds(98, 81, 307, 34); 
	  getContentPane().add(telaprincipal); 
	  
	   
	  JButton botaofuncionario = new JButton("Funcionário"); 
	  botaofuncionario.setBackground(new Color(255, 255, 255)); 
	  botaofuncionario.addActionListener(new ActionListener() { 
	   public void actionPerformed(ActionEvent e) { 
		LoginFuncionarioView loginfuncionario = new LoginFuncionarioView();
		loginfuncionario.setVisible(true);
		dispose();
	   } 
	  }); 
	  botaofuncionario.setFont(new Font("Tahoma", Font.BOLD, 12)); 
	  botaofuncionario.setBounds(251, 195, 154, 34); 
	  getContentPane().add(botaofuncionario); 
	   
	  
	  JButton botaocliente = new JButton("Cliente"); 
	  botaocliente.setBackground(new Color(255, 255, 255)); 
	  botaocliente.addActionListener(new ActionListener() { 
	   public void actionPerformed(ActionEvent e) { 
		LoginClienteView logincliente = new LoginClienteView();
		logincliente.setVisible(true);
		dispose();
	    } 
	   }); 
	  botaocliente.setFont(new Font("Tahoma", Font.BOLD, 12)); 
	  botaocliente.setBounds(69, 195, 154, 34); 
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
	  
	  
	 } 
	  
	 
	 public static void main(String[] args) { 
	  SwingUtilities.invokeLater(() -> { 
	   LoginView frame = new LoginView(); 
	   frame.setVisible(true); 
	  }); 
	 
	 } 
	}
