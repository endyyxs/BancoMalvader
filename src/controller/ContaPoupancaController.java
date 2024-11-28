package controller;

import java.util.List;
import javax.swing.JOptionPane;

import dao.ClienteDAO;
import dao.ContaPoupancaDAO;
import model.Cliente;
import model.ContaPoupanca;

public class ContaPoupancaController extends ContaController {

    // Passando o DAO específico para ContaPoupanca no construtor da classe pai
    public ContaPoupancaController(ContaPoupancaDAO contaPoupancaDAO) {
        super(contaPoupancaDAO);  // Chamando o construtor da classe pai (ContaController)
    }

    // Método específico para calcular o rendimento da conta poupança
    public double calcularRendimento(int numeroConta) {
        try {
            // Buscar a conta poupança do banco de dados
            ContaPoupanca contaPoupanca = (ContaPoupanca) super.dao.buscarPorNumero(numeroConta);
            if (contaPoupanca != null) {
                // Verificar se a taxa de rendimento e saldo são válidos
                if (contaPoupanca.getTaxaRendimento() > 0 && contaPoupanca.getSaldo() > 0) {
                    return contaPoupanca.getSaldo() * contaPoupanca.getTaxaRendimento();
                } else {
                    System.out.println("Taxa de rendimento ou saldo inválidos.");
                    return 0;
                }
            } else {
                System.out.println("Conta poupança não encontrada.");
                return 0;
            }
        } catch (Exception e) {
            System.err.println("Erro ao calcular rendimento: " + e.getMessage());
            return 0;
        }
    }

    // Método para salvar uma nova conta poupança no banco de dados
    public void salvarContaPoupanca(ContaPoupanca contaPoupanca, Cliente cliente) {
        try {
            /*
            if (contaPoupanca.getSaldo() <= 0 || contaPoupanca.getNumero() <= 0) {
                JOptionPane.showMessageDialog(null, "A conta precisa ter um número válido e saldo positivo!");
                return;
            }
            */

        	if(super.dao.cadastrarConta(contaPoupanca, cliente)) {
        		JOptionPane.showMessageDialog(null, "Conta Poupança cadastrada com sucesso!");
        	} else {
        		JOptionPane.showMessageDialog(null, "Erro ao cadastrar conta, tente novamente mais tarde...", "Erro", JOptionPane.ERROR_MESSAGE);
        	}
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar a conta: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}




