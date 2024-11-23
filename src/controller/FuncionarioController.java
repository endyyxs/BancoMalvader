package controller;

import javax.swing.JOptionPane;

public class FuncionarioController {

    public void processarEncerramentoConta(String senha, String numeroConta) {
        // Validação da senha
        if (!"admin123".equals(senha)) {  // Verifique a senha do administrador (pode vir de um banco de dados)
            JOptionPane.showMessageDialog(null, "Senha incorreta!", "Erro", JOptionPane.ERROR_MESSAGE);
            return; // Sai do método se a senha for incorreta
        }

        // Validação do número da conta
        if (numeroConta == null || numeroConta.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Número de conta inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
            return; // Sai do método se o número da conta não for válido
        }

        // Lógica para encerrar a conta (simulação)
        // Aqui você pode chamar métodos para acessar o banco de dados ou realizar a lógica necessária
        // Exemplo: buscar a conta pelo número e encerrá-la

        JOptionPane.showMessageDialog(null, "Conta " + numeroConta + " encerrada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }
}
