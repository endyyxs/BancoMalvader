package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/bancomalvada";
    private static final String USER = "root";
    private static final String PASSWORD = "*Jujuba.1245";
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                connection.setAutoCommit(false); 
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Erro ao conectar ao banco de dados", e);
            }
        }
        
        return connection;
    }


    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

