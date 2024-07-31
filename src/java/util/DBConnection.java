package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=BTLWEB;encrypt=true;trustServerCertificate=true";
    private static final String USER = "sa";
    private static final String PASSWORD = "123456";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    protected Connection connection;
    public DBConnection() {
        try {
            // Edit URL , username, password to authenticate with your MS SQL Server
            String url = "jdbc:sqlserver://localhost:1433;databaseName=BTLWEB;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "123456";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Kết nối thất bại: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

//    // Phương thức kiểm tra kết nối
//    public void checkConnection() {
//        try {
//            if (connection != null && !connection.isClosed()) {
//                System.out.println("Kết nối thành công!");
//            } else {
//                System.out.println("Kết nối thất bại!");
//            }
//        } catch (SQLException e) {
//            System.out.println("Kết nối thất bại: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) {
//        DBConnection dbContext = new DBConnection();
//        dbContext.checkConnection();
//    }
}
