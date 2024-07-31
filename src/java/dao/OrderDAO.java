/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Order;
import until.DBConnection;
import java.sql.*;

/**
 *
 * @author boyca
 */
public class OrderDAO {
    public List<Order> getAllOrder() throws SQLException{
        List<Order> orders = new ArrayList<>();
        String query = "select * from Orders;";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setUserId(rs.getInt("user_id"));
                order.setStatus(rs.getString("status_order"));
                order.setTotalAmount(rs.getBigDecimal("total_amount"));
                order.setPaymentStatus(rs.getString("payment_status"));
                order.setPaymentMethod(rs.getString("payment_method"));
                order.setTime(rs.getTimestamp("time"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
    // Get orders within a specific date range
    public List<Order> getOrdersByDateRange(Date fromDate, Date toDate) throws SQLException {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM Orders WHERE time BETWEEN ? AND ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDate(1, fromDate);
            stmt.setDate(2, toDate);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setUserId(rs.getInt("user_id"));
                order.setStatus(rs.getString("status_order"));
                order.setTotalAmount(rs.getBigDecimal("total_amount"));
                order.setPaymentStatus(rs.getString("payment_status"));
                order.setPaymentMethod(rs.getString("payment_method"));
                order.setTime(rs.getTimestamp("time"));
                orders.add(order);
            }
        }
        return orders;
    }
    
    // Get orders within a specific date range and user
    public List<Order> getOrdersByDateRangeAndUser(int userId, Date fromDate, Date toDate) throws SQLException {
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
                order.setStatus(rs.getString("status_order"));
                order.setTotalAmount(rs.getBigDecimal("total_amount"));
                order.setPaymentStatus(rs.getString("payment_status"));
                order.setPaymentMethod(rs.getString("payment_method"));
                order.setTime(rs.getTimestamp("time"));
                orders.add(order);
            }
        }
        return orders;
    }
    
    public Order getOrderById(int id) throws SQLException{
        Order order = new Order();
        String query = "Select * from Orders where id = ?;";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                order.setId(rs.getInt("id"));
                order.setUserId(rs.getInt("user_id"));
                order.setStatus(rs.getString("status_order"));
                order.setTotalAmount(rs.getBigDecimal("total_amount"));
                order.setPaymentStatus(rs.getString("payment_status"));
                order.setPaymentMethod(rs.getString("payment_method"));
                order.setTime(rs.getTimestamp("time"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }
    
    public void updateStatusOrder(String status, int id){
        String query = "update Orders set status_order = ? where id = ?;";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, status);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
