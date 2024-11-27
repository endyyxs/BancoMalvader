package controller;

import java.util.List;
import javax.swing.JOptionPane;
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
                // Calcular o rendimento com base no saldo e na taxa de rendimento
                return contaPoupanca.getSaldo() * contaPoupanca.getTaxaRendimento();
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
    public void salvarContaPoupanca(ContaPoupanca contaPoupanca) {
        try {
            super.dao.salvar(contaPoupanca);  // Chamando o método do DAO para salvar a conta no banco
            JOptionPane.showMessageDialog(null, "Conta Poupança cadastrada com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar a conta: " + e.getMessage());
        }
    }

    // Não é necessário sobrescrever esses métodos, pois já são tratados pela classe pai
}
