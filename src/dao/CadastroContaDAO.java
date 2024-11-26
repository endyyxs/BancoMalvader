package dao;

import model.Cliente;
import model.ContaCorrente;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CadastroContaDAO {

    private Connection conexao;

    public CadastroContaDAO() {
        this.conexao = DBUtil.getConnection();
    }

    public boolean cadastrarConta(ContaCorrente contaCorrente, Cliente cliente) {
        String sql = "INSERT INTO cliente (nome, cpf, data_nascimento, telefone, endereco, " +
                     "cep, local, numero_casa, bairro, cidade, estado, senha, saldo, limite_conta_corrente, " +
                     "data_vencimento) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setDate(3, java.sql.Date.valueOf(cliente.getDataNascimento()));  // Assume-se que cliente.getDataNascimento() retorna LocalDate
            stmt.setString(4, cliente.getTelefone());
            stmt.setString(5, cliente.getEndereco());
            stmt.setString(6, cliente.getCep());
            stmt.setString(7, cliente.getLocal());
            stmt.setString(8, cliente.getNumeroCasa());
            stmt.setString(9, cliente.getBairro());
            stmt.setString(10, cliente.getCidade());
            stmt.setString(11, cliente.getEstado());
            stmt.setString(12, contaCorrente.getSenha());
            stmt.setDouble(13, 0);  // Definindo saldo inicial como 0
            stmt.setDouble(14, contaCorrente.getLimiteContaCorrente());
            stmt.setDate(15, java.sql.Date.valueOf(contaCorrente.getDataVencimento()));

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;  // Retorna true se a conta foi cadastrada com sucesso
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar conta: " + e.getMessage());
            throw new RuntimeException("Erro ao cadastrar conta", e);
        }
    }
}
