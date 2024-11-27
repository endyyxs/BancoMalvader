package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Cliente;
import model.Conta;
import model.ContaCorrente;
import util.DBUtil;

public class ContaCorrenteDAO extends ContaDAO {

    private Connection conexao;

    public ContaCorrenteDAO() {
        this.conexao = DBUtil.getConnection();
    }

    // Consultar limite de uma conta corrente
    public double consultarLimite(Cliente cliente) {
        String sql = "SELECT cc.limite FROM conta_corrente cc " +
                     "JOIN conta c ON cc.conta_id = c.id_conta " +
                     "WHERE c.cliente_id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, cliente.getId());  // Usando o ID do cliente
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("limite");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao consultar limite: " + e.getMessage());
            throw new RuntimeException("Erro ao consultar limite", e);
        }
        return 0;  // Retorna 0 se não houver limite definido
    }

    @Override
    public double consultarSaldo(Cliente cliente) {
        // Utiliza o método da classe base (ContaDAO) para consultar o saldo
        return super.consultarSaldo(cliente);
    }

    @Override
    public boolean depositar(Cliente cliente, double valor) {
        // Utiliza o método da classe base (ContaDAO) para realizar o depósito
        return super.depositar(cliente, valor);
    }

    @Override
    public boolean sacar(Cliente cliente, double valor) {
        // Utiliza o método da classe base (ContaDAO) para realizar o saque
        return super.sacar(cliente, valor);
    }

    @Override
    public List<String> consultarExtrato(Cliente cliente) {
        // Adicione lógica específica para ContaCorrente, se necessário
        System.out.println("Consultando extrato para conta corrente");
        
        // Chama o método da classe base (ContaDAO)
        return super.consultarExtrato(cliente);
    }
}

