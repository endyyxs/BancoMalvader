package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroFuncionarioView extends JFrame {

    public CadastroFuncionarioView() {
        super("Cadastro de Funcionário");
        
        
        getContentPane().setBackground(new Color(252, 214, 247));
        this.setSize(500, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        this.setLocationRelativeTo(null);

        
        JLabel titulo = new JLabel("Cadastro de Funcionário");
        titulo.setFont(new Font("Tahoma", Font.BOLD, 18));
        titulo.setBounds(140, 20, 250, 30);
        getContentPane().add(titulo);

       
        JLabel codigoFuncionarioLabel = new JLabel("Código do Funcionário:");
        codigoFuncionarioLabel.setBounds(50, 70, 150, 25);
        getContentPane().add(codigoFuncionarioLabel);
        
        JTextField codigoFuncionarioField = new JTextField();
        codigoFuncionarioField.setBounds(200, 70, 200, 25);
        getContentPane().add(codigoFuncionarioField);
        
        JLabel cargoLabel = new JLabel("Cargo:");
        cargoLabel.setBounds(50, 110, 150, 25);
        getContentPane().add(cargoLabel);

        JTextField cargoField = new JTextField();
        cargoField.setBounds(200, 110, 200, 25);
        getContentPane().add(cargoField);

        JLabel nomeLabel = new JLabel("Nome:");
        nomeLabel.setBounds(50, 150, 150, 25);
        getContentPane().add(nomeLabel);

        JTextField nomeField = new JTextField();
        nomeField.setBounds(200, 150, 200, 25);
        getContentPane().add(nomeField);

        JLabel cpfLabel = new JLabel("CPF:");
        cpfLabel.setBounds(50, 190, 150, 25);
        getContentPane().add(cpfLabel);

        JTextField cpfField = new JTextField();
        cpfField.setBounds(200, 190, 200, 25);
        getContentPane().add(cpfField);

        JLabel dataNascimentoLabel = new JLabel("Data de Nascimento:");
        dataNascimentoLabel.setBounds(50, 230, 150, 25);
        getContentPane().add(dataNascimentoLabel);

        JTextField dataNascimentoField = new JTextField();
        dataNascimentoField.setBounds(200, 230, 200, 25);
        getContentPane().add(dataNascimentoField);

        JLabel telefoneLabel = new JLabel("Telefone:");
        telefoneLabel.setBounds(50, 270, 150, 25);
        getContentPane().add(telefoneLabel);

        JTextField telefoneField = new JTextField();
        telefoneField.setBounds(200, 270, 200, 25);
        getContentPane().add(telefoneField);

        JLabel enderecoLabel = new JLabel("Endereço:");
        enderecoLabel.setBounds(50, 310, 150, 25);
        getContentPane().add(enderecoLabel);

        JTextField enderecoField = new JTextField();
        enderecoField.setBounds(200, 310, 200, 25);
        getContentPane().add(enderecoField);

        JLabel cepLabel = new JLabel("CEP:");
        cepLabel.setBounds(50, 350, 150, 25);
        getContentPane().add(cepLabel);

        JTextField cepField = new JTextField();
        cepField.setBounds(200, 350, 200, 25);
        getContentPane().add(cepField);

        JLabel numeroCasaLabel = new JLabel("Número da Casa:");
        numeroCasaLabel.setBounds(50, 390, 150, 25);
        getContentPane().add(numeroCasaLabel);

        JTextField numeroCasaField = new JTextField();
        numeroCasaField.setBounds(200, 390, 200, 25);
        getContentPane().add(numeroCasaField);

        JLabel bairroLabel = new JLabel("Bairro:");
        bairroLabel.setBounds(50, 430, 150, 25);
        getContentPane().add(bairroLabel);

        JTextField bairroField = new JTextField();
        bairroField.setBounds(200, 430, 200, 25);
        getContentPane().add(bairroField);

        JLabel cidadeLabel = new JLabel("Cidade:");
        cidadeLabel.setBounds(50, 470, 150, 25);
        getContentPane().add(cidadeLabel);

        JTextField cidadeField = new JTextField();
        cidadeField.setBounds(200, 470, 200, 25);
        getContentPane().add(cidadeField);

        JLabel estadoLabel = new JLabel("Estado:");
        estadoLabel.setBounds(50, 510, 150, 25);
        getContentPane().add(estadoLabel);

        JTextField estadoField = new JTextField();
        estadoField.setBounds(200, 510, 200, 25);
        getContentPane().add(estadoField);

        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setBounds(50, 550, 150, 25);
        getContentPane().add(senhaLabel);

        JTextField senhaField = new JTextField();
        senhaField.setBounds(200, 550, 200, 25);
        getContentPane().add(senhaField);

        
        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.setBounds(200, 590, 200, 30);
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso!");
                
            }
        });
        getContentPane().add(cadastrarButton);

       
        JButton voltarButton = new JButton("Voltar");
        voltarButton.setBounds(50, 590, 100, 30);
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                MenuFuncionarioView menuFuncionarioView = new MenuFuncionarioView();
                menuFuncionarioView.setVisible(true);
                dispose(); 
            }
        });
        getContentPane().add(voltarButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CadastroFuncionarioView().setVisible(true);
            }
        });
    }
}
