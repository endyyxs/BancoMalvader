package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBUtil;

public class LoginFuncionarioDAO {
    
    public boolean validarLogin(String usuario, String senha) {
        String sql = "SELECT * FROM usuarios WHERE usuario = ? AND senha = ?";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, usuario);
            stmt.setString(2, senha);
            
            ResultSet rs = stmt.executeQuery();
            
            // Se houver um resultado, o login é válido
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao verificar login: " + e.getMessage());
        }
        return false; // Se não houver usuário com essas credenciais
    }
}