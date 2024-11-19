package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MenuFuncionarioView extends JFrame {
	public MenuFuncionarioView() {
		super("Menu Funcionario"); 
		  getContentPane().setBackground(new Color(252, 214, 247)); 
		  this.setSize(500, 430); 
		  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		  getContentPane().setLayout(null);
		  this.setLocationRelativeTo(null); 
		  
		  JLabel menufuncionario = new JLabel("MENU FUNCIONÁRIO");
		  menufuncionario.setForeground(new Color(63, 63, 63)); 
		  menufuncionario.setFont(new Font("Tahoma", Font.BOLD, 30));
		  menufuncionario.setBounds(77, 33, 359, 34);
		  getContentPane().add(menufuncionario);
		  
		  JButton aberturaconta = new JButton("Abertura de conta");
		  aberturaconta.setFont(new Font("Tahoma", Font.BOLD, 12));
		  aberturaconta.setBounds(140, 117, 188, 23);
		  getContentPane().add(aberturaconta);
		  
		  JButton encerramentodeconta = new JButton("Encerramento de conta");
		  encerramentodeconta.setFont(new Font("Tahoma", Font.BOLD, 12));
		  encerramentodeconta.setBounds(140, 151, 188, 23);
		  getContentPane().add(encerramentodeconta);
		  
		  JButton consultadedados = new JButton("Consulta de dados");
		  consultadedados.setFont(new Font("Tahoma", Font.BOLD, 12));
		  consultadedados.setBounds(140, 219, 188, 23);
		  getContentPane().add(consultadedados);
		  
		  JButton alteracaodedados = new JButton("Alteração de dados");
		  alteracaodedados.setFont(new Font("Tahoma", Font.BOLD, 12));
		  alteracaodedados.setBounds(140, 185, 188, 23);
		  getContentPane().add(alteracaodedados);
		  
		  JButton cadastrofuncionario = new JButton("Cadastro de funcionário");
		  cadastrofuncionario.setFont(new Font("Tahoma", Font.BOLD, 12));
		  cadastrofuncionario.setBounds(140, 254, 188, 23);
		  getContentPane().add(cadastrofuncionario);
		  
		  JButton btnGeraoDeRelatrios = new JButton("Geração de relatórios");
		  btnGeraoDeRelatrios.setFont(new Font("Tahoma", Font.BOLD, 12));
		  btnGeraoDeRelatrios.setBounds(140, 289, 188, 23);
		  getContentPane().add(btnGeraoDeRelatrios);
		 
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}