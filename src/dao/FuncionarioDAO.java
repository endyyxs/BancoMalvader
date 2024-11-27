package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Funcionario;
import util.DBUtil;

public class FuncionarioDAO {
    private Connection connection;

    // Construtor que recebe a conexão com o banco de dados
    public FuncionarioDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para cadastrar um novo funcionário
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

            // Obter o ID gerado automaticamente
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    funcionario.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    // Método para consultar os dados de um funcionário pelo ID
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
                        null, // O Endereco pode ser carregado de outra tabela se necessário
                        rs.getString("codigo_funcionario"),
                        rs.getString("cargo"),
                        rs.getString("senha")
                    );
                }
            }
        }
        return null;
    }

    // Método para atualizar os dados de um funcionário
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

    // Método para excluir um funcionário pelo ID
    public void excluirFuncionario(int id) throws SQLException {
        String sql = "DELETE FROM funcionario WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Método para listar todos os funcionários
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
                    null, // O Endereco pode ser carregado de outra tabela se necessário
                    rs.getString("codigo_funcionario"),
                    rs.getString("cargo"),
                    rs.getString("senha")
                );
                funcionarios.add(funcionario);
            }
        }
        return funcionarios;
    }

    // Método para gerar um relatório de movimentações de transações
    public void gerarRelatorioMovimentacao() throws SQLException {
        // Considerando que a tabela de transações existe no banco
        String sql = "SELECT t.id_transacao, t.tipo_transacao, t.valor, t.data_hora, c.numero_conta " +
                     "FROM transacao t " +
                     "JOIN conta c ON t.conta_id = c.id_conta";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                // Exemplo de impressão no console, pode ser modificado para salvar em um arquivo ou outro formato
                System.out.println("Transação: " + rs.getInt("id_transacao") + ", Tipo: " + rs.getString("tipo_transacao") +
                                   ", Valor: " + rs.getDouble("valor") + ", Data: " + rs.getTimestamp("data_hora") +
                                   ", Conta: " + rs.getString("numero_conta"));
            }
        }
    }
}
