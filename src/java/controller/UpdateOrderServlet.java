/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import service.OrderService;
import service.UserService;

@WebServlet("/update_order")
public class UpdateOrderServlet extends HttpServlet {
    private OrderService orderService;
    private UserService userService;
    
    @Override
    public void init(){
        this.orderService = new OrderService();
        this.userService = new UserService();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
        
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        String status = String.valueOf(request.getParameter("status"));
        orderService.updateOrderStatus(status, orderId);
        response.sendRedirect("list_orders");
    }

}
