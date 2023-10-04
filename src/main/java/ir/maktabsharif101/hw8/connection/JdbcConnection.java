package ir.maktabsharif101.hw8.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {
    private static final String DATABASE_URL="jdbc:postgresql://localhost:5432/postgres";
    private static final String DATABASE_USER="postgres";
    private static final String DATABASE_PASSWORD="12369874";
    private static final Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(DATABASE_URL,DATABASE_USER,DATABASE_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
