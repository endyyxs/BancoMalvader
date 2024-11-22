package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private static final String URL = "jdbc:mysql://localhost/bancomalvada";
	private static final String USER = "root";
	private static final String PASSWORD = "c@tolic@";
	private static Connection conexao;
	
	private DBUtil() {
		
	}
	
	public static Connection getConnection() {
		 if (conexao == null) {
		 		try {
	                conexao = DriverManager.getConnection(URL, USER, PASSWORD);
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

