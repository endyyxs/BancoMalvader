package dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.DBUtil;

public class LoginFuncionarioDAO {
    
    // Método para validar o login do funcionário
    public boolean validarLogin(String usuario, String senha) {
        String sql = "SELECT senha, salt FROM usuario WHERE usuario = ?";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, usuario);
            
            try (ResultSet rs = stmt.executeQuery()) {
                // Verifica se o usuário existe
                if (rs.next()) {
                    // Recupera o hash da senha e o salt do banco de dados
                    String senhaHash = rs.getString("senha");
                    String salt = rs.getString("salt");
                    
                    // Gera o hash da senha fornecida com o salt
                    String senhaHashCalculada = gerarHash(senha, salt);
                    
                    // Compara o hash calculado com o hash armazenado
                    if (senhaHash.equals(senhaHashCalculada)) {
                        return true; // Login válido
                    }
                }
            } catch (SQLException e) {
                System.err.println("Erro ao acessar o ResultSet: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.err.println("Erro ao verificar login: " + e.getMessage());
        }
        
        return false; // Se não houver usuário com essas credenciais ou se a senha for inválida
    }

    // Método para gerar o hash de uma senha com o salt
    private String gerarHash(String senha, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes());  // Adiciona o salt ao algoritmo
            byte[] hashBytes = md.digest(senha.getBytes());  // Calcula o hash da senha + salt
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                hexString.append(String.format("%02x", b));  // Converte o hash para formato hexadecimal
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao gerar o hash: " + e.getMessage(), e);
        }
    }

    // Método para gerar um salt aleatório
    public String gerarSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        StringBuilder hexString = new StringBuilder();
        for (byte b : salt) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }
}
