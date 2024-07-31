/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.AccInfo;
import model.Order;
import model.User;
import service.AccInfoService;
import service.OrderService;
import service.UserService;

/**
 *
 * @author boyca
 */
@WebServlet("/list_orders")
public class OrderServlet extends HttpServlet {
    private OrderService orderService;
    private AccInfoService accInfoService;
    private UserService userService;
    
    @Override
    public void init(){
        this.orderService = new OrderService();
        this.accInfoService = new AccInfoService();
        this.userService = new UserService();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        Object userIdObj = request.getSession().getAttribute("userId");
        
        if (userIdObj == null) {
            response.sendRedirect("login.jsp");
            return;
        } else {
            int userId = (int) userIdObj;
            User u = userService.getUserById(userId);
            if(!u.getRole().equals("admin")){
                response.sendRedirect("memberHome.jsp");
                return;
            }
        }
        if ("list".equals(action)) {
            List<Order> orders = new ArrayList<>();
            List<AccInfo> accs = new ArrayList<>();
            try {
                orders = orderService.listAllOrders();
                accs = accInfoService.createListAccInfo();
                request.setAttribute("accs", accs);
                request.setAttribute("orders", orders);
                request.getRequestDispatcher("list_orders.jsp").forward(request, response);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else if("filterOrders".equals(action)) {
            Date fromDate = Date.valueOf(request.getParameter("fromDate"));
            Date toDate = Date.valueOf(request.getParameter("toDate"));
            int userId = Integer.parseInt(request.getParameter("userId"));
            List<Order> filteredOrders = null;
            if(userId == 0){
                filteredOrders = orderService.getOrdersByDateRange(fromDate, toDate);
            } else {
                filteredOrders = orderService.getOrdersByDateRangeAndUser(userId, fromDate, toDate);
            }
            
            List<AccInfo> accs = new ArrayList<>();
            try {
                accs = accInfoService.createListAccInfo();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            request.setAttribute("accs", accs);
            request.setAttribute("orders", filteredOrders);
            request.setAttribute("select_id", userId);
            request.setAttribute("select_f_date", fromDate);
            request.setAttribute("select_t_date", toDate);
            request.getRequestDispatcher("list_orders.jsp").forward(request, response);
        } else if("update".equals(action)){
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            Order o = new Order();
            try {
                o = orderService.getOrderById(orderId);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            request.setAttribute("orderId", o.getId());
            request.setAttribute("orderStatus", o.getStatus());
            request.getRequestDispatcher("update_order.jsp").forward(request, response);
        } else {
            List<Order> orders = new ArrayList<>();
            List<AccInfo> accs = new ArrayList<>();
            try {
                orders = orderService.listAllOrders();
                accs = accInfoService.createListAccInfo();
                request.setAttribute("accs", accs);
                request.setAttribute("orders", orders);
                request.getRequestDispatcher("list_orders.jsp").forward(request, response);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
