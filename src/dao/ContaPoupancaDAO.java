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
                    int idCliente = rs.getInt("id_cliente"); // Para buscar o cliente
                    return new ContaPoupanca(numero, agencia, saldo, null, taxaRendimento); // Cliente é null aqui, pode ser buscado depois
                } else {
                    return null; // Conta não encontrada
                }
            }
        }
    }

	@Override
	public double consultarSaldo(Cliente cliente) {
		// TODO Auto-generated method stub
		return super.consultarSaldo(cliente);
	}

	@Override
	public boolean depositar(Cliente cliente, double valor) {
		// TODO Auto-generated method stub
		return super.depositar(cliente, valor);
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
