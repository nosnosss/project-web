package service;

import java.sql.SQLException;
import java.util.List;
import dao.OrderDAO;
import java.sql.Date;
import model.Order;
import model.OrderItem;

public class OrderService {
    private OrderDAO orderDAO;

    public OrderService() {
        this.orderDAO = new OrderDAO();
    }

    public List<Order> getOrdersByUserId(int userId) {
        try {
            return orderDAO.getOrdersByUserId(userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Order> getOrdersByDateRange(int userId, Date fromDate, Date toDate) {
        try {
            return orderDAO.getOrdersByDateRange(userId, fromDate, toDate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean cancelOrder(int orderId) {
        try {
            return orderDAO.cancelOrder(orderId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean reorder(int orderId) {
        try {
            return orderDAO.reorder(orderId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public List<OrderItem> getOrderItemsByOrderId(int orderId) {
        return orderDAO.getOrderItemsByOrderId(orderId);
        
    }
}
