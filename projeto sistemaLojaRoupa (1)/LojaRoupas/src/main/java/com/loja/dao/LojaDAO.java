package com.loja.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.loja.model.Loja;

public class LojaDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/loja_roupas";
    private String jdbcUsername = "root";
    private String jdbcPassword = ""; 

    private static final String INSERT_LOJA_SQL = "INSERT INTO lojas (nome, quantidade_itens, preco_medio) VALUES (?, ?, ?);";
    private static final String SELECT_LOJA_BY_ID = "SELECT id, nome, quantidade_itens, preco_medio FROM lojas WHERE id = ?;";
    private static final String SELECT_ALL_LOJAS = "SELECT * FROM lojas;";
    private static final String DELETE_LOJA_SQL = "DELETE FROM lojas WHERE id = ?;";
    private static final String UPDATE_LOJA_SQL = "UPDATE lojas SET nome = ?, quantidade_itens = ?, preco_medio = ? WHERE id = ?;";

    public LojaDAO() {}

    protected Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void insertLoja(Loja loja) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LOJA_SQL)) {
            preparedStatement.setString(1, loja.getNome());
            preparedStatement.setInt(2, loja.getQuantidadeItens());
            preparedStatement.setDouble(3, loja.getPrecoMedio());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Loja selectLoja(int id) {
        Loja loja = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LOJA_BY_ID);) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String nome = rs.getString("nome");
                int quantidadeItens = rs.getInt("quantidade_itens");
                double precoMedio = rs.getDouble("preco_medio");
                loja = new Loja(id, nome, quantidadeItens, precoMedio);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return loja;
    }

    public List<Loja> selectAllLojas() {
        List<Loja> lojas = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_LOJAS);) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                int quantidadeItens = rs.getInt("quantidade_itens");
                double precoMedio = rs.getDouble("preco_medio");
                lojas.add(new Loja(id, nome, quantidadeItens, precoMedio));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return lojas;
    }

    public boolean deleteLoja(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_LOJA_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateLoja(Loja loja) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_LOJA_SQL);) {
            statement.setString(1, loja.getNome());
            statement.setInt(2, loja.getQuantidadeItens());
            statement.setDouble(3, loja.getPrecoMedio());
            statement.setInt(4, loja.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
