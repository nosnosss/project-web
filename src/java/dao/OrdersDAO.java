package dao;

import model.Orders;
import until.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrdersDAO {
    public void saveOrder(Orders order) {
        String sql = "INSERT INTO Orders (user_id, status, price, quantity, total_amount, payment_status, payment_method, time) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection jdbcConnection = DBConnection.getConnection();
             PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setInt(1, order.getUserId());
            statement.setString(2, order.getStatus());
            statement.setDouble(3, order.getPrice());
            statement.setInt(4, order.getQuantity());
            statement.setDouble(5, order.getTotalAmount());
            statement.setString(6, order.getPaymentStatus());
            statement.setString(7, order.getPaymentMethod());
            statement.setDate(8, new java.sql.Date(order.getTime().getTime()));
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
