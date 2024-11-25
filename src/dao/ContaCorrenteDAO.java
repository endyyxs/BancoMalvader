package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Cliente;
import util.DBUtil;

public class ContaCorrenteDAO extends ContaDAO {
	
	private Connection conexao;
	
	public ContaCorrenteDAO() {
		this.conexao = DBUtil.getConnection();
	}
	
	public double consultarLimite(Cliente cliente) {
	    String sql = "SELECT limite FROM cliente WHERE id = ?";
	    try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
	        stmt.setInt(1, cliente.getId());
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            return rs.getDouble("limite");
	        }
	    } catch (SQLException e) {
	        System.err.println("Erro ao consultar limite: " + e.getMessage());
	        throw new RuntimeException("Erro ao consultar limite", e);
	    }
	    return 0;
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
