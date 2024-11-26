package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Cliente;
import model.ContaPoupanca;
import util.DBUtil;

public class ContaPoupancaDAO extends ContaDAO {
    
    private Connection conexao;
    
    public ContaPoupancaDAO() {
        this.conexao = DBUtil.getConnection();
    }
    
    // Buscar conta poupança no banco de dados
    public ContaPoupanca buscarContaPoupanca(int numeroConta) throws SQLException {
        String sql = "SELECT * FROM contas_poupanca WHERE numero = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, numeroConta);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    double saldo = rs.getDouble("saldo");
                    double taxaRendimento = rs.getDouble("taxa_rendimento");
                    int numero = rs.getInt("numero");
                    String agencia = rs.getString("agencia");
                    int idCliente = rs.getInt("id_cliente");

                    // Buscar o cliente associado
                    ClienteDAO clienteDAO = new ClienteDAO();
                    Cliente cliente = clienteDAO.buscarClientePorId(idCliente);

                    return new ContaPoupanca(numero, agencia, saldo, cliente, taxaRendimento);
                } else {
                    return null; // Conta não encontrada
                }
            }
        }
    }

    // Salvar conta poupança no banco de dados
    public void salvar(ContaPoupanca contaPoupanca) throws SQLException {
        // Garantir que o cliente foi salvo antes
        if (contaPoupanca.getCliente().getId() == 0) {
            ClienteDAO clienteDAO = new ClienteDAO();
            clienteDAO.salvar(contaPoupanca.getCliente());
        }

        String sql = "INSERT INTO contas_poupanca (numero, agencia, saldo, taxa_rendimento, id_cliente) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, contaPoupanca.getNumero());
            stmt.setString(2, contaPoupanca.getAgencia());
            stmt.setDouble(3, contaPoupanca.getSaldo());
            stmt.setDouble(4, contaPoupanca.getTaxaRendimento());
            stmt.setInt(5, contaPoupanca.getCliente().getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erro ao salvar a conta poupança no banco de dados.", e);
        }
    }

    @Override
    public double consultarSaldo(Cliente cliente) {
        return super.consultarSaldo(cliente);
    }

    @Override
    public boolean depositar(Cliente cliente, double valor) {
        return super.depositar(cliente, valor);
    }

    @Override
    public boolean sacar(Cliente cliente, double valor) {
        return super.sacar(cliente, valor);
    }

    @Override
    public List<String> consultarExtrato(Cliente cliente) {
        return super.consultarExtrato(cliente);
    }
}

