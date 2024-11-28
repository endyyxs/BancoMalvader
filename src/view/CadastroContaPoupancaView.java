package view;

import javax.swing.*;
import controller.ContaPoupancaController;
import model.Cliente;
import model.ContaPoupanca;
import model.Endereco;
import model.Usuario;
import dao.ContaPoupancaDAO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class CadastroContaPoupancaView extends JFrame {
    private static final long serialVersionUID = 1L;
    
    private JTextField agenciaField;
    private JTextField numeroContaField;
    private JTextField nomeField;
    private JTextField CPFField;
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

        getContentPane().setBackground(new Color(252, 214, 247)); 
        this.setSize(600, 650); 
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        getContentPane().setLayout(null); 
        this.setLocationRelativeTo(null); 

        JLabel label = new JLabel("Cadastro de Cliente - Conta Poupança");
        label.setFont(new Font("Tahoma", Font.BOLD, 18));
        label.setBounds(140, 20, 350, 30);
        getContentPane().add(label);
        
        addLabeledField("Agência:", 50, 60, 150, agenciaField = new JTextField());
        addLabeledField("Número da Conta:", 50, 100, 150, numeroContaField = new JTextField());
        addLabeledField("Nome do Cliente:", 50, 140, 150, nomeField = new JTextField());
        addLabeledField("CPF:", 50, 180, 150, CPFField = new JTextField());
        addLabeledField("Data de Nascimento:", 50, 220, 150, dataNascimentoField = new JTextField());
        addLabeledField("Telefone:", 50, 260, 150, telefoneField = new JTextField());
        addLabeledField("Endereço:", 50, 300, 150, enderecoField = new JTextField());
        addLabeledField("CEP:", 50, 340, 150, cepField = new JTextField());
        addLabeledField("Local:", 50, 380, 150, localField = new JTextField());
        addLabeledField("Número da Casa:", 50, 420, 150, numeroCasaField = new JTextField());
        addLabeledField("Bairro:", 50, 460, 150, bairroField = new JTextField());
        addLabeledField("Cidade:", 50, 500, 150, cidadeField = new JTextField());
        addLabeledField("Estado:", 50, 540, 150, estadoField = new JTextField());
        addLabeledField("Senha da Conta", 50, 580, 200, senhaField = new JPasswordField());
        
        JButton enviarButton = new JButton("Enviar");
        enviarButton.setBounds(200, 620, 100, 30);
        
        enviarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                	String nome = nomeField.getText();
                	String agencia = agenciaField.getText();
                	String conta = numeroContaField.getText();
                	String CPF = CPFField.getText();
                	String telefone = telefoneField.getText();
                	Endereco endereco = new Endereco();
                	String senha = senhaField.getText();
                	
                	if (agencia.isEmpty() || conta.isEmpty() || nome.isEmpty()) {
                	    JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
                	    return;
                	}
                	
                	LocalDate dataNascimento = null;
            
                	try {
                		dataNascimento = LocalDate.parse(dataNascimentoField.getText()); 
                	} catch (Exception ex) {
                	    JOptionPane.showMessageDialog(null, "Data de nascimento inválida! Use o formato yyyy-MM-dd.", "Erro", JOptionPane.ERROR_MESSAGE);
                	    return;
                	}

                	Cliente cliente = new Cliente(
                	    0,
                	    nome,
                	    CPF,
                	    dataNascimento, 
                	    telefone,
                	    endereco,
                	    senha
                	);
                	
                	ContaPoupanca contaPoupanca = new ContaPoupanca(
                	    Integer.parseInt(conta),
                	    agencia,
                	    0.0,
                	    0.05
                	);

                	ContaPoupancaDAO contaPoupancaDAO = new ContaPoupancaDAO();
                	ContaPoupancaController contaPoupancaController = new ContaPoupancaController(contaPoupancaDAO);
                	contaPoupancaController.salvarContaPoupanca(contaPoupanca, cliente);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar conta poupança: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        getContentPane().add(enviarButton);

        
        JButton voltarButton = new JButton("Voltar");
        voltarButton.setBounds(320, 620, 100, 30);
        voltarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MenuFuncionarioView menuFuncionarioView = new MenuFuncionarioView();
                menuFuncionarioView.setVisible(true);
                dispose(); 
            }
        });
        getContentPane().add(voltarButton);
    }

    
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
                new CadastroContaPoupancaView().setVisible(true); 
            }
        });
    }
}