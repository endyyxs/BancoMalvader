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

        // Cor de fundo
        getContentPane().setBackground(new Color(252, 214, 247));  

        // Título da tela
        JLabel titulo = new JLabel("Consulta de Dados");
        titulo.setFont(new Font("Tahoma", Font.BOLD, 18));
        titulo.setBounds(150, 20, 200, 30);
        getContentPane().add(titulo);

        // Botão para consultar conta
        JButton consultarConta = new JButton("Consultar Conta");
        consultarConta.setFont(new Font("Tahoma", Font.BOLD, 12));
        consultarConta.setBounds(150, 70, 200, 30);
        consultarConta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implementar a lógica para consultar conta
                ConsultaContaView consultaContaView = new ConsultaContaView();
                consultaContaView.setVisible(true);
                dispose();
            }
        });
        getContentPane().add(consultarConta);

        // Botão para consultar funcionário
        JButton consultarFuncionario = new JButton("Consultar Funcionário");
        consultarFuncionario.setFont(new Font("Tahoma", Font.BOLD, 12));
        consultarFuncionario.setBounds(150, 120, 200, 30);
        consultarFuncionario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implementar a lógica para consultar funcionário
                ConsultaFuncionarioView consultaFuncionarioView = new ConsultaFuncionarioView();
                consultaFuncionarioView.setVisible(true);
                dispose();
            }
        });
        getContentPane().add(consultarFuncionario);

        // Botão para consultar cliente
        JButton consultarCliente = new JButton("Consultar Cliente");
        consultarCliente.setFont(new Font("Tahoma", Font.BOLD, 12));
        consultarCliente.setBounds(150, 170, 200, 30);
        consultarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implementar a lógica para consultar cliente
                ConsultaClienteView consultaClienteView = new ConsultaClienteView();
                consultaClienteView.setVisible(true);
                dispose();
            }
        });
        getContentPane().add(consultarCliente);

        // Botão para voltar ao Menu
        JButton voltarButton = new JButton("Voltar");
        voltarButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        voltarButton.setBounds(150, 220, 200, 30);
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Voltar para o Menu Funcionario
                dispose();
                new MenuFuncionarioView().setVisible(true);
            }
        });
        getContentPane().add(voltarButton);
    }
}
