package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;
import model.Conta;
import model.ContaCorrente;
import model.ContaPoupanca;
import util.DBUtil;

public class ContaDAO {

	private Connection conexao;
	
	public ContaDAO() {
		this.conexao = DBUtil.getConnection();
	}
	
	public double consultarSaldo(Cliente cliente) {
	    String sql = "SELECT saldo FROM cliente WHERE id = ?";
	    try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
	        stmt.setInt(1, cliente.getId());
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            return rs.getDouble("saldo");
	        }
	    } catch (SQLException e) {
	        System.err.println("Erro ao consultar saldo: " + e.getMessage());
	        throw new RuntimeException("Erro ao consultar saldo", e);
	    }
	    return 0;
	}
	
	public boolean depositar(Cliente cliente, double valor) {
	    String sql = "UPDATE cliente SET saldo = saldo + ? WHERE id = ?";
	    try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
	        stmt.setDouble(1, valor);
	        stmt.setInt(2, cliente.getId());
	        stmt.executeUpdate();
	        return true;
	    } catch (SQLException e) {
	        System.err.println("Erro ao depositar: " + e.getMessage());
	        throw new RuntimeException("Erro ao depositar", e);
	    }
	}

	public boolean sacar(Cliente cliente, double valor) {
	    String sql = "UPDATE cliente SET saldo = saldo - ? WHERE id = ? AND saldo >= ?";
	    try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
	        stmt.setDouble(1, valor);
	        stmt.setInt(2, cliente.getId());
	        stmt.setDouble(3, valor);
	        int rowsAffected = stmt.executeUpdate();
	        return rowsAffected > 0;
	    } catch (SQLException e) {
	        System.err.println("Erro ao sacar: " + e.getMessage());
	        throw new RuntimeException("Erro ao sacar", e);
	    }
	}

	public List<String> consultarExtrato(Cliente cliente) {
	    String sql = "SELECT data, tipo, valor FROM transacoes WHERE cliente_id = ? ORDER BY data DESC";
	    List<String> extrato = new ArrayList<>();
	    try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
	        stmt.setInt(1, cliente.getId());
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            String linha = String.format("Data: %s, Tipo: %s, Valor: R$ %.2f",
	                    rs.getDate("data"),
	                    rs.getString("tipo"),
	                    rs.getDouble("valor"));
	            extrato.add(linha);
	        }
	    } catch (SQLException e) {
	        System.err.println("Erro ao consultar extrato: " + e.getMessage());
	        throw new RuntimeException("Erro ao consultar extrato", e);
	    }
	    return extrato;
	}
	
	public void cadastrarConta(Conta conta, Cliente cliente) {
        if (conta instanceof ContaCorrente) {
            cadastrarContaCorrente((ContaCorrente) conta, cliente);
        } else if (conta instanceof ContaPoupanca) {
            cadastrarContaPoupanca((ContaPoupanca) conta, cliente);
        }
    }

    // Método específico para cadastrar Conta Corrente
	public boolean cadastrarContaCorrente(ContaCorrente contaCorrente, Cliente cliente) {
        String sqlCliente = "INSERT INTO cliente (usuario_id) VALUES (?)";
        String sqlConta = "INSERT INTO conta (numero_conta, agencia, saldo, tipo_conta, cliente_id) VALUES (?, ?, ?, ?, LAST_INSERT_ID())";
        String sqlContaCorrente = "INSERT INTO conta_corrente (limite, data_vencimento, conta_id) VALUES (?, ?, LAST_INSERT_ID())";

        try {
            // Inicia uma transação
            conexao.setAutoCommit(false);

            // Inserir o cliente
            try (PreparedStatement stmtCliente = conexao.prepareStatement(sqlCliente)) {
                stmtCliente.setInt(1, cliente.getId());
                stmtCliente.executeUpdate();
            }

            // Inserir a conta
            try (PreparedStatement stmtConta = conexao.prepareStatement(sqlConta)) {
                stmtConta.setDouble(1, contaCorrente.getNumero());
                stmtConta.setString(2, contaCorrente.getAgencia());
                stmtConta.setDouble(3, contaCorrente.getSaldo());
                stmtConta.setString(4, "corrente");  // Tipo de conta: "corrente"
                stmtConta.executeUpdate();
            }

            // Inserir dados específicos da conta corrente
            try (PreparedStatement stmtContaCorrente = conexao.prepareStatement(sqlContaCorrente)) {
                stmtContaCorrente.setDouble(1, contaCorrente.getLimite());
                stmtContaCorrente.setDate(2, java.sql.Date.valueOf(contaCorrente.getDataVencimento()));
                stmtContaCorrente.executeUpdate();
            }

            // Commit da transação
            conexao.commit();
            return true;
        } catch (SQLException e) {
            try {
                // Reverter transação em caso de erro
                conexao.rollback();
            } catch (SQLException rollbackEx) {
                System.err.println("Erro ao reverter transação: " + rollbackEx.getMessage());
            }
            System.err.println("Erro ao cadastrar conta corrente: " + e.getMessage());
            return false;
        } finally {
            try {
                conexao.setAutoCommit(true); // Restaurar o comportamento padrão de auto-commit
            } catch (SQLException autoCommitEx) {
                System.err.println("Erro ao restaurar auto-commit: " + autoCommitEx.getMessage());
            }
        }
    }

    // Método para cadastrar uma conta poupança
    public boolean cadastrarContaPoupanca(ContaPoupanca contaPoupanca, Cliente cliente) {
        String sqlCliente = "INSERT INTO cliente (usuario_id) VALUES (?)";
        String sqlConta = "INSERT INTO conta (numero_conta, agencia, saldo, tipo_conta, cliente_id) VALUES (?, ?, ?, ?, LAST_INSERT_ID())";
        String sqlContaPoupanca = "INSERT INTO conta_poupanca (taxa_rendimento, conta_id) VALUES (?, LAST_INSERT_ID())";

        try {
            // Inicia uma transação
            conexao.setAutoCommit(false);

            // Inserir o cliente
            try (PreparedStatement stmtCliente = conexao.prepareStatement(sqlCliente)) {
                stmtCliente.setInt(1, cliente.getId());
                stmtCliente.executeUpdate();
            }

            // Inserir a conta
            try (PreparedStatement stmtConta = conexao.prepareStatement(sqlConta)) {
                stmtConta.setDouble(1, contaPoupanca.getNumero());
                stmtConta.setString(2, contaPoupanca.getAgencia());
                stmtConta.setDouble(3, contaPoupanca.getSaldo());
                stmtConta.setString(4, "poupanca");  // Tipo de conta: "poupanca"
                stmtConta.executeUpdate();
            }

            // Inserir dados específicos da conta poupança
            try (PreparedStatement stmtContaPoupanca = conexao.prepareStatement(sqlContaPoupanca)) {
                stmtContaPoupanca.setDouble(1, contaPoupanca.getTaxaRendimento());
                stmtContaPoupanca.executeUpdate();
            }

            // Commit da transação
            conexao.commit();
            return true;
        } catch (SQLException e) {
            try {
                // Reverter transação em caso de erro
                conexao.rollback();
            } catch (SQLException rollbackEx) {
                System.err.println("Erro ao reverter transação: " + rollbackEx.getMessage());
            }
            System.err.println("Erro ao cadastrar conta poupança: " + e.getMessage());
            return false;
        } finally {
            try {
                conexao.setAutoCommit(true); // Restaurar o comportamento padrão de auto-commit
            } catch (SQLException autoCommitEx) {
                System.err.println("Erro ao restaurar auto-commit: " + autoCommitEx.getMessage());
            }
        }
    }
}