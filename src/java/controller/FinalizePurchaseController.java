package controller;

import dao.OrdersDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Orders;

import java.io.IOException;
import java.sql.Date;

@WebServlet("/finalizePurchase")
public class FinalizePurchaseController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private OrdersDAO ordersDAO;

    @Override
    public void init() {
        ordersDAO = new OrdersDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        String productName = request.getParameter("productName");
        double productPrice = Double.parseDouble(request.getParameter("productPrice"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String paymentMethod = request.getParameter("paymentMethod");

        int userId = (int) request.getSession().getAttribute("userId");

        Orders order = new Orders();
        order.setUserId(userId);
        order.setPrice(productPrice);
        order.setQuantity(quantity);
        order.setTotalAmount(productPrice * quantity);
        order.setStatus("pending");
        order.setPaymentStatus("pending");
        order.setPaymentMethod(paymentMethod);
        order.setTime(new Date(System.currentTimeMillis()));

        ordersDAO.saveOrder(order);

        response.sendRedirect("confirmation.jsp");
    }
}
