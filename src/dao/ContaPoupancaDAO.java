package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Cliente;
import model.ContaPoupanca;
import util.DBUtil;

public class ContaPoupancaDAO extends ContaDAO {

    private Connection conexao;

    public ContaPoupancaDAO() {
        this.conexao = DBUtil.getConnection();
    }

    // Buscar conta poupança no banco de dados
    public ContaPoupanca buscarContaPoupanca(String numeroConta) throws SQLException {
        String sql = "SELECT c.id_conta, c.numero_conta, c.agencia, c.saldo, cp.taxa_rendimento, c.cliente_id " +
                     "FROM conta c " +
                     "JOIN conta_poupanca cp ON c.id_conta = cp.conta_id " +
                     "WHERE c.numero_conta = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, numeroConta); // numero_conta is a varchar(20), so use setString
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    double saldo = rs.getDouble("saldo");
                    double taxaRendimento = rs.getDouble("taxa_rendimento");
                    String numero = rs.getString("numero_conta"); // Correctly fetch as String
                    String agencia = rs.getString("agencia");
                    int idCliente = rs.getInt("cliente_id");

                    // Buscar o cliente associado
                    Cliente cliente = buscarClientePorId(idCliente); // Assuming you have a method to get a Client by ID

                    // Criar e retornar a conta poupança
                    return new ContaPoupanca(numero, agencia, saldo, cliente, taxaRendimento);
                } else {
                    return null; // Conta não encontrada
                }
            }
        }
    }

    // Assuming you have a method to fetch client details by id
    private Cliente buscarClientePorId(int idCliente) throws SQLException {
        String sql = "SELECT id_usuario, nome, cpf, data_nascimento, telefone, tipo_usuario " +
                     "FROM usuario WHERE id_usuario = (SELECT usuario_id FROM cliente WHERE id_cliente = ?)";
        
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setId(idCliente);
                    cliente.setNome(rs.getString("nome"));
                    cliente.setCpf(rs.getString("cpf"));
                    cliente.setDataNascimento(rs.getDate("data_nascimento"));
                    cliente.setTelefone(rs.getString("telefone"));
                    cliente.setTipoUsuario(rs.getString("tipo_usuario"));
                    return cliente;
                }
            }
        }
        return null;
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

