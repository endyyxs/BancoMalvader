package controller;

import java.util.List;
import javax.swing.JOptionPane;

import dao.ClienteDAO;
import dao.ContaPoupancaDAO;
import model.Cliente;
import model.ContaPoupanca;

public class ContaPoupancaController extends ContaController {

    public ContaPoupancaController(ContaPoupancaDAO contaPoupancaDAO) {
        super(contaPoupancaDAO);
    }

    public double calcularRendimento(int numeroConta) {
        try {
            ContaPoupanca contaPoupanca = (ContaPoupanca) super.dao.buscarPorNumero(numeroConta);
            if (contaPoupanca != null) {
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
