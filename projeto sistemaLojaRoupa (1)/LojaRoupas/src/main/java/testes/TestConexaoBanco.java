package testes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConexaoBanco {
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/loja_roupas";
        String jdbcUsername = "root";
        String jdbcPassword = "root";

        try {
            Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            if (connection != null && !connection.isClosed()) {
                System.out.println("Conexão ao banco de dados estabelecida com sucesso.");
            } else {
                System.out.println("Falha ao conectar ao banco de dados.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
}
