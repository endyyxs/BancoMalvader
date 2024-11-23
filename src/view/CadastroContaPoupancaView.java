package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroContaPoupancaView extends JFrame {

    private JTextField agenciaField;
    private JTextField numeroContaField;
    private JTextField nomeClienteField;
    private JTextField cpfField;
    private JTextField dataNascimentoField;
    private JTextField telefoneField;
    private JTextField enderecoField;
    private JTextField cepField;
    private JTextField localField;
    private JTextField numeroCasaField;
    private JTextField bairroField;
    private JTextField cidadeField;
    private JTextField estadoField;
    private JPasswordField senhaField;

    public CadastroContaPoupancaView() {
        super("Cadastro Conta Poupança");
        
        getContentPane().setBackground(new Color(252, 214, 247)); // cor de fundo
        this.setSize(600, 650); // tamanho da tela
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // operação de fechamento
        getContentPane().setLayout(null); // layout nulo para posicionamento absoluto
        this.setLocationRelativeTo(null); // centraliza na tela

        // Título da tela
        JLabel label = new JLabel("Cadastro de Cliente - Conta Poupança");
        label.setFont(new Font("Tahoma", Font.BOLD, 18));
        label.setBounds(140, 20, 350, 30);
        getContentPane().add(label);

        // Campos para os dados pessoais
        addLabeledField("Agência:", 50, 60, 150, agenciaField = new JTextField());
        addLabeledField("Número da Conta:", 50, 100, 150, numeroContaField = new JTextField());
        addLabeledField("Nome do Cliente:", 50, 140, 150, nomeClienteField = new JTextField());
        addLabeledField("CPF:", 50, 180, 150, cpfField = new JTextField());
        addLabeledField("Data de Nascimento:", 50, 220, 150, dataNascimentoField = new JTextField());
        addLabeledField("Telefone:", 50, 260, 150, telefoneField = new JTextField());
        addLabeledField("Endereço:", 50, 300, 150, enderecoField = new JTextField());
        addLabeledField("CEP:", 50, 340, 150, cepField = new JTextField());
        addLabeledField("Local:", 50, 380, 150, localField = new JTextField());
        addLabeledField("Número da Casa:", 50, 420, 150, numeroCasaField = new JTextField());
        addLabeledField("Bairro:", 50, 460, 150, bairroField = new JTextField());
        addLabeledField("Cidade:", 50, 500, 150, cidadeField = new JTextField());
        addLabeledField("Estado:", 50, 540, 150, estadoField = new JTextField());
        
        // Campo para a senha
        JLabel senhaLabel = new JLabel("Senha da Conta:");
        senhaLabel.setBounds(50, 580, 150, 20);
        getContentPane().add(senhaLabel);
        
        senhaField = new JPasswordField();
        senhaField.setBounds(200, 580, 200, 20);
        getContentPane().add(senhaField);

        // Botão de Enviar
        JButton enviarButton = new JButton("Enviar");
        enviarButton.setBounds(200, 620, 100, 30);
        getContentPane().add(enviarButton);

        // Botão de Voltar
        JButton voltarButton = new JButton("Voltar");
        voltarButton.setBounds(320, 620, 100, 30);
        voltarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MenuFuncionarioView menuFuncionarioView = new MenuFuncionarioView();
                menuFuncionarioView.setVisible(true);
                dispose(); // Fecha a janela atual
            }
        });
        getContentPane().add(voltarButton);
    }

    // Método para criar o campo com label
    private void addLabeledField(String labelText, int x, int y, int width, JTextField field) {
        JLabel label = new JLabel(labelText);
        label.setBounds(x, y, 150, 20);
        getContentPane().add(label);

        field.setBounds(x + 150, y, width, 20);
        getContentPane().add(field);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CadastroContaPoupancaView().setVisible(true); // Tornar a janela visível
            }
        });
    }
}
