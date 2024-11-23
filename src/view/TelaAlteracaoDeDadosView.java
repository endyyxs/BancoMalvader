package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaAlteracaoDeDadosView extends JFrame {

    public TelaAlteracaoDeDadosView() {
        super("Tela de Alteração de Dados");
        
       
        getContentPane().setBackground(new Color(252, 214, 247)); 
        this.setSize(500, 430); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        this.setLocationRelativeTo(null);
        
       
        JLabel titulo = new JLabel("Alteração de Dados");
        titulo.setFont(new Font("Tahoma", Font.BOLD, 18));
        titulo.setBounds(150, 50, 200, 30);
        
        getContentPane().add(titulo);

        
        JButton alterarContaButton = new JButton("Alterar Conta");
        alterarContaButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        alterarContaButton.setBounds(150, 120, 200, 30);
        getContentPane().add(alterarContaButton);

        // Botão para Alterar Funcionário
        JButton alterarFuncionarioButton = new JButton("Alterar Funcionário");
        alterarFuncionarioButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        alterarFuncionarioButton.setBounds(150, 170, 200, 30);
     
        getContentPane().add(alterarFuncionarioButton);

       
        JButton alterarClienteButton = new JButton("Alterar Cliente");
        alterarClienteButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        alterarClienteButton.setBounds(150, 220, 200, 30);
        getContentPane().add(alterarClienteButton);
        

      
        JButton voltarButton = new JButton("Voltar");
        voltarButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        voltarButton.setBounds(200, 270, 100, 30);
        getContentPane().add(voltarButton);
        voltarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MenuFuncionarioView menuFuncionarioView = new MenuFuncionarioView();
                menuFuncionarioView.setVisible(true);
                dispose(); 
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaAlteracaoDeDadosView().setVisible(true);
            }
        });
    }
}
