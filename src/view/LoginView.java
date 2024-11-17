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
   
  JLabel telaprincipal = new JLabel("MENU PRINCIPAL"); 
  telaprincipal.setForeground(new Color(63, 63, 63)); 
  telaprincipal.setFont(new Font("Tahoma", Font.BOLD, 30)); 
  telaprincipal.setBounds(101, 73, 355, 26); 
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
  botaofuncionario.setBounds(86, 199, 123, 53); 
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
  botaocliente.setBounds(269, 200, 123, 50); 
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
  this.setLocationRelativeTo(null); 
   
   
 } 
  
 
 public static void main(String[] args) { 
  SwingUtilities.invokeLater(() -> { 
   LoginView frame = new LoginView(); 
   frame.setVisible(true); 
  }); 
 
 } 
}
