package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.FuncionarioController;

public class EncerramentoContaView extends JFrame {

    private JPasswordField senhaField;  
    private FuncionarioController controller; 

    public EncerramentoContaView() {
        super("Encerramento de Conta");

        getContentPane().setBackground(new Color(252, 214, 247));
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        this.setLocationRelativeTo(null);

        JLabel label = new JLabel("Digite a senha do administrador");
        label.setFont(new Font("Tahoma", Font.BOLD, 14));
        label.setBounds(100, 50, 250, 30);
        getContentPane().add(label);

        senhaField = new JPasswordField();
        senhaField.setBounds(100, 100, 200, 30);
        getContentPane().add(senhaField);

        JButton confirmarButton = new JButton("Confirmar");
        confirmarButton.setBounds(150, 150, 100, 30);
        getContentPane().add(confirmarButton);

        
        controller = new FuncionarioController(null);

        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Recupera a senha digitada
                String senha = new String(senhaField.getPassword());

                // Pede o número da conta para encerrar
                String numeroConta = JOptionPane.showInputDialog("Digite o número da conta a ser encerrada:");

                // Chama o método do Controller para processar o encerramento
                controller.processarEncerramentoConta(senha, numeroConta);

                // Se a senha for incorreta ou o número da conta for inválido, o Controller exibe uma mensagem de erro.
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EncerramentoContaView().setVisible(true);
            }
        });
    }
}