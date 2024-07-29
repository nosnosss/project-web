package until;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:sqlserver://DESKTOP-9N1E28Q\\SQLEXPRESS:1433;databaseName=BTLWEB";
    private static final String USER = "sa";
    private static final String PASSWORD = "123";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
