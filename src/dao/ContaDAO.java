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

    // Consultar saldo de um cliente
    public double consultarSaldo(Cliente cliente) {
        String sql = "SELECT saldo FROM conta WHERE cliente_id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, cliente.getId());  // Usando o ID do cliente
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

    // Depositar valor na conta do cliente
    public boolean depositar(Cliente cliente, double valor) {
        String sql = "UPDATE conta SET saldo = saldo + ? WHERE cliente_id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setDouble(1, valor);
            stmt.setInt(2, cliente.getId());  // Usando o ID do cliente
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                registrarTransacao(cliente, valor, "deposito");
            }
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao depositar: " + e.getMessage());
            throw new RuntimeException("Erro ao depositar", e);
        }
    }

    // Sacar valor da conta do cliente
    public boolean sacar(Cliente cliente, double valor) {
        String sql = "UPDATE conta SET saldo = saldo - ? WHERE cliente_id = ? AND saldo >= ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setDouble(1, valor);
            stmt.setInt(2, cliente.getId());  // Usando o ID do cliente
            stmt.setDouble(3, valor);  // Verificando se o saldo é suficiente
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                registrarTransacao(cliente, valor, "saque");
            }
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao sacar: " + e.getMessage());
            throw new RuntimeException("Erro ao sacar", e);
        }
    }

    // Consultar extrato de transações de um cliente
    public List<String> consultarExtrato(Cliente cliente) {
        String sql = "SELECT t.data_hora, t.tipo_transacao, t.valor " +
                     "FROM transacao t " +
                     "JOIN conta c ON t.conta_id = c.id_conta " +
                     "WHERE c.cliente_id = ? ORDER BY t.data_hora DESC";
        List<String> extrato = new ArrayList<>();
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, cliente.getId());  // Usando o ID do cliente
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String linha = String.format("Data: %s, Tipo: %s, Valor: R$ %.2f",
                        rs.getTimestamp("data_hora"),
                        rs.getString("tipo_transacao"),
                        rs.getDouble("valor"));
                extrato.add(linha);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao consultar extrato: " + e.getMessage());
            throw new RuntimeException("Erro ao consultar extrato", e);
        }
        return extrato;
    }

    // Registrar transação no banco de dados (depósito ou saque)
    private void registrarTransacao(Cliente cliente, double valor, String tipoTransacao) {
        String sql = "INSERT INTO transacao (tipo_transacao, valor, data_hora, conta_id) " +
                     "SELECT ?, ?, NOW(), id_conta FROM conta WHERE cliente_id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, tipoTransacao);
            stmt.setDouble(2, valor);
            stmt.setInt(3, cliente.getId());  // Usando o ID do cliente
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao registrar transação: " + e.getMessage());
            throw new RuntimeException("Erro ao registrar transação", e);
        }
    }

    // Cadastrar conta genérica (Corrente ou Poupança)
    public boolean cadastrarConta(Conta conta, Cliente cliente) {
        try {
            conexao.setAutoCommit(false);  // Inicia a transação

            // Inserir cliente e dados do usuário
            if (!inserirCliente(cliente)) {
                return false;
            }

            // Inserir conta
            if (!inserirConta(conta, cliente)) {
                return false;
            }

            // Inserir dados específicos da conta (Conta Corrente ou Conta Poupança)
            if (conta instanceof ContaCorrente) {
                if (!inserirContaCorrente((ContaCorrente) conta)) {
                    return false;
                }
            } else if (conta instanceof ContaPoupanca) {
                if (!inserirContaPoupanca((ContaPoupanca) conta)) {
                    return false;
                }
            }

            conexao.commit();  // Commit da transação
            return true;
        } catch (SQLException e) {
            try {
                conexao.rollback();  // Rollback em caso de erro
            } catch (SQLException rollbackEx) {
                System.err.println("Erro ao reverter transação: " + rollbackEx.getMessage());
            }
            System.err.println("Erro ao cadastrar conta: " + e.getMessage());
            return false;
        } finally {
            try {
                conexao.setAutoCommit(true);  // Restaura o comportamento padrão de auto-commit
            } catch (SQLException autoCommitEx) {
                System.err.println("Erro ao restaurar auto-commit: " + autoCommitEx.getMessage());
            }
        }
    }

    // Inserir dados do cliente no banco de dados
    private boolean inserirCliente(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO cliente (usuario_id) VALUES (?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, cliente.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    // Inserir dados da conta no banco de dados
    private boolean inserirConta(Conta conta, Cliente cliente) throws SQLException {
        String sql = "INSERT INTO conta (numero_conta, agencia, saldo, tipo_conta, cliente_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setDouble(1, conta.getNumero());
            stmt.setString(2, conta.getAgencia());
            stmt.setDouble(3, conta.getSaldo());
            stmt.setString(4, conta instanceof ContaCorrente ? "corrente" : "poupanca");
            stmt.setInt(5, cliente.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    // Inserir dados específicos da Conta Corrente
    private boolean inserirContaCorrente(ContaCorrente contaCorrente) throws SQLException {
        String sql = "INSERT INTO conta_corrente (limite, data_vencimento, conta_id) VALUES (?, ?, LAST_INSERT_ID())";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setDouble(1, contaCorrente.getLimite());
            stmt.setDate(2, java.sql.Date.valueOf(contaCorrente.getDataVencimento()));
            return stmt.executeUpdate() > 0;
        }
    }

    // Inserir dados específicos da Conta Poupança
    private boolean inserirContaPoupanca(ContaPoupanca contaPoupanca) throws SQLException {
        String sql = "INSERT INTO conta_poupanca (taxa_rendimento, conta_id) VALUES (?, LAST_INSERT_ID())";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setDouble(1, contaPoupanca.getTaxaRendimento());
            return stmt.executeUpdate() > 0;
        }
    }
}
