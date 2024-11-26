package dao;

import model.Cliente;
import model.Endereco;
import util.DBUtil;
import java.sql.*;

public class ClienteDAO {

    private Connection conexao;

    public ClienteDAO() {
        this.conexao = DBUtil.getConnection();
    }

    public void salvar(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO clientes (nome, cpf) VALUES (?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.executeUpdate();
        }
    }
    
    private Endereco buscarEndereco(int idCliente) throws SQLException {
        String sql = "SELECT * FROM enderecos WHERE id_cliente = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Endereco(
                    );
                }
                return null;
            }
        }
    }


    public Cliente buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM clientes WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                	Endereco endereco = buscarEndereco(rs.getInt("id"));
                	return new Cliente(
                	    rs.getInt("id"),
                	    rs.getString("nome"),
                	    rs.getString("cpf"),
                	    rs.getDate("data_nascimento").toLocalDate(),
                	    rs.getString("telefone"),
                	    endereco
                	);

                }
                return null; // Cliente não encontrado
            }
        }
    }



    public void atualizar(Cliente cliente) throws SQLException {
        String sql = "UPDATE clientes SET nome = ?, cpf = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setInt(3, cliente.getId());
            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM clientes WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

	public static Cliente autenticarSenha(String senha) {
		// TODO Auto-generated method stub
		return null;
	}
}
