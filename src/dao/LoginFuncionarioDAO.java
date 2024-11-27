package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.DBUtil;
import org.mindrot.jbcrypt.BCrypt;  // Biblioteca para hashing de senhas

public class LoginFuncionarioDAO {
    
    // Método para validar o login do funcionário
    public boolean validarLogin(String usuario, String senha) {
        String sql = "SELECT senha FROM usuarios WHERE usuario = ?";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, usuario);
            
            ResultSet rs = stmt.executeQuery();
            
            // Verifica se o usuário existe
            if (rs.next()) {
                // Recupera o hash da senha do banco de dados
                String senhaHash = rs.getString("senha");
                
                // Compara a senha fornecida com o hash armazenado
                if (BCrypt.checkpw(senha, senhaHash)) {
                    return true; // Login válido
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao verificar login: " + e.getMessage());
        }
        
        return false; // Se não houver usuário com essas credenciais ou se a senha for inválida
    }
}
