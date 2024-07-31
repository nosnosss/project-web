package controller;

import dao.OrdersDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Orders;

import java.io.IOException;

@WebServlet("/purchase")
public class PurchaseController extends HttpServlet {

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

        int userId = (int) request.getSession().getAttribute("userId");

        Orders order = new Orders();
        order.setUserId(userId);
        order.setProductId(productId);
        order.setProductName(productName);
        order.setProductPrice(productPrice);

        ordersDAO.saveOrder(order);

        response.sendRedirect("orderConfirmation.jsp");
    }
}
