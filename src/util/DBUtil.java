package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    // Corrigindo a URL de conexão do MySQL
    private static final String URL = "jdbc:mysql://localhost:3307/bancomalvada";
    private static final String USER = "root";
    private static final String PASSWORD = "catolica";
    private static Connection conexao;

    private DBUtil() {
    }

    public static Connection getConnection() {
        if (conexao == null) {
            try {
                // Estabelecendo a conexão com o banco de dados
                conexao = DriverManager.getConnection(URL, USER, PASSWORD);
                // Desabilitar auto-commit para gerenciar transações manualmente
                conexao.setAutoCommit(false); 
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Erro ao conectar ao banco de dados", e);
            }
        }
        return conexao;
    }


    public static void closeConnection() {
        if (conexao != null) {
            try {
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

