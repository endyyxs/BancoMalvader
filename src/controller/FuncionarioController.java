package controller;

import javax.swing.JOptionPane;
import dao.ContaDAO; // Supondo que você tenha uma classe DAO para contas
import model.Conta;

public class FuncionarioController {

    private ContaDAO contaDAO; // Variável para acessar a lógica de contas

    public FuncionarioController(ContaDAO contaDAO) {
        this.contaDAO = contaDAO;
    }

    public void processarEncerramentoConta(String senha, String numeroConta) {
        // Validação da senha (em um sistema real, você deveria verificar isso em um banco de dados)
        if (!"admin123".equals(senha)) {  
            JOptionPane.showMessageDialog(null, "Senha incorreta!", "Erro", JOptionPane.ERROR_MESSAGE);
            return; // Sai do método se a senha for incorreta
        }

        // Validação do número da conta
        if (numeroConta == null || numeroConta.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Número de conta inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
            return; // Sai do método se o número da conta não for válido
        }

        try {
            // Busca a conta pelo número
            Conta conta = contaDAO.buscarPorNumero(numeroConta);
            if (conta == null) {
                JOptionPane.showMessageDialog(null, "Conta não encontrada!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Aqui você pode adicionar lógica para verificar se a conta pode ser encerrada
            // Por exemplo: verificar se não há saldo ou pendências

            // Realiza o encerramento da conta
            boolean sucesso = contaDAO.encerrarConta(conta);  // Chama o método de DAO para encerrar a conta
            if (sucesso) {
                JOptionPane.showMessageDialog(null, "Conta " + numeroConta + " encerrada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Falha ao encerrar a conta.", "Erro", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao processar encerramento da conta: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}

