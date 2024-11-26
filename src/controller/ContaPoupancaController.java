package controller;

import java.util.List;
import javax.swing.JOptionPane;
import dao.ContaPoupancaDAO;
import model.Cliente;
import model.ContaPoupanca;

public class ContaPoupancaController extends ContaController {

    private ContaPoupancaDAO contaPoupancaDAO;

    // Passando o DAO específico para ContaPoupanca
    public ContaPoupancaController(ContaPoupancaDAO contaPoupancaDAO) {
        super(contaPoupancaDAO);  // Chamando o construtor da classe pai (se necessário)
        this.contaPoupancaDAO = contaPoupancaDAO;
    }

    public double calcularRendimento(int numeroConta) {
        try {
            // Buscar a conta poupança do banco de dados
            ContaPoupanca contaPoupanca = contaPoupancaDAO.buscarContaPoupanca(numeroConta);
            if (contaPoupanca != null) {
                // Calcular o rendimento com base no saldo e na taxa de rendimento
                return contaPoupanca.getSaldo() * contaPoupanca.getTaxaRendimento();
            } else {
                System.out.println("Conta poupança não encontrada.");
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // Método para salvar uma nova conta poupança no banco de dados
    public void salvarContaPoupanca(ContaPoupanca contaPoupanca) {
        try {
            contaPoupancaDAO.salvar(contaPoupanca);  // Chamando o método do DAO para salvar a conta no banco
            JOptionPane.showMessageDialog(null, "Conta Poupança cadastrada com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar a conta: " + e.getMessage());
        }
    }

    @Override
    public double consultarSaldo(Cliente cliente) {
        // TODO Auto-generated method stub
        return super.consultarSaldo(cliente);
    }

    @Override
    public void depositar(Cliente cliente, double valor) {
        // TODO Auto-generated method stub
        super.depositar(cliente, valor);
    }

    @Override
    public boolean sacar(Cliente cliente, double valor) {
        // TODO Auto-generated method stub
        return super.sacar(cliente, valor);
    }

    @Override
    public List<String> consultarExtrato(Cliente cliente) {
        // TODO Auto-generated method stub
        return super.consultarExtrato(cliente);
    }
}



