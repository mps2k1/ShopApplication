package pl.mps2k1;

import java.sql.*;

public class DBConnector {
    private static final String URL = "jdbc:postgresql://localhost:5432/shop";
    private static final String USER = "postgres";
    private static final String PASSWORD = "qwerty";

    private Connection connection;

    public void DBConnect() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Połączono pomyślnie");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeDBConnect() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}
