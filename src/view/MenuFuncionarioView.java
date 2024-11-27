package view;

import java.awt.Color;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MenuFuncionarioView extends JFrame {

    public MenuFuncionarioView() {
        super("Menu Funcionario");

        // Definindo o fundo e o layout
        getContentPane().setBackground(new Color(252, 214, 247));
        this.setSize(500, 430);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        this.setLocationRelativeTo(null);

        // Título da tela
        JLabel menufuncionario = new JLabel("MENU FUNCIONÁRIO");
        menufuncionario.setForeground(new Color(63, 63, 63));
        menufuncionario.setFont(new Font("Tahoma", Font.BOLD, 30));
        menufuncionario.setBounds(77, 33, 359, 34);
        getContentPane().add(menufuncionario);

        // Botões de ações no menu
        JButton aberturaconta = new JButton("Abertura de conta");
        aberturaconta.setFont(new Font("Tahoma", Font.BOLD, 12));
        aberturaconta.setBounds(140, 117, 188, 23);
        aberturaconta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	AberturaContaView AberturaConta = new AberturaContaView();
            	AberturaConta.setVisible(true);	
            	dispose();
           }  
        });
        getContentPane().add(aberturaconta);
        
        JButton encerramentodeconta = new JButton("Encerramento de conta");
        encerramentodeconta.setFont(new Font("Tahoma", Font.BOLD, 12));
        encerramentodeconta.setBounds(140, 151, 188, 23);
        encerramentodeconta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	EncerramentoContaView  encerramentoContaView = new EncerramentoContaView ();
            	encerramentoContaView.setVisible(true);
            	dispose();
            			
            }
        });
        getContentPane().add(encerramentodeconta);
        JButton consultadedados = new JButton("Consulta de dados");
        consultadedados.setFont(new Font("Tahoma", Font.BOLD, 12));
        consultadedados.setBounds(140, 219, 188, 23);
        consultadedados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            	TelaconsultaView telaconsultaview = new TelaconsultaView();
            	telaconsultaview.setVisible(true);
            	dispose();
            	
            }
        });
        
        getContentPane().add(consultadedados);

        JButton alteracaodedados = new JButton("Alteração de dados");
        alteracaodedados.setFont(new Font("Tahoma", Font.BOLD, 12));
        alteracaodedados.setBounds(140, 185, 188, 23);
        alteracaodedados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                TelaAlteracaoDeDadosView telaAlteracaoDeDados = new TelaAlteracaoDeDadosView();
                telaAlteracaoDeDados.setVisible(true);
                dispose(); 
            }
        });
        getContentPane().add(alteracaodedados);

        JButton cadastrofuncionario = new JButton("Cadastro de funcionário");
        cadastrofuncionario.setFont(new Font("Tahoma", Font.BOLD, 12));
        cadastrofuncionario.setBounds(140, 254, 188, 23);
        cadastrofuncionario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String senha =JOptionPane.showInputDialog("Digite a senha para cadastrar funcionário:");
                
               
                if ("admin123".equals(senha)) {  
                    
                    CadastroFuncionarioView cadastroFuncionarioView = new CadastroFuncionarioView();
                    cadastroFuncionarioView.setVisible(true);
                    dispose();  
                } else {
                    
                    JOptionPane.showMessageDialog(null, "Senha incorreta!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
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
       
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MenuFuncionarioView frame = new MenuFuncionarioView();
                frame.setVisible(true);  
            }
        });
    }
}