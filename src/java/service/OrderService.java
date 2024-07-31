/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.OrderDAO;
import java.sql.*;
import java.util.List;
import model.Order;

/**
 *
 * @author boyca
 */
public class OrderService {
    private OrderDAO orderDao;
    
    public OrderService(){
        this.orderDao = new OrderDAO();
    }
    
    public List<Order> listAllOrders() throws SQLException{
        return orderDao.getAllOrder();
    }
    
    public List<Order> getOrdersByDateRange(Date fromDate, Date toDate) {
        try {
            return orderDao.getOrdersByDateRange(fromDate, toDate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Order> getOrdersByDateRangeAndUser(int userId, Date fromDate, Date toDate) {
        try {
            return orderDao.getOrdersByDateRangeAndUser(userId, fromDate, toDate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Order getOrderById(int id) throws SQLException{
        return orderDao.getOrderById(id);
    }
    
    public void updateOrderStatus(String status, int id){
        orderDao.updateStatusOrder(status, id);
    }
}
