package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Order;
import model.OrderItem;
import util.DBConnection;

public class OrderDAO {
    // Get all orders for a specific user
    public List<Order> getOrdersByUserId(int userId) throws SQLException {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM Orders WHERE user_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setUserId(rs.getInt("user_id"));
                order.setStatus(rs.getString("status"));
                order.setTotalAmount(rs.getBigDecimal("total_amount"));
                order.setPaymentStatus(rs.getString("payment_status"));
                order.setPaymentMethod(rs.getString("payment_method"));
                order.setTime(rs.getTimestamp("time"));
                orders.add(order);
            }
        }
        return orders;
    }

    // Get orders within a specific date range
    public List<Order> getOrdersByDateRange(int userId, Date fromDate, Date toDate) throws SQLException {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM Orders WHERE user_id = ? AND time BETWEEN ? AND ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setDate(2, fromDate);
            stmt.setDate(3, toDate);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setUserId(rs.getInt("user_id"));
                order.setStatus(rs.getString("status"));
                order.setTotalAmount(rs.getBigDecimal("total_amount"));
                order.setPaymentStatus(rs.getString("payment_status"));
                order.setPaymentMethod(rs.getString("payment_method"));
                order.setTime(rs.getTimestamp("time"));
                orders.add(order);
            }
        }
        return orders;
    }

    // Cancel an order
    public boolean cancelOrder(int orderId) throws SQLException {
        String query = "UPDATE Orders SET status = 'canceled' WHERE id = ? AND status = 'pending'";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, orderId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    // Reorder a canceled order
    public boolean reorder(int orderId) throws SQLException {
        String query = "UPDATE Orders SET status = 'pending' WHERE id = ? AND status = 'canceled'";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, orderId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
    
    public List<OrderItem> getOrderItemsByOrderId(int orderId) {
    List<OrderItem> orderItems = new ArrayList<>();
    String sql = "SELECT oi.id, oi.order_id, oi.product_id, oi.quantity, oi.price, p.name AS product_name "
               + "FROM Order_Items oi "
               + "JOIN Products p ON oi.product_id = p.id "
               + "WHERE oi.order_id = ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, orderId);
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                OrderItem orderItem = new OrderItem();
                orderItem.setId(rs.getInt("id"));
                orderItem.setOrderId(rs.getInt("order_id"));
                orderItem.setProductId(rs.getInt("product_id"));
                orderItem.setQuantity(rs.getInt("quantity"));
                orderItem.setPrice(rs.getBigDecimal("price"));
                orderItem.setProductName(rs.getString("product_name")); // Lấy tên sản phẩm từ bảng Products
                orderItems.add(orderItem);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return orderItems;
}

}
