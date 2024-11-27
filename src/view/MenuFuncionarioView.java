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

        // Adicionando os botões de funcionalidades
        addActionButton("Abertura de conta", 117, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirTela(new AberturaContaView());
            }
        });

        addActionButton("Encerramento de conta", 151, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirTela(new EncerramentoContaView());
            }
        });

        addActionButton("Consulta de dados", 219, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirTela(new TelaconsultaView());
            }
        });

        addActionButton("Alteração de dados", 185, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirTela(new TelaAlteracaoDeDadosView());
            }
        });

        addActionButton("Cadastro de funcionário", 254, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Verifica a senha para permitir cadastro
                String senha = JOptionPane.showInputDialog("Digite a senha para cadastrar funcionário:");
                if ("admin123".equals(senha)) {
                    abrirTela(new CadastroFuncionarioView());
                } else {
                    JOptionPane.showMessageDialog(null, "Senha incorreta!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Geração de relatórios - ainda sem ação associada
        JButton btnGeraoDeRelatrios = new JButton("Geração de relatórios");
        btnGeraoDeRelatrios.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnGeraoDeRelatrios.setBounds(140, 289, 188, 23);
        getContentPane().add(btnGeraoDeRelatrios);

        // Botão de Sair
        JButton botaosair = new JButton("Sair");
        botaosair.setFont(new Font("Tahoma", Font.BOLD, 12));
        botaosair.setBounds(360, 344, 114, 23);
        botaosair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LoginView telaprincipal = new LoginView();
                telaprincipal.setVisible(true);
                dispose(); 
            }
        });
        getContentPane().add(botaosair);
    }

    // Método auxiliar para adicionar os botões de ação
    private void addActionButton(String text, int yPos, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setFont(new Font("Tahoma", Font.BOLD, 12));
        button.setBounds(140, yPos, 188, 23);
        button.addActionListener(actionListener);
        getContentPane().add(button);
    }

    // Método para abrir novas telas
    private void abrirTela(JFrame tela) {
        tela.setVisible(true);
        dispose();
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
