package Factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public Connection getConnection() {
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/gerencia_condominio";
        try {
            conn = DriverManager.getConnection(url, "gerencia_condominio_user", "@*1206Fb");
        }catch(SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }
}
