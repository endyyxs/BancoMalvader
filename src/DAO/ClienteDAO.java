package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;
import model.Cliente;
import util.DBUtil;

public class ClienteDAO {
	
	private Connection conexao;
	
	public ClienteDAO() {
		this.conexao = DBUtil.getConnection();
	}
	
	public double consultarSaldo(Cliente cliente) {
	    String sql = "SELECT saldo FROM conta WHERE id = ?";
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
	    String sql = "UPDATE conta SET saldo = saldo + ? WHERE id = ?";
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
	    String sql = "UPDATE conta SET saldo = saldo - ? WHERE id = ? AND saldo >= ?";
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

	public double consultarLimite(Cliente cliente) {
	    String sql = "SELECT limite FROM conta_corrente WHERE id = ?";
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
}
