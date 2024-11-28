package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Funcionario;
import util.DBUtil;

public class FuncionarioDAO {
    private Connection connection;

    public FuncionarioDAO() {
        this.connection = DBUtil.getConnection();
    }

    public void cadastrarFuncionario(Funcionario funcionario) throws SQLException {
        String sql = "INSERT INTO funcionario (nome, cpf, data_nascimento, telefone, codigo_funcionario, cargo, senha) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCpf());
            stmt.setDate(3, Date.valueOf(funcionario.getDataNascimento()));
            stmt.setString(4, funcionario.getTelefone());
            stmt.setString(5, funcionario.getCodigoFuncionario());
            stmt.setString(6, funcionario.getCargo());
            stmt.setString(7, funcionario.getSenha());
            stmt.executeUpdate();

            
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    funcionario.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public Funcionario consultarFuncionario(int id) throws SQLException {
        String sql = "SELECT * FROM funcionario WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Funcionario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getDate("data_nascimento").toLocalDate(),
                        rs.getString("telefone"),
                        null, 
                        rs.getString("codigo_funcionario"),
                        rs.getString("cargo"),
                        rs.getString("senha")
                    );
                }
            }
        }
        return null;
    }

    
    public void alterarFuncionario(Funcionario funcionario) throws SQLException {
        String sql = "UPDATE funcionario SET nome = ?, cpf = ?, data_nascimento = ?, telefone = ?, codigo_funcionario = ?, cargo = ?, senha = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCpf());
            stmt.setDate(3, Date.valueOf(funcionario.getDataNascimento()));
            stmt.setString(4, funcionario.getTelefone());
            stmt.setString(5, funcionario.getCodigoFuncionario());
            stmt.setString(6, funcionario.getCargo());
            stmt.setString(7, funcionario.getSenha());
            stmt.setInt(8, funcionario.getId());
            stmt.executeUpdate();
        }
    }

    
    public void excluirFuncionario(int id) throws SQLException {
        String sql = "DELETE FROM funcionario WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    
    public List<Funcionario> listarFuncionarios() throws SQLException {
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM funcionario";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Funcionario funcionario = new Funcionario(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getDate("data_nascimento").toLocalDate(),
                    rs.getString("telefone"),
                    null, 
                    rs.getString("codigo_funcionario"),
                    rs.getString("cargo"),
                    rs.getString("senha")
                );
                funcionarios.add(funcionario);
            }
        }
        return funcionarios;
    }

    
    public void gerarRelatorioMovimentacao() throws SQLException {
        String sql = "SELECT t.id_transacao, t.tipo_transacao, t.valor, t.data_hora, c.numero_conta " +
                     "FROM transacao t " +
                     "JOIN conta c ON t.conta_id = c.id_conta";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                
                System.out.println("Transação: " + rs.getInt("id_transacao") + ", Tipo: " + rs.getString("tipo_transacao") +
                                   ", Valor: " + rs.getDouble("valor") + ", Data: " + rs.getTimestamp("data_hora") +
                                   ", Conta: " + rs.getString("numero_conta"));
            }
        }
    }
}
