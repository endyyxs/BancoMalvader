package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class SaqueClienteView extends JFrame {
	private JPasswordField senha;

    public SaqueClienteView() {
    	super("Saque"); 
        getContentPane().setBackground(new Color(252, 214, 247));
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        this.setLocationRelativeTo(null); 
        
        JLabel saque = new JLabel("SAQUE");
        saque.setForeground(new Color(63, 63, 63)); 
        saque.setFont(new Font("Tahoma", Font.BOLD, 30));
        saque.setBounds(135, 49, 140, 34);
        getContentPane().add(saque);
        
        JLabel senhasaque = new JLabel("Senha");
        senhasaque.setFont(new Font("Tahoma", Font.PLAIN, 13));
        senhasaque.setBounds(62, 114, 46, 14);
        getContentPane().add(senhasaque);
        senha = new JPasswordField();
        senha.setBounds(62, 132, 262, 20);
        getContentPane().add(senha);
        
        JButton botaoconsultarsaldo = new JButton("Entrar");
        botaoconsultarsaldo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        botaoconsultarsaldo.setBounds(145, 163, 100, 30);
        getContentPane().add(botaoconsultarsaldo);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SaqueClienteView().setVisible(true);
            }
        });
    }
}
