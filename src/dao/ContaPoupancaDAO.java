package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Cliente;
import model.Conta;
import model.ContaPoupanca;
import util.DBUtil;

public class ContaPoupancaDAO extends ContaDAO {

    private Connection conexao;

    public ContaPoupancaDAO() {
        this.conexao = DBUtil.getConnection();
    }

    // Buscar conta poupança no banco de dados
    public ContaPoupanca buscarContaPoupanca(int numeroConta) throws SQLException {
        String sql = "SELECT * FROM conta WHERE numero_conta = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, numeroConta);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    double saldo = rs.getDouble("saldo");
                    int numero = rs.getInt("numero_conta");
                    String agencia = rs.getString("agencia");
                    int idCliente = rs.getInt("cliente_id");

                    // Buscar o cliente associado
                    ClienteDAO clienteDAO = new ClienteDAO();
                    Cliente cliente = clienteDAO.buscarPorId(idCliente);

                    // Criar e retornar a conta poupança
                    return new ContaPoupanca(numero, agencia, saldo, cliente, 0.0); // taxa_rendimento pode ser definido posteriormente
                } else {
                    return null; // Conta não encontrada
                }
            }
        }
    }

    // Método para salvar a conta poupança no banco de dados
    public void salvar(ContaPoupanca contaPoupanca) throws SQLException {
        // Garantir que o cliente foi salvo antes
        if (contaPoupanca.getCliente().getId() == 0) {
            ClienteDAO clienteDAO = new ClienteDAO();
            clienteDAO.salvar(contaPoupanca.getCliente());
        }

        String sqlConta = "INSERT INTO conta (numero_conta, agencia, saldo, cliente_id) VALUES (?, ?, ?, ?)";
        String sqlPoupanca = "INSERT INTO conta_poupanca (conta_id, taxa_rendimento) VALUES (?, ?)";
        try (PreparedStatement stmtConta = conexao.prepareStatement(sqlConta, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmtConta.setInt(1, contaPoupanca.getNumero());
            stmtConta.setString(2, contaPoupanca.getAgencia());
            stmtConta.setDouble(3, contaPoupanca.getSaldo());
            stmtConta.setInt(4, contaPoupanca.getCliente().getId());

            stmtConta.executeUpdate();

            // Obter o ID da conta gerada
            ResultSet generatedKeys = stmtConta.getGeneratedKeys();
            if (generatedKeys.next()) {
                int idConta = generatedKeys.getInt(1);

                // Agora, salvar as informações específicas da conta poupança
                try (PreparedStatement stmtPoupanca = conexao.prepareStatement(sqlPoupanca)) {
                    stmtPoupanca.setInt(1, idConta);
                    stmtPoupanca.setDouble(2, contaPoupanca.getTaxaRendimento());
                    stmtPoupanca.executeUpdate();
                }
            }

            // Confirmar transação
            conexao.commit();
        } catch (SQLException e) {
            try {
                if (conexao != null) {
                    conexao.rollback(); // Desfaz alterações
                }
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            throw new SQLException("Erro ao salvar a conta poupança no banco de dados.", e);
        } finally {
            // Certifique-se de fechar a conexão
            try {
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Herda e utiliza os métodos da classe ContaDAO
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
