package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaconsultaView extends JFrame {

    public TelaconsultaView() {
        setTitle("Consulta de Dados");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        
        getContentPane().setBackground(new Color(252, 214, 247));  

    
        JButton consultarConta = new JButton("Consultar Conta");
        consultarConta.setFont(new Font("Tahoma", Font.BOLD, 12));
        consultarConta.setBounds(150, 50, 200, 30);
         getContentPane().add(consultarConta);

        JButton consultarFuncionario = new JButton("Consultar Funcionário");
        consultarFuncionario.setFont(new Font("Tahoma", Font.BOLD, 12));
        consultarFuncionario.setBounds(150, 100, 200, 30);
         getContentPane().add(consultarFuncionario);

        JButton consultarCliente = new JButton("Consultar Cliente");
        consultarCliente.setFont(new Font("Tahoma", Font.BOLD, 12));
        consultarCliente.setBounds(150, 150, 200, 30);
        getContentPane().add(consultarCliente);
        
        JButton voltarButton = new JButton("Voltar");
        voltarButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        voltarButton.setBounds(150, 200, 200, 30);
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                dispose();
                
                new MenuFuncionarioView().setVisible(true);
            }
        });
  getContentPane().add(voltarButton);
       
       
       
       
      
    }
}

