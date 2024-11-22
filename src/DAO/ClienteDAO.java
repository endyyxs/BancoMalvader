package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;
import util.DBUtil;

public class ClienteDAO {
	
	private Connection conexao;
	
	public ClienteDAO() {
		this.conexao = DBUtil.getConnection();
	}
	
	public double consultarSaldo(int clienteId) {
	    String sql = "SELECT saldo FROM cliente WHERE id = ?";
	    try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
	        stmt.setInt(1, clienteId);
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
	
	public boolean depositar(int clienteId, double valor) {
	    String sql = "UPDATE cliente SET saldo = saldo + ? WHERE id = ?";
	    try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
	        stmt.setDouble(1, valor);
	        stmt.setInt(2, clienteId);
	        stmt.executeUpdate();
	        return true;
	    } catch (SQLException e) {
	        System.err.println("Erro ao depositar: " + e.getMessage());
	        throw new RuntimeException("Erro ao depositar", e);
	    }
	}

	public boolean sacar(int clienteId, double valor) {
	    String sql = "UPDATE cliente SET saldo = saldo - ? WHERE id = ? AND saldo >= ?";
	    try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
	        stmt.setDouble(1, valor);
	        stmt.setInt(2, clienteId);
	        stmt.setDouble(3, valor);
	        int rowsAffected = stmt.executeUpdate();
	        return rowsAffected > 0;
	    } catch (SQLException e) {
	        System.err.println("Erro ao sacar: " + e.getMessage());
	        throw new RuntimeException("Erro ao sacar", e);
	    }
	}

	public List<String> consultarExtrato(int clienteId) {
	    String sql = "SELECT data, tipo, valor FROM transacoes WHERE cliente_id = ? ORDER BY data DESC";
	    List<String> extrato = new ArrayList<>();
	    try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
	        stmt.setInt(1, clienteId);
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

	public double consultarLimite(int clienteId) {
	    String sql = "SELECT limite FROM cliente WHERE id = ?";
	    try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
	        stmt.setInt(1, clienteId);
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

	
}
