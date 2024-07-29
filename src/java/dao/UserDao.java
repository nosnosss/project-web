package dao;

import model.User;


import java.sql.*;
import until.DBConnection;

public class UserDao {
    public User getUserByUsername(String username) throws SQLException {
        Connection connection = DBConnection.getConnection();
        String query = "SELECT * FROM Users WHERE username = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setEmail(resultSet.getString("email"));
            user.setRole(resultSet.getString("role"));
            user.setLocked(resultSet.getBoolean("locked"));
            return user;
        }
        return null;
    }

    public boolean addUser(User user) throws SQLException {
        Connection connection = DBConnection.getConnection();
        String query = "INSERT INTO Users (username, password, email, role, locked) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getEmail());
        statement.setString(4, user.getRole());
        statement.setBoolean(5, user.isLocked());
        int rowsInserted = statement.executeUpdate();
        return rowsInserted > 0;
    }
}
